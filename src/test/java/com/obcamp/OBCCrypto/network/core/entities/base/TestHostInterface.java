package com.obcamp.OBCCrypto.network.core.entities.base;

import com.obcamp.OBCCrypto.network.core.entities.HostInterface;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;

import static org.junit.jupiter.api.Assertions.*;

public class TestHostInterface {

    @Test
    public void testIpValidation(){

        assertAll("Test Verdaderos Positivos",
                () -> {
                    assertTrue(HostInterface.isValidIP("192.168.0.1"),"Rechazada una IPv4 válida");
                },
                () -> {assertTrue(HostInterface.isValidIP("192.168.0.17"),"Rechazada una IPv4 válida");
                }
        );

        assertAll("Test Verdaderos Negativos",
                () -> assertFalse(HostInterface.isValidIP("aaaaa"),"Aceptada IP no válida"),
                () -> assertFalse(HostInterface.isValidIP("1982.168.0.1"), "Aceptada IP no válida")
        );
    }

    @Test
    public void testMapIPv4ToIPv6() {

        assertAll(
                () -> assertEquals("::ffff:192.168.0.1", HostInterface.mappIPv4ToIPv6("192.168.0.1")),
                () -> assertEquals("::ffff:192.168.0.17", HostInterface.mappIPv4ToIPv6("192.168.0.17"))
        );

        String validIPv4Address = "192.168.99.1";
        byte[] validIPv4MappedAddress = new byte[16];
        validIPv4MappedAddress[10] = validIPv4MappedAddress[11] = (byte) 0xff;
        validIPv4MappedAddress[12] = (byte) 192; // 0xc0; // 192 en hexadecimal
        validIPv4MappedAddress[13] = (byte) 168; // 0xa8; // 168 en hex
        validIPv4MappedAddress[14] = (byte) 99; //0x63; // 99 en hex
        validIPv4MappedAddress[15] = (byte) 1; //0x01; // 1 en hex


        assertAll("Test reconocer IPv4 y IPv4 mapeada como la misma",
                () -> {
                    assertArrayEquals(validIPv4MappedAddress,
                            HostInterface.mappIPv4ToIPv6(InetAddress.getByName(validIPv4Address).getAddress()));
                }

                );

    }

    @Test
    public void testInetAddressInstantiation(){

        String validIPv4Address1 = "192.168.0.1";
        String validIPv4Address2 = "192.168.0.17";


        assertAll("Test instanciar con mapeo de IP",
                () -> {
                    assertDoesNotThrow(() -> InetAddress.getByName(HostInterface.mappIPv4ToIPv6(validIPv4Address1)));
                },
                () -> {
                    assertDoesNotThrow(() -> InetAddress.getByName(HostInterface.mappIPv4ToIPv6(validIPv4Address2)));
                }
        );

        assertAll("Test getHostAddress == IPv4 no mapeada",
                () -> {
                    InetAddress addr1 = InetAddress.getByName(HostInterface.mappIPv4ToIPv6(validIPv4Address1));
                    assertEquals(validIPv4Address1, addr1.getHostAddress());
                },
                () -> {
                    InetAddress addr2 = InetAddress.getByName(HostInterface.mappIPv4ToIPv6(validIPv4Address2));
                    assertEquals(validIPv4Address2, addr2.getHostAddress());
                }
                );


        assertAll("Test tamaño IPv4 mapeada",
                () -> {
                    assertEquals(
                            InetAddress.getByName(validIPv4Address1).getAddress().length,
                            InetAddress.getByName(HostInterface.mappIPv4ToIPv6(validIPv4Address1)).getAddress().length);
                },
                () -> {
                    assertEquals(4,
                            InetAddress.getByName(HostInterface.mappIPv4ToIPv6(validIPv4Address1)).getAddress().length);
                }
        );
    }
}
