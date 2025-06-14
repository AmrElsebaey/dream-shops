package com.smartsolution.dreamshops.repository;

import com.smartsolution.dreamshops.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByUserId(Long userId);

}
