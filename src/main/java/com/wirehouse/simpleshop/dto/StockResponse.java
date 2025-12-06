package com.wirehouse.simpleshop.dto;

import lombok.Data;

@Data
public class StockResponse {
    private Long id;
    private Long variantId;
    private Integer quantity;
    private Boolean reserved;
}