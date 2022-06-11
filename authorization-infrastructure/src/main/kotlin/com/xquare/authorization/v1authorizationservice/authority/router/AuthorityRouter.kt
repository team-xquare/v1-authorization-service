package com.xquare.authorization.v1authorizationservice.authority.router

import com.xquare.authorization.v1authorizationservice.authority.handler.AuthorityHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class AuthorityRouter(
    private val authorityHandler: AuthorityHandler
) {
    @Bean
    fun routeAuthority() = coRouter {
        "/authorities".nest {
            GET("/{userId}", authorityHandler::handleGetAuthorityList)
        }
    }
}
