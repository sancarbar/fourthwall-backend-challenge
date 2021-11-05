package com.fourthwall.moviesapi.data.movie

import org.springframework.data.mongodb.repository.MongoRepository

interface MovieShowTimesRepository : MongoRepository<MovieShowTimes, String> {

    fun findByImdbId(imdbId: String): MovieShowTimes?

}