package com.obcamp.OBCCrypto.Models.Interfaces;
/**
 * Proyecto OBC-Crypto
 * Interface Serializar, definirá el comportamiento de las clases que necesiten implementar
 * el serializado y desserializado de objetos
 * @author Josema
 * @version 1.0
 * Equipo: Josema, Adrian, Manu
 */
public interface Serializar<T> {
    boolean guardar(T objeto);
    boolean deserializar(T objeto);
}
