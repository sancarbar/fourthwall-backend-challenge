package com.fourthwall.moviesapi.service.user

import com.fourthwall.moviesapi.data.user.User

interface UserService {

    fun save(user: User): User

    fun findByEmail(email: String): User?
}