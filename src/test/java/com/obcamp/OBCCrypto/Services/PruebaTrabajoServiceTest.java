package com.obcamp.OBCCrypto.Services;

import com.obcamp.OBCCrypto.Models.Bloques.Bloque;
import com.obcamp.OBCCrypto.Models.Transacciones.Transaccion;
import com.obcamp.OBCCrypto.Services.Encrypt.SHA256;
import com.obcamp.OBCCrypto.Services.POW.PruebaTrabajoService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PruebaTrabajoServiceTest {
    @Test
    public void pruebaPow(){
        byte[] hashanterior = SHA256.getSHA256("Estes es el hash del bloque anteior").getBytes();
        List<Transaccion> transaciones = new ArrayList<>();
        Bloque bloque1 = new Bloque(hashanterior, transaciones, 5);
        System.out.println("Minando Bloque....");
        System.out.println(PruebaTrabajoService.pow(bloque1).toString());
    }
}
