package com.obcamp.OBCCrypto.Services;

import com.obcamp.OBCCrypto.Models.Wallets.Wallet;
import org.junit.jupiter.api.Test;

public class WalletTest {
    @Test
    public void crearWallet(){
        Wallet wallet1 = new Wallet();
        System.out.println("Clave privada: " + wallet1.getPrivateKey());
        System.out.println("Clave publica: " + wallet1.getPublicKey());
    }
}
