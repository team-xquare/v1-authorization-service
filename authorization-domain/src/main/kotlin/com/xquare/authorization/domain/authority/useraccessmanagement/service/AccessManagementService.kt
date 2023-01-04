package com.xquare.authorization.domain.authority.useraccessmanagement.service

import com.xquare.authorization.domain.annotations.SagaStep
import com.xquare.authorization.domain.authority.Authority
import com.xquare.authorization.domain.authority.spi.AuthorityRepositorySpi
import com.xquare.authorization.domain.authority.useraccessmanagement.UserAccessManagement
import com.xquare.authorization.domain.authority.useraccessmanagement.api.AccessManagementService
import com.xquare.authorization.domain.authority.useraccessmanagement.api.dtos.AuthorityListByTypeResponse
import com.xquare.authorization.domain.authority.useraccessmanagement.api.dtos.AuthorityListResponse
import com.xquare.authorization.domain.authority.useraccessmanagement.api.dtos.AuthorityResponse
import java.util.*

@SagaStep
class AccessManagementService(
    private val authorityRepositorySpi: AuthorityRepositorySpi
) : AccessManagementService {

    override suspend fun saveBaseAccessManagement(userId: UUID) {
        val userOwnAccessManagementMap = authorityRepositorySpi.getUserAuthority(userId).associateBy { it.id }
        val basicAuthorities = authorityRepositorySpi.getBaseUserAuthorities()
        val userAccessManagementList = basicAuthorities
            .filterAlreadyUsersAuthority(userOwnAccessManagementMap)
            .map { it.toUserAccessManagement(userId) }

        authorityRepositorySpi.saveAllUserAccessManagement(userAccessManagementList)
    }

    private fun Authority.toUserAccessManagement(userId: UUID) =
        UserAccessManagement(
            id = UUID.randomUUID(),
            userId = userId,
            authorityId = this.id
        )

    private fun List<Authority>.filterAlreadyUsersAuthority(ownAuthorityMap: Map<UUID, Authority>) =
        this.filter { !ownAuthorityMap.containsKey(it.id) }

    override suspend fun deleteBaseAccessManagement(userId: UUID) {
        val basicAuthorities = authorityRepositorySpi.getBaseUserAuthorities()

    }

    override suspend fun getUserAuthorityListByType(userId: UUID, type: String): AuthorityListByTypeResponse {
        val authorityList = authorityRepositorySpi.getUserAuthorityByType(userId, type)
        return authorityList.toAuthorityListByTypeResponse()
    }

    private fun List<Authority>.toAuthorityListByTypeResponse(): AuthorityListByTypeResponse {
        val authorityListByType = this.map { it.name }
        return AuthorityListByTypeResponse(authorityListByType)
    }

    override suspend fun getUserAuthorityList(userId: UUID): AuthorityListResponse {
        val authorityList = authorityRepositorySpi.getUserAuthority(userId)
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
