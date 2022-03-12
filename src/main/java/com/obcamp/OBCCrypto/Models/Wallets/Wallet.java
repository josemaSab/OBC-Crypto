package com.obcamp.OBCCrypto.Models.Wallets;

import com.obcamp.OBCCrypto.Services.Encrypt.SHA256;

/**
 * Proyecto OBC-Crypto
 * Clase Wallet
 * @author Josema
 * @version 1.0
 * Equipo: Josema, Adrian, Manu
 */
public class Wallet {

    //ATRIBUTOS

    private byte[] claveUsuario;
    private byte[] clavePublica;
    private byte[] clavePrivada;

    //CONSTRUCTORES

    /**
     * Consutor con un unico parametro.
     * @param claveUsuario es la clave de usuario a través de la cual se obtendrá
     * la clave publica(direccion de la wallet) y clave privada(firma digital).
     * Primero se obtiene la clave privada a traves de la clave de usuario. Una vez obtenida la
     * clave privada se vuelve a Hashear para obtener la clave publica.
     */
    public Wallet(String claveUsuario) {
        this.claveUsuario = claveUsuario.getBytes();
        //this.clavePrivada = TODO HASHEAR CON EL METODO QUE SE CREE EN ENCRIPTSERVICE
        this.clavePublica = SHA256.getSHA256(this.clavePrivada);
    }

    //GETTER Y SETTER

    public byte[] getClaveUsuario() {
        return claveUsuario;
    }

    public byte[] getClavePublica() {
        return clavePublica;
    }

    public void setClavePublica(byte[] clavePublica) {
        this.clavePublica = clavePublica;
    }

    public byte[] getClavePrivada() {
        return clavePrivada;
    }

    public void setClavePrivada(byte[] clavePrivada) {
        this.clavePrivada = clavePrivada;
    }
}
