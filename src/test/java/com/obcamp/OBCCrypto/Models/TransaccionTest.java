package com.obcamp.OBCCrypto.Models;

import com.obcamp.OBCCrypto.Models.Transacciones.DatosTransacción;
import com.obcamp.OBCCrypto.Models.Transacciones.Transaccion;
import org.junit.jupiter.api.Test;

public class TransaccionTest {
    String emisor = "Josema";
    String receptor = "Pepito";
    String firma ="Firma Josema";
    @Test
    public void creacionTransaccion(){
        Transaccion transaccion = new Transaccion(emisor.getBytes(), receptor.getBytes(), firma.getBytes(),
                new DatosTransacción("prueba", emisor.getBytes(), receptor.getBytes()));
        System.out.println(transaccion.toString());
    }
}
