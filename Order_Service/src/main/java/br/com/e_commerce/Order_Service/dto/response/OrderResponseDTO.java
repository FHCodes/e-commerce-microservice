package com.mdp.dto.response;

import com.mdp.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OrderResponseDTO(
        Long id,              // faz sentido expor
        LocalDate date,
        OrderStatus status,
        BigDecimal total
) {}
