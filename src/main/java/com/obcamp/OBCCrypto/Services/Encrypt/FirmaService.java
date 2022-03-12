package com.obcamp.OBCCrypto.Services.Encrypt;

import com.obcamp.OBCCrypto.Models.Transacciones.Transaccion;
import com.starkbank.ellipticcurve.Ecdsa;
import com.starkbank.ellipticcurve.PrivateKey;
import com.starkbank.ellipticcurve.PublicKey;
import com.starkbank.ellipticcurve.Signature;

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
    public static Signature firmaTransaccion(Transaccion transaccion, PrivateKey privateKey){
        Signature firma = null;
        firma = Ecdsa.sign(transaccion.toString(), privateKey);
        return firma;
    }

    /**
     * Metodo que comprueba si la fima de una transaccion es correcta
     * @param transaccion transaccion a verificar
     * @param firma firma de la transaccion
     * @param publicKey clave publica del emisor
     * @return true si es correcto y false si no lo es
     */
    public static boolean verificarFirma(Transaccion transaccion, Signature firma, PublicKey publicKey){
        boolean verificada = false;
        //Si la verificacion de la transaccion con la firma y la clave publica del emisor es true la variable
        //verificada toma el valor true
        if(Ecdsa.verify(transaccion.toString(), firma, publicKey)){
            verificada = true;
        }
        return verificada;
    }
}
