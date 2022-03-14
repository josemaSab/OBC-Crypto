package com.obcamp.OBCCrypto.Services.Wallet;



import java.security.*;
import java.security.spec.ECGenParameterSpec;

/**
 * Proyecto OBC-Crypto
 * Clase encargada de la generar la clave privada y clave publica de un usuario
 * @author Josema
 * @version 1.0
 * Equipo: Josema, Adrian, Manu
 */
public class WalletService {


    //METODOS

    /**
     * Metodo que genera un par de claves ECDSA
     * @return par de claves ECDSA
     * @throws NoSuchAlgorithmException excepción que lanzara si no encuentra el algoritmo
     * @throws InvalidAlgorithmParameterException excepcion que se lanzara si los parametros del algoritmo son invalidos
     */
    public static KeyPair generarKeyPair() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        Security.insertProviderAt(new org.bouncycastle.jce.provider.BouncyCastleProvider(), 1);
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
        keyPairGenerator.initialize(ecSpec);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }



}
