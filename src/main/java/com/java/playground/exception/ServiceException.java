package com.java.playground.exception;

/**
 * Custom exception class to handle template service errors
 */
public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }

}