package com.smartsolution.dreamshops.controller;

import com.smartsolution.dreamshops.dto.ProductDto;
import com.smartsolution.dreamshops.exceptions.ProductNotFoundException;
import com.smartsolution.dreamshops.model.Product;
import com.smartsolution.dreamshops.request.AddProductRequest;
import com.smartsolution.dreamshops.request.UpdateProductRequest;
import com.smartsolution.dreamshops.response.ApiResponse;
import com.smartsolution.dreamshops.service.product.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse("No products found", null));
        }
        List<ProductDto> productDtos = productService.getConvertProducts(products);
            return ResponseEntity.ok(new ApiResponse("Products retrieved successfully", productDtos));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            ProductDto productDto = productService.convertToDto(product);
            return ResponseEntity.ok(
                    new ApiResponse("Product fetched successfully", productDto)
            );
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error fetching product", e.getMessage()));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody @Valid AddProductRequest request) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse("Product added successfully", productService.addProduct(request))
            );
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error adding product", e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody @Valid UpdateProductRequest request, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse("Product updated successfully", productService.updateProduct(request, id))
            );
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error updating product", e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProductById(id);
            return ResponseEntity.ok(new ApiResponse("Product deleted successfully", null));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error deleting product", e.getMessage()));
        }
    }

    @GetMapping("/by-brand/and-name")
    public ResponseEntity<ApiResponse> getProductsByBrandAndName(
            @RequestParam String brand,
            @RequestParam String name
    ) {
        try {
            List<Product> products = productService.getProductsByBrandAndName(brand, name);
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No products found for the given brand and name", null));
            }
            List<ProductDto> productDtos = productService.getConvertProducts(products);
            return ResponseEntity.ok(
                    new ApiResponse("Products fetched successfully", productDtos)
            );
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error fetching products", e.getMessage()));
        }
    }

    @GetMapping("/by-category/and-brand")
    public ResponseEntity<ApiResponse> getProductsByCategoryAndBrand(
            @RequestParam String category,
            @RequestParam String brand
    ) {
        try {
            List<Product> products = productService.getProductsByCategoryAndBrand(category, brand);
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No products found for the given category and brand", null));
            }
            List<ProductDto> productDtos = productService.getConvertProducts(products);
            return ResponseEntity.ok(
                    new ApiResponse("Products fetched successfully", productDtos)
            );
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error fetching products", e.getMessage()));
        }
    }

    @GetMapping("/by-brand")
    public ResponseEntity<ApiResponse> getProductsByBrand(
            @RequestParam String brand
    ) {
        try {
            List<Product> products = productService.getProductsByBrand(brand);
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No products found for the given brand", null));
            }
            List<ProductDto> productDtos = productService.getConvertProducts(products);
            return ResponseEntity.ok(
                    new ApiResponse("Products fetched successfully", productDtos)
            );
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error fetching products", e.getMessage()));
        }
    }

    @GetMapping("/by-category")
    public ResponseEntity<ApiResponse> getProductsByCategory(
            @RequestParam String category
    ) {
        try {
            List<Product> products = productService.getProductsByCategory(category);
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No products found for the given category", null));
            }
            List<ProductDto> productDtos = productService.getConvertProducts(products);
            return ResponseEntity.ok(
                    new ApiResponse("Products fetched successfully", productDtos)
            );
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error fetching products", e.getMessage()));
        }
    }

    @GetMapping("/by-name")
    public ResponseEntity<ApiResponse> getProductsByName(
            @RequestParam String name
    ) {
        try {
            List<Product> products = productService.getProductsByName(name);
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No products found for the given name", null));
            }
            List<ProductDto> productDtos = productService.getConvertProducts(products);
            return ResponseEntity.ok(
                    new ApiResponse("Products fetched successfully", productDtos)
            );
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error fetching products", e.getMessage()));
        }
    }

    @GetMapping("/count/by-brand/and-name")
    public ResponseEntity<ApiResponse> countProductsByBrandAndName(
            @RequestParam String brand,
            @RequestParam String name
    ) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse("Product count fetched successfully", productService.countProductsByBrandAndName(brand, name))
            );
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error fetching product count", e.getMessage()));
        }
    }

}
