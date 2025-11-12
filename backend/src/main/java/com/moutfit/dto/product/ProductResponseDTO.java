package com.moutfit.dto.product;

import java.math.BigDecimal;

public record ProductResponseDTO(Integer id,String name, String description, BigDecimal price) {
}
