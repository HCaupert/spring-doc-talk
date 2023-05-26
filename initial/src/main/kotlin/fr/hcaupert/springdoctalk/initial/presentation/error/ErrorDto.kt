package fr.hcaupert.springdoctalk.initial.presentation.error

import fr.hcaupert.springdoctalk.initial.service.exception.ErrorCode

interface ErrorDto {
    val errorCode: ErrorCode?
    val reason: String
    val status: Int
}
