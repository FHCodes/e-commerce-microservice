package com.mdp.exceptions.customExceptions;

public class CustomerOrderAccessException extends RuntimeException {
    public CustomerOrderAccessException(String message) {
        super(message);
    }
}

