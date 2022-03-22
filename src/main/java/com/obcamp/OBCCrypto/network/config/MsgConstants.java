package com.obcamp.OBCCrypto.network.config;


import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class MsgConstants {

    // Puertos por defecto del protocolo
    public static final int MAINNET_PORT = 54321;
    public static final int TESTNET_PORT = 33333;



    // Constante de inicio
    public static final String MESSAGE_START_STRING = "obcoin";
    public static final int MESSAGE_START_LENGTH = MESSAGE_START_STRING.length();
    public static final byte[] MESSAGE_START_BYTES = MESSAGE_START_STRING.getBytes(StandardCharsets.ISO_8859_1);


    // Tamaño de los campos del header
    public static final Integer MESSAGE_COMAND_SIZE = 12;
    public static final Integer MESSAGE_PAYLOAD_SIZE_BYTES = Integer.BYTES;
    public static final Integer MESSAGE_CHECKSUM_SIZE = 32; // Asumiendo codificacion ISO_8859_1

    public static final Integer MESSAGE_FORMAT_VERSION_INTEGER = 1;


}


