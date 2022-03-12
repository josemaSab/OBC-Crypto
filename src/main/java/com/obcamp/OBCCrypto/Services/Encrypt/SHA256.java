package com.obcamp.OBCCrypto.Services.Encrypt;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Proyecto OBC-Crypto
 * Clase que define la encriptación en SHA-256
 * @author Josema
 * @version 1.0
 * Equipo: Josema, Adrian, Manu
 */
public class SHA256 {

    //METODOS

    /**
     * Metodo que hashea un string
     * @param cadena string a hashear
     * @return hash del string en hexadecimal
     */
    public static String getSHA256(String cadena){
        String resultado = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update((cadena.getBytes(StandardCharsets.UTF_8)));
            resultado = String.format("%064x",new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            System.err.println("No se encuentra el algoritmo instanciado: " + e.getMessage());
        }
        return resultado;
    }

    //GETTER Y SETTER
}
