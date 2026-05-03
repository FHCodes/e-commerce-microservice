package br.com.e_commerce.product_service.integration.service;

import br.com.e_commerce.product_service.exceptions.customExceptions.EntityNotFoundException;
import br.com.e_commerce.product_service.integration.repository.SellerCacheRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SellerCacheService {

    private final SellerCacheRepository sellerCacheRepository;

    public SellerCacheService(SellerCacheRepository sellerCacheRepository) {
        this.sellerCacheRepository = sellerCacheRepository;
    }

    @Transactional(readOnly = true)
    public void validateSeller(Long sellerCacheId) {
        if (!sellerCacheRepository.existsById(sellerCacheId)) {
            throw new EntityNotFoundException(sellerCacheId + " SellerCache not found");
        }
    }
}

