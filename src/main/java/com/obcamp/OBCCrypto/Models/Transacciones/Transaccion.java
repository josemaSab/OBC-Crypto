package com.obcamp.OBCCrypto.Models.Transacciones;

import com.obcamp.OBCCrypto.Models.Bloques.Bloque;
import com.obcamp.OBCCrypto.Services.Encrypt.SHA256;

import javax.persistence.*;
import java.io.Serializable;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Proyecto OBC-Crypto
 * Clase Transaccion
 * @author Josema
 * @version 1.0
 * Equipo: Josema, Adrian, Manu
 */
@Entity
@Table(name = "Transacciones")
public class Transaccion implements Serializable {

    //ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PublicKey emisor;
    private PublicKey receptor;
    private LocalDateTime horaTransaccion;
    private DatosTransacción datosAdicionales;
    private double cantidad;
    private byte[] hash;

    //Relaciones
    @ManyToOne
    @JoinColumn(name = "bloque_id")
    private Bloque bloque;


    //CONSTRUCTORES

    /**
     * Constructor con parametros
     * @param emisor clave publica emisor
     * @param receptor clave publica receptor
     * @param datosAdicionales datos adicionales de la transaccion
     * @param cantidad cantidad a enviar
     */
    public Transaccion(PublicKey emisor, PublicKey receptor, DatosTransacción datosAdicionales, double cantidad) {
        this.emisor = emisor;
        this.receptor = receptor;
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
    private byte[] getHashTransaccion(){
        return SHA256.getSHA256(this.transaccionToString()).getBytes();
    }

    /**
     * Metodo que pasa todos los valores de la transacción a un string
     * @return un string con todos los valores de la transacción
     */
    public String transaccionToString() {
        return "Transaccion{" +
                "emisor='" + emisor + '\'' +
                ", receptor='" + receptor + '\'' +
                ", horaTransaccion=" + horaTransaccion +
                ", datosAdicionales=" + datosAdicionales +
                ", cantidad=" + cantidad +
                '}';
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "emisor='" + emisor + '\'' + "\n"+
                ", receptor='" + receptor + '\'' + "\n"+
                ", horaTransaccion=" + horaTransaccion + "\n"+
                ", datosAdicionales=" + datosAdicionales.toString() + "\n"+
                ", cantidad=" + cantidad + "\n"+
                ", hash='" + new String(hash) + '\'' +
                '}';
    }

    //GETTER Y SETTER

    public PublicKey getEmisor() {
        return emisor;
    }

    public void setEmisor(PublicKey emisor) {
        this.emisor = emisor;
    }

    public PublicKey getReceptor() {
        return receptor;
    }

    public void setReceptor(PublicKey receptor) {
        this.receptor = receptor;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getHash() {
        return hash;
    }

    public void setHash(byte[] hash) {
        this.hash = hash;
    }
}
