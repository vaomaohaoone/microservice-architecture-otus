package org.otus.microservice.architecture.controller

import org.otus.microservice.architecture.dto.ErrorResponseDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleNotFoundException(request: HttpServletRequest, exception: Exception): ErrorResponseDto {
        return ErrorResponseDto(exception.message!!)
    }
}