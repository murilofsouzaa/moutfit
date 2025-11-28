package com.eden.dto.product;

import java.math.BigDecimal;

public record ProductPostDTO(String name, String description, BigDecimal price, int quantity) {
}
