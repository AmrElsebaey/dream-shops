package com.smartsolution.dreamshops.service.product;

import com.smartsolution.dreamshops.dto.ImageDto;
import com.smartsolution.dreamshops.dto.ProductDto;
import com.smartsolution.dreamshops.exceptions.CategoryNotFoundException;
import com.smartsolution.dreamshops.exceptions.ProductNotFoundException;
import com.smartsolution.dreamshops.model.Category;
import com.smartsolution.dreamshops.model.Image;
import com.smartsolution.dreamshops.model.Product;
import com.smartsolution.dreamshops.repository.CategoryRepository;
import com.smartsolution.dreamshops.repository.ImageRepository;
import com.smartsolution.dreamshops.repository.ProductRepository;
import com.smartsolution.dreamshops.request.AddProductRequest;
import com.smartsolution.dreamshops.request.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ImageRepository imageRepository;

    @Override
    public Product addProduct(AddProductRequest request) {
        Category category = categoryRepository.findByName(request.getCategory().getName()).
                orElseGet( () -> {
                    Category newCategory= new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        request.setCategory(category);
        return productRepository.save(createProduct(request, category));
    }

    private Product createProduct(AddProductRequest request, Category category) {
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category
        );
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product not found!"));
    }

    @Override
    public void deleteProductById(Long id) {
    productRepository.findById(id)
            .ifPresentOrElse(productRepository::delete,
                    () -> {
                        throw new ProductNotFoundException("Product not found!");
                    });
    }

    @Override
    public Product updateProduct(UpdateProductRequest request, Long productId) {
        Product existingProduct = getProductById(productId);
        return productRepository.save(updateExistingProduct(existingProduct, request));
    }

    private Product updateExistingProduct(Product existingProduct, UpdateProductRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());
        Category category= categoryRepository.findByName(request.getCategory().getName())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found: " + request.getCategory().getName()));
        existingProduct.setCategory(category);
        return existingProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

    @Override
    public List<ProductDto> getConvertProducts(List<Product> products) {
        return products.stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public ProductDto convertToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }
}
