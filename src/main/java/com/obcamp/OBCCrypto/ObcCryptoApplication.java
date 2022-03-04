package com.obcamp.OBCCrypto;

import com.obcamp.OBCCrypto.Services.Encrypt.SHA256;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@SpringBootApplication
public class ObcCryptoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObcCryptoApplication.class, args);
	}
}
