package com.obcamp.OBCCrypto.network.config.v1;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;


/**
 * Esta clase define constantes utilizadas para la comunicación entre participantes de la red.
 *
 * @author Manu
 * @version 1.0
 * Equipo: Josema, Adrian, Manu
 */
public class MsgConstants {



    // Constantes para marcar el inicio y el fin de un MessageCommand

    // Constantes de inicio
    public static final String MESSAGE_START_STRING = "obcoin";

    public static final byte[]  MESSAGE_START = MESSAGE_START_STRING.getBytes(StandardCharsets.UTF_8);
    public static final Integer MESSAGE_START_SIZE = MESSAGE_START.length;

    // La version indica la estructura del mensaje y sirve para deserializarlo
    public static final Integer MESSAGE_FORMAT_VERSION_INTEGER = 1;
    public static final byte[] MESSAGE_FORMAT_VERSION = ByteBuffer.allocate(4)
            .putInt(MESSAGE_FORMAT_VERSION_INTEGER).array();



    // Constantes de fin
    public static final String MESSAGE_END_STRING = "bye bye obcoin";

    public static final byte[] MESSAGE_END = MESSAGE_END_STRING.getBytes(StandardCharsets.UTF_8);
    public static final Integer MESSAGE_END_SIZE = MESSAGE_END.length;

    // TODO  añadir una constante para señalizar el cierre de la conexión TCP.



}
