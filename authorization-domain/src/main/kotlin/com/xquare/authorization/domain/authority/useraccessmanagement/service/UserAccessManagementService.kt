package com.xquare.authorization.domain.authority.useraccessmanagement.service

import com.xquare.authorization.domain.annotations.SagaStep
import com.xquare.authorization.domain.authority.Authority
import com.xquare.authorization.domain.authority.spi.AuthorityRepositorySpi
import com.xquare.authorization.domain.authority.useraccessmanagement.UserAccessManagement
import com.xquare.authorization.domain.authority.useraccessmanagement.api.GetUserAuthorityApi
import com.xquare.authorization.domain.authority.useraccessmanagement.api.SaveUserBaseAuthorityApi
import com.xquare.authorization.domain.authority.useraccessmanagement.api.dtos.AuthorityListResponse
import com.xquare.authorization.domain.authority.useraccessmanagement.api.dtos.AuthorityResponse
import com.xquare.authorization.domain.authority.useraccessmanagement.spi.GetUserAuthoritySpi
import com.xquare.authorization.domain.authority.useraccessmanagement.spi.UserAccessManagementRepositorySpi
import java.util.UUID

@SagaStep
class UserAccessManagementService(
    private val userAccessManagementRepositorySpi: UserAccessManagementRepositorySpi,
    private val authorityRepositorySpi: AuthorityRepositorySpi,
    private val getUserAuthoritySpi: GetUserAuthoritySpi,
) : SaveUserBaseAuthorityApi, GetUserAuthorityApi {

    override suspend fun saveBaseAuthority(userId: UUID) {
        val userOwnAccessManagementMap = getUserAuthoritySpi.getUserAuthority(userId).associateBy { it.id }
        val basicAuthorities = authorityRepositorySpi.getBaseUserAuthorities()
        val userAccessManagementList = basicAuthorities
            .filterAlreadyOwnAuthority(userOwnAccessManagementMap)
            .map { it.toUserAccessManagement(userId) }

        userAccessManagementRepositorySpi.saveAllUserAccessManagement(userAccessManagementList)
    }

    private fun Authority.toUserAccessManagement(userId: UUID) =
        UserAccessManagement(
            id = UUID.randomUUID(),
            userId = userId,
            authorityId = this.id
        )

    private fun List<Authority>.filterAlreadyOwnAuthority(ownAuthorityMap: Map<UUID, Authority>) =
        this.filter { !ownAuthorityMap.containsKey(it.id) }

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
