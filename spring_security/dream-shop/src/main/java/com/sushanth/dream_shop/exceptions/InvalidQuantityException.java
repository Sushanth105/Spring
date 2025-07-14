package com.sushanth.dream_shop.exceptions;

public class InvalidQuantityException extends RuntimeException  {
    public InvalidQuantityException(String message){
        super(message);
    }
}
