package com.smartsolution.dreamshops.controller;

import com.smartsolution.dreamshops.exceptions.CartNotFoundException;
import com.smartsolution.dreamshops.model.Cart;
import com.smartsolution.dreamshops.model.User;
import com.smartsolution.dreamshops.response.ApiResponse;
import com.smartsolution.dreamshops.service.cart.ICartItemService;
import com.smartsolution.dreamshops.service.cart.ICartService;
import com.smartsolution.dreamshops.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart-items")
public class CartItemController {

    private final ICartItemService cartItemService;
    private final ICartService cartService;
    private final IUserService userService;

    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestParam Long productId,
                                                     @RequestParam int quantity) {
        try {
            User user = userService.findUserById(1L);
            Cart cart = cartService.initializeNewCart(user);

            cartItemService.addItemToCart(cart.getId(), productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Item added to cart successfully", null));
        } catch (CartNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/item/remove")
    public ResponseEntity<ApiResponse> removeItemFromCart(@RequestParam Long cartId,
                                                          @RequestParam Long productId) {
        try {
            cartItemService.removeItemFromCart(cartId, productId);
            return ResponseEntity.ok(new ApiResponse("Item removed from cart successfully", null));
        } catch (CartNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PatchMapping("/item/update")
    public ResponseEntity<ApiResponse> updateItemQuantity(@RequestParam Long cartId,
                                                          @RequestParam Long productId,
                                                          @RequestParam int quantity) {
        try {
            cartItemService.updateItemQuantity(cartId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Item quantity updated successfully", null));
        } catch (CartNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
