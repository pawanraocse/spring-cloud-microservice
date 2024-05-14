package com.algo.microservices.inventoryservice.repository;

import com.algo.microservices.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM Inventory i WHERE i.sku_code = :skuCode AND i.quantity >= :quantity")
    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(@Param("skuCode") String skuCode, @Param("quantity") Integer quantity);
}
