package com.fourthwall.moviesapi.exception

import com.fourthwall.moviesapi.config.RestControllerExceptionHandler
import org.springframework.http.HttpStatus

abstract class InternalServerErrorException(val serverErrorResponseDto: RestControllerExceptionHandler.ServerErrorResponseDto) :
    RuntimeException() {

    constructor(message: String, serverError: ServerErrorEnum, httpStatus: HttpStatus) :
            this(RestControllerExceptionHandler.ServerErrorResponseDto(message, serverError, httpStatus))
}