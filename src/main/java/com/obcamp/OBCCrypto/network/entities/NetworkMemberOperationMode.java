package com.obcamp.OBCCrypto.network.entities;

public enum NetworkMemberOperationMode {

    UNDEFINED,  // Si aun no tiene asignada otra categoría

    NODE,       // Los nodos de la red (los que minan)

    RELAY,   // Miembros que solo observan (no minan, pero se les retransmiten los nuevos bloques)
                // y retransmiten direcciones.

    SEEDNODE,   // Los nodos que solo retransmiten direcciones de miembros de la red

}
