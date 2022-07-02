package org.otus.micarch.hw5.controller

import org.otus.micarch.hw5.entity.UserEntity
import org.otus.micarch.hw5.model.LoginCredentials
import org.otus.micarch.hw5.model.RegistrationCredentials
import org.otus.micarch.hw5.repository.UserRepository
import org.otus.micarch.hw5.service.util.JWTUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authManager: AuthenticationManager,
    private val jwtUtil: JWTUtil,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @PostMapping("/login")
    fun login(@RequestBody body: LoginCredentials): ResponseEntity<Any> {
        try {
            val authInputToken = UsernamePasswordAuthenticationToken(body.login, body.password)
            authManager.authenticate(authInputToken)
            val token = jwtUtil.generateToken(body.login)
            return ResponseEntity.ok().header(
                "x-auth-header", token
            ).build()
        } catch (e: AuthenticationException) {
            throw RuntimeException("Bad credentials")
        }
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.OK)
    fun registration(@RequestBody body: RegistrationCredentials) {
        userRepository.findByUsername(body.login)?.let {
            throw RuntimeException("User with login:${body.login} already exists")
        }
        userRepository.save(
            UserEntity(username = body.login, email = body.email, password = passwordEncoder.encode(body.password))
        )
    }

}