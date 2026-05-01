package br.com.e_commerce.adress_service.dto.request;

import jakarta.validation.constraints.*;

public record AddressRequestDTO(

        Long id,
        Long customerId,

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
) {
        public AddressRequestDTO withId(Long id) {
                return new AddressRequestDTO(
                        id,
                        this.customerId,
                        this.street,
                        this.city,
                        this.state,
                        this.zipCode,
                        this.number,
                        this.currentAddress
                );
        }
}
