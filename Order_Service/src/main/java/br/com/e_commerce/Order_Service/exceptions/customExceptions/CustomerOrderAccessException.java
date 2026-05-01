package br.com.e_commerce.order_service.exceptions.customExceptions;

public class CustomerOrderAccessException extends RuntimeException {
    public CustomerOrderAccessException(String message) {
        super(message);
    }
}

