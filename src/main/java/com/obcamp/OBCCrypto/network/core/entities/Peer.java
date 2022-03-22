package com.obcamp.OBCCrypto.network.core.entities;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.time.Instant;


/**
 * Clase para representar un nodo cualquiera. El tipo de nodo en concreto dependerá de los {@link NodeServices}
 * que soporte.
 *
 * @author Manu
 * @version 1.0
 * @since 1.0
 *
 */
public class Peer extends BasePeerAddress {

    /**
     * Los servicios soportados por el nodo.
     */
    private NodeServices services;

    /**
     * <p>El momento de la última conexión exitosa con el nodo. Si un nodo transmite
     * su propia dirección, mandará el instante actual.
     * Permite ignorar nodos inactivos</p>
     */
    private Instant lastSeen;


    // Constructores
    public Peer() {
    }

    public Peer(InetAddress address, int port, NodeServices services, Instant lastSeen) {
        super(address, port);
        this.services = services;
        this.lastSeen = lastSeen;
    }

    public Peer(String hostname, int port) throws UnknownHostException {
        super(hostname, port);
    }

    public NodeServices getServices() {
        return services;
    }

    public void setServices(NodeServices services) {
        this.services = services;
    }

    public Instant getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Instant lastSeen) {
        this.lastSeen = lastSeen;
    }

    // implement methods

    @Override
    public ByteBuffer getBytes(){
        // 1- timestamp
        // 2- services
        // 3- IP
        // 4- puerto
        ByteBuffer buffer = ByteBuffer.allocate(this.getBytesSize());

        buffer.putLong(this.lastSeen.getEpochSecond());
        buffer.put(this.services.getBytes());
        buffer.put(super.getBytes());

        return buffer;
    }

    @Override
    public int getBytesSize(){
        int result = super.getBytesSize(); // 16 + 4 de IP + puerto
        result += this.services.getBytesSize(); // 2
        result += 8; // del timestamp
        return result; // total 30 bytes por peer
    }
}
