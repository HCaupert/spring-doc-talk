package fr.hcaupert.springdoctalk.initial.presentation.documentation.customizer

import fr.hcaupert.springdoctalk.initial.presentation.documentation.annotation.ErrorApiResponse
import fr.hcaupert.springdoctalk.initial.presentation.error.ConflictErrorDto
import fr.hcaupert.springdoctalk.initial.service.exception.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class ConflictCustomizer : ErrorCodeCustomizer<ErrorApiResponse, ConflictErrorDto>(
    ErrorApiResponse::class,
    ErrorApiResponse::conflictErrorCodes,
    ConflictErrorDto::class,
    HttpStatus.CONFLICT,
) {
    override fun buildResponseDto(errorCode: ErrorCode) = ConflictErrorDto(
        errorCode = errorCode,
        reason = errorCode.message,
        status = HttpStatus.CONFLICT.value(),
    )
}
