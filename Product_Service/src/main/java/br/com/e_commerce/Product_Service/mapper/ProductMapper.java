package com.mdp.mapper;



import com.mdp.dto.response.ProductResponseDTO;
import com.mdp.entity.product.Product;

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




