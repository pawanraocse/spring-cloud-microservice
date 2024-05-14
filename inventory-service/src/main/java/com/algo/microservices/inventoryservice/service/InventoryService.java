package com.algo.microservices.inventoryservice.service;

import com.algo.microservices.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

     public boolean isInStock(String sku_code, Integer quantity) {
         return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(sku_code, quantity);
     }
}
