package br.com.e_commerce.order_service.integration.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCTS_CACHE") // Nome para diferenciar da tabela principal de produtos
public class ProductCache {

    @Id
    private Long id; // Este ID deve ser o mesmo que vem do Product-service

    private String name;

    private BigDecimal currentPrice;

    public ProductCache() {}

    public ProductCache(Long id, String name, BigDecimal currentPrice) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getCurrentPrice() { return currentPrice; }
}