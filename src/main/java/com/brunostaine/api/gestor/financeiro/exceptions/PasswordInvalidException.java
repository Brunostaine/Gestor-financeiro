package com.brunostaine.api.gestor.financeiro.exceptions;

public class PasswordInvalidException extends RuntimeException{
    public PasswordInvalidException(String message) {
        super(message);
    }
}
