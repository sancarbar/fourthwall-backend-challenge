package com.fourthwall.moviesapi.service

import com.fourthwall.moviesapi.exception.UserWithEmailAlreadyRegisteredException
import data.user.User
import data.user.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceMongoDb(private val userRepository: UserRepository) : UserService {

    override fun save(user: User): User {
        val foundUser = userRepository.findByEmail(user.email)
        if (foundUser == null)
            return userRepository.save(user)
        else
            throw UserWithEmailAlreadyRegisteredException()
    }

    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }
}