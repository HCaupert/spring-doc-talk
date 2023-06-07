package fr.hcaupert.springdoctalk.initial.presentation.documentation.annotation

import fr.hcaupert.springdoctalk.initial.service.exception.ErrorCode

annotation class ErrorApiResponse(
    val badRequestErrorCodes: Array<ErrorCode> = [],
    val notFoundErrorCodes: Array<ErrorCode> = [],
    val conflictErrorCodes: Array<ErrorCode> = [],
)
