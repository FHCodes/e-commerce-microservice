package br.com.e_commerce.register_service.integration;

@FeignClient(name = "address-service",url = "${address.service.url}")
public interface AddressService {

    @DeleteMapping("/delete/{customerId}")
    void deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
    }
}
