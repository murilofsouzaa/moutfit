package com.eden.repository;

import com.eden.models.ShoppingCartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartModel, Integer> {
}
