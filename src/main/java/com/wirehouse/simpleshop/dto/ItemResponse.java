package com.wirehouse.simpleshop.dto;

import com.wirehouse.simpleshop.model.Variant;
import lombok.Data;
import java.util.List;

@Data
public class ItemResponse {
    private Long id;
    private String name;
    private String description;
    private String category;
    private Boolean active;
    private List<Variant> variants;

    // Explicit getter to ensure Lombok generates it correctly
    public List<Variant> getVariants() {
        return variants;
    }
}