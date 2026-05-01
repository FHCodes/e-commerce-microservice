package br.com.e_commerce.address_service.client;


import br.com.e_commerce.address_service.exceptions.customExceptions.EntityNotFoundException;
import feign.FeignException;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidationGateway {

    private final RegisterServiceClient registerServiceClient;

    public CustomerValidationGateway(RegisterServiceClient registerServiceClient) {
        this.registerServiceClient = registerServiceClient;
    }

    public void validateCustomerExists(Long customerId) {
        try {
            registerServiceClient.validateCustomerExists(customerId);
        } catch (FeignException.NotFound e) {
            throw new EntityNotFoundException("Customer not found");
        } catch (FeignException e) {
            throw new RuntimeException("Register Service unavailable");
        }
    }
}


