package com.sushanth.dream_shop.exceptions;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException(String message){
        super(message);
    }
}
