package fr.hcaupert.springdoctalk.initial.presentation.documentation

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiGroupsConfig {

    @Bean
    fun internal(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("internal")
            .packagesToScan("fr.hcaupert.springdoctalk.initial.presentation.controller.internal")
            .build()
    }

    @Bean
    fun external(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("external")
            .packagesToScan("fr.hcaupert.springdoctalk.initial.presentation.controller.external")
            .build()
    }
}
