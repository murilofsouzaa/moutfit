package com.eden.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Table(name = "shoppingCart")
public class ShoppingCartModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany
    private List<ProductModel> products = new ArrayList<>();

    @Column(name = "prodQuantity", nullable = false)
    private int prodQuantity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "coupon")
    private int coupon;


    public ShoppingCartModel(Integer id, int prodQuantity, BigDecimal price, int coupon,  List<ProductModel> products) {
        this.id = id;
        this.prodQuantity = prodQuantity;
        this.price = price;
        this.coupon = coupon;
        this.products = products;
    }

    public ShoppingCartModel() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getProdQuantity() {
        return prodQuantity;
    }

    public void setProdQuantity(int prodQuantity) {
        this.prodQuantity = prodQuantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCoupon() {
        return coupon;
    }

    public void setCoupon(int coupon) {
        this.coupon = coupon;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }
}
