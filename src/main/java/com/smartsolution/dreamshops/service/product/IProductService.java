package com.smartsolution.dreamshops.service.product;

import com.smartsolution.dreamshops.dto.ProductDto;
import com.smartsolution.dreamshops.model.Product;
import com.smartsolution.dreamshops.request.AddProductRequest;
import com.smartsolution.dreamshops.request.UpdateProductRequest;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Product addProduct (AddProductRequest product);
    Product getProductById (Long id);
    void deleteProductById (Long id);
    Product updateProduct (UpdateProductRequest product, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand (String brand);
    List<Product> getProductsByCategoryAndBrand (String category, String brand);
    List<Product> getProductsByName (String name);
    List<Product> getProductsByBrandAndName (String brand, String name);
    Long countProductsByBrandAndName (String brand, String name);

    List<ProductDto> getConvertProducts(List<Product> products);

    ProductDto convertToDto(Product product);
}
