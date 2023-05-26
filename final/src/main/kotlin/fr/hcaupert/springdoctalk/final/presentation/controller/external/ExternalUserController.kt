package fr.hcaupert.springdoctalk.final.presentation.controller.external

import fr.hcaupert.springdoctalk.final.presentation.common.IdResponseDto
import fr.hcaupert.springdoctalk.final.presentation.common.PageRequestDto
import fr.hcaupert.springdoctalk.final.presentation.common.PageResponseDto
import fr.hcaupert.springdoctalk.final.presentation.controller.external.dto.UserCreateExternalRequestDto
import fr.hcaupert.springdoctalk.final.presentation.controller.external.dto.UserResponseDto
import fr.hcaupert.springdoctalk.final.presentation.controller.external.dto.UserSortAttribute
import fr.hcaupert.springdoctalk.final.presentation.documentation.ErrorApiResponse
import fr.hcaupert.springdoctalk.final.service.UserService
import fr.hcaupert.springdoctalk.final.service.exception.ErrorCode
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springdoc.core.annotations.ParameterObject
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/external/users")
@Tag(name = "Users", description = "A user represents a lorem ipsum dolor... It can be either a natural or a legal person")
class ExternalUserController(
    private val userService: UserService,
) {

    @Operation(
        summary = "Create a user",
        description = "Use this endpoint to create a new user.",
    )
    @PostMapping
    @ErrorApiResponse(
        badRequestErrorCodes = [ErrorCode.USER_MUST_BE_16_OR_OLDER],
        conflictErrorCodes = [ErrorCode.EMAIL_ALREADY_TAKEN, ErrorCode.COMPANY_NOT_UNIQUE]
    )
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody requestDto: UserCreateExternalRequestDto) = userService.create(requestDto).let(::IdResponseDto)

    @Operation(summary = "Find a user", description = "Find a user using it unique identifier")
    @GetMapping("/{id}")
    @ErrorApiResponse(
        notFoundErrorCodes = [ErrorCode.USER_NOT_FOUND],
    )
    fun byId(@PathVariable id: UUID) = userService.byId(id)

    @Operation(summary = "Search users", description = "List all users")
    @GetMapping
    fun all(@ParameterObject pageRequestDto: PageRequestDto<UserSortAttribute>): PageResponseDto<UserResponseDto> = userService.all()
}
