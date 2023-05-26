package fr.hcaupert.springdoctalk.initial.presentation.controller.internal.dto

import fr.hcaupert.springdoctalk.initial.presentation.common.CountryCode
import java.time.LocalDate

sealed interface UserCreateInternalRequestDto {
    val email: String
    val note: String?
}

data class PersonCreateInternalRequestDto(
    val firstName: String,
    val lastName: String,
    val birthDate: LocalDate,
    override val email: String,
    override val note: String? = null,
) : UserCreateInternalRequestDto


data class CompanyCreateInternalRequestDto(
    val name: String,
    val incorporationCountry: CountryCode,
    val incorporationCode: String,
    override val email: String,
    override val note: String? = null,
) : UserCreateInternalRequestDto
