package org.otus.micarch.hw5.service.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*


@Component
class JWTUtil {

    @Value("\${jwt_secret}")
    lateinit var secret: String

    fun generateToken(login: String): String {
        return JWT.create()
            .withSubject("User Details")
            .withClaim("login", login)
            .withIssuedAt(Date())
            .sign(Algorithm.HMAC256(secret))
    }

    fun validateTokenAndRetrieveSubject(token: String): String {
        val verifier = JWT.require(Algorithm.HMAC256(secret))
            .withSubject("User Details")
            .build()
        val jwt = verifier.verify(token)
        return jwt.getClaim("login").asString()
    }

}