package com.sushanth.dream_shop.exceptions;

public class CartItemNotFoundException extends RuntimeException  {
    public CartItemNotFoundException(String message){
        super(message);
    }
}
