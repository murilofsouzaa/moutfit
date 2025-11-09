package com.moutfit.services;

import com.moutfit.models.Product;
import com.moutfit.models.User;
import com.moutfit.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    ProductRepository productRepository;
    Product product;
    User user;
    List<Product> listProducts;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findByProductByName(String name){
        return productRepository.findProductByName(name).orElse(null);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void removeProduct(Long productId) {
        productRepository.delete(product);
    }

    public Product updateName(Long productID, String name) {
        productRepository.findById(productID);
        product.setName(name);

        return productRepository.save(product);
    }

    public Product updatePrice(Long productID, BigDecimal newPrice) {
        if(!"admin".equals(user.getRole())){
            throw new SecurityException("You are not allowed to change the price of this product.");
        }

        Product product = productRepository.findById(productID).orElseThrow(null);
        product.setPrice(newPrice);
        return productRepository.save(product);
    }




}
