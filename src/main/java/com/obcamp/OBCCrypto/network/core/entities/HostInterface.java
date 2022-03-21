package com.obcamp.OBCCrypto.network.core.entities;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;

public interface HostInterface {

    void setAddress(String hostname) throws UnknownHostException;
    void setAddress(InetAddress addr);

    /**
     * Devuelve la dirección IP del host
     * @return Una dirección IPv6 o IPv4 mapeada a IPv6
     */
    byte[] getIPv6RawAddress();

    /**
     * Convierte la representación en formato cadena de una dirección IPv4 en una representación mapeada a IPv6
     * @param addr Una dirección IPv4 válida (ej: "192.168.0.1")
     * @return una dirección IPv4 mapeada a IPv6 (ej:"::ffff:192.168.0.1")
     * @see <a href="https://www.rfc-editor.org/rfc/rfc5952#section-5">RFC5952#5</a>
     */
    static String mappIPv4ToIPv6(String addr){
        return isValidIP(addr) ? "::ffff:" + addr : null;
    }

    /**
     * Convierte la representación en bytes de una dirección IPv4 en la versión mapeada a IPv6 de la dirección.
     * @param addr una dirección IPv4 válida (no se realizan comprobaciones excepto de tamaño de la dirección)
     * @return La dirección IPv4 mapeada a IPv6 (formato ::ffff:xxx.xxx.xxx.xxx)
     * @throws IllegalArgumentException Si el array que recibe como argumento tiene longitud distinta de 4.
     */
    static byte[] mappIPv4ToIPv6(byte[] addr) throws IllegalArgumentException{
        if(addr.length != 4) throw new IllegalArgumentException("Las direcciones IPv4 deben ocupar 4 bytes");

        byte[] result = new byte[16];

        result[10] = (byte) 0xff;
        result[11] = (byte) 0xff;
        System.arraycopy(addr, 0, result, 12, 4);

        return result;
    }

    static boolean isValidIP(byte[] addr){
        try{
            InetAddress address = InetAddress.getByAddress(addr);
        }catch (UnknownHostException e) {
            return false;
        }
        return true;
    }

    static boolean isValidIP(String ipAddress){
        try {
            InetAddress address = InetAddress.getByName(ipAddress);
        } catch (UnknownHostException e) {
            return false;
        }
        return true;
    }

    InetAddress getInetAddress();

    void setPort(int port);
    int getPort();

    void setLastSeen(Instant moment);
    Instant getLastSeen();

    void setServices(NodeServices services);
    NodeServices getServices();









}
