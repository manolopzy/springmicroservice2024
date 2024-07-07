package com.worldexplorers.happylearning.gateway.authorization.config;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.util.UriUtils;

import reactor.core.publisher.Mono;

@Component
public class UrlDecoderFilter implements WebFilter{
	@Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String originalUrl = exchange.getRequest().getURI().toString();
        String decodedUrl = UriUtils.decode(originalUrl, "UTF-8");
        System.out.println("decoded url from js = " + decodedUrl);
        exchange.getAttributes().put("decodedUrl", decodedUrl);
        return chain.filter(exchange);
    }
}
