package fr.hcaupert.springdoctalk.final.presentation.common

abstract class PageRequestDto<T : Enum<T>> {
    abstract val page: Int
    abstract val size: Int
    abstract val sortDirection: SortDirection
    abstract val sortAttribute: T
}
