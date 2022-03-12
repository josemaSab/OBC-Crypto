package com.obcamp.OBCCrypto.Models;

import com.obcamp.OBCCrypto.Models.Transacciones.DatosTransacción;
import com.obcamp.OBCCrypto.Models.Transacciones.Transaccion;
import com.obcamp.OBCCrypto.Services.Wallet.WalletService;
import org.junit.jupiter.api.Test;

import java.security.*;

public class TransaccionTest {
    KeyPair keyPair;
    PublicKey emisor;
    PublicKey receptor;


    @Test
    public void creacionTransaccion() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException {
        keyPair = WalletService.generarKeyPair();
        emisor = keyPair.getPublic();
        receptor = keyPair.getPublic();
        Transaccion transaccion = new Transaccion(emisor, receptor,
                new DatosTransacción("prueba", emisor, receptor));
        transaccion.setCantidad(50);
        System.out.println(transaccion.toString());
    }
}
