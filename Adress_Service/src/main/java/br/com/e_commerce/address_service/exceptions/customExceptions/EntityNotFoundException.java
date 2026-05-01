package br.com.e_commerce.adress_service.exceptions.customExceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}

