package com.guilhermecamargo.testeuol.infra.exceptions;

public class GenericErrorException extends RuntimeException {

    public GenericErrorException(String message){
        super(message);
    }

}
