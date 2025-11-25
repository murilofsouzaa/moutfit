package com.eden.repository;

import com.eden.models.ShoppingCartModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartModel, Integer> {
}
