package com.obcamp.OBCCrypto.Models;

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

    private String claveUsuario;
    private String clavePublica;
    private String clavePrivada;

    //CONSTRUCTORES

    /**
     * Consutor con un unico parametro.
     * @param claveUsuario es la clave de usuario a través de la cual se obtendrá
     * la clave publica(direccion de la wallet) y clave privada(firma digital).
     * Primero se obtiene la clave privada a traves de la clave de usuario. Una vez obtenida la
     * clave privada se vuelve a Hashear para obtener la clave publica.
     */
    public Wallet(String claveUsuario) {
        this.claveUsuario = claveUsuario;
        this.clavePrivada = SHA256.getSHA256(this.claveUsuario);
        this.clavePublica = SHA256.getSHA256(this.clavePrivada);
    }

    //GETTER Y SETTER

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public String getClavePublica() {
        return clavePublica;
    }

    public void setClavePublica(String clavePublica) {
        this.clavePublica = clavePublica;
    }

    public String getClavePrivada() {
        return clavePrivada;
    }

    public void setClavePrivada(String clavePrivada) {
        this.clavePrivada = clavePrivada;
    }
}
