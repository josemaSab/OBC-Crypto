package com.obcamp.OBCCrypto.Repositories;

import com.obcamp.OBCCrypto.Models.Transacciones.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Proyecto OBC-Crypto
 * Repositorio de Transaccion extiende de Jpa repository para crear el crud
 * @author Josema
 * @version 1.0
 * Equipo: Josema, Adrian, Manu
 */
@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
}
