package com.xquare.authorization.v1authorizationservice.authority.accessmanagement

import com.xquare.authorization.domain.authority.useraccessmanagement.UserAccessManagement
import com.xquare.authorization.domain.annotations.DomainService
import com.xquare.authorization.domain.annotations.SagaStep
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
