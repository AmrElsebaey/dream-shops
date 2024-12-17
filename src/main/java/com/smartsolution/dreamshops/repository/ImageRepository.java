package com.smartsolution.dreamshops.repository;

import com.smartsolution.dreamshops.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
