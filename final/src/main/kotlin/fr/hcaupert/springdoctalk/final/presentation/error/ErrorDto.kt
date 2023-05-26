package fr.hcaupert.springdoctalk.final.presentation.error

import fr.hcaupert.springdoctalk.final.service.exception.ErrorCode

interface ErrorDto {
    val errorCode: ErrorCode?
    val reason: String
    val status: Int
}
