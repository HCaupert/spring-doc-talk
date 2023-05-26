package fr.hcaupert.springdoctalk.final.presentation.documentation

import fr.hcaupert.springdoctalk.final.service.exception.ErrorCode

annotation class ErrorApiResponse(
    val badRequestErrorCodes: Array<ErrorCode> = [],
    val notFoundErrorCodes: Array<ErrorCode> = [],
    val conflictErrorCodes: Array<ErrorCode> = [],
)
