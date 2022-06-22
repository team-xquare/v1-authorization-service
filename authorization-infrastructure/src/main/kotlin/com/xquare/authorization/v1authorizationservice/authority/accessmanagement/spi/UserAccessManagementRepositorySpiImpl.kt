package com.xquare.authorization.v1authorizationservice.authority.accessmanagement.spi

import com.xquare.authorization.domain.authority.useraccessmanagement.UserAccessManagement
import com.xquare.authorization.domain.authority.useraccessmanagement.spi.UserAccessManagementRepositorySpi
import com.xquare.authorization.v1authorizationservice.authority.accessmanagement.mapper.UserAccessManagementDomainMapper
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.r2dbc.core.R2dbcEntityOperations
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class UserAccessManagementRepositorySpiImpl(
    private val userAccessManagementDomainMapper: UserAccessManagementDomainMapper,
    private val entityOperations: R2dbcEntityOperations
) : UserAccessManagementRepositorySpi {
    @Transactional
    override suspend fun saveAllUserAccessManagement(userAccessManagements: List<UserAccessManagement>): List<UserAccessManagement> {
        val userAccessManagementEntityListToSave = userAccessManagements
            .map { userAccessManagementDomainMapper.userAccessManagementDomainToEntity(it) }

        val savedUserAccessManagements =
            userAccessManagementEntityListToSave.map { entityOperations.insert(it).awaitSingle() }

        return savedUserAccessManagements
            .map { userAccessManagementDomainMapper.userAccessManagementEntityToDomain(it) }
    }
}
