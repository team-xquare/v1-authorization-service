package com.xquare.authentication.v1authenticationservice.authority.mapper

import com.xquare.authentication.domain.authority.Authority
import com.xquare.authentication.v1authenticationservice.authority.AuthorityEntity
import org.mapstruct.Mapper

@Mapper
interface AuthorityDomainMapper {
    fun authorityDomainToEntity(authority: Authority): AuthorityEntity
    fun authorityEntityToDomain(authorityEntity: AuthorityEntity): Authority
}
