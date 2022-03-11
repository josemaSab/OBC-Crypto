package com.obcamp.OBCCrypto.network.core.messages.base;

import com.obcamp.OBCCrypto.network.core.messages.exceptions.InvalidArgumentSizeException;

public class ConcreteHeader extends AbstractHeader{

    private String checksum;
    private int payloadSize;

    public ConcreteHeader(String commandName) throws InvalidArgumentSizeException, NullPointerException {
        super(commandName);
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public int getPayloadSize() {
        return payloadSize;
    }

    public void setPayloadSize(int payloadSize) {
        this.payloadSize = payloadSize;
    }
}
