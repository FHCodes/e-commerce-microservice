package br.com.e_commerce.address_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "register-service",
        url = "${register.service.url}"
)
public interface RegisterServiceClient {

    @GetMapping("/customers/{customerId}")
    void validateCustomerExists(@PathVariable("customerId") Long customerId);
}
