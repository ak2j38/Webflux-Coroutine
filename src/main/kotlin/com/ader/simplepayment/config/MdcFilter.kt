package com.ader.simplepayment.config

import org.slf4j.MDC
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import java.util.UUID

@Component
@Order(1)
class MdcFilter: WebFilter {
  override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
    val uuid = "${UUID.randomUUID()}"
    MDC.put("txId", uuid)
    return chain.filter(exchange)
  }
}