package com.obcamp.OBCCrypto.Models.Transacciones;

import com.obcamp.OBCCrypto.Services.Saldos.SaldoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.security.PublicKey;

/**
 * Proyecto OBC-Crypto
 * Clase Datos Transaccion
 * @author Josema
 * @version 1.0
 * Equipo: Josema, Adrian, Manu
 */
public class DatosTransacción implements Serializable {

    //ATRBUTOS
    @Autowired
    private SaldoService saldoService ;
    private double saldoActualEmisor;
    private double saldoActualReceptor;
    private String datos;

    //CONSTRUCTORES

    /**
     * Constructor con parametros
     * @param datos adicionales de la transaccion
     * @param emisor clave publica del emisor(wallet)
     * @param receptor clave publica del emisor(wallet)
     */
    public DatosTransacción(String datos, PublicKey emisor, PublicKey receptor, double cantidadEnviada, double comision) {
        this.datos = datos;
        this.saldoActualEmisor = saldoService.calculoSaldos(emisor,true, cantidadEnviada, comision);
        this.saldoActualReceptor = saldoService.calculoSaldos(receptor, false, cantidadEnviada, comision);
    }

    @Override
    public String toString() {
        return "DatosTransacción{" +
                "saldoActualEmisor=" + saldoActualEmisor +
                ", saldoActualReceptor=" + saldoActualReceptor +
                ", datos='" + datos + '\'' +
                '}';
    }

    //GETTER Y SETTER

    public double getSaldoActualEmisor() {
        return saldoActualEmisor;
    }

    public void setSaldoActualEmisor(double saldoActualEmisor) {
        this.saldoActualEmisor = saldoActualEmisor;
    }

    public double getSaldoActualReceptor() {
        return saldoActualReceptor;
    }

    public void setSaldoActualReceptor(double saldoActualReceptor) {
        this.saldoActualReceptor = saldoActualReceptor;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }
}
