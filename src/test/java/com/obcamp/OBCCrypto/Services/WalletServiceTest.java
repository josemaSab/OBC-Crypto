package com.obcamp.OBCCrypto.Services;

import com.obcamp.OBCCrypto.Services.Wallet.WalletService;
import org.junit.jupiter.api.Test;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

public class WalletServiceTest {
    @Test
    public void comprubaGenerarKeyPair(){
        try {
            KeyPair keyPair = WalletService.generarKeyPair();
            System.out.println(keyPair.getPrivate());
            System.out.println(keyPair.getPublic());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }
}
