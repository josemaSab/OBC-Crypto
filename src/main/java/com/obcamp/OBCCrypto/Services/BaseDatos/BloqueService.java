package com.obcamp.OBCCrypto.Services.BaseDatos;

import com.obcamp.OBCCrypto.Models.Bloques.Bloque;
import com.obcamp.OBCCrypto.Models.Transacciones.Transaccion;
import com.obcamp.OBCCrypto.Repositories.BloqueRepository;
import com.obcamp.OBCCrypto.Repositories.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Proyecto OBC-Crypto
 * Servicio que realiza todas las funciones de crud sobre la base de datos
 * @author Josema
 * @version 1.0
 * Equipo: Josema, Adrian, Manu
 */
@Service
public class BloqueService {
    //ATRIBUTOS
    @Autowired
    BloqueRepository bloqueRepository;
    @Autowired
    TransaccionRepository transaccionRepository;
    //CONTRUCTORES

    /**
     * Constructor sin parametros
     */
    public BloqueService() {
    }

    /**
     * Consutrctor con parametros
     * @param bloqueRepository repositorio de bloque
     * @param transaccionRepository repositorio de transaccion
     */
    public BloqueService(BloqueRepository bloqueRepository, TransaccionRepository transaccionRepository) {
        this.bloqueRepository = bloqueRepository;
        this.transaccionRepository = transaccionRepository;
    }


    //METODOS

    /**
     * Encuentra un Bloque por su ID
     * @param id del bloque
     * @return Bloque bloque buscado
     */
    public Bloque findById(Long id) {
        return bloqueRepository.findById(id).get();
    }

    /**
     * Encuentra un bloque por su hash
     * @param hashString hash del bloque
     * @return Bloque buscado
     */
    public Bloque findByHashActual(String hashString){
        byte[] hash = hashString.getBytes();
        return bloqueRepository.findByHashActual(hash);
    }

    /**
     * Devuelve la cadena de bloques
     * @return cadena de bloques completa
     */
    public List<Bloque> findAll() {
        return bloqueRepository.findAll();
    }

    /**
     * Guarda un bloque en la base de datos
     * @param bloque pasado
     * @return true si lo ha guardado sin problemas y false si hubo algun problema
     */
    public boolean save(Bloque bloque) {
        //TODO SE PUEDE CREAR UNA EXCEPCIÓN PARA CONTROLAR QUE YA EXISTE UN BLOQUE CON UN HASH DEL BLOQUE ANTERIOR
        //IGUAL QUE EL QUE QUEREMOS GUARDAR
        if(bloqueRepository.existsByHashAnterior(bloque.getHashAnterior())){
            return false;
        }
        List<Transaccion> transacciones = bloque.getTransacciones();
        try{
            for(Transaccion t : transacciones){
                transaccionRepository.save(t);
            }
            bloqueRepository.save(bloque);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
