package br.com.e_commerce.order_service.entity;

import br.com.e_commerce.order_service.integration.product.entity.ProductCache;
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
    private ProductCache productCache;

    @ManyToOne
    private Order order;

    public OrderItem() {
    }

    public OrderItem(Integer quantity, ProductCache productCache, Order order) {
        this.quantity = quantity;
        this.productCache = productCache;
        this.order = order;
        this.priceAtPurchase = productCache.getCurrentPrice();
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ProductCache getProductCache() {
        return productCache;
    }

    public Order getOrder() {
        return order;
    }

    public BigDecimal getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public BigDecimal getSubTotItem() {
        return productCache.getCurrentPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
