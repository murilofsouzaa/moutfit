package com.eden.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class ProductModel {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", nullable = false, length = 50)
    private String name;

    @Column(name="price", nullable = false, scale = 2)
    private BigDecimal price;

    @Column(name="description", nullable = true, length = 1000)
    private String description;

    @Column(name="quantity", nullable = false)
    private int quantity;

    @Column(name = "image", nullable = true, length = 1000)
    private String imageURL;

    @Column(name="category", nullable = false, length = 50)
    private String category;

    @Column(name="status", nullable = false, length = 15)
    private boolean status;

    @Column(name = "size", nullable = true, length = 1)
    private char size;

    @Column(name = "quantity_sold", nullable = true)
    private int quantity_sold;

    public ProductModel() {}

    public ProductModel(Integer id, String name, BigDecimal price, String description,
                        int quantity, String image, String category, boolean status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.imageURL = image;
        this.category = category;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public char getSize() {
        return size;
    }

    public void setSize(char size) {
        this.size = size;
    }

    public int getQuantity_sold() {
        return quantity_sold;
    }

    public void setQuantity_sold(int quantity_sold) {
        this.quantity_sold = quantity_sold;
    }

}
