package com.moutfit.repository;

import com.moutfit.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findById(Integer id);
    List<Product> findAllProductsByName(String name);
    Optional<Product> findProductByName(String name);
    Optional<Product> findProductByCategory(String category);
    List<Product> findAllProductsByCategory(String category);

}
