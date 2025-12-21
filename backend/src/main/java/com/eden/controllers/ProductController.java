package com.eden.controllers;

import com.eden.dto.product.CreateProductRequest;
import com.eden.dto.product.ProductResponse;
import com.eden.dto.product.UpdateProductRequest;
import com.eden.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Integer id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<CreateProductRequest> createProduct(@RequestBody CreateProductRequest createProductRequest){
        return ResponseEntity.ok(productService.add(createProductRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Integer id, @RequestBody UpdateProductRequest updateProductRequest){
        productService.updateById(id, updateProductRequest);
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id){
        productService.delete(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

}

