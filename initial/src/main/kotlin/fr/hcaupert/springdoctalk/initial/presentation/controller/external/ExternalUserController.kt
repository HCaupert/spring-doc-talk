package fr.hcaupert.springdoctalk.initial.presentation.controller.external

import fr.hcaupert.springdoctalk.initial.presentation.common.IdResponseDto
import fr.hcaupert.springdoctalk.initial.presentation.common.PageResponseDto
import fr.hcaupert.springdoctalk.initial.presentation.controller.external.dto.UserCreateExternalRequestDto
import fr.hcaupert.springdoctalk.initial.presentation.controller.external.dto.UserPageExternalRequestDto
import fr.hcaupert.springdoctalk.initial.presentation.controller.external.dto.UserResponseDto
import fr.hcaupert.springdoctalk.initial.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/external/users")
class ExternalUserController(
    private val userService: UserService,
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody requestDto: UserCreateExternalRequestDto): IdResponseDto {
        val id = userService.create(requestDto)
        return IdResponseDto(id)
    }

    @GetMapping("/{id}")
    fun byId(@PathVariable id: UUID): UserResponseDto {
        return userService.byId(id)
    }

    @GetMapping
    fun all(pageRequestDto: UserPageExternalRequestDto): PageResponseDto<UserResponseDto> {
        return userService.all()
    }
}
