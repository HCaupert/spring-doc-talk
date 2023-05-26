package fr.hcaupert.springdoctalk.initial.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests()
            .requestMatchers(*authWhitelist).permitAll()
            .anyRequest().authenticated()
            .and().oauth2Login()
        return http.build()
    }

    private val authWhitelist = arrayOf( // -- Swagger UI v2
        "/v2/api-docs",
        "v2/api-docs",
        "/swagger-resources",
        "swagger-resources",
        "/swagger-resources/**",
        "swagger-resources/**",
        "/configuration/ui",
        "configuration/ui",
        "/configuration/security",
        "configuration/security",
        "/swagger-ui.html",
        "swagger-ui.html",
        "webjars/**",  // -- Swagger UI v3
        "/v3/api-docs/**",
        "v3/api-docs/**",
        "/swagger-ui/**",
        "swagger-ui/**",  // CSA Controllers
        "/csa/api/token",  // Actuators
        "/actuator/**",
        "/health/**"
    )
}
