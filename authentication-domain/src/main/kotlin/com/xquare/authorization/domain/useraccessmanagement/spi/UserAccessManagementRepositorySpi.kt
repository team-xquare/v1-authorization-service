package com.xquare.authorization.domain.useraccessmanagement.spi

import com.xquare.authorization.domain.useraccessmanagement.UserAccessManagement

interface UserAccessManagementRepositorySpi {
    suspend fun saveUserAccessManagementAndOutbox(userAccessManagement: UserAccessManagement): UserAccessManagement
}