package com.market_place.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDto {
    private long id;

    @NotBlank(message = "Product name not be empty")
    @Size(min = 3, max = 50, message = "Product name should be 3 more symbols")
    private String productName;

    @NotNull(message = "Product price not be empty")
    @Positive(message = "Price should be more than 0")
    private float price;
}
