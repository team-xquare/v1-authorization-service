package com.xquare.authorization.v1authorizationservice

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@OpenAPIDefinition(info = Info(title = "APIs", version = "1.0", description = "Documentation APIs"))
@SpringBootApplication
class V1AuthorizationServiceApplication

fun main(args: Array<String>) {
    runApplication<V1AuthorizationServiceApplication>(*args)
}
