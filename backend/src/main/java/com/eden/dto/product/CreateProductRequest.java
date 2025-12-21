package com.eden.dto.product;

import java.math.BigDecimal;

public record CreateProductRequest(String name, String description, BigDecimal price, int quantity, String category, String image) {
}
