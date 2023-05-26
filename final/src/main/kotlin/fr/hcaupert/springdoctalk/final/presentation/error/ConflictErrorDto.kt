package fr.hcaupert.springdoctalk.final.presentation.error

import fr.hcaupert.springdoctalk.final.service.exception.ErrorCode
import io.swagger.v3.oas.annotations.media.Schema

data class ConflictErrorDto(
    override val errorCode: ErrorCode,
    override val reason: String,
    @Schema(example = "409")
    override val status: Int,
) : ErrorDto

