package com.obcamp.OBCCrypto.Models.Wallets;


import com.starkbank.ellipticcurve.Curve;
import com.starkbank.ellipticcurve.PrivateKey;
import com.starkbank.ellipticcurve.PublicKey;

import java.math.BigInteger;

/**
 * Proyecto OBC-Crypto
 * Clase Wallet
 * @author Josema
 * @version 1.0
 * Equipo: Josema, Adrian, Manu
 */
public class Wallet {

    //ATRIBUTOS
    private PrivateKey privateKey;
    private PublicKey publicKey;

    //CONSTRUCTORES

    /**
     * Constructor el cual le pasamos una clave que solo la sabe el usuario para generar las claves privadas y publicas
     * @param claveUsuario clave del usuario
     */
    public Wallet(String claveUsuario) {

        this.privateKey = new PrivateKey(Curve.secp256k1,new BigInteger(claveUsuario.getBytes()));
        this.publicKey = privateKey.publicKey();
    }

    //GETTER Y SETTER

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }
}
