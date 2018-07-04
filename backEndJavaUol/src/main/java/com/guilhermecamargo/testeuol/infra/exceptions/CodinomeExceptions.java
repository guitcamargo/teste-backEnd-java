package com.guilhermecamargo.testeuol.infra.exceptions;

import javax.xml.bind.ValidationException;

public class CodinomeExceptions  extends ValidationException {

    public CodinomeExceptions(String message) {
        super(message);
    }
    
}

