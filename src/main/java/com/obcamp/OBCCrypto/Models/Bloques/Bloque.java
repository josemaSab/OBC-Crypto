package com.obcamp.OBCCrypto.Models.Bloques;

import com.obcamp.OBCCrypto.Models.Transacciones.Transaccion;
import com.obcamp.OBCCrypto.Services.Encrypt.SHA256;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

/**
 * Proyecto OBC-Crypto
 * Clase Bloque
 * @author Josema
 * @version 1.0
 * Equipo: Josema, Adrian, Manu
 */
public abstract class Bloque {

    //ATRIBUTOS

    private String hashActual;
    private String hashAnterior;
    private List<Transaccion> transacciones;
    private LocalDateTime horaCreacionBloque;
    private long nonce;

    //CONSTRUCTORES

    /**
     * Contructor con parametros
     * @param hashAnterior hash del bloque anterior
     * @param transacciones lista de transacciones
     * @param nonce numero para la prueba de trabajo
     */
    public Bloque(String hashAnterior, List<Transaccion> transacciones, long nonce) {
        this.hashActual = SHA256.getSHA256(this.bloqueToString());
        this.hashAnterior = hashAnterior;
        this.transacciones = transacciones;
        this.horaCreacionBloque = LocalDateTime.now(ZoneOffset.UTC);
        this.nonce = nonce;
    }

    //METODOS

    /**
     * Metodo que crea un string con todos los datos del bloque
     * @return string con todos los datos del bloque
     */
    private String bloqueToString() {
        return "Bloque{" +
                ", hashAnterior='" + hashAnterior + '\'' +
                ", transacciones=" + transacciones +
                ", horaCreacionBloque=" + horaCreacionBloque +
                ", nonce=" + nonce +
                '}';
    }

    @Override
    public String toString() {
        return "Bloque{" +
                "hashActual='" + hashActual + '\'' +
                ", hashAnterior='" + hashAnterior + '\'' +
                ", transacciones=" + transacciones +
                ", horaCreacionBloque=" + horaCreacionBloque +
                ", nonce=" + nonce +
                '}';
    }

    //GETTER Y SETTER

    public String getHashActual() {
        return hashActual;
    }

    public void setHashActual(String hashActual) {
        this.hashActual = hashActual;
    }

    public String getHashAnterior() {
        return hashAnterior;
    }

    public void setHashAnterior(String hashAnterior) {
        this.hashAnterior = hashAnterior;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public LocalDateTime getHoraCreacionBloque() {
        return horaCreacionBloque;
    }

    public void setHoraCreacionBloque(LocalDateTime horaCreacionBloque) {
        this.horaCreacionBloque = horaCreacionBloque;
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(long nonce) {
        this.nonce = nonce;
    }
}
