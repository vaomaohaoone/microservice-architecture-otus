package org.otus.micarch.hw5.controller

import org.otus.micarch.hw5.model.UserData
import org.otus.micarch.hw5.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/user")
class UserController(
    private val userRepository: UserRepository
) {

    @GetMapping
    fun getUserProfile(): UserData {
        val login = SecurityContextHolder.getContext().authentication.principal as String
        return userRepository.findByUsername(login)!!.run {
            UserData(firstName, lastName, email, phone)
        }
    }

    @PostMapping
    @Transactional
    fun updateUserProfile(@RequestBody body: UserData) {
        val login = SecurityContextHolder.getContext().authentication.principal as String
        val user = userRepository.findByUsername(login)!!.apply {
            firstName = body.firstName
            lastName = body.lastName
            email = body.email
            phone = body.phone
        }
        userRepository.save(user)
    }

}