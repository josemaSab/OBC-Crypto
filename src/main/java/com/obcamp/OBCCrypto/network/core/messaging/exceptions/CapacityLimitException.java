package com.obcamp.OBCCrypto.network.core.messaging.exceptions;

public class CapacityLimitException extends Exception{

    public CapacityLimitException() {
    }

    public CapacityLimitException(String message) {
        super(message);
    }
}
