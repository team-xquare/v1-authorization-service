package com.xquare.authorization.domain.user.spi

import com.xquare.authorization.domain.user.User
import java.util.UUID

interface UserRepositorySpi {

    suspend fun getUser(userId: UUID): User
}
