package br.com.e_commerce.order_service.integration.product.service;

import br.com.e_commerce.order_service.integration.product.entity.ProductCache;
import br.com.e_commerce.order_service.integration.product.repository.ProductCacheRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductCacheService {


    private ProductCacheRepository productCacheRepository;

    @Transactional(readOnly = true)
    public ProductCache getProduct(Long id){
        return productCacheRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with ID: " + id));
    }
}
