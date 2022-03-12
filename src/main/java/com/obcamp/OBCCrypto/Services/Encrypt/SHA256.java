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
    public static byte[] getSHA256(byte[] cadena){
        byte[] resultado = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(cadena);
            resultado =digest.digest();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("No se encuentra el algoritmo instanciado: " + e.getMessage());
        }
        return resultado;
    }

    //GETTER Y SETTER
}
