package br.com.e_commerce.adress_service.dto.response;

public record AddressResponseDTO(
        Long id,
        String fullAddress,
        Boolean currentAddress
) {}
