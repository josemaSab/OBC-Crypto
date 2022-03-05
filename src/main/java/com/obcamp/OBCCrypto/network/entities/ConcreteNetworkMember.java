package com.obcamp.OBCCrypto.network.entities;

import com.obcamp.OBCCrypto.network.entities.base.AbstractNetworkMember;

import java.net.InetAddress;


/**
 * Esta clase representa cualquier dirección IP que participa en la comunicación en la blockchain.
 * Cuando un miembro de la red toma contacto con un desconocido, tratara de clasificarlo entre tres categorias,
 * en base a su comportamiento en la red. La categoría determina el tipo de mensajes que se le enviarán.
 *
 * @author Manu
 * @version 1.0
 */
public class ConcreteNetworkMember extends AbstractNetworkMember {

    private NetworkMemberCategory classOfMember = NetworkMemberCategory.UNDEFINED;
    private NetworkMemberStatus status;


    // Constructores

    public ConcreteNetworkMember(InetAddress address) {
        super(address);
    }

    public ConcreteNetworkMember(InetAddress address, NetworkMemberCategory classOfNode, NetworkMemberStatus status) {
        super(address);
        this.classOfMember = classOfNode;
        this.status = status;
    }

    // getter y setter

    public NetworkMemberCategory getClassOfMember() {
        return classOfMember;
    }

    public void setClassOfMember(NetworkMemberCategory classOfMember) {
        this.classOfMember = classOfMember;
    }

}
