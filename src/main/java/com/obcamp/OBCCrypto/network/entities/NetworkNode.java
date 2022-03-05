package com.obcamp.OBCCrypto.network.entities;

import java.net.InetAddress;
import java.util.Date;

/**
 * Esta clase representa un nodo minero de la red.
 */
public class NetworkNode extends ConcreteNetworkMember {

    private Date lastSeen;
    private long ping;

    public NetworkNode(InetAddress address, NetworkMemberCategory classOfNode, NetworkMemberStatus status) {
        super(address, classOfNode, status);

        if(classOfNode != NetworkMemberCategory.NODE){
            throw new IllegalArgumentException("Solo merece la pena llevar un seguimiento de nodos mineros");
        }
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    public long getPing() {
        return ping;
    }

    public void setPing(long ping) {
        this.ping = ping;
    }
}
