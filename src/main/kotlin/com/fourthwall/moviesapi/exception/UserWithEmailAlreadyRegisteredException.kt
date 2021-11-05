package com.fourthwall.moviesapi.exception

import org.springframework.http.HttpStatus

class UserWithEmailAlreadyRegisteredException : InternalServerErrorException(
    "User with same email already registered",
    ServerErrorEnum.USER_ALREADY_WITH_EMAIL,
    HttpStatus.BAD_REQUEST
)