package fr.hcaupert.springdoctalk.initial.service

import fr.hcaupert.springdoctalk.initial.presentation.common.PageResponseDto
import fr.hcaupert.springdoctalk.initial.presentation.controller.external.dto.UserCreateExternalRequestDto
import fr.hcaupert.springdoctalk.initial.presentation.controller.external.dto.UserResponseDto
import fr.hcaupert.springdoctalk.initial.presentation.controller.internal.dto.UserCreateInternalRequestDto
import fr.hcaupert.springdoctalk.initial.service.exception.BadRequestException
import fr.hcaupert.springdoctalk.initial.service.exception.ConflictException
import fr.hcaupert.springdoctalk.initial.service.exception.NotFoundException
import java.util.*

interface UserService {
    /**
     * Creates a user.
     * @throws BadRequestException if the user is not aged 16 or more.
     * @throws ConflictException if email is already taken.
     * @throws ConflictException if incorporationCode is already taken.
     * @return the id of the created user.
     */
    fun create(requestDto: UserCreateExternalRequestDto): UUID
    fun create(requestDto: UserCreateInternalRequestDto): UUID

    /**
     * Finds a user by id.
     * @throws NotFoundException if the user is not found.
     */
    fun byId(id: UUID): UserResponseDto
    fun all(): PageResponseDto<UserResponseDto>
}
