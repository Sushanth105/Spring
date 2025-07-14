package com.sushanth.dream_shop.services.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sushanth.dream_shop.Repositories.CartRepository;
import com.sushanth.dream_shop.Repositories.ProductRepository;
import com.sushanth.dream_shop.dtos.cartItem.request.AddCartItemRequest;
import com.sushanth.dream_shop.dtos.cartItem.response.CartItemResponse;
import com.sushanth.dream_shop.exceptions.CartNotFoundException;
import com.sushanth.dream_shop.exceptions.ProductNotFoundException;
import com.sushanth.dream_shop.models.Cart;
import com.sushanth.dream_shop.models.CartItem;
import com.sushanth.dream_shop.models.Product;

@Service
public class CartItemMapper {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    public CartItemResponse cartItemToCartItemResponse(CartItem cartItem) {
        return new CartItemResponse(
                cartItem.getId(),
                cartItem.getQuantity(),
                cartItem.getUnitPrice(),
                cartItem.getTotalPrice(),
                productMapper.productToProductResponse(cartItem.getProduct()),
                cartItem.getCart().getId());
    }

    public CartItem addCartItemRequestToCartItem(AddCartItemRequest req) {
        Product product = productRepository.findById(req.productId())
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product with id " + req.productId() + " not found"));

        Cart cart = cartRepository.findById(req.cartId())
                .orElseThrow(() -> new CartNotFoundException(
                        "Cart with id " + req.cartId() + " not found"));

        CartItem cartItem = new CartItem(
                req.quantity(),
                product.getPrice(),
                product,
                cart);
        cartItem.updateTotalPrice();

        return cartItem;
    }
}
