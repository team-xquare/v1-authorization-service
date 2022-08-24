package com.xquare.authorization.v1authorizationservice.authority.accessmanagement.router

import com.xquare.authorization.v1authorizationservice.authority.accessmanagement.handler.AccessManagementHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class AccessManagementRouter(
    private val accessManagementHandler: AccessManagementHandler
) {
    @Bean
    fun saveBaseAuthority() = coRouter {
        "/authorities/access-management/basic".nest {
            POST("", accessManagementHandler::createUserBaseAccessManagement)
            GET("/{userId}", accessManagementHandler::handleGetAuthorityList)
        }
    }
}
