package com.wirehouse.simpleshop.dto;

import lombok.Data;

@Data
public class VariantRequest {
    private String name;
    private Double price;
    private Integer initialStock;
    private Boolean active;
}