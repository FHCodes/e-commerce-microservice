package br.com.e_commerce.register_service.integration;

import feign.FeignException;
import org.springframework.stereotype.Component;

@Component
public class DeleteAddressGateway {

    private final AddressService addressService;

    public DeleteAddressGateway(AddressService addressService) {
        this.addressService = addressService;
    }

    public void deleteAllAddress(Long custmerId){
        try {
            addressService.deleteAllAddressByCustomerId(custmerId);
        } catch (FeignException e) {
            throw new RuntimeException("Register Service unavailable " +
                    "the addresses could not be deleted.");
        }
    }
}
