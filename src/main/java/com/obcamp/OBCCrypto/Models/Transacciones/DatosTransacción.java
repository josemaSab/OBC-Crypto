package com.obcamp.OBCCrypto.Models.Transacciones;

import com.obcamp.OBCCrypto.Services.Saldos.SaldoService;

/**
 * Proyecto OBC-Crypto
 * Clase Datos Transaccion
 * @author Josema
 * @version 1.0
 * Equipo: Josema, Adrian, Manu
 */
public class DatosTransacción {

    //ATRBUTOS

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
    public DatosTransacción(String datos, String emisor, String receptor) {
        this.datos = datos;
        this.saldoActualEmisor = SaldoService.calculoSaldoActual(emisor);
        this.saldoActualReceptor = SaldoService.calculoSaldoActual(receptor);
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
