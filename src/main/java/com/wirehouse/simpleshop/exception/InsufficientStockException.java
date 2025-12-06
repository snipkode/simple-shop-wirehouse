package com.wirehouse.simpleshop.exception;

/**
 * Exception buat kasus stock kurang
 * Dipake kalo yang diminta lebih dari stok yang tersedia
 */
public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
        super(message);
    }
}