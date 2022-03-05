package com.obcamp.OBCCrypto.network.entities;

public enum NetworkMemberStatus {

    ACTIVE, // Si el miembro de la red sigue correctamente el protocolo
    SILENT, // Si el miembro de la red no contesta durante 1h  (p. ej)
    DEAD,   // Si el miembro de la red no contesta durante 48h (p.ej)

    BANED,  // Si el miembro de la red hace excesivas peticiones consecutivas (para loggearlo más que otra cosa)

}
