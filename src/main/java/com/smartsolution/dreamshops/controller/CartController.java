package com.smartsolution.dreamshops.controller;

import com.smartsolution.dreamshops.dto.CartDto;
import com.smartsolution.dreamshops.exceptions.CartNotFoundException;
import com.smartsolution.dreamshops.model.Cart;
import com.smartsolution.dreamshops.response.ApiResponse;
import com.smartsolution.dreamshops.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private final ICartService cartService;

    @GetMapping("/cart/{cartId}")
    public ResponseEntity<ApiResponse> getCartById(@PathVariable Long cartId) {
        try {
            Cart cart = cartService.getCartById(cartId);
            CartDto cartDto = cartService.convertToDto(cart);
            return ResponseEntity.ok(new ApiResponse("Cart retrieved successfully", cartDto));
        } catch (CartNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/cart/{cartId}/clear")
    public ResponseEntity<ApiResponse> clearCart(@PathVariable Long cartId) {
        try {
            cartService.clearCart(cartId);
            return ResponseEntity.ok(new ApiResponse("Cart cleared successfully", null));
        } catch (CartNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/cart/{cartId}/total-price")
    public ResponseEntity<ApiResponse> getTotalPrice(@PathVariable Long cartId) {
        try {
            BigDecimal totalPrice = cartService.getTotalPrice(cartId);
            return ResponseEntity.ok(new ApiResponse("Total price retrieved successfully", totalPrice));
        } catch (CartNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
