package br.com.e_commerce.register_service.entity;


import br.com.e_commerce.register_service.enums.Role;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "CUSTOMER")
@Table(name = "CUSTOMER")
public class Customer extends br.com.e_commerce.register_service.entity.user.User {

    @Column(unique = true, nullable = false)
    private String cpf;

    public Customer() {
    }

    public Customer(br.com.e_commerce.register_service.dto.request.CustomerRequestDTO customerDTO) {
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

