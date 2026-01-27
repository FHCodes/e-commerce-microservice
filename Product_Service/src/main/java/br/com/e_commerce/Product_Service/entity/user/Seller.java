package com.mdp.entity.user;

import com.mdp.dto.request.CustomerRequestDTO;
import com.mdp.dto.request.SellerRequestDTO;
import com.mdp.entity.customerAddress.Address;
import com.mdp.entity.product.Product;
import com.mdp.enums.Role;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "SELLER")
@Table(name = "SELLER")
public class Seller extends User {

    @Column(unique = true, nullable = false)
    private String cnpj;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    public Seller() {
    }

    public Seller(SellerRequestDTO SellerDTO) {
        super(SellerDTO.name(), SellerDTO.email(), SellerDTO.password(), Role.SELLER);
        this.cnpj = SellerDTO.cnpj();
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}

