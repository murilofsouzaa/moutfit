package com.moutfit.dto.product;

import java.math.BigDecimal;

public record ProductRequestDTO(Long id, String name, String description, BigDecimal price) {
}
