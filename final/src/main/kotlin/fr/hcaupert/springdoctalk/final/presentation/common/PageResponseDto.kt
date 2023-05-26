package fr.hcaupert.springdoctalk.final.presentation.common

class PageResponseDto<T>(
    val content: List<T>,
    val totalElements: Long,
    val page: Int,
    val pageSize: Int,
)
