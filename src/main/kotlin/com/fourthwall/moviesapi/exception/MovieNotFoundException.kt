package com.fourthwall.moviesapi.exception

import org.springframework.http.HttpStatus

class MovieNotFoundException : InternalServerErrorException(
    "Movie with provided Id not found",
    ServerErrorEnum.MOVIE_NOT_FOUND,
    HttpStatus.BAD_REQUEST
)