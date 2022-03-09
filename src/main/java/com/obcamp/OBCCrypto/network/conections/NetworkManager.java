package com.obcamp.OBCCrypto.network.conections;


import com.obcamp.OBCCrypto.network.conections.tasks.PortListeningTask;

import java.io.IOException;
import java.util.concurrent.*;

//import java.util.concurrent.*;

public class NetworkManager implements Runnable{

    // valores por defecto para limitar el número de conexiones simultaneas máximo
    // habría que moverlos a {@link NetworkConfig} en algún momento
    private final int DEFAULT_MAX_CONNECTIONS = 12;
    private final int DEFAULT_OPEN_NEW_CONECTION_THRESHOLD = 8;

    private volatile static boolean EXIT = false;

    /**
     * Número máximo de conexiones. No se inician ni aceptan nuevas conexiones si se alcanza
     */
    private int MAX_CONNECTIONS = DEFAULT_MAX_CONNECTIONS;

    /**
     * Umbral a partir del cual no abrimos más conexiones
     */
    private int ACCEPT_CONECTION_THRESHOLD = DEFAULT_OPEN_NEW_CONECTION_THRESHOLD;


    private final ServerSocketManager server;
    private final ExecutorService portListenerService = Executors.newFixedThreadPool(MAX_CONNECTIONS);

    private BlockingQueue<PeerConnection> threadsQueue = new LinkedBlockingQueue<>();




    public NetworkManager(int port) throws IOException {
        this.server = ServerSocketManager.getInstance(port);
    }

    public NetworkManager(int port, int maxConections) throws IOException{
        this.server = ServerSocketManager.getInstance(port);
        this.MAX_CONNECTIONS = maxConections;

    }

    @Override
    public void run() {
        /**
         * TODO implementar toda la parte de la gestión del flujo de mensajes
         **/
        try{
        wait(1);
        }catch (Exception e){
            System.out.println("Esta clase no esta terminada");
        }

    }


}
