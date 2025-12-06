package com.wirehouse.simpleshop.repository;

import com.wirehouse.simpleshop.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository buat Item, handle query ke database
 * Make JPA buat auto-generate query
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // Get semua item yang aktif
    List<Item> findByActiveTrue();

    // Get item berdasarkan kategori
    List<Item> findByCategory(String category);

    // Get item sama variants-nya (buat gak ada N+1 problem)
    @Query("SELECT i FROM Item i LEFT JOIN FETCH i.variants WHERE i.id = :id")
    Optional<Item> findByIdWithVariants(Long id);

    // Get semua item aktif + variants-nya
    @Query("SELECT i FROM Item i LEFT JOIN FETCH i.variants WHERE i.active = true")
    List<Item> findAllActiveWithVariants();
}