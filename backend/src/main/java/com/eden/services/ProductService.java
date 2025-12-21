package com.eden.services;

import com.eden.dto.product.*;
import com.eden.models.ProductModel;
import com.eden.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService implements IProductDiscountCalculator {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse getProductById(Integer id){
        ProductModel productModel = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return new ProductResponse(
                productModel.getId(),
                productModel.getName(),
                productModel.getDescription(),
                productModel.getPrice(),
                productModel.getCategory(),
                productModel.getImageURL(),
                productModel.getQuantity(),
                productModel.getStatus()
        );
    }

    public ProductResponse getByName(String name){
        ProductModel productModel = productRepository.findProductByName(name)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return new ProductResponse(
                productModel.getId(),
                productModel.getName(),
                productModel.getDescription(),
                productModel.getPrice(),
                productModel.getCategory(),
                productModel.getImageURL(),
                productModel.getQuantity(),
                productModel.getStatus()
        );
    }

    public List<ProductResponse> getAll() {
        return productRepository.findAll()
                .stream().map(productModel ->
                        new ProductResponse(
                                productModel.getId(),
                                productModel.getName(),
                                productModel.getDescription(),
                                productModel.getPrice(),
                                productModel.getCategory(),
                                productModel.getImageURL(),
                                productModel.getQuantity(),
                                productModel.getStatus()
                        )).toList();
    }

    public CreateProductRequest add(CreateProductRequest createProductRequest) {
        ProductModel productModel = new ProductModel();
        productModel.setName(createProductRequest.name());
        productModel.setDescription(createProductRequest.description());
        productModel.setPrice(createProductRequest.price());
        productModel.setCategory(createProductRequest.category());
        productModel.setImageURL(createProductRequest.image());

        productRepository.save(productModel);

        return new CreateProductRequest(
                productModel.getName(),
                productModel.getDescription(),
                productModel.getPrice(),
                productModel.getQuantity(),
                productModel.getCategory(),
                productModel.getImageURL()
        );
    }

    public UpdateProductRequest update(UpdateProductRequest updateProductRequest) {
        ProductModel productModel = new ProductModel();
        productModel.setName(updateProductRequest.name());
        productModel.setDescription(updateProductRequest.description());
        productModel.setPrice(updateProductRequest.price());
        productModel.setCategory(updateProductRequest.category());
        productModel.setImageURL(updateProductRequest.image());

        productRepository.save(productModel);

        return new UpdateProductRequest(
                productModel.getName(),
                productModel.getDescription(),
                productModel.getPrice(),
                productModel.getCategory(),
                productModel.getImageURL(),
                productModel.getQuantity(),
                productModel.getStatus()
        );
    }

    public UpdateProductRequest updateById(Integer id, UpdateProductRequest updateProductRequest) {
        ProductModel productModel = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productModel.setName(updateProductRequest.name());
        productModel.setDescription(updateProductRequest.description());
        productModel.setPrice(updateProductRequest.price());
        productModel.setCategory(updateProductRequest.category());
        productModel.setImageURL(updateProductRequest.image());

        productRepository.save(productModel);

        return new UpdateProductRequest(
                productModel.getName(),
                productModel.getDescription(),
                productModel.getPrice(),
                productModel.getCategory(),
                productModel.getImageURL(),
                productModel.getQuantity(),
                productModel.getStatus()
        );
    }

    public DeleteProductRequest delete(Integer id) {
        ProductModel productModel = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productRepository.delete(productModel);

        return new DeleteProductRequest(productModel.getId());
    }

    @Override
    public BigDecimal calculateProductDiscount(ProductModel product, double discountRate) {
        BigDecimal price = product.getPrice();
        BigDecimal discount = product.getPrice().multiply(BigDecimal.valueOf(discountRate));
        return price.subtract(discount);
    }
}
