package fr.hcaupert.springdoctalk.initial.presentation.controller.internal

import fr.hcaupert.springdoctalk.initial.presentation.common.IdResponseDto
import fr.hcaupert.springdoctalk.initial.presentation.controller.internal.dto.UserCreateInternalRequestDto
import fr.hcaupert.springdoctalk.initial.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

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

    @PatchMapping("/{id}")
    fun patch(@PathVariable id: UUID) {

    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun rgpdAnonymize() {
    }
}
