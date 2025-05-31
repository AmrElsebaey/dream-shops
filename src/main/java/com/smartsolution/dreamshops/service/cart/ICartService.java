package com.smartsolution.dreamshops.service.cart;

import com.smartsolution.dreamshops.dto.CartDto;
import com.smartsolution.dreamshops.model.Cart;
import com.smartsolution.dreamshops.model.User;

import java.math.BigDecimal;

public interface ICartService {

    Cart getCartById(Long cartId);
    void clearCart(Long cartId);
    BigDecimal getTotalPrice(Long cartId);

    Cart initializeNewCart(User user);

    CartDto convertToDto(Cart cart);

    Cart getCartByUserId(Long userId);
}
