package com.xquare.authorization.v1authorizationservice

import com.xquare.authorization.v1authorizationservice.configuration.r2dbc.DatasourceConfiguration
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest
import org.springframework.context.annotation.Import

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@DataR2dbcTest
@Import(DatasourceConfiguration::class)
annotation class RepositoryIntegrationTest
