package br.com.e_commerce.order_service.dto.response;

import br.com.e_commerce.order_service.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OrderResponseDTO(
        Long id,              // faz sentido expor
        LocalDate date,
        OrderStatus status,
        BigDecimal total
) {}
