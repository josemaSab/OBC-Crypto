package com.obcamp.OBCCrypto.network.core.entities;

import com.obcamp.OBCCrypto.network.core.messaging.payloads.base.Writable;
import com.obcamp.OBCCrypto.network.core.messaging.payloads.dto.VersionDTO;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Esta clase representa el conjunto de "servicios" que un nodo puede prestar. En caso de que tanto
 * {@link #NodeNetwork} como {@link #NodeNetworkLimited} sean false, todavía se le pueden solicitar headers de bloques,
 * aunque la respuesta no está garantizada.
 * <p>Se incluye en los mensajes de tipo version</p>
 *
 * @author Manu
 * @version 1.0
 * @since 1.0
 * @see VersionDTO
 */
public class NodeServices implements Writable {

    /**
     * Si es {@code true}, al nodo se le puede pedir bloques enteros (es un "full node"), y debe implementar todas
     * las características del protocolo correspondientes a la versión que manifiesta.
     * Si es {@code false}, significa que no es un nodo "completo", y puede que sea incapaz de transmitir
     * más información que las transacciones que genera
     */
    private Boolean NodeNetwork;

    /**
     * Igual que {@link #NodeNetwork} pero el nodo conocerá solo los últimos ~288 bloques (unos ~2 días, depende de
     * como configuremos el ajuste de la dificultad)
     */
    private Boolean NodeNetworkLimited;

    // Constructores
    public NodeServices() {
    }


    public NodeServices(Boolean nodeNetwork, Boolean nodeNetworkLimited) {
        NodeNetwork = nodeNetwork;
        NodeNetworkLimited = nodeNetworkLimited;
    }

    // TODO este constructor quizas sobra teniendo el constructor vacío
    public NodeServices(String services){
        setNodeNetwork(services.substring(0,1));
        setNodeNetworkLimited(services.substring(1));
    }

    // getter y setter
    public Boolean getNodeNetwork() {
        return this.NodeNetwork;
    }

    public void setNodeNetwork(Boolean nodeNetwork) {
        this.NodeNetwork = nodeNetwork;
    }

    public void setNodeNetwork(String nodeNetwork){
        this.NodeNetwork = Objects.equals(nodeNetwork, "t");
    }

    public Boolean getNodeNetworkLimited() {
        return this.NodeNetworkLimited;
    }

    public void setNodeNetworkLimited(Boolean nodeNetworkLimited) {
        this.NodeNetworkLimited = nodeNetworkLimited;
    }

    public void setNodeNetworkLimited(String NodeNetworkLimited){
        this.NodeNetworkLimited = Objects.equals(NodeNetworkLimited, "t");
    }

    /**
     * Devuelve "t" o "f" (codificados en Latin 1) según el valor del argumento
     * @param flag un {@code boolean}
     * @return El {@code byte} correspondiente a "t" para {@code true}, "f" para {@code false}, codificado según el
     * conjunto de carácteres ISO_8859_1
     */
    protected byte getByteValue(boolean flag){
        String sValue = flag ? "t" : "f";
        return sValue.getBytes(StandardCharsets.ISO_8859_1)[0];
    }

    @Override
    public ByteBuffer getBytes() {
        ByteBuffer bytes = ByteBuffer.allocate(this.getBytesSize());

        bytes.put(getByteValue(this.NodeNetwork)).put(getByteValue(NodeNetworkLimited));
        return bytes;
    }

    @Override
    public int getBytesSize() {
        // 1 byte por boleano -> se transmiten como carácteres Latín 1
        return 2;
    }

    @Override
    public boolean isFixedSize() {
        return true;
    }
}
