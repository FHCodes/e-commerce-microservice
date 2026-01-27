package com.mdp.entity.order;

import com.mdp.entity.product.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "ORDER_ITEM")
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private BigDecimal priceAtPurchase;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Order order;

    public OrderItem() {
    }

    public OrderItem(Integer quantity, Product product, Order order) {
        this.quantity = quantity;
        this.product = product;
        this.order = order;
        this.priceAtPurchase = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Order getOrder() {
        return order;
    }

    public BigDecimal getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public BigDecimal getSubTotItem() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
