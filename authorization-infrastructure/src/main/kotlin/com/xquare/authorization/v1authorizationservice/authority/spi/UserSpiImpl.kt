package com.xquare.authorization.v1authorizationservice.authority.spi

import com.xquare.authorization.domain.user.User
import com.xquare.authorization.domain.user.spi.UserRepositorySpi
import com.xquare.authorization.v1authorizationservice.authority.exceptions.UserRequestFailedException
import com.xquare.authorization.v1authorizationservice.authority.spi.dtos.UserResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import java.util.*

@Repository
class UserSpiImpl(
    private val webClient: WebClient,
    @Value("\${service.scheme}")
    private val scheme: String,
    @Value("\${service.user.host}")
    private val userHost: String,
) : UserRepositorySpi {
    override suspend fun getUser(userId: UUID): User {
        val clientResponse = sendGetUserRequest(userId)
        return getAuthorityNamesFromResponse(clientResponse)
    }

    private suspend fun sendGetUserRequest(userId: UUID): WebClient.ResponseSpec {
        return webClient.get().uri { uri ->
            uri.scheme(scheme)
                .host(userHost)
                .path("/users/id/{userId}")
                .build(userId)
        }.retrieve()
            .onStatus(HttpStatus::isError) {
                throw UserRequestFailedException("Request failed to get user", it.rawStatusCode())
            }
    }

    private suspend fun getAuthorityNamesFromResponse(clientResponse: WebClient.ResponseSpec): User {
        val userResponse = clientResponse.awaitBody<UserResponse>()
        return User(
            name = userResponse.name,
            id = userResponse.id,
            birthDay = userResponse.birthDay,
            grade = userResponse.grade,
            classNum = userResponse.classNum,
            profileFileName = userResponse.profileFileName,
            password = userResponse.password,
            accountId = userResponse.accountId,
            num = userResponse.num
        )
    }
}
