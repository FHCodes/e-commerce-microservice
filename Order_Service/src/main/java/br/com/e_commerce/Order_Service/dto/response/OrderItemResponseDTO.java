package com.mdp.dto.response;

import java.math.BigDecimal;

public record OrderItemResponseDTO(
        Long id,
        String productName,
        BigDecimal price,
        Integer quantity,
        BigDecimal subtotal
) {}

