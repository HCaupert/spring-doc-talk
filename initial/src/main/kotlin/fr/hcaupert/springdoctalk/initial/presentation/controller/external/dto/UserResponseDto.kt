package fr.hcaupert.springdoctalk.initial.presentation.controller.external.dto

import fr.hcaupert.springdoctalk.initial.presentation.common.CountryCode
import java.time.LocalDate
import java.time.LocalDateTime


sealed interface UserResponseDto {
    val email: String
    val creationDate: LocalDateTime
    val updateDate: LocalDateTime
}

data class PersonResponseDto(
    val firstName: String,
    val lastName: String,
    val birthDate: LocalDate,
    override val email: String,
    override val creationDate: LocalDateTime,
    override val updateDate: LocalDateTime,
) : UserResponseDto

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
