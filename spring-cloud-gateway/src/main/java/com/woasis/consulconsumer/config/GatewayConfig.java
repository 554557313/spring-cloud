package com.woasis.consulconsumer.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig {
	
	@Bean
	public KeyResolver hostAddrKeyResolver() {
	    return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
	}
	
	@Bean
	public KeyResolver userKeyResolver() {
	    return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
	}
	
	@Bean
	KeyResolver apiKeyResolver() {
	    return exchange -> Mono.just(exchange.getRequest().getPath().value());
	}

}
