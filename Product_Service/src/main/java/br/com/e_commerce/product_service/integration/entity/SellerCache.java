package br.com.e_commerce.product_service.integration.entity;


import jakarta.persistence.*;

@Entity(name = "SELLERS_CACHE")
@Table(name = "SELLERS_CACHE")
public class SellerCache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String cnpj;

    public SellerCache() {}

    public SellerCache(String name, String cnpj) {
        this.name = name;
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}

