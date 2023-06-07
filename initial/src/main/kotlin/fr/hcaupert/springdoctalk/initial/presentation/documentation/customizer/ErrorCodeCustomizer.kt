package fr.hcaupert.springdoctalk.initial.presentation.documentation.customizer

import fr.hcaupert.springdoctalk.initial.presentation.error.ErrorDto
import fr.hcaupert.springdoctalk.initial.service.exception.ErrorCode
import io.swagger.v3.core.converter.AnnotatedType
import io.swagger.v3.core.converter.ModelConverters
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.examples.Example
import io.swagger.v3.oas.models.media.Content
import io.swagger.v3.oas.models.media.MediaType
import io.swagger.v3.oas.models.media.StringSchema
import io.swagger.v3.oas.models.responses.ApiResponse
import org.springdoc.core.customizers.OperationCustomizer
import org.springframework.http.HttpStatus
import org.springframework.web.method.HandlerMethod
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

abstract class ErrorCodeCustomizer<T : Annotation, R : Any>(
    private val annotationClass: KClass<T>,
    private val annotationErrorCode: KProperty1<T, Array<ErrorCode>>,
    private val responseDtoClass: KClass<R>,
    private val httpStatus: HttpStatus,
) : OperationCustomizer {
    abstract fun buildResponseDto(errorCode: ErrorCode): R

    override fun customize(operation: Operation, handlerMethod: HandlerMethod): Operation {
        val annotation = handlerMethod.getMethodAnnotation(annotationClass.java)
            ?: return operation
        val errorCodes = annotationErrorCode.get(annotation)

        if (errorCodes.isEmpty()) return operation

        // Create the Schema model from ErrorDto class
        val type = AnnotatedType(responseDtoClass.java)
        val schema = ModelConverters.getInstance().resolveAsResolvedSchema(type).schema

        // Add the enum possible values to the schema field 'errorCode'
        val errorCodeFieldName = ErrorDto::errorCode.name
        schema.properties.putIfAbsent(errorCodeFieldName, StringSchema())
        schema.properties[errorCodeFieldName]?.enum = errorCodes.map { it.name }.toList()

        // Add the response to the operation with the newly built schema
        val mediaType = MediaType().schema(schema)
        mediaType.examples = errorCodes.associate { it.toPrettyString() to Example().apply { value = buildResponseDto(it) } }

        operation.responses.addApiResponse(
            httpStatus.value().toString(),
            ApiResponse()
                .description(httpStatus.reasonPhrase)
                .content(Content().addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE, mediaType))
        )

        return operation
    }

    private fun Enum<*>.toPrettyString() =
        name.lowercase()
            .replace("_", "-")
            .replaceFirstChar(Char::uppercase)
}
