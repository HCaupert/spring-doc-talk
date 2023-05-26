package fr.hcaupert.springdoctalk.final.presentation.common

data class PageRequestDto<T : Enum<T>>(
    val page: Int,
    val size: Int,
    val sortDirection: SortDirection,
    val sortAttribute: T
)

