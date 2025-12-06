package com.wirehouse.simpleshop.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

/**
 * Entity Item buat nyimpen data item di warehouse
 * Misalnya: Baju, Celana, dll
 */
@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    // Relasi ke variants (banyak variants bisa punya 1 item)
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference  // Untuk cegah infinite loop di JSON
    private List<Variant> variants;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Boolean active = true;

    // Helper method buat maintain bidirectional relationship
    public void addVariant(Variant variant) {
        if (variants == null) {
            variants = new java.util.ArrayList<>();
        }
        variants.add(variant);
        variant.setItem(this);
    }
}