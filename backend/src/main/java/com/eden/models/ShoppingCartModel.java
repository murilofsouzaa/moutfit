package com.eden.models;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Table(name = "shoppingCart")
public class ShoppingCartModel {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "prodId", nullable = false)
    private Integer prodId;
    @Column(name = "prodQuantity", nullable = false)
    private int prodQuantity;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "coupon")
    private int coupon;

    public ShoppingCartModel(Integer id, Integer prodId, int prodQuantity, BigDecimal price, int coupon) {
        this.id = id;
        this.prodId = prodId;
        this.prodQuantity = prodQuantity;
        this.price = price;
        this.coupon = coupon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
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
}
