package com.xquare.authentication.v1authenticationservice.authority.accessmanagement.mapper

import com.xquare.authentication.domain.useraccessmanagement.UserAccessManagement
import com.xquare.authentication.v1authenticationservice.authority.accessmanagement.UserAccessManagementEntity
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
interface UserAccessManagementDomainMapper {
    fun userAccessManagementDomainToEntity(userAccessManagement: UserAccessManagement): UserAccessManagementEntity
    fun userAccessManagementEntityToDomain(userAccessManagementEntity: UserAccessManagementEntity): UserAccessManagement
}
