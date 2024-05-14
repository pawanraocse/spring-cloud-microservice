package com.algo.microservices.orderservice.dto;

public record OrderRequest(Long id,
                           String orderNumber,
                           String skuCode,
                           Double price,
                           Integer quantity) {

}
