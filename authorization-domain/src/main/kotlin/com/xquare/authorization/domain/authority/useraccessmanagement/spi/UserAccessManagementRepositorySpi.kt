package com.xquare.authorization.domain.authority.useraccessmanagement.spi

import com.xquare.authorization.domain.authority.useraccessmanagement.UserAccessManagement

interface UserAccessManagementRepositorySpi {
    suspend fun saveAllUserAccessManagement(userAccessManagements: List<UserAccessManagement>): List<UserAccessManagement>
}
