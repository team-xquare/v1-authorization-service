package com.xquare.authorization.v1authorizationservice.authority.spi

import com.xquare.authorization.domain.authority.Authority
import com.xquare.authorization.domain.authority.useraccessmanagement.spi.GetUserAuthoritySpi
import com.xquare.authorization.v1authorizationservice.authority.mapper.AuthorityDomainMapper
import com.xquare.authorization.v1authorizationservice.authority.repositories.AuthorityRepository
import java.util.UUID
import org.springframework.stereotype.Repository

@Repository
class GetUserAuthoritySpiImpl(
    private val authorityRepository: AuthorityRepository,
    private val authorityDomainMapper: AuthorityDomainMapper
) : GetUserAuthoritySpi {
    override suspend fun getUserAuthority(userId: UUID): List<Authority> {
        val authorityEntities = authorityRepository.findAllByUserId(userId)
        return authorityEntities.map { authorityDomainMapper.authorityEntityToDomain(it) }
    }
}
