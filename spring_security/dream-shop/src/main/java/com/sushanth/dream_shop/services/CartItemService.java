package com.sushanth.dream_shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sushanth.dream_shop.Repositories.CartItemRepository;
import com.sushanth.dream_shop.Repositories.ProductRepository;
import com.sushanth.dream_shop.dtos.cartItem.request.AddCartItemRequest;
import com.sushanth.dream_shop.exceptions.CartItemNotFoundException;
import com.sushanth.dream_shop.exceptions.InsufficientInventoryException;
import com.sushanth.dream_shop.exceptions.InvalidQuantityException;
import com.sushanth.dream_shop.exceptions.ProductNotFoundException;
import com.sushanth.dream_shop.models.CartItem;
import com.sushanth.dream_shop.models.Product;
import com.sushanth.dream_shop.services.mappers.CartItemMapper;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemMapper cartItemMapper;

    public void addCartItem(AddCartItemRequest req) {
        Product product = productRepository.findById(req.productId())
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + req.productId() + " not found"));
        if (req.quantity() <= product.getInventory()) {
            cartItemRepository.findByCartIdAndProductId(req.cartId(), req.productId())
                    .ifPresentOrElse((cl) -> {
                        cl.setQuantity(cl.getQuantity() + req.quantity());
                        cl.updateTotalPrice();
                        cartItemRepository.save(cl);
                    }, () -> {
                        cartItemRepository.save(cartItemMapper.addCartItemRequestToCartItem(req));
                    });
        } else {
            throw new InsufficientInventoryException(
                    "There is only " + product.getInventory() + " " + product.getName() + " present.");
        }
    }

    public void removeCartItem(Integer id) {
        cartItemRepository.findById(id).ifPresentOrElse(cartItemRepository::delete, () -> {
            throw new CartItemNotFoundException("Cart Item with id " + id + " not Found!");
        });
    }

    public void updateCartItemQuantity(Integer id, Integer quantity) {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity must be greater than zero");
        }
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new CartItemNotFoundException("Cart Item with id " + id + " not Found!"));
        Product product = cartItem.getProduct();
        if (quantity <= product.getInventory()) {
            cartItem.setQuantity(quantity);
            cartItem.updateTotalPrice();
            cartItemRepository.save(cartItem);
        } else {
            throw new InsufficientInventoryException(
                    "There is only " + product.getInventory() + " " + product.getName() + " present.");
        }
    }
}
