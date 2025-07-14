package com.sushanth.dream_shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sushanth.dream_shop.dtos.cartItem.request.AddCartItemRequest;
import com.sushanth.dream_shop.response.ResponseApi;
import com.sushanth.dream_shop.services.CartItemService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/cartItem")
public class CartItemController {
    
    @Autowired
    private CartItemService cartItemService;

    @PostMapping("")
    public ResponseEntity<ResponseApi> addCartItem(@RequestBody AddCartItemRequest addCartItemRequest) {
        cartItemService.addCartItem(addCartItemRequest);
        return ResponseEntity.ok(new ResponseApi(null,"Add success!!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi> removeCartItem(@PathVariable Integer id) {
        cartItemService.removeCartItem(id);
        return ResponseEntity.ok(new ResponseApi(null,"Remove success!!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseApi> updateCartItemQuantity(@PathVariable Integer id,@RequestParam Integer quantity) {
        cartItemService.updateCartItemQuantity(id, quantity);
        return ResponseEntity.ok(new ResponseApi(null,"Update success!!"));
    }
}
