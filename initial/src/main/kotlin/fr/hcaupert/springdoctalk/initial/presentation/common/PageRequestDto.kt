package fr.hcaupert.springdoctalk.initial.presentation.common

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
abstract class PageRequestDto<T : Enum<T>> {
    abstract val page: Int
    abstract val size: Int
    abstract val sortDirection: SortDirection
    abstract val sortAttribute: T
}
