package com.fourthwall.moviesapi.controller.user

import com.fourthwall.moviesapi.controller.user.dto.UserRegistrationDto
import com.fourthwall.moviesapi.data.user.User
import com.fourthwall.moviesapi.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/user")
class UserController(@Autowired val userService: UserService) {


    @PostMapping
    fun register(@RequestBody userDto: UserRegistrationDto): User? {
        return userService.save(User(userDto))
    }


}