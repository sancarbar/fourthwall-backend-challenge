package com.fourthwall.moviesapi.service

import data.user.User
import data.user.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceMongoDb(private val userRepository: UserRepository) : UserService {

    override fun save(user: User): User {
        return userRepository.save(user)
    }

    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }
}