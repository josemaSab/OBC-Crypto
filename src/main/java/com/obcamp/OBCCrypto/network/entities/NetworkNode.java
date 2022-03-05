package com.obcamp.OBCCrypto.network.entities;

import java.net.InetAddress;
import java.util.Date;

/**
 * Esta clase representa un miembro de la red (nodo).
 * Cada nodo es un sub de todos los nodos que le conocen.
 * Cada nodo es un pub para todos los nodos que conoce.
 *
 *
 *
 * Cuando a un nodo le notifican la existencia de un nuevo bloque/transacción (que aun no conozca),
 * este retransmite esa notificación a todos sus subs (excepto al que le ha notificado a el).
 * Esto garantiza que cada nodo retransmite un bloque/transacción nuevos una única vez, limitando el número máximo de
 * notificaciones repetidas a (n-1)^2 en el caso de que todos los nodos de la red ya se conozcan entre si.
 */
public class NetworkNode extends ConcreteNetworkMember {

    private Date lastSeen;
    private long ping;

    public NetworkNode(InetAddress address, NetworkMemberOperationMode classOfNode, NetworkMemberStatus status) {
        super(address, classOfNode, status);

        if(classOfNode != NetworkMemberOperationMode.NODE){
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
