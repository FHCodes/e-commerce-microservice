package br.com.e_commerce.address_service.dto.response;

public record AddressResponseDTO(
        Long id,
        String fullAddress,
        Boolean currentAddress
) {}
