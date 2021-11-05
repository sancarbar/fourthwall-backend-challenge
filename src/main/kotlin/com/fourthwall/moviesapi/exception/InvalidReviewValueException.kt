package com.fourthwall.moviesapi.exception

import org.springframework.http.HttpStatus

class InvalidReviewValueException : InternalServerErrorException(
    "Invalid review value, it must be 1 - 5",
    ServerErrorEnum.INVALID_REVIEW_VALUE,
    HttpStatus.BAD_REQUEST
)