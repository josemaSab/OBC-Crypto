package com.obcamp.OBCCrypto.Models.Bloques;

import com.obcamp.OBCCrypto.Models.Transacciones.Transaccion;
import com.obcamp.OBCCrypto.Services.Encrypt.SHA256;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Proyecto OBC-Crypto
 * Clase Bloque
 * @author Josema
 * @version 1.0
 * Equipo: Josema, Adrian, Manu
 */
@Entity
public class Bloque implements Serializable {

    //ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numeroBloque;
    private String hashActual;
    private String hashAnterior;
    private LocalDateTime horaCreacionBloque;
    private long nonce;
    private int dificultad;
    //Relacion oneToMany con Transacción
    private Set<Transaccion> transacciones = new HashSet<>();

    //CONSTRUCTORES

    /**
     * Contructor con parametros
     * @param hashAnterior hash del bloque anterior
     * @param transacciones lista de transacciones
     * @param nonce numero para la prueba de trabajo
     */
    public Bloque(String hashAnterior, Set<Transaccion> transacciones, long nonce) {
        this.hashActual = SHA256.getSHA256(this.bloqueToString());
        this.hashAnterior = hashAnterior;
        this.transacciones = transacciones;
        this.horaCreacionBloque = LocalDateTime.now(ZoneOffset.UTC);
        this.nonce = nonce;
        this.dificultad = 0;
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

    public Set<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(Set<Transaccion> transacciones) {
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
