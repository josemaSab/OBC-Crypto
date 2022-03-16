package com.obcamp.OBCCrypto.network.conections;

import com.obcamp.OBCCrypto.network.conections.tasks.PortListeningTask;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Implementación del patrón Singleton que se ocupa de gestionar el {@link ServerSocket}, así como de atender
 * asíncronamente las peticiones entrantes para establecer una {@link PeerConnection}.
 * <p> Para poder instanciar el Singleton hay que invocar al método {@link #getInstance(int port)}. </p>
 * <p>
 *     Existe una versión sobrecargada del mismo método que devuelve una instancia ya creada sin necesidad de
 *     pasarle el argumento {@code port} --> {@link #getInstance()}
 * </p>
 *
 * <p>Esta clase no está pensada para ser usada directamente, pero de ser necesario puede hacerse de la siguiente manera:</p>
 *
 *          <p>{@code    // Obtenemos control de la instancia mediante {@link #getInstance}}</p>
 *          <p>{@code   ServerSocketManager serverManager = ServerSocetManager.getInstance(port); }</p>
 *
 *          <p>{@code // Ejecuta ServerSocket.accept() en otro hilo para evitar el bloqueo. }</p>
 *          <p>{@code  serverManager.asyncListen(); }</p>
 *
 *          <p>{@code // recupera PeerConection si ha habido alguna conexión entrante, o null en caso contrario }</p>
 *          <p>{@code  PeerConection newIncomingConection = asyncAcept() }</p>
 *
 */
public class ServerSocketManager {

    private final ServerSocket sServer;
    private static ServerSocketManager instance = null;

    private final ExecutorService ServerWorker = Executors.newSingleThreadExecutor();
    private Callable<PeerConnection> task;
    private Future<PeerConnection> incomingConection;


    private ServerSocketManager(int port) throws IOException {
            this.sServer = new ServerSocket(port);
            this.task = new PortListeningTask(sServer);
    }

    public static ServerSocketManager getInstance(){
        if(instance == null){
            throw new IllegalStateException("La instancia no puede ser creada sin un número de puerto");
        }
        return instance;
    }

    public static ServerSocketManager getInstance(int port) throws IOException, IllegalStateException{
        if(instance == null){
            return new ServerSocketManager(port);
        }else if (instance.sServer.getLocalPort() != port){
            throw new IllegalStateException(
                    "Ya estamos escuchando el puerto: " + instance.sServer.getLocalPort() +", " +
                            "imposible escuchar simultaneamente el" + "puerto: " + port);
        }
        return instance;
    }

    /**
     * Añade una {@link PortListeningTask} a la cola del {@link ExecutorService}
     */
    private void asyncListen(){
        this.incomingConection = this.ServerWorker.submit(task);
    }

    /**
     * Comprueba asíncronamente si la tarea {@link PortListeningTask} ha recibido una nueva conexión.
     * <p>
     *     Si es así, llama a {@link #tryAccept()} para intentar devolver la conexión. Si todavía no ha podido aceptar
     *     ninguna conexión entrante,
     *     devuelve null.
     * </p>
     * @return Un nuevo {@link PeerConnection} si se ha recibido un socket entrante, o null en caso contrario
     */
    private PeerConnection asyncAccept() {
        return this.incomingConection.isDone() ? tryAccept() : null;
    }

    /**
     * Envuelve la llamada a {@link Future<PeerConnection>} con un {@code try {...}catch{...}finally{...}}
     * <p>
     *     Además, añade una nueva {@link PortListeningTask} al {@link #ServerWorker} mediante una llamada a
     *     la función {@link #asyncListen()}
     * </p>
     * @return Una nueva conexión entrante gestionada por la clase {@link PeerConnection}
     */
    private PeerConnection tryAccept(){
        try {
            return  this.incomingConection.get();
        }catch (Exception e){
            // TODO meter logger aqui

        }finally{
            // programamos una nueva tarea para el ExecutorService
            asyncListen();
        }
        return null;
    }

    /**
     * Fuerza el cierre de {@link #task} y cierra el {@link ServerSocket}
     * @throws InterruptedException Cuando el hilo es interrumpido mientras espera para comprobar la correcta finalización
     * del {@link #ServerWorker}
     * @throws IOException Cuando ocurre un error de I/O mientras se cierra el socket.
     */
    public void close() throws InterruptedException, IOException{
        ServerWorker.shutdown();
        wait(200);
        if(!ServerWorker.isShutdown()){
            ServerWorker.shutdownNow();
        }
        sServer.close();
    }


}
