package com.fourthwall.moviesapi.service.movies

import com.fourthwall.moviesapi.data.movie.MovieShow
import com.fourthwall.moviesapi.data.movie.MovieShowTimes

interface MoviesService {


    fun updateMovieShowTimes(name: String?, imdbId: String, movieShows: List<MovieShow>): MovieShowTimes

    fun getMovieShowTimes(imdbId: String): MovieShowTimes

    fun review(imdbId: String, review: Int): MovieShowTimes

}