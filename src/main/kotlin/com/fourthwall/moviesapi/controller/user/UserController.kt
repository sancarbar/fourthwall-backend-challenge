package com.fourthwall.moviesapi.controller.user

import com.fourthwall.moviesapi.controller.user.dto.UserDto
import com.fourthwall.moviesapi.service.UserService
import data.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(@Autowired val userService: UserService) {


    @PostMapping()
    fun register(@RequestBody userDto: UserDto): User? {
        return userService.save(User(userDto))
    }


}