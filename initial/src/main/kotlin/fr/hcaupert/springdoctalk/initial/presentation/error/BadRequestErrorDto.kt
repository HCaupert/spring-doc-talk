package fr.hcaupert.springdoctalk.initial.presentation.error

import fr.hcaupert.springdoctalk.initial.service.exception.ErrorCode

data class BadRequestErrorDto(
    override val errorCode: ErrorCode?,
    override val reason: String,
    val rejectedValue: String,
    val rejectedField: String,
    override val status: Int,
): ErrorDto


