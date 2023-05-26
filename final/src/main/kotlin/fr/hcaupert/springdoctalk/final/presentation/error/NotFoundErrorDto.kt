package fr.hcaupert.springdoctalk.final.presentation.error

import fr.hcaupert.springdoctalk.final.service.exception.ErrorCode
import io.swagger.v3.oas.annotations.media.Schema

data class NotFoundErrorDto(
    override val errorCode: ErrorCode,
    override val reason: String,
    @Schema(example = "404")
    override val status: Int,
): ErrorDto
