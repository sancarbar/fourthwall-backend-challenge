package com.fourthwall.moviesapi

import com.fourthwall.moviesapi.controller.auth.AuthController
import com.fourthwall.moviesapi.controller.auth.dto.LoginDto
import com.fourthwall.moviesapi.data.user.RoleEnum
import com.fourthwall.moviesapi.data.user.User
import com.fourthwall.moviesapi.exception.InvalidLoginCredentialsException
import com.fourthwall.moviesapi.service.user.UserService
import com.fourthwall.moviesapi.utils.CLAIMS_ROLES_KEY
import io.jsonwebtoken.Jwts
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import java.util.*


@SpringJUnitConfig
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthControllerTest {


    @Mock
    lateinit var userService: UserService


    private lateinit var authController: AuthController

    private val secret = "test_secret"

    @BeforeAll
    fun setup() {
        authController = AuthController(userService, secret)
    }


    @Test
    fun emptyLoginCredentialsTest() {
        val email = "test@mail.com"
        val loginDto = LoginDto(email, null)
        Assertions.assertThrows(InvalidLoginCredentialsException::class.java) { authController.login(loginDto) }
    }

    @Test
    fun invalidLoginEmailTest() {
        val email = "test@mail.com"
        val loginDto = LoginDto(email, null)
        Mockito.`when`(userService.findByEmail(email)).thenReturn(null)
        Assertions.assertThrows(InvalidLoginCredentialsException::class.java) { authController.login(loginDto) }
    }

    @Test
    fun invalidLoginPasswordTest() {
        val email = "test@mail.com"
        val password = "passw0rd"
        val loginDto = LoginDto(email, password)
        val user = User(null, "name", email, listOf(RoleEnum.USER), "passwordHash", Date())
        Mockito.`when`(userService.findByEmail(email)).thenReturn(user)
        Assertions.assertThrows(InvalidLoginCredentialsException::class.java) { authController.login(loginDto) }
    }

    @Test
    fun userLoginTestTokenInformationTest() {
        val id = "user-id"
        val name = "Santiago"
        val email = "test@mail.com"
        val password = "pass"
        val roles = arrayListOf(RoleEnum.USER)
        val passwordHash = BCrypt.hashpw(password, BCrypt.gensalt())
        val user = User(id, name, email, roles, passwordHash, Date())
        Mockito.`when`(userService.findByEmail(email)).thenReturn(user)
        val loginDto = LoginDto(email, password)
        val token = authController.login(loginDto)
        val claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token.accessToken).body
        org.assertj.core.api.Assertions.assertThat(claims.subject).isEqualTo(email)
        org.assertj.core.api.Assertions.assertThat(claims[CLAIMS_ROLES_KEY]).isEqualTo(roles.map { it.name })
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MINUTE, 300)
        org.assertj.core.api.Assertions.assertThat(claims.expiration).isBefore(calendar.time)
    }

}