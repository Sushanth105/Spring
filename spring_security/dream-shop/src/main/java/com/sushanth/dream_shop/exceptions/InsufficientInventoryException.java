package com.sushanth.dream_shop.exceptions;

public class InsufficientInventoryException extends RuntimeException {
    public InsufficientInventoryException(String message){
        super(message);
    }
}
