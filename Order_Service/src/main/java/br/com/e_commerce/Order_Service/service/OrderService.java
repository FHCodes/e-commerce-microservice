package com.mdp.service;

import com.mdp.dto.request.OrderRequestDTO;
import com.mdp.dto.response.OrderItemResponseDTO;
import com.mdp.dto.response.OrderResponseDTO;
import com.mdp.entity.order.Order;
import com.mdp.entity.product.Product;
import com.mdp.entity.user.Customer;
import com.mdp.exceptions.customExceptions.CustomerOrderAccessException;
import com.mdp.exceptions.customExceptions.EntityNotFoundException;
import com.mdp.mapper.OrderMapper;
import com.mdp.repository.OrderItemRepository;
import com.mdp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired CustomerService customerService;

    @Transactional
    public Order createOrder(Long clientId, OrderRequestDTO dto) {
        Customer customer = customerService.getCustomerById(clientId);
        Order order = new Order(customer);

        for (OrderRequestDTO.OrderItemRequestDTO itemDto : dto.orderItems()) {
            Product product = productService.getProduct(itemDto.productId());

            order.addItem(product, itemDto.quantity());
        }
        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDTO> allOrdersByCustomer(Long customerId) {
       List<Order> order = orderRepository.findByCustomerId(customerId);
       return OrderMapper.orderResponseDTOList(order);
    }

    @Transactional(readOnly = true)
    public List<OrderItemResponseDTO> allItemsByOrder(Long customerId, Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        if (!order.getCustomer().getId().equals(customerId)) {
            throw new CustomerOrderAccessException("Order does not belong to this customer");
        }

        return OrderMapper.toOrderItemResponseDTOList(order.getItems());
    }

}
