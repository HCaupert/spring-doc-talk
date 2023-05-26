package fr.hcaupert.springdoctalk.initial.presentation.controller.internal

import fr.hcaupert.springdoctalk.initial.presentation.common.IdResponseDto
import fr.hcaupert.springdoctalk.initial.presentation.controller.internal.dto.UserCreateInternalRequestDto
import fr.hcaupert.springdoctalk.initial.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/internal/users")
class InternalUserController(
    private val userService: UserService,
) {
    @PostMapping
    fun create(@RequestBody requestDto: UserCreateInternalRequestDto): IdResponseDto {
        val id = userService.create(requestDto)
        return IdResponseDto(id)
    }
}
