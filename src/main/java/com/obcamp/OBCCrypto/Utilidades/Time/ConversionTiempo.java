package com.obcamp.OBCCrypto.Utilidades.Time;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Proyecto OBC-Crypto
 * Clase encargada de la conversion de los tipos de datos de tiempo
 * @author Josema
 * @version 1.0
 * Equipo: Josema, Adrian, Manu
 */
public class ConversionTiempo {
    /**
     * Metodo que convierte un LocalDateTime en Milisegundos
     * @param localDateTime fecha hora
     * @return fecha en milis
     */
    public static long localDateTimeToMIlis(LocalDateTime localDateTime){
        return LocalDateTime.of(1970, 1, 1, 0, 0, 0)
                .until(localDateTime, ChronoUnit.MILLIS);
    }
}
