package org.otus.micarch.hw5.service

import org.otus.micarch.hw5.config.SecurityConfig.Companion.USER_ROLE
import org.otus.micarch.hw5.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        userRepository.findByUsername(username)?.let {
            return User.withUsername(it.username)
                .password(it.password)
                .roles(USER_ROLE)
                .build()
        }
        throw UsernameNotFoundException("User with login:$username not found")
    }

}