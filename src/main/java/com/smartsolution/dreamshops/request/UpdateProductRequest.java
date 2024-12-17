package com.smartsolution.dreamshops.request;

import com.smartsolution.dreamshops.model.Category;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateProductRequest {
    private Long id;

    @NotBlank(message = "Product name cannot be empty.")
    private String name;

    @NotBlank(message = "Brand cannot be empty.")
    private String brand;

    @NotNull(message = "Price cannot be null.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero.")
    private BigDecimal price;

    @NotNull(message = "Inventory cannot be null.")
    @Min(value = 0, message = "Inventory must be zero or greater.")
    private Integer inventory;

    private String description;

    private Category category;
}
