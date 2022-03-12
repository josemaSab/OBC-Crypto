package com.obcamp.OBCCrypto.Models.Bloques;

import com.obcamp.OBCCrypto.Models.Transacciones.Transaccion;
import com.obcamp.OBCCrypto.Services.Encrypt.SHA256;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Entity
public class Bloque {

    //ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numeroBloque;
    private byte[] hashActual;
    private byte[] hashAnterior;
    private List<Transaccion> transacciones;
    private LocalDateTime horaCreacionBloque;
    private long nonce;
    private int dificultad;

    //CONSTRUCTORES

    /**
     * Contructor con parametros
     * @param hashAnterior hash del bloque anterior
     * @param transacciones lista de transacciones
     * @param nonce numero para la prueba de trabajo
     */
    public Bloque(byte[] hashAnterior, List<Transaccion> transacciones, long nonce) {
        this.hashAnterior = hashAnterior;
        this.transacciones = transacciones;
        this.horaCreacionBloque = LocalDateTime.now(ZoneOffset.UTC);
        this.nonce = nonce;
        this.dificultad = 0;
        //this.bloqueActual = TODO hacer la prueba de trabajo
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
                ", dificultad=" + dificultad +
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
                ", dificultad=" + dificultad +
                '}';
    }

    //GETTER Y SETTER

    public byte[] getHashActual() {
        return hashActual;
    }

    public void setHashActual(byte[] hashActual) {
        this.hashActual = hashActual;
    }

    public byte[] getHashAnterior() {
        return hashAnterior;
    }

    public void setHashAnterior(byte[] hashAnterior) {
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

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }
}
