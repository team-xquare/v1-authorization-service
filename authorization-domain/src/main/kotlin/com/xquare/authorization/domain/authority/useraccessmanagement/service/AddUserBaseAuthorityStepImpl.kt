package com.xquare.authorization.domain.authority.useraccessmanagement.service

import com.xquare.authorization.domain.authority.Authority
import com.xquare.authorization.domain.authority.spi.AuthorityRepositorySpi
import com.xquare.authorization.domain.authority.useraccessmanagement.UserAccessManagement
import com.xquare.authorization.domain.authority.useraccessmanagement.api.AddUserBaseAuthorityStep
import com.xquare.authorization.domain.authority.useraccessmanagement.spi.UserAccessManagementRepositorySpi
import com.xquare.authorization.domain.annotations.SagaStep
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
