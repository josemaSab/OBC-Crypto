package com.obcamp.OBCCrypto.Services.BaseDatos;

import java.io.Serializable;
import java.util.List;

/**
 * Proyecto OBC-Crypto
 * Interface que define los metodos CRUD genericos
 * @author Josema
 * @version 1.0
 * Equipo: Josema, Adrian, Manu
 */
public interface GenericCRUDService<T, ID extends Serializable> {
    T findById (ID id);
    List<T> findAll();
    boolean save(T objeto);
    boolean update(T objeto);
    boolean deleteById(ID id);
    boolean deleteAll();
}
