package fr.hcaupert.springdoctalk.final.presentation.documentation

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.OAuthFlow
import io.swagger.v3.oas.annotations.security.OAuthFlows
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

private const val SECURITY_SCHEME = "oauth"

@OpenAPIDefinition(
    //info is required
    info = Info(title = "SpringDoc Talk", version = "1.0"),
    security = [SecurityRequirement(name = SECURITY_SCHEME)],
)
@SecurityScheme(
    name = SECURITY_SCHEME,
    type = SecuritySchemeType.OAUTH2,
    flows = OAuthFlows(
        implicit = OAuthFlow(
            authorizationUrl = "https://dev-qbztblpx2ly1bvr8.eu.auth0.com/oauth/authorize",
        ),
    ),
)
@Configuration
class OpenApiGroupsConfiguration {
    @Bean
    fun internalDocumentation(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("internal")
            .packagesToScan("fr.hcaupert.springdoctalk.final.presentation.controller.internal")
            .build()
    }

    @Bean
    fun externalDocumentation(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("external")
            .packagesToScan("fr.hcaupert.springdoctalk.final.presentation.controller.external")
            .build()
    }
}
