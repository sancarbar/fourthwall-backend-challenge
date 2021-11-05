package com.fourthwall.moviesapi.service

import data.user.User

interface UserService {

    fun save(user: User): User

    fun findByEmail(email: String): User?
}