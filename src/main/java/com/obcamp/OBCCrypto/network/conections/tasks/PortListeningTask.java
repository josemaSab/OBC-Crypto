package com.obcamp.OBCCrypto.network.conections.tasks;

import com.obcamp.OBCCrypto.network.conections.PeerConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Callable;


/**
 * <p>Implementación de la interfaz {@link Callable} que permite aceptar nuevas conexiones
 * de forma asíncrona, asignando el resultado obtenido al llamar al método {@link #call()} a
 * un {@link java.util.concurrent.Future}
 * </p>
 * <p>Está implementación devuelve una nueva instancia de {@link PeerConnection}</p>
 *
 * @author Manu
 * @version 1.0
 * @since 1.0
 */
public class PortListeningTask implements Callable<PeerConnection> {

    private final ServerSocket serverSocket;

    public PortListeningTask(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     *
     * @return Nueva instancia de {@link PeerConnection}
     * @throws IOException Si ocurre un error de I/O mientras esperamos la conexión
     */
    @Override
    public PeerConnection call() throws IOException {
        return new PeerConnection(this.serverSocket.accept());
    }
}
