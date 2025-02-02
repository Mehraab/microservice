package com.example.gateway.config;

import com.example.gateway.filter.AddJwtTokenFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public GatewayFilter addJwtTokenFilter() {
        return new AddJwtTokenFilter();
    }
}
