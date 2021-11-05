package com.fourthwall.moviesapi

import data.user.UserRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = [UserRepository::class])
class MoviesApiApplication

fun main(args: Array<String>) {
	runApplication<MoviesApiApplication>(*args)
}
