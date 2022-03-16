package com.obcamp.OBCCrypto.network.core.messaging.base;

import com.obcamp.OBCCrypto.network.config.MsgConstants;
import com.obcamp.OBCCrypto.network.core.messaging.ConcreteHeader;
import com.obcamp.OBCCrypto.network.core.messaging.exceptions.InvalidArgumentSizeException;

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
public abstract class AbstractHeader{

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
    private ByteBuffer header;

    // 6 + 12 + 4 + 32 = 54
    public static final int HEADER_LENGTH = 54;

    /**
     * Sirve para identificar el tipo de mensaje, y notificar al controller correspondiente
     */
    protected String commandName;

    /**
     * El tamaño en bytes del contenido del mensaje
     */
    protected int payloadSize;

    /**
     * El checksum del contenido del mensaje
     */
    protected byte[] checksum;

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
        if(commandName.length() > MsgConstants.MESSAGE_COMAND_SIZE){
            throw new InvalidArgumentSizeException("El nombre del comando es demasiado largo (máx 12 caracteres)");
        }

        this.commandName = commandName;
    }


    //getters y setters

    public String getCommandName() {
        return this.commandName;
    }

    /**
     * Devuelve una copia de solo lectura del {@link AbstractHeader#header}
     * @return Un {@link ByteBuffer} de solo lectura
     */
    public ByteBuffer getHeader() {
        return this.header.hasArray() ? this.header.asReadOnlyBuffer() : null;
    }

    public int getPayloadSize() {
        return this.payloadSize;
    }

    public void setPayloadSize(int payloadSize) {
        this.payloadSize = payloadSize;
    }

    public byte[] getChecksum() {
        return checksum;
    }

    public void setChecksum(byte[] checksum) {
        this.checksum = checksum;
    }

    // metodos append sobrecargados
    private AbstractHeader append(byte[] bytes){
        header.put(bytes);
        return this;
    }

    private AbstractHeader append(ByteBuffer byteBuffer){
        header.put(byteBuffer);
        return this;
    }

    private AbstractHeader append(Integer num){
        header.put(num.byteValue());
        return this;
    }

    // Probablemente no lo necesitemos
    private AbstractHeader append(Long num){
        header.put(num.byteValue());
        return this;
    }

    private AbstractHeader append(String s){
        header.put(s.getBytes(StandardCharsets.ISO_8859_1));
        return this;
    }

    /**
     * Funcion de utilidad que devuelve una cadena de 12 caracteres, con la cadena original más un padding de nulls
     * hasta alcanzar ese tamaño.
     * @param s un String
     * @return s con nulls añadidos a la derecha hasta alcanzar 12 caracteres.
     */
    public String addPadding(String s){
        String result = new String(s);

        result = result.concat("\0\0\0\0\0\0\0\0\0\0\0\0").substring(0,MsgConstants.MESSAGE_COMAND_SIZE);
        return result;
    }

    /**
     * Esta función escribe la información en {@link AbstractHeader#header}.
     * @throws IllegalStateException Si el buffer ya ha sido escrito.
     */
    protected void writeBuffer() throws IllegalStateException{
        if(!this.header.hasArray()){
            throw new IllegalStateException("Este mensaje ya ha sido escrito");
        }
        this.header = ByteBuffer.allocate(HEADER_LENGTH);
        this.append(MsgConstants.MESSAGE_START_BYTES)
                .append(addPadding(this.commandName))
                .append(this.payloadSize)
                .append(this.checksum);
    }


}


