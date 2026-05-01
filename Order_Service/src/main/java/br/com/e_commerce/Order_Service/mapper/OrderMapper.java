package br.com.e_commerce.order_service.mapper;

import br.com.e_commerce.order_service.dto.response.OrderItemResponseDTO;
import br.com.e_commerce.order_service.dto.response.OrderResponseDTO;
import br.com.e_commerce.order_service.entity.Order;
import br.com.e_commerce.order_service.entity.OrderItem;

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
                item.getProductCache().getName(),
                item.getProductCache().getCurrentPrice(),
                item.getQuantity(),
                item.getSubTotItem()
        );
    }
}
