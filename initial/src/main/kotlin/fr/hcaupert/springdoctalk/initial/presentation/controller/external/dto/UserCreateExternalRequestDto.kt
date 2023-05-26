package fr.hcaupert.springdoctalk.initial.presentation.controller.external.dto

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import fr.hcaupert.springdoctalk.initial.presentation.common.CountryCode
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import java.time.LocalDate

@JsonTypeInfo(
    use = JsonTypeInfo.Id.DEDUCTION,
)
@JsonSubTypes(
    JsonSubTypes.Type(PersonCreateExternalRequestDto::class),
    JsonSubTypes.Type(CompanyCreateExternalRequestDto::class),
)
sealed interface UserCreateExternalRequestDto {
    @get:Email
    val email: String
}

data class PersonCreateExternalRequestDto(
    @NotBlank
    val firstName: String,
    @NotBlank
    val lastName: String,
    val birthDate: LocalDate,
    override val email: String,
) : UserCreateExternalRequestDto

data class CompanyCreateExternalRequestDto(
    @NotBlank
    val name: String,
    val incorporationCountry: CountryCode,
    @Pattern(regexp = "[A-Z]{2}[0-9]{9}")
    val incorporationCode: String,
    @Min(1)
    val employeeCount: Int,
    override val email: String,
) : UserCreateExternalRequestDto
