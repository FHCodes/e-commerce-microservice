package br.com.e_commerce.address_service.entity;

import br.com.e_commerce.address_service.dto.request.AddressRequestDTO;
import jakarta.persistence.*;

@Entity(name = "ADDRESS")
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String state;
    private String zipCode;
    private Integer number;
    private Boolean currentAddress;

    // Apenas o ID do dono do endereço
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    public Address() {
    }

    // Construtor correto para microserviços
    public Address(AddressRequestDTO dto) {
        this.street = dto.street();
        this.city = dto.city();
        this.state = dto.state();
        this.zipCode = dto.zipCode();
        this.number = dto.number();
        this.currentAddress = dto.currentAddress();
        this.customerId = dto.customerId(); // vem direto do DTO
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }
    public String getCity() {
        return city;
    }
    public String getState() {
        return state;
    }
    public String getZipCode() {
        return zipCode;
    }
    public Integer getNumber() {
        return number;
    }
    public Boolean getCurrentAddress() {
        return currentAddress;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public void setCurrentAddress(Boolean currentAddress) {
        this.currentAddress = currentAddress;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
