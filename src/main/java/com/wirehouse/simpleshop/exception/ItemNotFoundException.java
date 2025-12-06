package com.wirehouse.simpleshop.exception;

/**
 * Exception buat kasus item gak ketemu
 * Dipake kalo search item tp gak ketemu
 */
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}