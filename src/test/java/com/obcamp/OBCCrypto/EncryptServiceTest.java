package com.obcamp.OBCCrypto;

import com.obcamp.OBCCrypto.Services.Encrypt.EncryptService;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;

public class EncryptServiceTest {
    @Test
    public void probarEncryptMensaje(){
        String mensaje = "Hola Mundo";
        try {
            SecretKey key= EncryptService.generarKey("AES");
            System.out.println(EncryptService.encryptMensaje(mensaje.getBytes(), key));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchProviderException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void probarDecryptMensaje(){
        String mensaje = "Hola Mundo";
        try {
            SecretKey key= EncryptService.generarKey("AES");
            byte[] encypt = EncryptService.encryptMensaje(mensaje.getBytes(), key);
            byte[] decrypt = EncryptService.decryptMensaje(encypt, key, "AES");
            System.out.println(Arrays.equals(mensaje.getBytes(), decrypt));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }
}
