package fr.hcaupert.springdoctalk.initial.service.exception

enum class ErrorCode(val message: String) {
    USER_NOT_FOUND("User was not found"),
    COMPANY_NOT_UNIQUE("Company incorporationCode is already taken. It must be unique"),
    EMAIL_ALREADY_TAKEN("Email is already taken. It must be unique."),
    USER_MUST_BE_16_OR_OLDER("User must be 16 or older to register in the app."),
    FOO_ERROR_CODE("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
    ;
}
