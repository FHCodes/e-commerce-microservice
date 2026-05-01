package br.com.e_commerce.order_service.entity;

import br.com.e_commerce.order_service.enums.OrderStatus;
import br.com.e_commerce.order_service.integration.product.entity.ProductCache;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ORDERS")
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate creationData;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private BigDecimal total;

    private Long customerId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public Order() {
    }

    public Order(Long customerId) {
        this.creationData = LocalDate.now();
        this.status = OrderStatus.CREATED;
        this.customerId = customerId;
    }

    public void addItem(ProductCache productLocal, int quantity) {
        // No ato da compra, "congelamos" o preço atual do cache no item do pedido
        OrderItem item = new OrderItem(quantity, productLocal, this);
        this.items.add(item);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public BigDecimal getTotal() {
        return items.stream()
                .map(OrderItem::getSubTotItem)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return creationData;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Long getCustomerId() {
        return customerId;
    }
}


