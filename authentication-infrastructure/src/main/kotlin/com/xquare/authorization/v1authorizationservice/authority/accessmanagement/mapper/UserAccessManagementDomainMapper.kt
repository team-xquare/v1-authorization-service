package com.xquare.authorization.v1authorizationservice.authority.accessmanagement.mapper

import com.xquare.authorization.domain.useraccessmanagement.UserAccessManagement
import com.xquare.authorization.v1authorizationservice.authority.accessmanagement.UserAccessManagementEntity
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
interface UserAccessManagementDomainMapper {
    fun userAccessManagementDomainToEntity(userAccessManagement: UserAccessManagement): UserAccessManagementEntity
    fun userAccessManagementEntityToDomain(userAccessManagementEntity: UserAccessManagementEntity): UserAccessManagement
}
