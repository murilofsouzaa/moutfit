package com.eden.controllers;

import com.eden.dto.shoppingCart.AddProductToCartDTO;
import com.eden.dto.shoppingCart.ShoppingCartRequestDTO;
import com.eden.dto.shoppingCart.ShoppingCartResponseDTO;
import com.eden.services.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/cart")
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartResponseDTO> getShoppingCartRepository(@PathVariable Integer id) {
        return ResponseEntity.ok(shoppingCartService.getShoppingCartRepository(id));
    }

    @PostMapping
    public ResponseEntity<ShoppingCartResponseDTO> addProductToShoppingCart(@RequestBody AddProductToCartDTO request) {

        ShoppingCartResponseDTO response =
                shoppingCartService.addProductToShoppingCart(request.cartId(), request.prodId());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> createShoppingCart(@RequestBody ShoppingCartRequestDTO shoppingCartRequestDTO) {
        shoppingCartService.createShoppingCart(shoppingCartRequestDTO);
        return ResponseEntity.ok("Create Shopping Cart Success");
    }

    @DeleteMapping("/{cartId}/product/{prodId}")
    public ResponseEntity<String> deleteShoppingCart(@PathVariable Integer cartId, @PathVariable Integer prodId) {
        shoppingCartService.removeProductFromShoppingCart(cartId, prodId);
        return ResponseEntity.ok("Delete Shopping Cart Success");
    }

}
