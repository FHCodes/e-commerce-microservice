package br.com.e_commerce.register_service.entity;

import br.com.e_commerce.register_service.entity.user.User;
import br.com.e_commerce.register_service.dto.request.SellerRequestDTO;
import br.com.e_commerce.register_service.enums.Role;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "SELLER")
@Table(name = "SELLER")
public class Seller extends User {

    @Column(unique = true, nullable = false)
    private String cnpj;

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

