package com.fourthwall.moviesapi.controller.auth

import com.fourthwall.moviesapi.controller.auth.dto.LoginDto
import com.fourthwall.moviesapi.controller.auth.dto.TokenDto
import com.fourthwall.moviesapi.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpServerErrorException
import java.util.*
import javax.servlet.ServletException

@RestController
@RequestMapping("v1/auth")
class AuthController(val userService: UserService) {

    @Value("\${app.secret}")
    private val secret: String? = null


    @PostMapping
    fun login(@RequestBody loginDto: LoginDto): TokenDto {

        val email = loginDto.email

        if (email == null || loginDto.password == null) {
            throw ServletException("Please fill in email and password")
        }

        val user = userService.findByEmail(email)

        if (user != null && BCrypt.checkpw(loginDto.password, user.passwordHash)) {
            return generateSessionToken(email)
        }
        throw  HttpServerErrorException(HttpStatus.UNAUTHORIZED)
    }

    private fun generateSessionToken(email: String): TokenDto {
        val expiration = Calendar.getInstance()
        expiration.add(Calendar.MINUTE, 300)
        val token = generateToken(email, expiration.time)
        return TokenDto(token)
    }

    private fun generateToken(email: String?, expirationDate: Date): String {
        return Jwts.builder().setSubject(email).claim("role", "User")
            .setIssuedAt(Date())
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }
}