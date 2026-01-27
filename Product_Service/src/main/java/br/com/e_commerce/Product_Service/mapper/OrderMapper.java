package com.mdp.mapper;

import com.mdp.dto.response.OrderItemResponseDTO;
import com.mdp.dto.response.OrderResponseDTO;
import com.mdp.entity.order.Order;
import com.mdp.entity.order.OrderItem;

import java.util.List;

public class OrderMapper {

    public static List<OrderResponseDTO> orderResponseDTOList(List<Order> orders){
        return orders.stream()
                .map(OrderMapper::toOrderDTO)
                .toList();
    }

    public static OrderResponseDTO toOrderDTO(Order order){
        if (order == null){
            return null;
        }
        return new OrderResponseDTO(
                order.getId(),
                order.getDate(),
                order.getStatus(),
                order.getTotal()
        );
    }

    public static List<OrderItemResponseDTO> toOrderItemResponseDTOList(List<OrderItem> items) {
        return items.stream()
                .map(OrderMapper::toOrderItemResponseDTO)
                .toList();
    }

    public static OrderItemResponseDTO toOrderItemResponseDTO(OrderItem item) {
        return new OrderItemResponseDTO(
                item.getId(),
                item.getProduct().getName(),
                item.getProduct().getPrice(),
                item.getQuantity(),
                item.getSubTotItem()
        );
    }
}
