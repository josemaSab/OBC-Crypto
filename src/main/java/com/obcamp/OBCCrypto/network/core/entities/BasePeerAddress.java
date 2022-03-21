package com.obcamp.OBCCrypto.network.core.entities;

import com.obcamp.OBCCrypto.network.core.messaging.payloads.base.Writable;

import java.net.InetAddress;
import java.net.UnknownHostException;


public abstract class BasePeerAddress implements HostInterface, Writable {

    protected InetAddress address;

    /**
     * El puerto en el que escucha el peer
     */
    protected int port;


    // constructores

    public BasePeerAddress() {
    }

    public BasePeerAddress(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public BasePeerAddress(String hostname, int port) throws UnknownHostException {
        setAddress(hostname);
        this.port = port;
    }

    /**
     * Comprueba si una dirección IP (o la dirección IP resuelta a partir de un nombre de dominio) pertenece
     * a la familia IPv6
     * @param addr Una instancia de {@link InetAddress}
     * @return {@code true} si se corresponde a una dirección IPv6 válida, {@code false} en caso contrario
     */
    static boolean isIPv6(InetAddress addr) {
        return addr.getAddress().length == 16;
    }

    // Métodos implementados de HostInterface

    @Override
    public void setAddress(String hostname) throws UnknownHostException {
        this.address = InetAddress.getByName(hostname);
    }

    @Override
    public void setAddress(InetAddress addr) {
        this.address = addr;
    }


    @Override
    public byte[] getIPv6RawAddress() {
        return isIPv6(this.address) ? this.address.getAddress() : HostInterface.mappIPv4ToIPv6(this.address.getAddress());

    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public int getPort() {
        return this.port;
    }

    @Override
    public InetAddress getInetAddress() {
        return this.address;
    }

    // Metodos implementados de Writable

    @Override
    public int getBytesSize() {
        // 16 de la IP y 4 del puerto → 20
        return 20;
    }

    @Override
    public boolean isFixedSize() {
        // todas las IP ocupan 16 bytes y el puerto siempre ocupa 4
        return true;
    }
}
