package fr.hcaupert.springdoctalk.initial

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class InitialApplication

fun main(args: Array<String>) {
    runApplication<InitialApplication>(*args)
}
