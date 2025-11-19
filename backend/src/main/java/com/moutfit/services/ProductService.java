package com.moutfit.services;

import com.moutfit.dto.product.ProductRequestDTO;
import com.moutfit.dto.product.ProductResponseDTO;
import com.moutfit.dto.product.ProductUpdateDTO;
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

    public ProductResponseDTO getProductById(Integer id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory(),
                product.getImageURL(),
                product.getQuantity(),
                product.getStatus()
        );
    }


    public ProductResponseDTO add(ProductUpdateDTO productRequestDTO) {
        Product product = new Product();
        product.setName(productRequestDTO.name());
        product.setDescription(productRequestDTO.description());
        product.setPrice(productRequestDTO.price());
        product.setCategory(productRequestDTO.category());
        product.setImageURL(productRequestDTO.image());

        productRepository.save(product);

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory(),
                product.getImageURL(),
                product.getQuantity(),
                product.getStatus()
        );
    }

    public ProductResponseDTO updateById(Integer id, ProductUpdateDTO productUpdateDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productUpdateDTO.name());
        product.setDescription(productUpdateDTO.description());
        product.setPrice(productUpdateDTO.price());
        product.setCategory(productUpdateDTO.category());
        product.setImageURL(productUpdateDTO.image());

        productRepository.save(product);

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory(),
                product.getImageURL(),
                product.getQuantity(),
                product.getStatus()
        );
    }

    public ProductResponseDTO update(ProductRequestDTO productRequestDTO) {
        Product product = new Product();
        product.setName(productRequestDTO.name());
        product.setDescription(productRequestDTO.description());
        product.setPrice(productRequestDTO.price());
        product.setCategory(productRequestDTO.category());
        product.setImageURL(productRequestDTO.image());

        productRepository.save(product);

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory(),
                product.getImageURL(),
                product.getQuantity(),
                product.getStatus()
        );
    }


    public void remove(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productRepository.delete(product);
    }

    public ProductResponseDTO getByName(String name){
        Product product = productRepository.findProductByName(name)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory(),
                product.getImageURL(),
                product.getQuantity(),
                product.getStatus()
        );
    }

    public List<ProductResponseDTO> getAll() {
         return productRepository.findAll()
                .stream().map(product ->
                        new ProductResponseDTO(
                                product.getId(),
                                product.getName(),
                                product.getDescription(),
                                product.getPrice(),
                                product.getCategory(),
                                product.getImageURL(),
                                product.getQuantity(),
                                product.getStatus()
                        )).toList();
    }
}
