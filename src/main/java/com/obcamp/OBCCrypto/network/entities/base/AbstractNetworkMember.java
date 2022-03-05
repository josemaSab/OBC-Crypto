package com.obcamp.OBCCrypto.network.entities.base;

import java.net.InetAddress;



public abstract class AbstractNetworkMember {

    private InetAddress address;


    public AbstractNetworkMember(InetAddress address) {
        this.address = address;
    }


    public InetAddress getAddress() {
        return address;
    }

}
