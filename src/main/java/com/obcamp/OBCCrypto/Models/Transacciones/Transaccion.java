package com.obcamp.OBCCrypto.Models.Transacciones;

import com.obcamp.OBCCrypto.Services.Encrypt.SHA256;

import java.io.Serializable;
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
public class Transaccion implements Serializable {

    //ATRIBUTOS

    private String emisor;
    private String receptor;
    private String firma;
    private LocalDateTime horaTransaccion;
    private DatosTransacción datosAdicionales;
    private double cantidad;
    private String hash;

    //CONSTRUCTORES

    /**
     * Constructor con parametros
     * @param emisor clave publica emisor
     * @param receptor clave publica receptor
     * @param firma clave privada emisor
     * @param datosAdicionales datos adicionales de la transaccion
     * @param cantidad enviada
     */
    public Transaccion(String emisor, String receptor, String firma, DatosTransacción datosAdicionales, double cantidad) {
        this.emisor = emisor;
        this.receptor = receptor;
        this.firma = firma;
        this.datosAdicionales = datosAdicionales;
        this.cantidad = cantidad;
        this.horaTransaccion = LocalDateTime.now(ZoneOffset.UTC);
        this.hash = this.getHashTransaccion();
    }

    //METODOS

    /**
     * Metodo que hashea los datos de la transaccion
     * @return hash de la transaccion.
     */
    private String getHashTransaccion(){
        return SHA256.getSHA256(this.transaccionToString());
    }

    /**
     * Metodo que pasa todos los valores de la transacción a un string
     * @return un string con todos los valores de la transacción
     */
    public String transaccionToString() {
        return "Transaccion{" +
                "emisor='" + emisor + '\'' +
                ", receptor='" + receptor + '\'' +
                ", firma='" + firma + '\'' +
                ", horaTransaccion=" + horaTransaccion +
                ", datosAdicionales=" + datosAdicionales +
                ", cantidad=" + cantidad +
                '}';
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "emisor='" + emisor + '\'' +
                ", receptor='" + receptor + '\'' +
                ", firma='" + firma + '\'' +
                ", horaTransaccion=" + horaTransaccion +
                ", datosAdicionales=" + datosAdicionales +
                ", cantidad=" + cantidad +
                ", hash='" + hash + '\'' +
                '}';
    }

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

    public DatosTransacción getDatosAdicionales() {
        return datosAdicionales;
    }

    public void setDatosAdicionales(DatosTransacción datosAdicionales) {
        this.datosAdicionales = datosAdicionales;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
}
