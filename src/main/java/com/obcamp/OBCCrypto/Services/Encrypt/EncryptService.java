package com.obcamp.OBCCrypto.Services.Encrypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;

/**
 * Proyecto OBC-Crypto
 * Servicio encargado de la encripración.
 * @author Josema
 * @version 1.0
 * Equipo: Josema, Adrian, Manu
 */
public class EncryptService {
    /**
     * Metodo que encripta un mensaje pasando una clave y el tipo de cifrado a aplicar
     * @param mensaje a encriptar
     * @param claveEnciptacion de encriptacion
     * @return string con el mensaje cifrado
     */
    public static byte[] encryptMensaje(byte[] mensaje, SecretKey claveEnciptacion) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, InvalidAlgorithmParameterException {
        byte[] encriptado = null;
        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, claveEnciptacion);
        encriptado = cipher.doFinal(mensaje);
        return encriptado;
    }

    /**
     * Metodo que desencripta un mensaje pasando una clave y el tipo de cifrado para desencriptar
     * el mensaje.
     * @param mensajeCifrado a desencriptar
     * @param claveEncriptacion de encriptacion.
     * @param algoritmoSK algoritmo utilizado para crear la claveEnciptacion
     * @return String con el mensaje desencriptado
     */
    public static byte[] decryptMensaje(byte[] mensajeCifrado, SecretKey claveEncriptacion, String algoritmoSK) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, InvalidAlgorithmParameterException {
        byte[] desencriptado = null;
        Security.addProvider(new BouncyCastleProvider());
        SecretKeySpec spec = new SecretKeySpec(claveEncriptacion.getEncoded(), algoritmoSK);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, spec);
        desencriptado = cipher.doFinal(mensajeCifrado);
        return desencriptado;
    }

    /**
     * Metodo que genera una clave segun el algoritmo de cifrado pasado por parametro
     * @param algoritmo de cifrado
     * @return un SecretKey
     * @throws NoSuchAlgorithmException si no encuentra el agoritmo salta una excepcion
     */
    public static SecretKey generarKey(String algoritmo) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algoritmo);
        keyGenerator.init(128);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }
}
