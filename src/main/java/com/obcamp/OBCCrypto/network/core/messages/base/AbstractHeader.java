package com.obcamp.OBCCrypto.network.core.messages.base;

import com.obcamp.OBCCrypto.network.config.MsgConstants;
import com.obcamp.OBCCrypto.network.core.messages.ConcreteHeader;
import com.obcamp.OBCCrypto.network.core.messages.exceptions.InvalidArgumentSizeException;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 *<p>Clase abstracta que reúne la funcionalidad básica a la hora de construir el header de un mensaje.
 * Se ocupa de añadir padding a la cadena del nombre de comando, así como de definir métodos {@link #append}
 * sobrecargados para los tipos {@code byte[]}, {@link ByteBuffer}, {@link Integer}, {@link Long} y
 * {@link String}.</p>
 *
 * <p>Nota: el metodo {@link #append(String)} utiliza {@link StandardCharsets#ISO_8859_1}
 * para codificar la cadena utilizando un solo {@code byte} por carácter.
 * </p>
 *
 * @author Manu
 * @version 1.0
 * @since 1.0
 */
public abstract class AbstractHeader {

    // TODO El tamaño del header habría que juntarlo con otras constantes
    /**
     *
     * El buffer aloja la información del header en bytes. El orden debe ser:
     * <pre>
     *  1-La cadena inicial → Para distinguir un mensaje de otro a la hora
     *                      de recibirlos
     *  2-El comando (un {@link String}, con padding hasta ocupar 12 carácteres)
     *  3-El número de bytes en el payload
     *  4-El hash del payload → Para  comprobar que ha llegado toda la información
     *                          en buen estado.
     *  </pre>
     *
     *  <p>El tamaño y el hash del payload se gestionan desde la clase hija {@link ConcreteHeader}</p>
     *
     */
    private ByteBuffer header = ByteBuffer.allocate(54);

    /**
     * Sirve para identificar el tipo de mensaje, y notificar al controller correspondiente
     */
    private final String commandName;

    /**
     *
     * @param commandName El tipo de mensaje
     * @throws NullPointerException Cuando se le pasa un valor nulo.
     * @throws InvalidArgumentSizeException Si el argumento tiene más de 12 caracteres
     */
    public AbstractHeader(String commandName) throws NullPointerException, InvalidArgumentSizeException {
        // Comprobamos que el nombre de comando no sea nulo
        if(commandName == null) throw new IllegalArgumentException("El argumento 'commandName' no puede ser nulo");

        // Comprobamos que el nombre de comando no sea más largo de 12 carácteres
        if(commandName.length() > 12){
            throw new InvalidArgumentSizeException("El nombre del comando es demasiado largo (máx 12 caracteres)");

            // Añadimos padding al nombre de comando para que tenga exáctamente 12 carácteres.
        }else if(commandName.length() < 12){
            // Esto habría que intentar dejarlo más elegante
            commandName = commandName.concat("\0\0\0\0\0\0\0\0\0\0\0\0").substring(0,12);
        }

        this.commandName = commandName;
        // Añadimos la cadena inicial y el nombre del comando al buffer directamente.
        this.append(MsgConstants.MESSAGE_START_STRING).append(commandName);
    }

    //getters

    public String getCommandName() {
        return this.commandName;
    }

    public ByteBuffer getHeader() {
        return this.header;
    }

    // metodos append sobrecargados
    public AbstractHeader append(byte[] bytes){
        header.put(bytes);
        return this;
    }

    public AbstractHeader append(ByteBuffer byteBuffer){
        header.put(byteBuffer);
        return this;
    }

    public AbstractHeader append(Integer num){
        header.put(num.byteValue());
        return this;
    }

    // Probablemente no lo necesitemos
    public AbstractHeader append(Long num){
        header.put(num.byteValue());
        return this;
    }

    public AbstractHeader append(String s){
        header.put(s.getBytes(StandardCharsets.ISO_8859_1));
        return this;
    }

}


