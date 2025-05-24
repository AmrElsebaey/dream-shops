package com.smartsolution.dreamshops.controller;

import com.smartsolution.dreamshops.exceptions.ProductNotFoundException;
import com.smartsolution.dreamshops.request.AddProductRequest;
import com.smartsolution.dreamshops.request.UpdateProductRequest;
import com.smartsolution.dreamshops.response.ApiResponse;
import com.smartsolution.dreamshops.service.product.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @PostMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
            return ResponseEntity.ok(new ApiResponse("Products retrieved successfully", productService.getAllProducts()));
    }

    @GetMapping("/product")
    public ResponseEntity<ApiResponse> getProductById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse("Product fetched successfully", productService.getProductById(id))
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

    @GetMapping
    public ResponseEntity<ApiResponse> getProductsByBrandAndName(
            @RequestParam String brand,
            @RequestParam String name
    ) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse("Products fetched successfully", productService.getProductsByBrandAndName(brand, name))
            );
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error fetching products", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getProductsByCategoryAndBrand(
            @RequestParam String category,
            @RequestParam String brand
    ) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse("Products fetched successfully", productService.getProductsByCategoryAndBrand(category, brand))
            );
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error fetching products", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getProductsByBrand(
            @RequestParam String brand
    ) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse("Products fetched successfully", productService.getProductsByBrand(brand))
            );
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error fetching products", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getProductsByCategory(
            @RequestParam String category
    ) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse("Products fetched successfully", productService.getProductsByCategory(category))
            );
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error fetching products", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getProductsByName(
            @RequestParam String name
    ) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse("Products fetched successfully", productService.getProductsByName(name))
            );
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error fetching products", e.getMessage()));
        }
    }

}
