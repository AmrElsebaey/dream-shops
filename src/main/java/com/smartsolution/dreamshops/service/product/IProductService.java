package com.smartsolution.dreamshops.service.product;

import com.smartsolution.dreamshops.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Product saveProduct (Product product);
    Product getProductById (Long id);
    void deleteProductById (Long id);
    void updateProduct (Product product, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand (String brand);
    List<Product> getProductsByCategoryAndBrand (String category, String brand);
    List<Product> getProductsByName (String name);
    List<Product> getProductsByBrandAndName (String brand, String name);
    Long countProductsByBrandAndName (String brand, String name);

}