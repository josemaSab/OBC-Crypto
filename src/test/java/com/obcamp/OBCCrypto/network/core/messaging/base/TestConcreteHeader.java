package com.obcamp.OBCCrypto.network.core.messaging.base;

import com.obcamp.OBCCrypto.network.core.messaging.ConcreteHeader;
import com.obcamp.OBCCrypto.network.core.messaging.exceptions.InvalidArgumentSizeException;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class TestConcreteHeader {


    @Test
    public void testHeaderConstructor(){

        assertAll("Test Concrete/Abstract Header constructor",
                () -> assertEquals("version",
                        new ConcreteHeader("version").getCommandName().replaceAll("\0",""),
                        "Fallo en el primer assert"),

                () -> assertThrows(InvalidArgumentSizeException.class,
                        ()-> new ConcreteHeader("veeeeeeeeersion"), "Fallo al no lanzar la excepción"),
                () -> {
                    ConcreteHeader testHeader = new ConcreteHeader("version");
                    // para filtrar los nulos:
                    // ByteBuffer -> byte[] -> String (byte[], charset) -> regex
                    byte[] bytes = testHeader.getHeader().array();
                    byte[] startString = new byte[6];
                    byte[] command = new byte[12];
                    System.arraycopy(bytes,0,startString,0,6);
                    System.arraycopy(bytes,6,command,0, 12);

                    String v = new String(command, StandardCharsets.ISO_8859_1).replaceAll("\0","");

                    assertEquals("obcoin", new String(startString,StandardCharsets.ISO_8859_1));
                    assertEquals("version", v);
                }
        );

    }


}
