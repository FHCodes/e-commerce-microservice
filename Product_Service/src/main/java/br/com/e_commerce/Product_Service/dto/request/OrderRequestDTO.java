package com.mdp.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;

public record OrderRequestDTO(

        @NotNull(message = "Items cannot be null")
        @Size(min = 1, message = "Order must have at least one item")
        @Valid
        List<OrderItemRequestDTO> orderItems

) {
        public record OrderItemRequestDTO(
                Long productId,

                @NotNull(message = "Quantity is required")
                @Min(value = 1, message = "Quantity must be at least 1")
                Integer quantity
        ) {}
}

