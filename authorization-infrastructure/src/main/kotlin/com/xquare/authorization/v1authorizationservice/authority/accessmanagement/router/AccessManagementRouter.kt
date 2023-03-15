package com.xquare.authorization.v1authorizationservice.authority.accessmanagement.router

import com.xquare.authorization.domain.authority.useraccessmanagement.api.dtos.AuthorityListByTypeResponse
import com.xquare.authorization.domain.authority.useraccessmanagement.api.dtos.AuthorityListResponse
import com.xquare.authorization.v1authorizationservice.authority.accessmanagement.handler.AccessManagementHandler
import com.xquare.authorization.v1authorizationservice.authority.accessmanagement.handler.UserBaseAuthorityRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springdoc.core.annotations.RouterOperation
import org.springdoc.core.annotations.RouterOperations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class AccessManagementRouter(
    private val accessManagementHandler: AccessManagementHandler
) {
    @Bean
    @RouterOperations(
        RouterOperation(
            path = "/authorities/access-management/basic",
            method = [RequestMethod.POST],
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            operation = Operation(
                operationId = "createUserBaseAccessManagement",
                summary = "Create user base access management",
                description = "회원가입시 기본적으로 필요한 권한들을 추가합니다.",
                responses = [
                    ApiResponse(responseCode = "201", description = "권한 추가 성공")
                ],
                requestBody = RequestBody(
                    content = [Content(
                        schema = Schema(implementation = UserBaseAuthorityRequest::class),
                        examples = [
                            ExampleObject(
                                value = """
                                    {
                                        "userId": "UUID"
                                    }
                                """
                            )
                        ]
                    )]
                )
            )
        ),
        RouterOperation(
            path = "/authorities/access-management/{userId}",
            method = [RequestMethod.GET],
            produces = [MediaType.APPLICATION_JSON_VALUE],
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            params = ["userId"],
            operation = Operation(
                operationId = "getUserAuthorityList",
                summary = "Get user authority list",
                description = "사용자가 가지고 있는 권한을 조회합니다.",
                parameters = [
                    Parameter(
                        name = "userId",
                        description = "사용자 아이디",
                        `in` = ParameterIn.PATH,
                        allowEmptyValue = false,
                        required = true
                    )
                ],
                requestBody = RequestBody(content = []),
                responses = [
                    ApiResponse(
                        responseCode = "200", description = "권한 조회 성공", content = [
                            Content(
                                schema = Schema(implementation = AuthorityListResponse::class),
                                examples = [
                                    ExampleObject(
                                        value = """
                                        {
                                            "authorities": [
                                                {
                                                    "authorityId": "asdfasdf-asdfasdf",
                                                    "authorityName": "SDT",
                                                    "description": "학생 권한입니다."
                                                },
                                                {
                                                    "authorityId": "asdfasdf-asdfasdf",
                                                    "authorityName": "STA",
                                                    "description": "학생 자치회 권한입니다."
                                                }
                                            ]
                                        }
                                    """
                                    )
                                ]
                            )
                        ]
                    )
                ],
            )
        ),
        RouterOperation(
            path = "/authorities/access-management/type/{type}",
            method = [RequestMethod.GET],
            produces = [MediaType.APPLICATION_JSON_VALUE],
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            params = ["type"],
            operation = Operation(
                operationId = "getUserAuthorityByTypeList",
                summary = "Get user authority list",
                description = "사용자가 가지고 있는 권한을 조회합니다.",
                parameters = [
                    Parameter(
                        name = "type",
                        description = "권한의 종류",
                        `in` = ParameterIn.PATH,
                        allowEmptyValue = false,
                        required = true
                    )
                ],
                requestBody = RequestBody(content = []),
                responses = [
                    ApiResponse(
                        responseCode = "200", description = "권한 조회 성공", content = [
                            Content(
                                schema = Schema(implementation = AuthorityListByTypeResponse::class),
                                examples = [
                                    ExampleObject(
                                        value = """
                                        {
                                            "authorities": [
                                                {
                                                    "authorityId": "asdfasdf-asdfasdf",
                                                    "authorityName": "학생회"
                                                },
                                                {
                                                    "authorityId": "asdfasdf-asdfasdf",
                                                    "authorityName": "동아리장"
                                                }
                                            ]
                                        }
                                    """
                                    )
                                ]
                            )
                        ]
                    )
                ],
            )
        ),
        RouterOperation(
            path = "/authorities/access-management/basic/{userId}",
            method = [RequestMethod.DELETE],
            produces = [MediaType.APPLICATION_JSON_VALUE],
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            params = ["userId"],
            operation = Operation(
                operationId = "deleteBasicAuthorities",
                summary = "Delete Basic Authorities",
                description = "사용자가 가지고 있는 기본 권한을 삭제합니다. (보상 트랜잭션 API)",
                parameters = [
                    Parameter(
                        name = "userId",
                        description = "사용자 아이디",
                        `in` = ParameterIn.PATH,
                        allowEmptyValue = false,
                        required = true
                    )
                ],
                requestBody = RequestBody(content = []),
                responses = [ApiResponse(responseCode = "204", description = "삭제 성공")],
            )
        )
    )
    fun accessManagementRouters() = coRouter {
        "/authorities/access-management".nest {
            POST("/basic", accessManagementHandler::createUserBaseAccessManagement)
            GET("/{userId}", accessManagementHandler::handleGetAuthorityList)
            GET("/type/{userId}", accessManagementHandler::handleGetAuthorityListByType)
            DELETE("/basic/{userId}", accessManagementHandler::handleDeleteBaseAccessManagement)
        }
    }
}
