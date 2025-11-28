package com.eden.services;

import com.eden.dto.shoppingCart.AddProductToCartDTO;
import com.eden.dto.shoppingCart.ShoppingCartDeleteDTO;
import com.eden.dto.shoppingCart.ShoppingCartRequestDTO;
import com.eden.dto.shoppingCart.ShoppingCartResponseDTO;
import com.eden.models.ProductModel;
import com.eden.models.ShoppingCartModel;
import com.eden.repository.ProductRepository;
import com.eden.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartService {

    List<ProductModel> cart = new ArrayList<>();

    private ShoppingCartRepository shoppingCartRepository;
    private ProductRepository productRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository,  ProductRepository productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
    }

    public ShoppingCartResponseDTO getShoppingCartRepository(Integer id) {
        ShoppingCartModel shoppingCartModel = shoppingCartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ShoppingCart not found"));
        return new ShoppingCartResponseDTO(
                shoppingCartModel.getId(),
                shoppingCartModel.getProdQuantity(),
                shoppingCartModel.getPrice(),
                shoppingCartModel.getCoupon());
    }

    public ShoppingCartResponseDTO createShoppingCart(ShoppingCartRequestDTO shoppingCartRequestDTO) {
        ShoppingCartModel shoppingCartModel = new ShoppingCartModel();

        shoppingCartModel.setPrice(shoppingCartRequestDTO.price());
        shoppingCartModel.setProdQuantity(shoppingCartRequestDTO.prodQuantity());
        shoppingCartModel.setCoupon(shoppingCartRequestDTO.coupon());
        shoppingCartModel.setProdQuantity(shoppingCartRequestDTO.prodQuantity());

        return new ShoppingCartResponseDTO(
                shoppingCartModel.getId(),
                shoppingCartModel.getProdQuantity(),
                shoppingCartModel.getPrice(),
                shoppingCartModel.getCoupon()
        );
    }

    public ShoppingCartResponseDTO addProductToShoppingCart(Integer cartId, Integer prodId) {
        ShoppingCartModel shoppingCart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("ShoppingCart not found"));

        ProductModel product = productRepository.findById(prodId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        shoppingCart.getProducts().add(product);

        shoppingCartRepository.save(shoppingCart);

        return new ShoppingCartResponseDTO(
                shoppingCart.getId(),
                shoppingCart.getProdQuantity(),
                shoppingCart.getPrice(),
                shoppingCart.getCoupon()
        );
    }

    public ShoppingCartDeleteDTO removeProductFromShoppingCart(Integer cartId, Integer prodId) {
        ShoppingCartModel shoppingCartModel = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("ShoppingCart not found"));

        ProductModel product = productRepository.findById(prodId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        shoppingCartModel.getProducts().remove(product);

        return new ShoppingCartDeleteDTO(
                shoppingCartModel.getId(),
                product.getId()
        );
    }
}
