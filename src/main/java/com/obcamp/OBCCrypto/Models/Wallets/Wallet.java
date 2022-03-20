package com.obcamp.OBCCrypto.Models.Wallets;


import com.obcamp.OBCCrypto.Services.Wallet.WalletService;
import com.starkbank.ellipticcurve.Ecdsa;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
     * Constructor que genera las claves privadas y publicas y las serializa en un archivo local
     */
    public Wallet() {
        try {
            this.keyPair = WalletService.generarKeyPair();
            this.privateKey = keyPair.getPrivate();
            this.publicKey = keyPair.getPublic();
            //Guardamos las claves en local
            FileOutputStream fileOutputStream = new FileOutputStream("wallet.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this.keyPair);
            objectOutputStream.close();
        } catch (NoSuchAlgorithmException e) {
            this.keyPair = null;
            System.err.println("Hubo un error. No se encuentra el algoritmo. " + e.getMessage() );
        } catch (InvalidAlgorithmParameterException e) {
            this.keyPair = null;
            System.err.println("Hubo un error. Los parametros del algoritmo son incorrectos. " + e.getMessage() );
        } catch (FileNotFoundException e) {
            System.out.println("Hubo un error. No se encuentra el el fichero." +e.getMessage());
        } catch (IOException e) {
            System.out.println("Hubo un error con el Stream." + e.getMessage());
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
