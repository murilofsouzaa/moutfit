package com.eden.dto.shoppingCart;

import java.math.BigDecimal;

public record ShoppingCartUpdateDTO(Integer id, Integer prodId, int prodQuantity, BigDecimal price, int coupon) {
}
