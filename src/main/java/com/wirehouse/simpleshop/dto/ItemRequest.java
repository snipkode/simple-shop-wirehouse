package com.wirehouse.simpleshop.dto;

import lombok.*;
import java.util.List;

/**
 * DTO buat request bikin/update item
 * Dipake pas POST/PUT ke endpoint item
 */
@Data
public class ItemRequest {
    private String name;         // Nama item
    private String description;  // Deskripsi item
    private String category;     // Kategori item (misal: baju, celana, etc)
    private Boolean active;      // Status aktif item
    private List<VariantRequest> variants; // List variant buat item ini
}