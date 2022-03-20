package com.obcamp.OBCCrypto;

import com.obcamp.OBCCrypto.Models.Bloques.Bloque;
import com.obcamp.OBCCrypto.Models.Transacciones.Transaccion;
import com.obcamp.OBCCrypto.Models.Wallets.Wallet;
import com.obcamp.OBCCrypto.Repositories.BloqueRepository;
import com.obcamp.OBCCrypto.Repositories.TransaccionRepository;
import com.obcamp.OBCCrypto.Services.Encrypt.FirmaService;
import com.obcamp.OBCCrypto.Services.POW.PruebaTrabajoService;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
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
		Wallet emisor = new Wallet();
		Bloque preBloqueGenesis = new Bloque(null,null,5);
		Bloque genesis = PruebaTrabajoService.pow(preBloqueGenesis);
		System.out.println("BLOQUE MINADO!!!!!!!!!!!");
		System.out.println("Hash Actual: " + new String(genesis.getHashActual()));
		System.out.println("Hash Anterior: " + genesis.getHashAnterior());
		System.out.println("Hash Dificultad: " + genesis.getDificultad());
		System.out.println("Hash Nonce: " + genesis.getNonce());
		System.out.println("Hash hora de creacion: " + genesis.getHoraCreacionBloque());
		System.out.println("Hash numero de bloque: " + genesis.getNumeroBloque());

		System.out.println("Insertamos el bloque en la blockchain.");
		bloqueRepository.save(genesis);

		Bloque bloqueRecuperado = bloqueRepository.findById(1l).get();
		System.out.println("------------BLOQUE RECUPERADO BD------------------");
		System.out.println("Hash Actual: " + new String(bloqueRecuperado.getHashActual()));
		System.out.println("Hash Anterior: " + bloqueRecuperado.getHashAnterior());
		System.out.println("Hash Dificultad: " + bloqueRecuperado.getDificultad());
		System.out.println("Hash Nonce: " + bloqueRecuperado.getNonce());
		System.out.println("Hash hora de creacion: " + bloqueRecuperado.getHoraCreacionBloque());
		System.out.println("Hash numero de bloque: " + bloqueRecuperado.getNumeroBloque());
	}
}
