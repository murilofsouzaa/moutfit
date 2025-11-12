package com.moutfit.services;

import com.moutfit.dto.product.ProductRequestDTO;
import com.moutfit.dto.product.ProductResponseDTO;
import com.moutfit.dto.product.ProductUpdate;
import com.moutfit.models.Product;
import com.moutfit.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDTO updateProduct(ProductUpdate productUpdateDTO) {
        Product product = productRepository.findById(productUpdateDTO.id())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productUpdateDTO.name());
        product.setPrice(productUpdateDTO.price());
        product.setDescription(productUpdateDTO.description());

        Product updated = productRepository.save(product);

        return new ProductResponseDTO(
                updated.getId(), updated.getName(), updated.getDescription() ,updated.getPrice());
    }

    public void remove(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productRepository.delete(product);
    }

    public List<ProductResponseDTO> findAll() {
         return productRepository.findAll()
                .stream().map(product ->
                        new ProductResponseDTO(
                                product.getId(),
                                product.getName(),
                                product.getDescription(),
                                product.getPrice()
                        )).toList();
    }

    public ProductResponseDTO findByName(String name){
        Product product = productRepository.findProductByName(name)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return new ProductResponseDTO(
                product.getId() ,product.getName(), product.getDescription(), product.getPrice());
    }

    public ProductResponseDTO add(ProductRequestDTO productRequestDTO) {
        Product product = new Product();
        product.setName(productRequestDTO.name());
        product.setPrice(productRequestDTO.price());
        product.setDescription(productRequestDTO.description());

        Product saved = productRepository.save(product);

        return new ProductResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getPrice()
        );
    }
}
