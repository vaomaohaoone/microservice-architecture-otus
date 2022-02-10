package org.otus.microservice.architecture.part3.exception

import org.otus.microservice.architecture.part3.model.ErrorResponseDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(EntityNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(request: HttpServletRequest, exception: EntityNotFoundException): ErrorResponseDto {
        return ErrorResponseDto(exception.message!!)
    }
}