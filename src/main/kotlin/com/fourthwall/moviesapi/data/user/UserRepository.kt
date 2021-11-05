package com.fourthwall.moviesapi.data.user

import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String> {

    fun findByEmail(email: String): User?

}