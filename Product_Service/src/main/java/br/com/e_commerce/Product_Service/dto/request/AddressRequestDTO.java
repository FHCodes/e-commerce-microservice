package com.mdp.dto.request;

import jakarta.validation.constraints.*;

public record AddressRequestDTO(

        Long id,

        @NotBlank(message = "Street is required")
        String street,

        @NotBlank(message = "City is required")
        String city,

        @NotBlank(message = "State is required")
        String state,

        @NotBlank(message = "ZIP code is required")
        @Pattern(regexp = "\\d{5}-?\\d{3}", message = "ZIP code must be in the format 00000-000")
        String zipCode,

        @NotNull(message = "Number is required")
        @Positive(message = "Number must be positive")
        Integer number,

        @NotNull(message = "You must specify if this is the current address")
        Boolean currentAddress
) {}
