package com.xquare.authorization.v1authorizationservice.authority.accessmanagement.router

import com.xquare.authorization.v1authorizationservice.authority.accessmanagement.handler.UserAuthorityHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class AccessManagementRouter(
    private val userAuthorityHandler: UserAuthorityHandler
) {
    @Bean
    fun saveBaseAuthority() = coRouter {
        "/authorities/access-management/basic".nest {
            POST("", userAuthorityHandler::createUserBaseAuthority)
        }
    }
}
