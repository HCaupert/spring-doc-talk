package fr.hcaupert.springdoctalk.final.presentation.documentation.customizer

import fr.hcaupert.springdoctalk.final.presentation.documentation.ErrorApiResponse
import fr.hcaupert.springdoctalk.final.presentation.error.BadRequestErrorDto
import fr.hcaupert.springdoctalk.final.service.exception.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class BadRequestCustomizer : ErrorCodeCustomizer<ErrorApiResponse, BadRequestErrorDto>(
    ErrorApiResponse::class,
    ErrorApiResponse::badRequestErrorCodes,
    BadRequestErrorDto::class,
    HttpStatus.BAD_REQUEST,
) {
    override fun buildResponseDto(errorCode: ErrorCode) = BadRequestErrorDto(
        errorCode = errorCode,
        reason = errorCode.message,
        rejectedValue = "\$rejectedValue",
        rejectedField = "\$path.to.field",
        status = HttpStatus.BAD_REQUEST.value(),
    )
}

