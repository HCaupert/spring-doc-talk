package fr.hcaupert.springdoctalk.initial.presentation.documentation.customizer

import fr.hcaupert.springdoctalk.initial.presentation.documentation.annotation.ErrorApiResponse
import fr.hcaupert.springdoctalk.initial.presentation.error.NotFoundErrorDto
import fr.hcaupert.springdoctalk.initial.service.exception.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class NotFoundCustomizer : ErrorCodeCustomizer<ErrorApiResponse, NotFoundErrorDto>(
    ErrorApiResponse::class,
    ErrorApiResponse::notFoundErrorCodes,
    NotFoundErrorDto::class,
    HttpStatus.NOT_FOUND,
){
    override fun buildResponseDto(errorCode: ErrorCode) = NotFoundErrorDto(
        errorCode = errorCode,
        reason = errorCode.message,
        status = HttpStatus.NOT_FOUND.value(),
    )
}
