package com.sushanth.dream_shop.exceptions;

public class CartNotFoundException extends RuntimeException  {
    public CartNotFoundException(String message){
        super(message);
    }
}
