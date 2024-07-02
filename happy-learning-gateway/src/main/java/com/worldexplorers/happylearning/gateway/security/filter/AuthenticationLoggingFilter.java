package com.worldexplorers.happylearning.gateway.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;
/**
 * This filter will be added automatically to the web flux filter chain
 */
@Component
public class AuthenticationLoggingFilter implements WebFilter {

//	public void doFilter(ServletRequest request, ServletResponse response, WebFilterChain filterChain) {
//		
//	}
	private final Logger logger = LoggerFactory.getLogger(AuthenticationLoggingFilter.class);
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		//exchange.getResponse().getHeaders().add("web-filter", "web-filter-test");
		
		logger.info("request = ", exchange.getRequest());
		logger.info("request = ", exchange.getAttributes());
		return chain.filter(exchange);
	}

}
