package com.obcamp.OBCCrypto.Services;

import com.obcamp.OBCCrypto.Models.Transacciones.DatosTransacción;
import com.obcamp.OBCCrypto.Models.Transacciones.Transaccion;
import com.obcamp.OBCCrypto.Models.Wallets.Wallet;
import com.obcamp.OBCCrypto.Services.Encrypt.FirmaService;
import com.obcamp.OBCCrypto.Services.Wallet.WalletService;
import org.junit.jupiter.api.Test;

import java.security.*;

public class FirmaServiceTest {

    @Test
    public void comprobarFirmaTransaccion() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException {
        Wallet emisor = new Wallet();
        Wallet receptor = new Wallet();
        byte[] transaccionFirmada = null;
        Transaccion transaccion = new Transaccion(emisor.getPublicKey(), receptor.getPublicKey(),
                new DatosTransacción("prueba", emisor.getPublicKey(), receptor.getPublicKey()));
        transaccion.setCantidad(50);

        transaccionFirmada = FirmaService.firmaTransaccion(transaccion, emisor.getPrivateKey());
        System.out.println(transaccionFirmada);
    }
}
