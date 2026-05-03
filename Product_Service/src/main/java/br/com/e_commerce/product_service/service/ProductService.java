package br.com.e_commerce.product_service.service;

import br.com.e_commerce.product_service.dto.request.ProductRequestDTO;
import br.com.e_commerce.product_service.dto.response.ProductResponseDTO;
import br.com.e_commerce.product_service.entity.Product;
import br.com.e_commerce.product_service.exceptions.customExceptions.EntityNotFoundException;
import br.com.e_commerce.product_service.integration.service.SellerCacheService;
import br.com.e_commerce.product_service.mapper.ProductMapper;
import br.com.e_commerce.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SellerCacheService sellerCacheService;

    public ProductService(ProductRepository productRepository, SellerCacheService sellerCacheService) {
        this.productRepository = productRepository;
        this.sellerCacheService = sellerCacheService;
    }

    public List<ProductResponseDTO> getAllProductsForSellerId(Long id) {
        return ProductMapper.toProductDTOList(productRepository.findBySellerCacheId(id));
    }

    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id+ " Product not found"));
    }

    @Transactional(readOnly = true)
    public ProductResponseDTO getProductDTOById(Long id) {
        return ProductMapper.toProductDTO(productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id+ " Product not found")));
    }

    @Transactional
    public ProductResponseDTO registerProduct(ProductRequestDTO productDTO,Long sellerCacheId) {

        sellerCacheService.validateSeller(sellerCacheId);

        return ProductMapper.toProductDTO(productRepository.save(new Product(productDTO,sellerCacheId)));
    }

    @Transactional
    public ProductResponseDTO updateProduct(ProductRequestDTO productDTO) {
        if (productDTO.id() == null) {
            throw new EntityNotFoundException("Product not found");
        }

        Product existingProduct = getProductById(productDTO.id());

        if (productDTO.name() != null && !productDTO.name().isBlank()) {
            existingProduct.setName(productDTO.name());
        }
        if (productDTO.description() != null && !productDTO.description().isBlank()) {
            existingProduct.setDescription(productDTO.description());
        }
        if (productDTO.price() != null) {
            existingProduct.setPrice(productDTO.price());
        }
        if (productDTO.stock() != null) {
            existingProduct.setStock(productDTO.stock());
        }

       return ProductMapper.toProductDTO(productRepository.save(existingProduct));
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
