package br.com.e_commerce.order_service.dto.response;

import java.math.BigDecimal;

public record OrderItemResponseDTO(
        Long id,
        String productName,
        BigDecimal price,
        Integer quantity,
        BigDecimal subtotal
) {}

