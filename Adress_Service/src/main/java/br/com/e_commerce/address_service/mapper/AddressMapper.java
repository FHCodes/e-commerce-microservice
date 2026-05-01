package br.com.e_commerce.address_service.mapper;

import br.com.e_commerce.address_service.dto.response.AddressResponseDTO;
import br.com.e_commerce.address_service.entity.Address;

import java.util.List;

public class AddressMapper {

    public static List<AddressResponseDTO> toAddressDTOList(List<Address> addresses) {
        return addresses.stream()
                .map(AddressMapper::toAddressDTO)
                .toList();
    }

    public static AddressResponseDTO toAddressDTO(Address address) {
        if (address == null) {
            return null;
        }

        String fullAddress = address.getStreet() + " - " +
                address.getCity() + " - " +
                address.getState() + " - " +
                address.getZipCode();

        return new AddressResponseDTO(
                address.getId(),
                fullAddress,
                address.getCurrentAddress()
        );
    }
}




