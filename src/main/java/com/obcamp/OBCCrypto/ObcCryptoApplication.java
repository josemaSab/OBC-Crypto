package com.obcamp.OBCCrypto;

import com.obcamp.OBCCrypto.Models.Bloques.Bloque;
import com.obcamp.OBCCrypto.Models.Transacciones.Transaccion;
import com.obcamp.OBCCrypto.Repositories.BloqueRepository;
import com.obcamp.OBCCrypto.Repositories.TransaccionRepository;
import com.obcamp.OBCCrypto.Services.POW.PruebaTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ObcCryptoApplication {

	public static void main(String[] args) {
		//Obtenemos el context
		ApplicationContext context = SpringApplication.run(ObcCryptoApplication.class, args);
		//Obtenemos los repositories para trabajar con la base de datos
		BloqueRepository bloqueRepository = context.getBean(BloqueRepository.class);
		TransaccionRepository transaccionRepository = context.getBean(TransaccionRepository.class);

		Bloque preBloqueGenesis = new Bloque(null,null,6);
		Bloque genesis = PruebaTrabajoService.pow(preBloqueGenesis);
		bloqueRepository.save(genesis);

	}
}
