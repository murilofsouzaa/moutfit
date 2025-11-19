package com.moutfit.controllers;

import com.moutfit.dto.product.ProductRequestDTO;
import com.moutfit.dto.product.ProductResponseDTO;
import com.moutfit.dto.product.ProductUpdateDTO;
import com.moutfit.repository.ProductRepository;
import com.moutfit.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Integer id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductUpdateDTO productUpdateDTO){
        ProductResponseDTO productResponseDTO = productService.add(productUpdateDTO);
        return ResponseEntity.ok(productResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Integer id, @RequestBody ProductUpdateDTO productUpdateDTO){
        productService.updateById(id, productUpdateDTO);
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id){
        productService.delete(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

}

