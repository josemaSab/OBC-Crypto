package com.obcamp.OBCCrypto.Repositories;

import com.obcamp.OBCCrypto.Models.Bloques.Bloque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Proyecto OBC-Crypto
 * Repositorio de Bloque extiende de Jpa para crear el crud
 * @author Josema
 * @version 1.0
 * Equipo: Josema, Adrian, Manu
 */
@Repository
public interface BloqueRepository extends JpaRepository<Bloque, Long> {

    Bloque findByHashActual(byte[] hash);
    boolean existsByHashAnterior(byte[] hashAnterior);
    boolean existsByHashActual(byte[] hashActual);

}
