package br.com.e_commerce.product_service.repository;

import br.com.e_commerce.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findBySellerCacheId(Long sellerCacheId);
}
