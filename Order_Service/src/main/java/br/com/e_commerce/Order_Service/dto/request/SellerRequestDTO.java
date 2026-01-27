package com.mdp.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SellerRequestDTO(
        Long id,

        @NotBlank(message = "Name is required")
        String name,

        @Email(message = "Invalid email")
        @NotBlank(message = "Email is required")
        String email,

        @Size(min = 6, message = "Password must be at least 6 characters long")
        String password,

        @Pattern(regexp = "\\d{15}", message = "CNPJ must contain 15 digits")
        String cnpj
) {}
