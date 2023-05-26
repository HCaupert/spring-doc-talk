package fr.hcaupert.springdoctalk.initial.service.exception

sealed class ApiException(open val errorCode: ErrorCode) : RuntimeException()

data class BadRequestException(override val errorCode: ErrorCode) : ApiException(errorCode)

data class ConflictException(override val errorCode: ErrorCode) : ApiException(errorCode)

data class NotFoundException(override val errorCode: ErrorCode) : ApiException(errorCode)
