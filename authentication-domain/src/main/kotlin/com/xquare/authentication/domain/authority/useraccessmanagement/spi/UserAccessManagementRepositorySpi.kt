package com.xquare.authentication.domain.authority.useraccessmanagement.spi

import com.xquare.authentication.domain.authority.useraccessmanagement.UserAccessManagement

interface UserAccessManagementRepositorySpi {
    suspend fun saveAllUserAccessManagementAndOutbox(userAccessManagements: List<UserAccessManagement>): List<UserAccessManagement>
}
