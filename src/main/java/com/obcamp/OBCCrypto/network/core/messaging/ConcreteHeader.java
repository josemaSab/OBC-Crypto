package com.obcamp.OBCCrypto.network.core.messaging;

import com.obcamp.OBCCrypto.network.core.messaging.base.AbstractHeader;
import com.obcamp.OBCCrypto.network.core.messaging.exceptions.InvalidArgumentSizeException;

public class ConcreteHeader extends AbstractHeader {


    public ConcreteHeader(String commandName)throws InvalidArgumentSizeException, NullPointerException{
        super(commandName);
    }

}
