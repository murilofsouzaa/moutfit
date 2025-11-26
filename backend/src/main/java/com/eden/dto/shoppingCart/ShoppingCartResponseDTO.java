package com.eden.dto.shoppingCart;

import java.math.BigDecimal;

public record ShoppingCartResponseDTO(Integer id, int prodQuantity, BigDecimal price, int coupon) {
}
