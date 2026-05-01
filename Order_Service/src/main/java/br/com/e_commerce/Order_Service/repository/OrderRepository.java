package br.com.e_commerce.order_service.repository;



import br.com.e_commerce.order_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long custumerId);
}
