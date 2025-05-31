package com.smartsolution.dreamshops.service.user;

import com.smartsolution.dreamshops.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
