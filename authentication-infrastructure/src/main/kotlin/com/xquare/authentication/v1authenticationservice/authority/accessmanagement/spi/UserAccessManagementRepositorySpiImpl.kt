package com.xquare.authentication.v1authenticationservice.authority.accessmanagement.spi

import com.xquare.authentication.domain.useraccessmanagement.UserAccessManagement
import com.xquare.authentication.domain.useraccessmanagement.spi.UserAccessManagementRepositorySpi
import com.xquare.authentication.v1authenticationservice.authority.accessmanagement.mapper.UserAccessManagementDomainMapper
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
