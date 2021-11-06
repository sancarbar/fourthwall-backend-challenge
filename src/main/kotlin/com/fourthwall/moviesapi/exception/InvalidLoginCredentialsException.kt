package com.fourthwall.moviesapi.exception

import org.springframework.http.HttpStatus

class InvalidLoginCredentialsException : InternalServerErrorException(
    "Invalid login credentials",
    ServerErrorEnum.INVALID_LOGIN_CREDENTIALS,
    HttpStatus.BAD_REQUEST
) {
}