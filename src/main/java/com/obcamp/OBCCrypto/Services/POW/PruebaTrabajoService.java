package com.obcamp.OBCCrypto.Services.POW;

import com.obcamp.OBCCrypto.Models.Bloques.Bloque;
import com.obcamp.OBCCrypto.Services.Encrypt.SHA256;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Proyecto OBC-Crypto
 * Clase encargada de gestionar la prueba de trabajo
 * @author Josema
 * @version 1.0
 * Equipo: Josema, Adrian, Manu
 */
public class PruebaTrabajoService {

    public static Bloque pow(Bloque prebloque){
        Bloque bloque = null;
        long nonce = prebloque.getNonce();
        String hash= SHA256.getSHA256(prebloque.bloqueToString());
        while(obtenerCeros(hash, prebloque.getDificultad()) == false){
            nonce++;
            prebloque.setNonce(nonce);
            prebloque.setHoraCreacionBloque(LocalDateTime.now(ZoneOffset.UTC));
            hash = SHA256.getSHA256(prebloque.bloqueToString());
        }
        bloque = prebloque;
        bloque.setHashActual(hash.getBytes());
        return bloque;
    }

    /**
     * Metodo que busca n ceros en un string
     * @param hash
     * @param dificultad
     * @return
     */
    private static boolean obtenerCeros(String hash, int dificultad) {
        int contadorCeros = 0;
        char[] ceros = hash.toCharArray();
        for(int i = 0; i < dificultad; i++){
            if(ceros[i] == '0'){
                contadorCeros++;
            }
        }
        if(contadorCeros == dificultad){
            return true;
        }else{
            return false;
        }
    }
}
