package com.eden.dto.product;

import java.math.BigDecimal;

public record ProductResponse(Integer id, String name, String description, BigDecimal price, String category, String image, int quantity, boolean status) {
}
