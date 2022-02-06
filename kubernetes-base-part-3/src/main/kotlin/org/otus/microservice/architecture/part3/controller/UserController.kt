package org.otus.microservice.architecture.part3.controller

import org.otus.microservice.architecture.part3.model.UserEntity
import org.otus.microservice.architecture.part3.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {

    @PostMapping
    fun createOrUpdateUser(@RequestBody userEntity: UserEntity) = userService.createOrUpdateUser(userEntity)

    @GetMapping("/{id}")
    fun getUserByUserId(@PathVariable("id") userId: Long) = userService.getUserById(userId)

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable("id") userId: Long) = userService.deleteUserById(userId)

    @GetMapping
    fun listAllUsers() = userService.listAllUsers()
}