package com.obcamp.OBCCrypto.Models.Transacciones;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Proyecto OBC-Crypto
 * Clase Transaccion
 * @author Josema
 * @version 1.0
 * Equipo: Josema, Adrian, Manu
 */
public class Transaccion {

    //ATRIBUTOS

    private String emisor;
    private String receptor;
    private String firma;
    private LocalDateTime horaTransaccion;
    private String datosAdicionales;
    private double cantidad;
    private String hash;

    //CONSTRUCTORES

    /**
     * Constructor con parametros
     * @param emisor clave publica emisor
     * @param receptor clave publica receptor
     * @param firma clave privada emisor
     * @param datosAdicionales datos adicionales de la transaccion
     * @param cantidad
     */
    public Transaccion(String emisor, String receptor, String firma, String datosAdicionales, double cantidad) {
        this.emisor = emisor;
        this.receptor = receptor;
        this.firma = firma;
        this.datosAdicionales = datosAdicionales;
        this.cantidad = cantidad;
        this.horaTransaccion = LocalDateTime.now(ZoneOffset.UTC);
    }

    //METODOS

    //GETTER Y SETTER

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public LocalDateTime getHoraTransaccion() {
        return horaTransaccion;
    }

    public void setHoraTransaccion(LocalDateTime horaTransaccion) {
        this.horaTransaccion = horaTransaccion;
    }

    public String getDatosAdicionales() {
        return datosAdicionales;
    }

    public void setDatosAdicionales(String datosAdicionales) {
        this.datosAdicionales = datosAdicionales;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
}
