package com.smartsolution.dreamshops.service.cart;

import com.smartsolution.dreamshops.dto.CartDto;
import com.smartsolution.dreamshops.model.Cart;

import java.math.BigDecimal;

public interface ICartService {

    Cart getCartById(Long cartId);
    void clearCart(Long cartId);
    BigDecimal getTotalPrice(Long cartId);

    Long initializeNewCart();

    CartDto convertToDto(Cart cart);
}
