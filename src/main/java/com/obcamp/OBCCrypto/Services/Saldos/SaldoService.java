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

    //METODOS

    /**
     * Calcula el saldo actual del emisor
     * @param publicKey wallet del emisor
     * @return saldo actual
     */
    public double calculoSaldoActualEmisor(PublicKey publicKey){
        double saldoActual = 0d;
        Transaccion ultimaTransaccion = null;
        List<Transaccion> listaTransacciones = new ArrayList<>();

        listaTransacciones = this.transaccionRepository.findAllByEmisor(publicKey);
        for(int i = 1; i< listaTransacciones.size(); i++){
            if(i == 1){
                ultimaTransaccion = listaTransacciones.get(i-1);
            }
            if(ultimaTransaccion.getHoraTransaccion().isBefore(listaTransacciones.get(i).getHoraTransaccion())){
                ultimaTransaccion = listaTransacciones.get(i);
            }
        }
        saldoActual = ultimaTransaccion.getDatosAdicionales().getSaldoActualEmisor();
        return saldoActual;
    }

    /**
     * Calculo actual del receptor
     * @param publicKey wallet del receptor
     * @return saldo actual
     */
    public double calculoSaldoActualReceptor(PublicKey publicKey){
        double saldoActual = 0d;
        Transaccion ultimaTransaccion = null;
        List<Transaccion> listaTransacciones = new ArrayList<>();

        listaTransacciones = this.transaccionRepository.findAllByEmisor(publicKey);
        for(int i = 1; i< listaTransacciones.size(); i++){
            if(i == 1){
                ultimaTransaccion = listaTransacciones.get(i-1);
            }
            if(ultimaTransaccion.getHoraTransaccion().isBefore(listaTransacciones.get(i).getHoraTransaccion())){
                ultimaTransaccion = listaTransacciones.get(i);
            }
        }
        saldoActual = ultimaTransaccion.getDatosAdicionales().getSaldoActualReceptor();
        return saldoActual;
    }

    //GETTER Y SETTER

    public TransaccionRepository getTransaccionRepository() {
        return transaccionRepository;
    }

    public void setTransaccionRepository(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }
}
