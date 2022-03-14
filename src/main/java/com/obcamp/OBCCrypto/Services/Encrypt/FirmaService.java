package com.obcamp.OBCCrypto.Services.Encrypt;

import com.obcamp.OBCCrypto.Models.Transacciones.Transaccion;
import java.security.*;


/**
 * Proyecto OBC-Crypto
 * Servicio encargado de la firma de las transacciones.
 * @author Josema
 * @version 1.0
 * Equipo: Josema, Adrian, Manu
 */
public class FirmaService {

    //METODOS

    /**
     * Metodo que genera la firma de la transacción
     * @param transaccion transacción a firmar
     * @param privateKey clave privada del emisor
     * @return objeto firma
     */
    public static byte[] firmaTransaccion(Transaccion transaccion, PrivateKey privateKey){
        Security.insertProviderAt(new org.bouncycastle.jce.provider.BouncyCastleProvider(), 1);
        Signature firma = null;
        byte[] transaccionFirmada = null;
        try {
            //Creamos la instancia del algoritmo a utilizar
            firma = Signature.getInstance("SHA256withECDSA");
            //Inicializa el objeto para la firma
            firma.initSign(privateKey);
            //Se prepara la firma de los datos pasados a byte[]
            firma.update(transaccion.toString().getBytes());
            //Se firma los datos
            transaccionFirmada = firma.sign();
            return transaccionFirmada;
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error no se encuentra el algoritmo. " +e.getMessage());
        } catch (InvalidKeyException e) {
            System.err.println("Clave privada invalida. " + e.getMessage());
        } catch (SignatureException e) {
            System.err.println("Error en la firma. " + e.getMessage());
        }
        return null;
    }

    /**
     * Metodo que comprueba si la fima de una transaccion es correcta
     * @param transaccion transaccion a verificar
     * @param transaccionFirmada transaccion firmada
     * @param publicKey clave publica del emisor
     * @return true si es correcto y false si no lo es
     */
    public static boolean verificarFirma(Transaccion transaccion, byte[] transaccionFirmada, PublicKey publicKey){
        boolean verificada = false;
        Signature firma = null;
        try {
            //Creamos la instancia del algoritmo a utilizar
            firma = Signature.getInstance("SHA256withECDSA");
            //Inicializa el objeto verificar
            firma.initVerify(publicKey);
            //Se prepara la firma de los datos pasados a byte[]
            firma.update(transaccion.toString().getBytes());
            //Verificamos si es correcto los datos pasados
            verificada = firma.verify(transaccionFirmada);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error no se encuentra el algoritmo. " +e.getMessage());
        } catch (InvalidKeyException e) {
            System.err.println("Clave privada invalida. " + e.getMessage());
        } catch (SignatureException e) {
            System.err.println("Error en la firma. " + e.getMessage());
        }
        return verificada;
    }

    /**
     * Muestra un array de bytes en formato cadena de caracteres Hexadecimal
     * @param arrayBytes array de bytes a convertir
     * @return un string en formato hexadecimal
     */
    public static String ArrayBytesToHexString(byte[] arrayBytes){
        StringBuilder sb = new StringBuilder();
        for(byte b: arrayBytes){
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}
