package com.xquare.authorization.v1authorizationservice.configuration.exception

import com.xquare.authorization.domain.exception.BaseException
import com.xquare.authorization.v1authorizationservice.configuration.validate.BadRequestException
import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.core.annotation.Order
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.server.ServerWebInputException
import reactor.core.publisher.Mono

@Order(-2)
@Component
class ErrorWebExchangeHandler(
    errorAttributes: ErrorAttributes,
    webProperties: WebProperties,
    applicationContext: ApplicationContext,
    serverCodecConfigurer: ServerCodecConfigurer
) : AbstractErrorWebExceptionHandler(
    errorAttributes,
    webProperties.resources,
    applicationContext
) {

    init {
        super.setMessageReaders(serverCodecConfigurer.readers)
        super.setMessageWriters(serverCodecConfigurer.writers)
    }

    override fun getRoutingFunction(errorAttributes: ErrorAttributes?): RouterFunction<ServerResponse> =
        RouterFunctions.route(RequestPredicates.all(), this::handleError)

    private fun handleError(request: ServerRequest): Mono<ServerResponse> =
        when (val throwable = super.getError(request)) {
            is BaseException -> buildErrorResponse(throwable)
            is ServerWebInputException -> buildErrorResponse(BadRequestException("Missing Request Body"))
            is ResponseStatusException -> buildErrorResponse(RequestHandlerNotFoundException("${request.method()} ${request.path()} Handler Not Found"))
            else -> buildErrorResponse(InternalServerError(throwable.message))
        }

    private fun buildErrorResponse(exception: BaseException) =
        ServerResponse.status(exception.statusCode)
            .bodyValue(
                ErrorResponse(
                    errorMessage = exception.errorMessage,
                    statusCode = exception.statusCode
                )
            )
}
