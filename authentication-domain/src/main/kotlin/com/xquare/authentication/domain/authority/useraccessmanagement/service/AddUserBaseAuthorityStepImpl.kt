package com.xquare.authentication.domain.authority.useraccessmanagement.service

import com.xquare.authentication.domain.authority.Authority
import com.xquare.authentication.domain.authority.spi.AuthorityRepositorySpi
import com.xquare.authentication.domain.authority.useraccessmanagement.UserAccessManagement
import com.xquare.authentication.domain.authority.useraccessmanagement.api.AddUserBaseAuthorityStep
import com.xquare.authentication.domain.authority.useraccessmanagement.spi.UserAccessManagementRepositorySpi
import com.xquare.authentication.domain.annotations.SagaStep
import java.util.UUID

@SagaStep
class AddUserBaseAuthorityStepImpl(
    private val userAccessManagementRepositorySpi: UserAccessManagementRepositorySpi,
    private val authorityRepositorySpi: AuthorityRepositorySpi
) : AddUserBaseAuthorityStep {

    override suspend fun processStep(userId: UUID) {
        val basicAuthorities = authorityRepositorySpi.getBaseUserAuthorities()
        val userAccessManagementList = basicAuthorities.map { it.toUserAccessManagement(userId) }
        userAccessManagementRepositorySpi.saveAllUserAccessManagementAndOutbox(userAccessManagementList)
    }

    private fun Authority.toUserAccessManagement(userId: UUID) =
        UserAccessManagement(
            id = UUID.randomUUID(),
            userId = userId,
            authorityId = this.id
        )
}
