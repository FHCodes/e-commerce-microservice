package com.mdp.entity.order;

import com.mdp.dto.request.OrderRequestDTO;
import com.mdp.entity.product.Product;
import com.mdp.entity.user.Customer;
import com.mdp.enums.OrderStatus;
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

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public Order() {
    }

    public Order(Customer customer) {
        this.creationData = LocalDate.now();
        this.status = OrderStatus.CREATED;
        this.customer = customer;
    }

    public void addItem(Product product, int quantity) {
        OrderItem item = new OrderItem(quantity,product,this);
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

    public Customer getCustomer() {
        return customer;
    }
}


