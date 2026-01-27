package br.com.e_commerce.adress_service.client;


import br.com.e_commerce.adress_service.exceptions.customExceptions.EntityNotFoundException;
import feign.FeignException;
import org.springframework.stereotype.Component;

@Component
public class CustomerClient {

    private final RegisterClient registerClient;

    public CustomerClient(RegisterClient registerClient) {
        this.registerClient = registerClient;
    }

    public void validateCustomerExists(Long customerId) {
        try {
            registerClient.validateCustomerExists(customerId);
        } catch (FeignException.NotFound e) {
            throw new EntityNotFoundException("Customer not found");
        } catch (FeignException e) {
            throw new RuntimeException("Register Service unavailable");
        }
    }
}


