package com.xquare.authorization.v1authorizationservice.authority.mapper

import com.xquare.authorization.domain.authority.Authority
import com.xquare.authorization.v1authorizationservice.authority.AuthorityEntity
import org.mapstruct.Mapper

@Mapper
interface AuthorityDomainMapper {
    fun authorityDomainToEntity(authority: Authority): AuthorityEntity
    fun authorityEntityToDomain(authorityEntity: AuthorityEntity): Authority
}
