package com.obcamp.OBCCrypto.Services;

import com.obcamp.OBCCrypto.Models.Transacciones.DatosTransacción;
import com.obcamp.OBCCrypto.Models.Transacciones.Transaccion;
import com.obcamp.OBCCrypto.Models.Wallets.Wallet;
import com.obcamp.OBCCrypto.Services.Encrypt.FirmaService;


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
        System.out.println(FirmaService.ArrayBytesToHexString(transaccionFirmada));
    }

    @Test
    public void verificarFirmaTest(){
        //GENERAMOS UNA TRANSACCION Y LA FIRMAMOS
        Wallet emisor = new Wallet();
        Wallet receptor = new Wallet();
        byte[] transaccionFirmada = null;
        Transaccion transaccion = new Transaccion(emisor.getPublicKey(), receptor.getPublicKey(),
                new DatosTransacción("prueba", emisor.getPublicKey(), receptor.getPublicKey()));
        transaccion.setCantidad(50);

        transaccionFirmada = FirmaService.firmaTransaccion(transaccion, emisor.getPrivateKey());
        //transaccion.setCantidad(40); para verificar el false se modifica la cantidad de la transacción
        System.out.println("Firma: " + FirmaService.ArrayBytesToHexString(transaccionFirmada));
        //------------------------------------VERIFICAMOS LA FIRMA Y LA TRANSACCION
        System.out.println("Verificada: "+ FirmaService.verificarFirma(transaccion, transaccionFirmada, emisor.getPublicKey()));
    }
}
