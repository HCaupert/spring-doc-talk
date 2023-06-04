package fr.hcaupert.springdoctalk.initial.presentation.controller.external.dto

import fr.hcaupert.springdoctalk.initial.presentation.common.PageRequestDto
import fr.hcaupert.springdoctalk.initial.presentation.common.SortDirection

class UserPageExternalRequestDto(
    override val page: Int,
    override val size: Int,
    override val sortDirection: SortDirection,
    override val sortAttribute: UserSortAttribute,
) : PageRequestDto<UserSortAttribute>()
