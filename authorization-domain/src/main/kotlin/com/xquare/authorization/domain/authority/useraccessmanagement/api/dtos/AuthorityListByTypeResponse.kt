package com.xquare.authorization.domain.authority.useraccessmanagement.api.dtos

data class AuthorityListByTypeResponse(
    val authorityList: List<AuthorityByTypeResponse>
)

data class AuthorityByTypeResponse(
    val authorityName: String,
)
