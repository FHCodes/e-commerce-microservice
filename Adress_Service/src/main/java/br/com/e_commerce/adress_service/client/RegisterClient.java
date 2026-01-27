package br.com.e_commerce.adress_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "register-service",
        url = "${register.service.url}"
)
public interface RegisterClient {

    @GetMapping("/customers/{customerId}")
    void validateCustomerExists(@PathVariable("customerId") Long customerId);
}
