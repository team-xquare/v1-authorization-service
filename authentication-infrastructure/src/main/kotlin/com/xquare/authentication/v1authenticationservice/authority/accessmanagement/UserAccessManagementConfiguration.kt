package com.xquare.authentication.v1authenticationservice.authority.accessmanagement

import com.xquare.authentication.domain.authority.useraccessmanagement.UserAccessManagement
import com.xquare.authentication.domain.annotations.DomainService
import com.xquare.authentication.domain.annotations.SagaStep
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

@Configuration
@ComponentScan(
    basePackageClasses = [UserAccessManagement::class],
    includeFilters = [
        ComponentScan.Filter(
            type = FilterType.ANNOTATION,
            value = [SagaStep::class, DomainService::class]
        )
    ]
)
class UserAccessManagementConfiguration
