package com.moutfit.dto.product;

import java.math.BigDecimal;

public record ProductRequestDTO(Integer id, String name, String description, BigDecimal price) {
}
