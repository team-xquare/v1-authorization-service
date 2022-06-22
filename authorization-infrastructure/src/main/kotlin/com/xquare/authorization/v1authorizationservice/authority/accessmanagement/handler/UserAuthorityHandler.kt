package com.xquare.authorization.v1authorizationservice.authority.accessmanagement.handler

import com.xquare.authorization.domain.authority.useraccessmanagement.api.SaveUserBaseAuthorityApi
import com.xquare.authorization.v1authorizationservice.configuration.validate.RequestBodyValidator
import java.net.URI
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import org.springframework.web.reactive.function.server.buildAndAwait

@Component
class UserAuthorityHandler(
    private val saveUserBaseAuthorityApi: SaveUserBaseAuthorityApi,
    private val requestBodyValidator: RequestBodyValidator
) {
    suspend fun createUserBaseAuthority(serverRequest: ServerRequest): ServerResponse {
        val request = serverRequest.bodyToMono<UserBaseAuthorityRequest>().awaitSingle()
        requestBodyValidator.validate(request)
        saveUserBaseAuthorityApi.saveBaseAuthority(request.userId)
        return ServerResponse.created(URI.create("")).buildAndAwait()
    }
}
