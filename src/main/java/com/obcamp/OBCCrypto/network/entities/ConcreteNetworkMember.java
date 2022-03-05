package com.obcamp.OBCCrypto.network.entities;

import com.obcamp.OBCCrypto.network.entities.base.AbstractNetworkMember;
import com.obcamp.OBCCrypto.network.messaging.v1.MessageCommand;

import java.net.InetAddress;


/**
 * Esta clase representa cualquier dirección IP que participa en la comunicación en la blockchain.
 * Cuando un miembro de la red toma contacto con un desconocido, tratara de clasificarlo entre tres categorias,
 * en base a su comportamiento en la red. La categoría determina el tipo de mensajes que se le enviarán.
 *
 * @author Manu
 * @version 1.0
 */
public class ConcreteNetworkMember extends AbstractNetworkMember implements Subscriber {

    private NetworkMemberOperationMode operationMode = NetworkMemberOperationMode.UNDEFINED;
    private NetworkMemberStatus status;


    // Constructores

    public ConcreteNetworkMember(InetAddress address) {
        super(address);
    }

    public ConcreteNetworkMember(InetAddress address, NetworkMemberOperationMode operationMode, NetworkMemberStatus status) {
        super(address);
        this.operationMode = operationMode;
        this.status = status;
    }

    // getter y setter

    public NetworkMemberOperationMode getOperationMode() {
        return operationMode;
    }


    public void setOperationMode(NetworkMemberOperationMode operationMode) {
        this.operationMode = operationMode;
    }

    public NetworkMemberStatus getStatus() {
        return status;
    }

    public void setStatus(NetworkMemberStatus status) {
        this.status = status;
    }

    // Overrides


    /**
     * TODO implementar la interfaz Subscriber. Se necesita antes la parte de TCP.
     * Este metodo se encargara de enviar los mensajes. Lo dejo para implementarlo cuando este la parte de TCP
     * más avanzada.
     *
     * El metodo esencialmente pasara el msg a una clase que se ocupe de gestionar el proceso de serialiación, el
     * establecimiento de la conexión TCP, y la gestión de las respuestas.
     *
     * La idea general es agrupar los miembros en colecciones en función de su operationMode, y según el MessageCommandType
     * notificar a una o más colecciones
     *
     * @param msg Una instancia de MessageCommand ya inicializada.
     *
     */
    @Override
    public void notify(MessageCommand msg) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("El método no está implementado")
         ;
    }
}
