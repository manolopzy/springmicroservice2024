package com.worldexplorers.happylearning.gateway.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * This example shows us how to write a pre global filter, it runs 
 * before any other filter.
 */
@Component
public class LoggingGlobalPreFilter implements GlobalFilter{
	private final Logger logger = LoggerFactory.getLogger(LoggingGlobalPreFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("Global Pre Filter request headers " + exchange.getRequest().getHeaders().toString());
		return chain.filter(exchange);
	}
}
