package com.obcamp.OBCCrypto;

import com.obcamp.OBCCrypto.Services.Encrypt.EncryptService;
import com.obcamp.OBCCrypto.Services.Encrypt.SHA256;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@SpringBootApplication
public class ObcCryptoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObcCryptoApplication.class, args);
	}
}
