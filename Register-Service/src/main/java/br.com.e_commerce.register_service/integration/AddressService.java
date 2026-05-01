package br.com.e_commerce.register_service.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "address-service",url = "${address.service.url}")
public interface AddressService {

    @DeleteMapping("/addresses/delete/{customerId}")
    void deleteAllAddressByCustomerId(@PathVariable("customerId") Long customerId);
}