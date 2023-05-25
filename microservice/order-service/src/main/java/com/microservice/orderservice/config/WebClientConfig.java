package com.microservice.orderservice.config;

import com.microservice.orderservice.client.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;

    @Bean
    public WebClient webClientBuilder(){
        return WebClient.builder()
                .baseUrl("http://product-service")
                .filter(filterFunction)
                .build();
    }

    @Bean
    public ProductClient employeeClient(){
        HttpServiceProxyFactory proxyFactory
                = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClientBuilder()))
                .build();

        return proxyFactory.createClient(ProductClient.class);
    }

}
