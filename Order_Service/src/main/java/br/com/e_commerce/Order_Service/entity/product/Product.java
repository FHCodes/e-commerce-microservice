package com.mdp.entity.product;

import com.mdp.dto.request.ProductRequestDTO;
import com.mdp.entity.user.Seller;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "PRODUCTS")
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    public Product(){}

    public Product(ProductRequestDTO productRequestDTO, Seller seller){
        this.name = productRequestDTO.name();
        this.description = productRequestDTO.description();
        this.price = productRequestDTO.price();
        this.stock = productRequestDTO.stock();
        this.seller = seller;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}

