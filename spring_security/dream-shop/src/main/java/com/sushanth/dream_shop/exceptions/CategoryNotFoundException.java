package com.sushanth.dream_shop.exceptions;

public class CategoryNotFoundException extends RuntimeException  {
    public CategoryNotFoundException(String message){
        super(message);
    }
}
