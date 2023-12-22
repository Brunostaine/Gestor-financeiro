package com.brunostaine.api.gestor.financeiro.exceptions;

public class UsernameUniqueViolationException extends RuntimeException {

    public UsernameUniqueViolationException(String message){
        super(message);
    }
}
