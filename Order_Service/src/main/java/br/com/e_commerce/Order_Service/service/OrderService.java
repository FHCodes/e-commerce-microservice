package br.com.e_commerce.order_service.service;

import br.com.e_commerce.order_service.dto.request.OrderRequestDTO;
import br.com.e_commerce.order_service.dto.response.OrderResponseDTO;
import br.com.e_commerce.order_service.entity.Order;
import br.com.e_commerce.order_service.integration.product.entity.ProductCache;
import br.com.e_commerce.order_service.integration.product.service.ProductCacheService;
import br.com.e_commerce.order_service.mapper.OrderMapper;
import br.com.e_commerce.order_service.repository.OrderItemRepository;
import br.com.e_commerce.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final ProductCacheService productCacheService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;


    public OrderService(ProductCacheService productCacheService,
                        OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository) {
        this.productCacheService = productCacheService;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

//    @Autowired CustomerService customerService;

    @Transactional
    public void createOrder(Long customerId, OrderRequestDTO dto) {
        Order order = new Order(customerId);

        for (OrderRequestDTO.OrderItemRequestDTO itemDto : dto.orderItems()) {

            ProductCache productCache = productCacheService.getProduct(itemDto.productId());
            order.addItem(productCache,itemDto.quantity());
        }

        orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDTO> allOrdersByCustomer(Long customerId) {
       List<Order> order = orderRepository.findByCustomerId(customerId);
       return OrderMapper.orderResponseDTOList(order);
    }

//    @Transactional(readOnly = true)
//    public List<OrderItemResponseDTO> allItemsByOrder(Long customerId, Long orderId) {
//        Order order = orderRepository.findById(orderId)
//                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
//
//        if (!order.getCustomer().getId().equals(customerId)) {
//            throw new CustomerOrderAccessException("Order does not belong to this customer");
//        }
//
//        return OrderMapper.toOrderItemResponseDTOList(order.getItems());
//    }

}
