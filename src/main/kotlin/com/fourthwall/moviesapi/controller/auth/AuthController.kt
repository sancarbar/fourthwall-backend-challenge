package com.fourthwall.moviesapi.controller.auth

import com.fourthwall.moviesapi.controller.auth.dto.LoginDto
import com.fourthwall.moviesapi.controller.auth.dto.TokenDto
import com.fourthwall.moviesapi.data.user.User
import com.fourthwall.moviesapi.exception.InvalidLoginCredentialsException
import com.fourthwall.moviesapi.service.user.UserService
import com.fourthwall.moviesapi.utils.CLAIMS_ROLES_KEY
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("v1/auth")
class AuthController(
    private val userService: UserService,
    @Value("\${app.secret}")
    private val secret: String? = null
) {


    @PostMapping
    fun login(@RequestBody loginDto: LoginDto): TokenDto {

        val email = loginDto.email

        if (email == null || loginDto.password == null) {
            throw InvalidLoginCredentialsException()
        }

        val user = userService.findByEmail(email)
        try {
            if (user != null && BCrypt.checkpw(loginDto.password, user.passwordHash)) {
                return generateSessionToken(user)
            }
        } catch (e: IllegalArgumentException) {
        }
        throw  InvalidLoginCredentialsException()
    }

    private fun generateSessionToken(user: User): TokenDto {
        val expiration = Calendar.getInstance()
        expiration.add(Calendar.MINUTE, 300)
        val token = generateToken(user, expiration.time)
        return TokenDto(token)
    }

    private fun generateToken(user: User, expirationDate: Date): String {
        return Jwts.builder().setSubject(user.email).claim(CLAIMS_ROLES_KEY, user.roles)
            .setIssuedAt(Date())
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }
}