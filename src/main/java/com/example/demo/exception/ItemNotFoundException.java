package com.example.demo.exception;

public class ItemNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ItemNotFoundException(Long itemId) {
        super("Item id not found : " + itemId);
    }
}
