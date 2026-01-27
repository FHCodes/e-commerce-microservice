package com.mdp.entity.user;

import com.mdp.dto.request.CustomerRequestDTO;
import com.mdp.entity.customerAddress.Address;
import com.mdp.entity.order.Order;
import com.mdp.enums.Role;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "CUSTOMER")
@Table(name = "CUSTOMER")
public class Customer extends User {

    @Column(unique = true, nullable = false)
    private String cpf;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    public Customer() {
    }

    public Customer(CustomerRequestDTO customerDTO) {
        super(customerDTO.name(), customerDTO.email(), customerDTO.password(), Role.CUSTOMER);
        this.cpf = customerDTO.cpf();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}

