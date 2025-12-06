package com.wirehouse.simpleshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Entity Variant buat nyimpen variasi dari item
 * Misalnya: Baju bisa ada ukuran S, M, L atau warna Merah, Biru
 */
@Entity
@Table(name = "variants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Variant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    @JsonBackReference  // Untuk cegah infinite loop di JSON
    private Item item;

    @Column(nullable = false)
    private String name;  // misalnya: "Merah", "Ukuran L", dll

    @Column(nullable = false)
    private Double price;

    @OneToOne(mappedBy = "variant", cascade = CascadeType.ALL)
    @JsonManagedReference  // Untuk cegah infinite loop di JSON
    private Stock stock;

    @Column(nullable = false)
    private Boolean active = true;
}