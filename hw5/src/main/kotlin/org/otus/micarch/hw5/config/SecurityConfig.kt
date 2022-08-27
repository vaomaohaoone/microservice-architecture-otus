package org.otus.micarch.hw5.config

import org.otus.micarch.hw5.service.CustomUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.http.HttpServletResponse


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val userDetailsService: CustomUserDetailsService,
    private val filter: JWTFilter
) : WebSecurityConfigurerAdapter() {

    companion object {
        const val USER_ROLE = "USER"
    }

    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .httpBasic().disable()
            .cors().and().authorizeRequests()
            .antMatchers("/auth/**").permitAll()
            .antMatchers("/user/**").hasRole(USER_ROLE)
            .and()
            .userDetailsService(userDetailsService)
            .exceptionHandling()
            .authenticationEntryPoint { _, response, _ -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized") }
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

}