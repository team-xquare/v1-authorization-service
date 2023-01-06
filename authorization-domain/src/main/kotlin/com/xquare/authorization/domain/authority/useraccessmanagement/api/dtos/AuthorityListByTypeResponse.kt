package com.xquare.authorization.domain.authority.useraccessmanagement.api.dtos

import java.util.*

data class AuthorityListByTypeResponse(
    val authorityList: List<AuthorityByTypeResponse>
)

data class AuthorityByTypeResponse(
    val authorityId: UUID,
    val authorityName: String
)