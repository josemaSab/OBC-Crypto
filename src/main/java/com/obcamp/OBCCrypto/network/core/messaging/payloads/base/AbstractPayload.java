package com.obcamp.OBCCrypto.network.core.messaging.payloads.base;

import com.obcamp.OBCCrypto.network.core.messaging.exceptions.CapacityLimitException;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Iterator;

/**
 * Clase base que encapsula el comportamiento necesario para convertir una instancia/colección de instancias de {@code T}
 * en el contenido válido de un mensaje.
 * <p>Nota: {@code T} debe implementar la interfaz {@link Writable}</p>
 * @param <T> El tipo de contenido que queremos incluir en el mensaje.
 *
 * @author Manu
 * @version 1.0
 * @since 1.0
 * @see Writable
 */
public abstract class AbstractPayload<T extends Writable> {

    /**
     * Si las instancias de {@code T} no tienen un tamaño fijo, la colección no tendrá permitido almacenar
     * más de un elemento.
     */
    protected Collection<T> payload;

    /**
     * Será {@code false} si las instancias de {@code T} son de tamaño variable, y {@code true} si dichas
     * instancias son de tamaño fijo, para evitar problemas parseando el payload.
     */
    protected boolean allowsMultipleItems;
    protected int payloadBytesSize = 0;

    /**
     * Añade {@code item} al {@link #payload}. La presencia o no de duplicados dependerá de
     * la implementación concreta de la colección
     * @param item instancia de {@code T}
     * @throws CapacityLimitException En los casos en los que no se admitan múltiples {@code item}s,
     * si {@link #payload} ya contiene algún elemento.
     * @see #payload
     */
    public void addItem(T item) throws CapacityLimitException {
        if(!allowsMultipleItems && payload.size() == 1){
            throw new CapacityLimitException("No se admiten multiples elementos");
        }
        payload.add(item);
    }

    /**
     * Comprueba si el {@link #payload} admite más de un elemento
     * @return {@code true} si se permite incluir más de un elemento, {@code false} en caso contrario
     */
    public boolean allowsMultipleItems(){
        return allowsMultipleItems;
    }



    /**
     * Devuelve el número de elementos en el {@link #payload}
     * @return {@code int} número de elementos en {@link #payload}
     */
    public int getItemCount(){
        return payload.size();
    }

    /**
     * Calcula el número de bytes que ocupará el {@link #payload} una vez serializado, y actualiza
     * {@link #payloadBytesSize}
     * <p>La implementación actual hace uso de {@link Writable#getBytesSize()} sobre cada elemento del
     * {@link #payload} y utiliza la suma de estos resultados</p>
     */
    public void setPayloadBytesSize(){
        int bytesSize = 0;
        for(T item : payload) bytesSize += item.getBytesSize();
        this.payloadBytesSize = bytesSize;
    }

    /**
     * Devuelve el tamaño en bytes que ocupa el contenido de {@link #payload} una vez serializado.
     * @return número de bytes que ocupará el contenido del {@link #payload} una vez serializado.
     * @throws IllegalStateException Si no se ha calculado el tamaño del {@link #payload} con anterioridad
     */
    public int getPayloadBytesSize() throws IllegalStateException{

        if(!(payloadBytesSize > 0)) throw new IllegalStateException("Tamaño del payload desconocido");

        return this.payloadBytesSize;
    }

    /**
     * Devuelve un iterador del {@link #payload}.
     * @return Un {@link Iterator} a partir del contenido del {@link #payload}
     * @see Collection#iterator()
     */
    public Iterator<T> getIterator(){
        return payload.iterator();
    }


    /**
     * Construye y devuelve un {@link ByteBuffer} con el contenido serializado del {@link #payload}. El orden de
     * inserción depende de la implementación concreta de la interfaz {@link Collection} utilizada.
     * @return un {@link ByteBuffer} con tamaño {@link #payloadBytesSize} que contiene la representación en bytes del
     * contenido del {@link #payload}
     */
    public ByteBuffer getPayloadBuffer(){
        ByteBuffer buffer = ByteBuffer.allocate(this.getPayloadBytesSize());

        for(T item : payload) buffer.put(item.getBytes().array());

        return buffer;
    }
}
