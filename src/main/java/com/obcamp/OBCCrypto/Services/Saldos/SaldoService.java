package com.obcamp.OBCCrypto.Services.Saldos;

import com.obcamp.OBCCrypto.Models.Transacciones.Transaccion;
import com.obcamp.OBCCrypto.Repositories.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Proyecto OBC-Crypto
 * Servicio que calcula los saldos de una wallet
 * @author Josema
 * @version 1.0
 * Equipo: Josema, Adrian, Manu
 */
public class SaldoService {
    //ATRIBUTOS
    @Autowired
    public TransaccionRepository transaccionRepository;

    //CONSTRUCTORES

    /**
     * Constructor sin parametros
     */
    public SaldoService() {
    }

    /**
     * Constructor con parametros
     * @param transaccionRepository repositorio de transacciones
     */
    public SaldoService(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    //METODOS

    /**
     * Metodo que calcula los saldos del emisor y del recptor despues de una transaccion
     * @param publicKey wallet
     * @param emisor definimos true si es emisor y false si es receptor
     * @param cantidadEnviada cantidad enviada en la transaccion
     * @param comision comision de la transaccion
     * @return el calculo del saldo
     */
    public double calculoSaldos(PublicKey publicKey, boolean emisor, double cantidadEnviada, double comision ){
        double saldo = 0d;
        Transaccion ultimaTransaccion;
        //Si es emisor restamos la cantidad de la transaccion y la comision
        if (emisor == true) {
             ultimaTransaccion = ultimaTransaccion(publicKey);
             //Si existen transacciones de emisor se calcula el saldo despues de la transaccion
             if(ultimaTransaccion != null) {
                 saldo = ultimaTransaccion.getDatosAdicionales().getSaldoActualReceptor() - comision - cantidadEnviada;
             //Si no existen ninguna transacción el saldo para esa wallet es 0
             }else{
                 saldo = 0;
             }
        //Si es el receptor sumamos la cantidad de la transaccion
        }else{
            ultimaTransaccion = ultimaTransaccion(publicKey);
            saldo = ultimaTransaccion.getDatosAdicionales().getSaldoActualReceptor() + cantidadEnviada;
        }
        return saldo;
    }

    /**
     * Devuleve la ultima transaccion en la que participo la wallet pasada por parametro
     * @param publicKey wallet buscada
     * @return saultima transaccion
     */
    private Transaccion ultimaTransaccion(PublicKey publicKey){
        Transaccion ultimaTransaccion = null;
        List<Transaccion> listaTransacciones = null;

        listaTransacciones = this.transaccionRepository.findAllByReceptor(publicKey);
        if(listaTransacciones != null) {
            for (int i = 1; i < listaTransacciones.size(); i++) {
                if (i == 1) {
                    ultimaTransaccion = listaTransacciones.get(i - 1);
                }
                if (ultimaTransaccion.getHoraTransaccion().isBefore(listaTransacciones.get(i).getHoraTransaccion())) {
                    ultimaTransaccion = listaTransacciones.get(i);
                }
            }
        }
        return ultimaTransaccion;
    }

    //GETTER Y SETTER

    public TransaccionRepository getTransaccionRepository() {
        return transaccionRepository;
    }

    public void setTransaccionRepository(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }
}
