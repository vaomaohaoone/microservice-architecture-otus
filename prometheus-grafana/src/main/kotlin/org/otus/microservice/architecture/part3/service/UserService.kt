package org.otus.microservice.architecture.part3.service

import org.otus.microservice.architecture.part3.exception.EntityNotFoundException
import org.otus.microservice.architecture.part3.model.UserEntity
import org.otus.microservice.architecture.part3.model.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpServerErrorException
import kotlin.random.Random

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun createOrUpdateUser(userEntity: UserEntity) = userRepository.save(userEntity)

    fun getUserById(id: Long): UserEntity = userRepository.findById(id).orElseThrow {
        EntityNotFoundException("User with id:$id not found")
    }

    fun deleteUserById(id: Long) {
        getUserById(id)
        userRepository.deleteById(id)
    }

    fun listAllUsers(): List<UserEntity> =
        if (Random.nextInt(0, 5000) > 500)
            userRepository.findAll()
        else
            throw HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR)
}