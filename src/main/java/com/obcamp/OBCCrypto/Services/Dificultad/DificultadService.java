package com.obcamp.OBCCrypto.Services.Dificultad;

import com.obcamp.OBCCrypto.Models.Bloques.Bloque;
import com.obcamp.OBCCrypto.Services.BaseDatos.BloqueService;
import com.obcamp.OBCCrypto.Utilidades.Time.ConversionTiempo;


/**
 * Proyecto OBC-Crypto
 * Servicio para el calculo de la dificultad cada n bloques
 * @author Josema
 * @version 1.0
 * Equipo: Josema, Adrian, Manu
 */
public class DificultadService {

    //METODOS

    /**
     * Metodo que calcula la dificultad segun un determinado numero de bloques.
     * Si el tiempo de minado entre los dos bloques es superior al estimado se disminuye la dificultad y
     * si es mayor que el tiempo estimado se reduce
     * @param bloqueActual Ultimo bloque minado
     * @param numeroBloques numero de bloques a inspeccionar
     * @param tiempoMinadoBloque tiempo estimado de minado de numeroBloques bloques
     * @return la dificultad modificada
     */
    public static int calculoDificultad(Bloque bloqueActual, long numeroBloques, long tiempoMinadoBloque){
        int dificultad = bloqueActual.getDificultad();
        BloqueService bloqueService = new BloqueService();
        if((bloqueActual.getNumeroBloque() % numeroBloques == 0) && (bloqueActual.getNumeroBloque() >= numeroBloques)){
            //Obtengo el bloque numeroBloques anterior al actual para comprobar el tiempo
            Bloque bloqueAnterior = bloqueService.findById(bloqueActual.getNumeroBloque()-numeroBloques);
            //Pasamos la fecha hora de minado de los bloque a milis
            long milisBloqueAnterior = ConversionTiempo.localDateTimeToMIlis(bloqueAnterior.getHoraCreacionBloque());
            long milisBloqueActual = ConversionTiempo.localDateTimeToMIlis(bloqueActual.getHoraCreacionBloque());
            //Tiempo estimado en minar numeroBloques bloques
            long tiempoEstimado = numeroBloques * tiempoMinadoBloque * 1000;
            //Si la diferencia entre bloques en milis es superior al tiempo estimado
            if((milisBloqueActual-milisBloqueAnterior) > tiempoEstimado){
                //Se reduce la dificultad
                if(dificultad > 1){
                    dificultad--;
                }
            //Si la diferencia entre bloques en milis es inferior al tiempo estimado aumenta la dificultad
            }else if((milisBloqueActual-milisBloqueAnterior) < tiempoEstimado){
                dificultad++;
            }
        }
        return dificultad;
    }
}
