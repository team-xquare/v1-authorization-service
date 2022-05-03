package com.xquare.authentication.v1authenticationservice.authority.spi

import com.xquare.authentication.domain.authority.Authority
import com.xquare.authentication.domain.authority.spi.AuthorityRepositorySpi
import com.xquare.authentication.v1authenticationservice.authority.mapper.AuthorityDomainMapper
import com.xquare.authentication.v1authenticationservice.authority.repositories.AuthorityRepository
import org.springframework.stereotype.Repository

@Repository
class AuthorityRepositorySpiImpl(
    private val authorityRepository: AuthorityRepository,
    private val authorityDomainMapper: AuthorityDomainMapper
) : AuthorityRepositorySpi {
    companion object {
        private val DEFAULT_NAME_LIST = listOf("학생")
    }

    override suspend fun getBaseUserAuthorities(): List<Authority> {
        val authorityEntities = authorityRepository.findAllByNameIn(DEFAULT_NAME_LIST)
        return authorityEntities.map { authorityDomainMapper.authorityEntityToDomain(it) }
    }
}
