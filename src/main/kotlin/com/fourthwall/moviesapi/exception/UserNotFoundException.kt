package com.fourthwall.moviesapi.exception

import org.springframework.http.HttpStatus

class UserNotFoundException : InternalServerErrorException(
    "User not found",
    ServerErrorEnum.USER_NOT_FOUND,
    HttpStatus.UNAUTHORIZED
)