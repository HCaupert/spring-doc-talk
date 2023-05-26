package fr.hcaupert.springdoctalk.initial.service

import fr.hcaupert.springdoctalk.initial.presentation.common.PageResponseDto
import fr.hcaupert.springdoctalk.initial.presentation.controller.external.dto.UserCreateExternalRequestDto
import fr.hcaupert.springdoctalk.initial.presentation.controller.external.dto.UserResponseDto
import fr.hcaupert.springdoctalk.initial.presentation.controller.internal.dto.UserCreateInternalRequestDto
import org.slf4j.LoggerFactory
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import java.io.File
import java.net.URL
import java.util.*

@Service
class UserServiceImpl : UserService {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun create(requestDto: UserCreateExternalRequestDto): UUID {
        TODO("Not yet implemented")
    }

    override fun create(requestDto: UserCreateInternalRequestDto): UUID {
        TODO("Not yet implemented")
    }

    override fun byId(id: UUID): UserResponseDto {
        TODO("Not yet implemented")
    }

    override fun all(): PageResponseDto<UserResponseDto> {
        TODO("Not yet implemented")
    }

    @EventListener
    fun cheatApiDoc(event: ContextRefreshedEvent) {
        val file = File("doc.json")
        URL("http://localhost:8080/v3/api-docs")
            .readBytes()
            .also(file::writeBytes)
        logger.info("Downloaded open-api specification at ${file.absolutePath}")
    }
}
