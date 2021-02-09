package com.priscilasanfer.concrete.exception;

public class NoSuchElementFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NoSuchElementFoundException(Object id) {
        super("Resource not found. Id " + id);
    }
}
