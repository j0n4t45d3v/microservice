package com.example.microserviceorder.config;

import com.example.microserviceorder.client.ClientProduct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient clientConfig() {
        return WebClient.builder()
                .baseUrl("http://localhost:8083")
                .build();
    }

    @Bean
    public ClientProduct clientProduct(WebClient webClient) {
        HttpServiceProxyFactory factory
                = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(clientConfig()))
                .build();

        return factory.createClient(ClientProduct.class);
    }

}
