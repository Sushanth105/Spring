package com.sushanth.dream_shop.exceptions.handler;

import com.sushanth.dream_shop.exceptions.CartItemNotFoundException;
import com.sushanth.dream_shop.exceptions.CartNotFoundException;
import com.sushanth.dream_shop.exceptions.CategoryAlreadyExistsException;
import com.sushanth.dream_shop.exceptions.CategoryNotFoundException;
import com.sushanth.dream_shop.exceptions.ImageNotFoundException;
import com.sushanth.dream_shop.exceptions.InsufficientInventoryException;
import com.sushanth.dream_shop.exceptions.InvalidQuantityException;
import com.sushanth.dream_shop.exceptions.ProductNotFoundException;
import com.sushanth.dream_shop.response.ResponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ResponseApi> handleProductNotFound(ProductNotFoundException ex) {
        return new ResponseEntity<>(new ResponseApi(null, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<ResponseApi> handleImageNotFound(ImageNotFoundException ex) {
        return new ResponseEntity<>(new ResponseApi(null, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ResponseApi> handleCategoryAlreadyExist(CategoryAlreadyExistsException ex) {
        return new ResponseEntity<>(new ResponseApi(null, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ResponseApi> handleCategoryNotFound(CategoryNotFoundException ex) {
        return new ResponseEntity<>(new ResponseApi(null, ex.getMessage()), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ResponseApi> handleCartNotFound(CartNotFoundException ex){
        return new ResponseEntity<>(new ResponseApi(null, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<ResponseApi> handleCartItemNotFound(CartItemNotFoundException ex) {
        return new ResponseEntity<>(new ResponseApi(null, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity<ResponseApi> handleInvalidQuantity(InvalidQuantityException ex) {
        return new ResponseEntity<>(new ResponseApi(null, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientInventoryException.class)
    public ResponseEntity<ResponseApi> handleInsufficientInventory(InsufficientInventoryException ex) {
        return new ResponseEntity<>(new ResponseApi(null, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseApi> handleGeneralException(Exception ex) {
        return new ResponseEntity<>(new ResponseApi(null, "Unexpected error: " + ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

