package com.moutfit.dto.product;

import java.math.BigDecimal;

public record ProductUpdate(Integer id, String name, String description, BigDecimal price) {
}
