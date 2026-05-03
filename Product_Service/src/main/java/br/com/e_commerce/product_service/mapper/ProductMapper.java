package br.com.e_commerce.product_service.mapper;



import br.com.e_commerce.product_service.dto.response.ProductResponseDTO;
import br.com.e_commerce.product_service.entity.Product;

import java.util.List;

public class ProductMapper {

    public static List<ProductResponseDTO> toProductDTOList(List<Product> products) {
        return products.stream()
                .map(ProductMapper::toProductDTO)
                .toList();
    }

    public static ProductResponseDTO toProductDTO(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
    }
}




