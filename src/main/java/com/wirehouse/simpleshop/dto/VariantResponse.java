package com.wirehouse.simpleshop.dto;

import com.wirehouse.simpleshop.model.Stock;
import lombok.Data;

@Data
public class VariantResponse {
    private Long id;
    private String name;
    private Double price;
    private Boolean active;
    private Stock stock;
}
