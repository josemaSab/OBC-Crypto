package com.obcamp.OBCCrypto.network.config;

import java.nio.charset.StandardCharsets;

public class MsgConstants {



    // Constantes para abrir y cerrar las conexiones TCP
    public static final String MESSAGE_START_STRING = "obcoin";
    public static final byte[]  MESSAGE_START = MESSAGE_START_STRING.getBytes(StandardCharsets.UTF_8);
    public static final Integer MESSAGE_START_SIZE = MESSAGE_START.length;

    public static final String MESSAGE_END_STRING = "bye bye obcoin";
    public static final byte[] MESSAGE_END = MESSAGE_END_STRING.getBytes(StandardCharsets.UTF_8);
    public static final Integer MESSAGE_END_SIZE = MESSAGE_END.length;



}
