package fr.hcaupert.springdoctalk.initial.presentation.error

import fr.hcaupert.springdoctalk.initial.service.exception.BadRequestException
import fr.hcaupert.springdoctalk.initial.service.exception.ConflictException
import fr.hcaupert.springdoctalk.initial.service.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class CoolControllerAdvice {

    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handle(exception: BadRequestException): BadRequestErrorDto {
        return buildBadRequest(exception)
    }

    @ExceptionHandler(ConflictException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handle(exception: ConflictException): ConflictErrorDto {
        return buildConflict(exception)
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handle(exception: NotFoundException): NotFoundErrorDto {
        return buildNotFound(exception)
    }


    fun buildBadRequest(exception: RuntimeException): BadRequestErrorDto {
        //NOT needed for demonstration
        TODO()
    }

    fun buildNotFound(exception: RuntimeException): NotFoundErrorDto {
        //NOT needed for demonstration
        TODO()
    }

    fun buildConflict(exception: RuntimeException): ConflictErrorDto {
        //NOT needed for demonstration
        TODO()
    }
}
