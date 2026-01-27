package com.mdp.mapper;

import com.mdp.dto.response.SellerResponseDTO;
import com.mdp.entity.user.Seller;

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

