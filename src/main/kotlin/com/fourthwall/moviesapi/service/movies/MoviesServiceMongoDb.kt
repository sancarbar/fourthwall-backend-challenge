package com.fourthwall.moviesapi.service.movies

import com.fourthwall.moviesapi.data.movie.MovieShow
import com.fourthwall.moviesapi.data.movie.MovieShowTimes
import com.fourthwall.moviesapi.data.movie.MovieShowTimesRepository
import com.fourthwall.moviesapi.exception.InvalidReviewValueException
import com.fourthwall.moviesapi.exception.MovieNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MoviesServiceMongoDb(@Autowired val repository: MovieShowTimesRepository) : MoviesService {


    override fun updateMovieShowTimes(name: String?, imdbId: String, movieShows: List<MovieShow>): MovieShowTimes {

        val movieShowTimes = repository.findByImdbId(imdbId)
        return if (movieShowTimes != null) {
            movieShowTimes.showTimes = movieShows
            repository.save(movieShowTimes)
        } else {
            repository.save(MovieShowTimes(null, imdbId, name!!, movieShows, 1))
        }
    }

    override fun getMovieShowTimes(imdbId: String): MovieShowTimes {
        val foundMovie = repository.findByImdbId(imdbId)
        if (foundMovie == null)
            throw MovieNotFoundException()
        else
            return foundMovie
    }

    override fun review(imdbId: String, review: Int): MovieShowTimes {
        val movieShowTime = getMovieShowTimes(imdbId)
        if (review in 1..5) {
            movieShowTime.reviewsAvg = (movieShowTime.reviewsAvg + review) / 2
            return movieShowTime
        }
        throw InvalidReviewValueException()
    }
}