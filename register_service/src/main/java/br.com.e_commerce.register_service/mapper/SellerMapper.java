package br.com.e_commerce.register_service.mapper;

import br.com.e_commerce.register_service.dto.response.SellerResponseDTO;
import br.com.e_commerce.register_service.entity.Seller;

import java.util.List;


public class SellerMapper {

    public static List<SellerResponseDTO> toSellerDTOList(List<Seller> sellers) {
        return sellers.stream()
                .map(SellerMapper::toSellerDTO)
                .toList();
    }

    public static SellerResponseDTO toSellerDTO(Seller seller) {
        if (seller == null) {
            return null;
        }

        return new SellerResponseDTO(
                seller.getId(),
                seller.getName(),
                seller.getEmail(),
                seller.getCnpj()
        );
    }
}

