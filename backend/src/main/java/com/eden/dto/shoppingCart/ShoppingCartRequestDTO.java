package com.eden.dto.shoppingCart;

import java.math.BigDecimal;

public record ShoppingCartRequestDTO (Integer id, int prodQuantity, BigDecimal price, int coupon ) {
}
