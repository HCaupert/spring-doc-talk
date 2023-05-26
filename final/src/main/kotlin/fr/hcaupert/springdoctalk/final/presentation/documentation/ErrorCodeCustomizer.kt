package fr.hcaupert.springdoctalk.final.presentation.documentation

import fr.hcaupert.springdoctalk.final.presentation.error.BadRequestErrorDto
import fr.hcaupert.springdoctalk.final.presentation.error.ConflictErrorDto
import fr.hcaupert.springdoctalk.final.presentation.error.ErrorDto
import fr.hcaupert.springdoctalk.final.presentation.error.NotFoundErrorDto
import fr.hcaupert.springdoctalk.final.service.exception.ErrorCode
import io.swagger.v3.core.converter.AnnotatedType
import io.swagger.v3.core.converter.ModelConverters
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.examples.Example
import io.swagger.v3.oas.models.media.Content
import io.swagger.v3.oas.models.media.MediaType
import io.swagger.v3.oas.models.media.StringSchema
import io.swagger.v3.oas.models.responses.ApiResponse
import org.springdoc.core.customizers.GlobalOperationCustomizer
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.method.HandlerMethod
import kotlin.reflect.KClass
import org.springframework.http.MediaType as SpringMediaType

@Service
class ErrorCodeCustomizer : GlobalOperationCustomizer {
    override fun customize(operation: Operation, handlerMethod: HandlerMethod): Operation {
        val errorApiResponse = handlerMethod.getMethodAnnotation(ErrorApiResponse::class.java)
            ?: return operation
        customize(operation, errorApiResponse.badRequestErrorCodes, BadRequestErrorDto::class, HttpStatus.BAD_REQUEST, ::badRequestBody)
        customize(operation, errorApiResponse.notFoundErrorCodes, NotFoundErrorDto::class, HttpStatus.NOT_FOUND, ::notFoundBody)
        customize(operation, errorApiResponse.conflictErrorCodes, ConflictErrorDto::class, HttpStatus.CONFLICT, ::conflictBody)
        return operation
    }

    private fun customize(
        operation: Operation,
        errorCodes: Array<ErrorCode>,
        responseBodyClass: KClass<out ErrorDto>,
        httpStatus: HttpStatus,
        bodyBuilder: (ErrorCode) -> ErrorDto,
    ) {
        if (errorCodes.isEmpty()) return
        val type = AnnotatedType(responseBodyClass.java)
        val schema = ModelConverters.getInstance().resolveAsResolvedSchema(type).schema

        val errorCodeFieldName = ErrorDto::errorCode.name

        schema.properties.putIfAbsent(errorCodeFieldName, StringSchema())
        schema.properties[errorCodeFieldName]?.enum = errorCodes.map { it.name }.toList()

        val mediaType = MediaType().schema(schema)

        mediaType.examples = errorCodes.associate { it.toPrettyString() to Example().apply { value = bodyBuilder(it) } }

        operation.responses.addApiResponse(
            httpStatus.value().toString(),
            ApiResponse()
                .description(httpStatus.reasonPhrase)
                .content(Content().addMediaType(SpringMediaType.APPLICATION_JSON_VALUE, mediaType))
        )
    }

    fun badRequestBody(errorCode: ErrorCode) = BadRequestErrorDto(
        errorCode = errorCode,
        reason = errorCode.message,
        rejectedValue = "\$rejectedValue",
        rejectedField = "\$path.to.field",
        status = HttpStatus.BAD_REQUEST.value(),
    )

    fun conflictBody(errorCode: ErrorCode) = ConflictErrorDto(
        errorCode = errorCode,
        reason = errorCode.message,
        status = HttpStatus.CONFLICT.value(),
    )

    fun notFoundBody(errorCode: ErrorCode) = NotFoundErrorDto(
        errorCode = errorCode,
        reason = errorCode.message,
        status = HttpStatus.NOT_FOUND.value(),
    )

    fun Enum<*>.toPrettyString() =
        name.lowercase()
            .replace("_", "-")
            .replaceFirstChar(Char::uppercase)
}
