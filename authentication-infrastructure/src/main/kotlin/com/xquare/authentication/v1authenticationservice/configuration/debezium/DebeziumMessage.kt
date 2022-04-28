package com.xquare.authentication.v1authenticationservice.configuration.debezium

class DebeziumMessage(
    val after: After
)

class After(
    val payload: String
)
