package fr.hcaupert.springdoctalk.final.presentation.error

import fr.hcaupert.springdoctalk.final.service.exception.ErrorCode
import io.swagger.v3.oas.annotations.media.Schema

data class BadRequestErrorDto(
    override val errorCode: ErrorCode?,
    override val reason: String,
    val rejectedValue: String,
    val rejectedField: String,
    @Schema(example = "400")
    override val status: Int,
): ErrorDto


