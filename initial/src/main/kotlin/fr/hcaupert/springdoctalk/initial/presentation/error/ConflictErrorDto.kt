package fr.hcaupert.springdoctalk.initial.presentation.error

import fr.hcaupert.springdoctalk.initial.service.exception.ErrorCode

data class ConflictErrorDto(
    override val errorCode: ErrorCode,
    override val reason: String,
    override val status: Int,
) : ErrorDto

