package com.obcamp.OBCCrypto.Services.BaseDatos;

import com.obcamp.OBCCrypto.Repositories.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Proyecto OBC-Crypto
 * Servicio que realiza todas las funciones de crud sobre la base de datos
 * @author Josema
 * @version 1.0
 * Equipo: Josema, Adrian, Manu
 */
@Service
public class TransaccionService {
    //ATRIBUTOS
    @Autowired
    TransaccionRepository transaccionRepository;

    //CONSTRUCTORES

    /**
     * Constructor con parametros
     * @param transaccionRepository Repositorio de transaccion
     */
    public TransaccionService(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    //METODOS

    //GETTER Y SETTER

    public TransaccionRepository getTransaccionRepository() {
        return transaccionRepository;
    }

    public void setTransaccionRepository(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }
}
