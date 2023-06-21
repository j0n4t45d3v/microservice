package com.microservice.orderservice.dto;

public record RequestRequested (
    String nameProduct,
    Integer quantityRequested
){

}
