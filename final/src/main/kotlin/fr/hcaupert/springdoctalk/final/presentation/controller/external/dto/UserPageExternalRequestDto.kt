package fr.hcaupert.springdoctalk.final.presentation.controller.external.dto

import fr.hcaupert.springdoctalk.final.presentation.common.PageRequestDto
import fr.hcaupert.springdoctalk.final.presentation.common.SortDirection

class UserPageExternalRequestDto(
    override val page: Int,
    override val size: Int,
    override val sortDirection: SortDirection,
    override val sortAttribute: UserSortAttribute,
) : PageRequestDto<UserSortAttribute>()
