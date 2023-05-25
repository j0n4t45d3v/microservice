package com.microservice.orderservice.client;

import com.microservice.orderservice.dto.ProductDto;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface ProductClient {

    @GetExchange("/product")
    List<ProductDto> getProduct();

}
