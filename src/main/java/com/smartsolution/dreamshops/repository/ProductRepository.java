package com.smartsolution.dreamshops.repository;

import com.smartsolution.dreamshops.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<List<Product>> findByCategoryName (String category);

    Optional<List<Product>> findByBrand(String brand);

    Optional<List<Product>> findByCategoryNameAndBrand(String category, String brand);

    Optional<List<Product>> findByName(String name);

    Optional<List<Product>> findByBrandAndName(String brand, String name);

    Long countByBrandAndName(String brand, String name);
}
