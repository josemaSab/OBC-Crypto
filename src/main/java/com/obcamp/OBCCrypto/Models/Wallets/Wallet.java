package com.obcamp.OBCCrypto.Models.Wallets;


import com.obcamp.OBCCrypto.Services.Wallet.WalletService;
import com.starkbank.ellipticcurve.Ecdsa;


import java.security.*;

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
    private KeyPair keyPair;

    //CONSTRUCTORES

    /**
     * Constructor que genera las claves privadas y publicas
     */
    public Wallet() {
        try {
            this.keyPair = WalletService.generarKeyPair();
            this.privateKey = keyPair.getPrivate();
            this.publicKey = keyPair.getPublic();
        } catch (NoSuchAlgorithmException e) {
            this.keyPair = null;
            System.err.println("Hubo un error. No se encuentra el algoritmo. " + e.getMessage() );
        } catch (InvalidAlgorithmParameterException e) {
            this.keyPair = null;
            System.err.println("Hubo un error. Los parametros del algoritmo son incorrectos. " + e.getMessage() );
        }

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
