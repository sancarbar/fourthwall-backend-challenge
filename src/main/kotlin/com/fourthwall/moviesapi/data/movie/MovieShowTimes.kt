package com.fourthwall.moviesapi.data.movie

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class MovieShowTimes(
    @Id
    var id: String?,
    var imdbId: String,
    var name: String,
    var showTimes: List<MovieShow>
)
