package com.wirehouse.simpleshop.repository;

import com.wirehouse.simpleshop.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findByVariantId(Long variantId);
}