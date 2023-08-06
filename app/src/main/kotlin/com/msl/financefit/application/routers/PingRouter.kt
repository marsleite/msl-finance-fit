package com.msl.financefit.application.routers

import com.msl.financefit.application.handlers.PingHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

private const val PATH = "/financefit"

@Configuration
class PingRouter {

  @Bean
  fun getPingPong(pingHandler: PingHandler) = coRouter {
    GET("$PATH/ping", pingHandler::getPong)
  }
}