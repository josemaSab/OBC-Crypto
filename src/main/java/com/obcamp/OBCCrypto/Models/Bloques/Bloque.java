package com.obcamp.OBCCrypto.Models.Bloques;

import com.obcamp.OBCCrypto.Models.Transacciones.Transaccion;
import com.obcamp.OBCCrypto.Services.Encrypt.SHA256;
import com.obcamp.OBCCrypto.Services.POW.PruebaTrabajoService;

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
     * @param dificultad del bloque
     */
    public Bloque(byte[] hashAnterior, List<Transaccion> transacciones, int dificultad) {
        this.hashAnterior = hashAnterior;
        this.transacciones = transacciones;
        this.horaCreacionBloque = LocalDateTime.now(ZoneOffset.UTC);
        this.nonce = 0;
        this.dificultad = dificultad;
    }

    //METODOS

    /**
     * Metodo que crea un string con todos los datos del bloque
     * @return string con todos los datos del bloque
     */
    public String bloqueToString() {
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
                "hashActual='" + new String(hashActual)+ '\'' +
                ", hashAnterior='" + new String(hashAnterior) + '\'' +
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
