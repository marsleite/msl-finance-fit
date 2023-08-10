package com.msl.financefit.application.handlers

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

private const val PONG = "pong"

@Component
class PingHandler {

  suspend fun getPong(request: ServerRequest): ServerResponse {
    return ServerResponse.ok().bodyValueAndAwait(PONG)
  }
}