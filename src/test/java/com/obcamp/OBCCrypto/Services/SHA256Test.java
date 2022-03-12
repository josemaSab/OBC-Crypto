package com.obcamp.OBCCrypto.Services;

import com.obcamp.OBCCrypto.Services.Encrypt.SHA256;
import org.junit.jupiter.api.Test;

public class SHA256Test {
    @Test
    public void pruebaGetSHA256(){
        String prueba = "Hola Mundo";
        System.out.println(SHA256.getSHA256(prueba));
    }
}
