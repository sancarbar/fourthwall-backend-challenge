package com.fourthwall.moviesapi.config

import com.fourthwall.moviesapi.exception.InternalServerErrorException
import com.fourthwall.moviesapi.exception.ServerErrorEnum
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.multipart.support.MissingServletRequestPartException

@RestControllerAdvice
class RestControllerExceptionHandler {


    @ExceptionHandler(HttpMessageNotReadableException::class)
    private fun handleHTTPMessageNotReadable(e: HttpMessageNotReadableException): ResponseEntity<String> {
        return ResponseEntity(e.cause?.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MissingServletRequestPartException::class)
    private fun handleMissingServletRequestPart(e: MissingServletRequestPartException): ResponseEntity<String> {
        return ResponseEntity(e.cause?.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(InternalServerErrorException::class)
    private fun handleRuntimeException(e: InternalServerErrorException): ResponseEntity<ServerErrorResponseDto> {
        return ResponseEntity(e.serverErrorResponseDto, e.serverErrorResponseDto.httpStatus)
    }

    data class ServerErrorResponseDto(val message: String, val serverError: ServerErrorEnum, val httpStatus: HttpStatus)

}