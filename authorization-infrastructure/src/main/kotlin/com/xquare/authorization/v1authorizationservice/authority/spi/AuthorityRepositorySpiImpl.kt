package com.xquare.authorization.v1authorizationservice.authority.spi

import com.xquare.authorization.domain.authority.Authority
import com.xquare.authorization.domain.authority.spi.AuthorityRepositorySpi
import com.xquare.authorization.domain.authority.useraccessmanagement.UserAccessManagement
import com.xquare.authorization.v1authorizationservice.authority.accessmanagement.mapper.UserAccessManagementDomainMapper
import com.xquare.authorization.v1authorizationservice.authority.accessmanagement.repositories.UserAccessManagementRepository
import com.xquare.authorization.v1authorizationservice.authority.mapper.AuthorityDomainMapper
import com.xquare.authorization.v1authorizationservice.authority.repositories.AuthorityRepository
import kotlinx.coroutines.flow.toCollection
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
class AuthorityRepositorySpiImpl(
    private val authorityRepository: AuthorityRepository,
    private val authorityDomainMapper: AuthorityDomainMapper,
    private val userAccessManagementDomainMapper: UserAccessManagementDomainMapper,
    private val accessManagementRepository: UserAccessManagementRepository
) : AuthorityRepositorySpi {
    companion object {
        private val DEFAULT_NAME_LIST = listOf("STU", "UKN")
    }

    override suspend fun getBaseUserAuthorities(): List<Authority> {
        val authorityEntities = authorityRepository.findAllByNameIn(DEFAULT_NAME_LIST)
        return authorityEntities.map { authorityDomainMapper.authorityEntityToDomain(it) }
    }

    override suspend fun getUserAuthorities(authorities: List<String>): List<Authority> {
        val authorityEntities = authorityRepository.findAuthorityEntity()
            .filter { authorities.contains(it.name) }
        return authorityEntities.map { authorityDomainMapper.authorityEntityToDomain(it) }
    }

    override suspend fun getUserAuthority(userId: UUID): List<Authority> {
        val authorityEntities = authorityRepository.findAllByUserId(userId)
        return authorityEntities.map { authorityDomainMapper.authorityEntityToDomain(it) }
    }

    override suspend fun getUserAuthorityByType(userId: UUID, type: String): List<Authority> {
        val authorityEntities = authorityRepository.findAllByUserIdAndType(userId, type)
        return authorityEntities.map { authorityDomainMapper.authorityEntityToDomain(it) }
    }

    @Transactional
    override suspend fun saveAllUserAccessManagement(userAccessManagements: List<UserAccessManagement>): List<UserAccessManagement> {
        val userAccessManagementEntityListToSave = userAccessManagements
            .map { userAccessManagementDomainMapper.userAccessManagementDomainToEntity(it) }

        val savedUserAccessManagements = accessManagementRepository.saveAll(userAccessManagementEntityListToSave)
            .toCollection(LinkedList())

        return savedUserAccessManagements
            .map { userAccessManagementDomainMapper.userAccessManagementEntityToDomain(it) }
    }

    override suspend fun deleteAllUserAccessManagement(userId: UUID, userAccessManagements: List<UUID>) {
        accessManagementRepository.deleteAllByUserIdAndAuthorityIdIn(userId, userAccessManagements)
    }
}
