package com.mdp.dto.response;

public record AddressResponseDTO(
        Long id,
        String fullAddress,
        Boolean currentAddress
) {}
