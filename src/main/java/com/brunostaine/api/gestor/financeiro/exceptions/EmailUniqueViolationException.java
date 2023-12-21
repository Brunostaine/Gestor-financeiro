package com.brunostaine.api.gestor.financeiro.exceptions;

public class EmailUniqueViolationException extends RuntimeException {

    public EmailUniqueViolationException(String message) {
        super(message);
    }
}
