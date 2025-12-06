package com.wirehouse.simpleshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Entity Stock buat nyimpen jumlah stok buat tiap variant
 * Gak boleh jual kalo stock abis
 */
@Entity
@Table(name = "stocks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id", nullable = false)
    @JsonBackReference  // Untuk cegah infinite loop di JSON
    private Variant variant;

    @Column(nullable = false)
    private Integer quantity = 0;

    @Column(nullable = false)
    private Boolean reserved = false;
}