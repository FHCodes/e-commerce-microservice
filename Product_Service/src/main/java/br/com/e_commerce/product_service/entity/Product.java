package br.com.e_commerce.product_service.entity;

import br.com.e_commerce.product_service.dto.request.ProductRequestDTO;
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

    @Column(name = "sellerCacheId", nullable = false)
    private Long sellerCacheId;

    public Product(){}

    public Product(ProductRequestDTO productRequestDTO, Long sellerCacheId){
        this.name = productRequestDTO.name();
        this.description = productRequestDTO.description();
        this.price = productRequestDTO.price();
        this.stock = productRequestDTO.stock();
        this.sellerCacheId = sellerCacheId;
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

    public Long getSellerCacheId() {
        return sellerCacheId;
    }
}

