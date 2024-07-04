package com.worldexplorers.happylearning.gateway.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;

/**
 * This is an example showing us how to write a global post filter which 
 * will be executed after all other filters.
 */
@Configuration
public class LoggingGlobalPostFilter {

	private final Logger logger = LoggerFactory.getLogger(
			LoggingGlobalPostFilter.class);

	    @Bean
	    public GlobalFilter postGlobalFilter() {
	        return (exchange, chain) -> {
	            return chain.filter(exchange)
	              .then(Mono.fromRunnable(() -> {
	                  logger.info("The request has been filtered.");
	              }));
	        };
	    }
}
