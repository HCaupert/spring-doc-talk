package fr.hcaupert.springdoctalk.final.presentation.controller.external.dto

import fr.hcaupert.springdoctalk.final.presentation.common.CountryCode
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.time.LocalDateTime

@Schema(
    anyOf = [
        PersonResponseDto::class,
        CompanyResponseDto::class,
    ]
)
sealed interface UserResponseDto {
    @get:Schema(format = "email")
    val email: String
    val creationDate: LocalDateTime
    val updateDate: LocalDateTime
}

@Schema(title = "Person")
data class PersonResponseDto(
    val firstName: String,
    val lastName: String,
    val birthDate: LocalDate,
    override val email: String,
    override val creationDate: LocalDateTime,
    override val updateDate: LocalDateTime,
) : UserResponseDto

@Schema(title = "Company")
data class CompanyResponseDto(
    val name: String,
    val incorporationCountry: CountryCode,
    val incorporationCode: String,
    val employeeCount: Int,
    override val email: String,
    override val creationDate: LocalDateTime,
    override val updateDate: LocalDateTime,
) : UserResponseDto

enum class UserSortAttribute {
    EMAIL,
    CREATION_DATE,
    UPDATE_DATE,
    ;
}
