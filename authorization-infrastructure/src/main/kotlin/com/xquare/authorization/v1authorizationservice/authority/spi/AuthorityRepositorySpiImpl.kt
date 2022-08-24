package com.xquare.authorization.v1authorizationservice.authority.spi

import com.xquare.authorization.domain.authority.Authority
import com.xquare.authorization.domain.authority.spi.AuthorityRepositorySpi
import com.xquare.authorization.domain.authority.useraccessmanagement.UserAccessManagement
import com.xquare.authorization.v1authorizationservice.authority.accessmanagement.mapper.UserAccessManagementDomainMapper
import com.xquare.authorization.v1authorizationservice.authority.mapper.AuthorityDomainMapper
import com.xquare.authorization.v1authorizationservice.authority.repositories.AuthorityRepository
import java.util.UUID
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.r2dbc.core.R2dbcEntityOperations
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class AuthorityRepositorySpiImpl(
    private val authorityRepository: AuthorityRepository,
    private val authorityDomainMapper: AuthorityDomainMapper,
    private val userAccessManagementDomainMapper: UserAccessManagementDomainMapper,
    private val entityOperations: R2dbcEntityOperations
) : AuthorityRepositorySpi {
    companion object {
        private val DEFAULT_NAME_LIST = listOf("학생")
    }

    override suspend fun getBaseUserAuthorities(): List<Authority> {
        val authorityEntities = authorityRepository.findAllByNameIn(DEFAULT_NAME_LIST)
        return authorityEntities.map { authorityDomainMapper.authorityEntityToDomain(it) }
    }

    override suspend fun getUserAuthority(userId: UUID): List<Authority> {
        val authorityEntities = authorityRepository.findAllByUserId(userId)
        return authorityEntities.map { authorityDomainMapper.authorityEntityToDomain(it) }
    }

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
