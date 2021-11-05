package com.fourthwall.moviesapi.controller.movies.dto

import com.fourthwall.moviesapi.data.movie.MovieShow

data class MovieTimesDto(val name: String?, val movieShows: List<MovieShow>)