package br.com.e_commerce.order_service.controller;

import br.com.e_commerce.order_service.dto.request.OrderRequestDTO;
import br.com.e_commerce.order_service.dto.response.OrderItemResponseDTO;
import br.com.e_commerce.order_service.dto.response.OrderResponseDTO;
import br.com.e_commerce.order_service.entity.Order;
import br.com.e_commerce.order_service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{custumerId}/create")
    public ResponseEntity<Void> createOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO, @PathVariable Long custumerId) {
        orderService.createOrder(custumerId,orderRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{custumerId}")
    public ResponseEntity<List<OrderResponseDTO>> allOrderByCustumer(@Valid @PathVariable Long custumerId){
        return ResponseEntity.ok(orderService.allOrdersByCustomer(custumerId));
    }

//    @GetMapping("/{custumerId}/{orderId}")
//    public ResponseEntity<List<OrderItemResponseDTO>> allItemsByOrder(@Valid @PathVariable Long custumerId, @PathVariable Long orderId){
//        return ResponseEntity.ok(orderService.allItemsByOrder(custumerId,orderId));
//    }
}
