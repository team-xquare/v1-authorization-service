package com.xquare.authorization.domain.authority.useraccessmanagement.service

import com.xquare.authorization.domain.annotations.DomainService
import com.xquare.authorization.domain.authority.Authority
import com.xquare.authorization.domain.authority.useraccessmanagement.api.GetUserAuthorityApi
import com.xquare.authorization.domain.authority.useraccessmanagement.api.dtos.AuthorityListResponse
import com.xquare.authorization.domain.authority.useraccessmanagement.api.dtos.AuthorityResponse
import com.xquare.authorization.domain.authority.useraccessmanagement.spi.GetUserAuthoritySpi
import java.util.UUID

@DomainService
class GetUserAuthorityApiImpl(
    private val getUserAuthoritySpi: GetUserAuthoritySpi
) : GetUserAuthorityApi {
    override suspend fun getUserAuthorityList(userId: UUID): AuthorityListResponse {
        val authorityList = getUserAuthoritySpi.getUserAuthority(userId)
        return authorityList.toAuthorityListResponse()
    }

    private fun List<Authority>.toAuthorityListResponse(): AuthorityListResponse {
        val authorityResponseList = this.map {
            AuthorityResponse(
                authorityId = it.id,
                authorityName = it.name,
                description = it.description
            )
        }

        return AuthorityListResponse(authorityResponseList)
    }
}
