package br.com.e_commerce.order_service.integration.product.repository;


import br.com.e_commerce.order_service.integration.product.entity.ProductCache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCacheRepository extends JpaRepository<ProductCache,Long> {
}
