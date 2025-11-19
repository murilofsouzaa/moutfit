package com.moutfit.dto.product;

import java.math.BigDecimal;

public record ProductUpdateDTO(String name, String description, BigDecimal price, String category, String image, boolean status) {
}
