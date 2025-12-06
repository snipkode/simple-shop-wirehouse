package com.wirehouse.simpleshop.dto;

import lombok.Data;

@Data
public class StockRequest {
    private Long variantId;
    private Integer quantity;
}