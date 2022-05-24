package com.xquare.authorization.v1authorizationservice.configuration.debezium

class DebeziumMessage(
    val after: After
)

class After(
    val payload: String
)
