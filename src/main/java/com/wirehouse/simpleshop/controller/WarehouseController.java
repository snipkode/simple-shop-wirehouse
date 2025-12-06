package com.wirehouse.simpleshop.controller;

import com.wirehouse.simpleshop.dto.*;
import com.wirehouse.simpleshop.service.WarehouseService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
@RequiredArgsConstructor
public class WarehouseController {

    final WarehouseService warehouseService;

    @PostMapping("/items")
    public ResponseEntity<ItemResponse> createItem(@RequestBody ItemRequest itemRequest) {
        ItemResponse response = warehouseService.createItem(itemRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemResponse>> getAllItems() {
        List<ItemResponse> response = warehouseService.getAllItems();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<ItemResponse> getItemById(@PathVariable Long id) {
        ItemResponse response = warehouseService.getItemById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<ItemResponse> updateItem(@PathVariable Long id,
        @RequestBody ItemRequest itemRequest) {
        ItemResponse response = warehouseService.updateItem(id, itemRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteItem( @PathVariable Long id) {
        warehouseService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/items/{itemId}/variants")
    public ResponseEntity<VariantResponse> createVariantForItem( @PathVariable Long itemId,
        @RequestBody VariantRequest variantRequest) {
        VariantResponse response = warehouseService.createVariantForItem(itemId, variantRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/items/{itemId}/variants")
    public ResponseEntity<List<VariantResponse>> getVariantsByItem(@PathVariable Long itemId) {
        List<VariantResponse> response = warehouseService.getVariantsByItem(itemId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/stocks/{variantId}")
    public ResponseEntity<StockResponse> updateStock(@PathVariable Long variantId,
        @RequestBody StockRequest stockRequest) {
        StockResponse response = warehouseService.updateStock(variantId, stockRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/stocks/{variantId}")
    public ResponseEntity<StockResponse> getStockByVariant( @PathVariable Long variantId) {
        StockResponse response = warehouseService.getStockByVariant(variantId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/stocks/{variantId}/reserve")
    public ResponseEntity<Boolean> reserveStock( @PathVariable Long variantId, @RequestParam Integer quantity) {
        Boolean success = warehouseService.reserveStock(variantId, quantity);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @PutMapping("/stocks/{variantId}/quantity")
    public ResponseEntity<Boolean> updateStockQuantity( @PathVariable Long variantId,@RequestParam Integer quantityChange) {
        Boolean success = warehouseService.updateStockQuantity(variantId, quantityChange);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }
}