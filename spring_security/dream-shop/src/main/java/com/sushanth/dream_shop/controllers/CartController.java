package com.sushanth.dream_shop.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sushanth.dream_shop.response.ResponseApi;
import com.sushanth.dream_shop.services.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi> getCart(@PathVariable Integer id) {
        return ResponseEntity.ok(new ResponseApi(cartService.getCart(id), "Found!!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi> clearCart(@PathVariable Integer id) {
        cartService.clearCart(id);
        return ResponseEntity.ok(new ResponseApi(null, "Deleted!!"));
    }

}
