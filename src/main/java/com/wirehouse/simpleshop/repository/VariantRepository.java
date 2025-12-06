package com.wirehouse.simpleshop.repository;

import com.wirehouse.simpleshop.model.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
    List<Variant> findByItemIdAndActiveTrue(Long itemId);
    List<Variant> findByActiveTrue();
}