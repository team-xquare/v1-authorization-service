package com.xquare.authorization.v1authorizationservice.authority.accessmanagement.handler

import com.xquare.authorization.domain.authority.useraccessmanagement.api.AccessManagementService
import com.xquare.authorization.v1authorizationservice.configuration.validate.BadRequestException
import com.xquare.authorization.v1authorizationservice.configuration.validate.RequestBodyValidator
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait
import org.springframework.web.reactive.function.server.queryParamOrNull
import java.net.URI
import java.util.*

@Component
class AccessManagementHandler(
    private val requestBodyValidator: RequestBodyValidator,
    private val accessManagementService: AccessManagementService
) {
    suspend fun createUserBaseAccessManagement(serverRequest: ServerRequest): ServerResponse {
        val request = serverRequest.bodyToMono<UserBaseAuthorityRequest>().awaitSingle()
        requestBodyValidator.validate(request)
        accessManagementService.saveBaseAccessManagement(request.userId)
        return ServerResponse.created(URI.create("")).buildAndAwait()
    }

    suspend fun createUserAccessManagement(serverRequest: ServerRequest): ServerResponse {
        val request = serverRequest.bodyToMono<UserAuthorityRequest>().awaitSingle()
        requestBodyValidator.validate(request)
        accessManagementService.saveAccessManagement(request.userId, request.authorities)
        return ServerResponse.created(URI("/authorities/access-management")).buildAndAwait()
    }

    suspend fun handleGetAuthorityList(serverRequest: ServerRequest): ServerResponse {
        val userId = UUID.fromString(serverRequest.pathVariable("userId"))
        val authorityList = accessManagementService.getUserAuthorityList(userId)
        return ServerResponse.ok().bodyValueAndAwait(authorityList)
    }

    suspend fun handleGetAuthorityListByType(serverRequest: ServerRequest): ServerResponse {
        val userId = UUID.fromString(serverRequest.pathVariable("userId"))
        val type = serverRequest.queryParamOrNull("type") ?: throw BadRequestException("Type Not Found")
        val authorityList = accessManagementService.getUserAuthorityListByType(userId, type)
        return ServerResponse.ok().bodyValueAndAwait(authorityList)
    }

    suspend fun handleDeleteBaseAccessManagement(serverRequest: ServerRequest): ServerResponse {
        val userId = UUID.fromString(serverRequest.pathVariable("userId"))
        accessManagementService.deleteBaseAccessManagement(userId)
        return ServerResponse.noContent().buildAndAwait()
    }
}
