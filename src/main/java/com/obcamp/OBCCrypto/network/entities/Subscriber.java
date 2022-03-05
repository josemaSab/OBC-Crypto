package com.obcamp.OBCCrypto.network.entities;

import com.obcamp.OBCCrypto.network.messaging.v1.MessageCommand;

public interface Subscriber {

    void notify(MessageCommand msg);
}
