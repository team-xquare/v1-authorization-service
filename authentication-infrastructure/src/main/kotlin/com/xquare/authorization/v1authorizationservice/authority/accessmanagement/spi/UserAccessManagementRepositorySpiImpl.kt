package com.xquare.authorization.v1authorizationservice.authority.accessmanagement.spi

import com.xquare.authorization.domain.useraccessmanagement.UserAccessManagement
import com.xquare.authorization.domain.useraccessmanagement.spi.UserAccessManagementRepositorySpi
import com.xquare.authorization.v1authorizationservice.authority.accessmanagement.mapper.UserAccessManagementDomainMapper
import com.xquare.authorization.v1authorizationservice.authority.accessmanagement.repositories.UserAccessManagementRepository
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
    override suspend fun saveUserAccessManagementAndOutbox(userAccessManagement: UserAccessManagement): UserAccessManagement {
        val userAccessManagementEntityToSave = userAccessManagementDomainMapper
            .userAccessManagementDomainToEntity(userAccessManagement)
        val savedUserAccessManagement = entityOperations.insert(userAccessManagementEntityToSave).awaitSingle()
        return userAccessManagementDomainMapper.userAccessManagementEntityToDomain(savedUserAccessManagement)
    }
}