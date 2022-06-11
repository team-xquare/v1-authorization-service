package com.xquare.authorization.v1authorizationservice.authority.handler

import com.xquare.authorization.domain.authority.useraccessmanagement.api.GetUserAuthorityApi
import java.util.UUID
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class AuthorityHandler(
    private val getUserAuthorityApi: GetUserAuthorityApi
) {
    suspend fun handleGetAuthorityList(serverRequest: ServerRequest): ServerResponse {
        val userId = UUID.fromString(serverRequest.pathVariable("userId"))
        val authorityList = getUserAuthorityApi.getUserAuthorityList(userId)
        return ServerResponse.ok().bodyValueAndAwait(authorityList)
    }
}
