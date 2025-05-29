package com.smartsolution.dreamshops.service.cart;

import com.smartsolution.dreamshops.exceptions.CartNotFoundException;
import com.smartsolution.dreamshops.model.Cart;
import com.smartsolution.dreamshops.repository.CartItemRepository;
import com.smartsolution.dreamshops.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public Cart getCartById(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with id: " + cartId));
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cartRepository.save(cart);
    }

    @Override
    public void clearCart(Long cartId) {
        Cart cart = getCartById(cartId);
        cartItemRepository.deleteAllByCartId(cartId);
        cart.getItems().clear();
        cartRepository.deleteById(cartId);
    }

    @Override
    public BigDecimal getTotalPrice(Long cartId) {
        Cart cart = getCartById(cartId);
        return cart.getTotalAmount();
    }
}
