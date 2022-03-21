package com.obcamp.OBCCrypto.network.core.messaging.payloads.base;

import java.nio.ByteBuffer;

/**
 * Interfaz que define un contrato único para todas las clases que han de ser total o parcialmente serializadas para
 * el envío de información en el payload de un mensaje.
 *
 * @author Manu
 * @version 1.0
 * @since 1.0
 */
public interface Writable {

    /**
     * Debe devolver un {@link ByteBuffer} con el contenido que se desea transmitir
     * @return {@link ByteBuffer}
     */
    ByteBuffer getBytes();

    /**
     * Debe devolver la cantidad de bytes que ocupa el contenido de la clase serializado.
     * <p>Si el tamaño es fijo, puede devolver una constante, si no, habrá de calcularse instancia por instancia</p>
     * @return Tamaño del {@code ByteBuffer} que devuelve {@link #getBytes()}
     */
    int getBytesSize();

    /**
     * Debe devolver true si todas las instancias ocupan la misma cantidad de espacio una vez serializadas
     * @return {@code true} si todas las instancias tienen el mismo tamaño, {@code false} en caso contrario
     */
    default boolean isFixedSize(){
        return false;
    }

}
