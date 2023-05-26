package fr.hcaupert.springdoctalk.initial.presentation.common

data class PageRequestDto<T : Enum<T>>(
    val page: Int,
    val size: Int,
    val sortDirection: SortDirection,
    val sortAttribute: T
)

