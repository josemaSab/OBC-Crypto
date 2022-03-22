package com.obcamp.OBCCrypto.network.core.messaging.payloads.dto;

import com.obcamp.OBCCrypto.network.config.MsgConstants;
import com.obcamp.OBCCrypto.network.core.entities.HostInterface;
import com.obcamp.OBCCrypto.network.core.entities.Peer;
import com.obcamp.OBCCrypto.network.core.messaging.payloads.base.Writable;

import java.nio.ByteBuffer;

/**
 * Esta clase representa el payload del mensaje version
 *
 * @author Manu
 * @version 1.0
 * @since 1.0
 * @see Peer
 * @see MsgConstants
 * @see Writable
 */
public class VersionDTO implements Writable{

    /**
     * La version más alta del protocolo que entiende el nodo que transmite el mensaje
     */
    int version;

    /**
     * La info del nodo que envía el mensaje: un timestamp, los servicios que soporta y la IP/puerto.
     */
    Peer node; // TODO habría que ver como obtener la IP/puerto propia

    /**
     * para detectar conexiones a sí mismo
     */
    long nonce;

    /**
     * La altura de la mejor cadena del nodo que transmite el mensaje
     */
    int startHeight;

    // constructores


    public VersionDTO(Peer peer, long nonce, int startHeight) {
        this.version = MsgConstants.MESSAGE_FORMAT_VERSION_INTEGER;
        this.node = peer;

        // Estos dos se podrían obtener desde algún servicio
        this.nonce = nonce;
        this.startHeight = startHeight;
    }

    // getters


    public int getVersion() {
        return version;
    }

    public HostInterface getNode() {
        return node;
    }

    public long getNonce() {
        return nonce;
    }

    public int getStartHeight() {
        return startHeight;
    }

    // metodos implementados de Writable


    /**
     * Devuelve el contenido del mensaje versión en bytes.
     * <pre>
     *     <p>El orden de los elementos del mensaje es:</p>
     *     <p>
     *         - int -> Version
     *         - long -> timestamp
     *         - NodeServices -> services (transmitidos como "t" o "f"
     *         - byte[16] -> IP (v6 o v4 mapeada)
     *         - int -> puerto
     *         - long -> nonce
     *         -int > la altura de la mejor cadena del bloque
     *     </p>
     * </pre>
     * @return Un bytebuffer con el contenido del mensaje version
     */
    @Override
    public ByteBuffer getBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(this.getBytesSize());

        buffer.putInt(this.version)
                .put(this.node.getBytes())
                .putLong(this.nonce)
                .putInt(this.startHeight);
        return buffer;
    }

    @Override
    public int getBytesSize() {
        // 2 int, 1 long y 1 Peer -> 8 + 8 + 30 = 46 bytes
        return 16 + this.node.getBytesSize();
    }

    @Override
    public boolean isFixedSize() {
        return true; // ningún campo es variable
    }
}
