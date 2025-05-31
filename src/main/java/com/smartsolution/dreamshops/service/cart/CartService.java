package com.smartsolution.dreamshops.service.cart;

import com.smartsolution.dreamshops.dto.CartDto;
import com.smartsolution.dreamshops.exceptions.CartNotFoundException;
import com.smartsolution.dreamshops.model.Cart;
import com.smartsolution.dreamshops.model.User;
import com.smartsolution.dreamshops.repository.CartRepository;
import com.smartsolution.dreamshops.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with id: " + cartId));
    }

    @Override
    public void clearCart(Long cartId) {
        Cart cart = getCartById(cartId);
        User user = cart.getUser();
        user.setCart(null);
        userRepository.save(user);
    }

    @Override
    public BigDecimal getTotalPrice(Long cartId) {
        Cart cart = getCartById(cartId);
        return cart.getTotalAmount();
    }

    @Override
    public Cart initializeNewCart(User user) {
        return Optional.ofNullable(getCartByUserId(user.getId()))
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });
    }

    @Override
    public CartDto convertToDto(Cart cart) {
        return modelMapper.map(cart, CartDto.class);
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }
}
