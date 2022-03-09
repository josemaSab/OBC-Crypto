package com.obcamp.OBCCrypto.network.conections;

import com.obcamp.OBCCrypto.network.core.messages.v1.ConcreteMessage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.InetAddress;
import java.net.Socket;
import java.time.Instant;
import java.util.Arrays;

/**
 * Esta clase se ocupa de gestionar las conexiones por TCP con los peers.
 * Implementa la interfaz {@link Runnable}, para poder gestionar la comunicación a través del socket
 * en un hilo aparte.
 * Su proposito es dirigir los mensajes entrantes al handler correspondiente, asignar el peer a los canales de tópicos
 * que correspondan, así como proporcionar un punto único de escritura al {@link Socket} del peer,
 * {@link #sendMessage(ConcreteMessage)}.
 * También se ocupa de gestionar el proceso de saludo cuando se inicia la conexión, para asegurarse de que el peer
 * se comunica bajo el mismo protocolo, y bajo la misma versión, así como para determinar distintas características
 * de la misma.
 *
 * @author Manu
 * @version 1.0
 * @since 1.0
 *
 */
public class PeerConnection implements Runnable{


    /**
     * Dirección IP y puerto del peer:
     * Si iniciamos la conexión, conocemos esta información y la usaremos para crear el socket.
     * En caso de aceptar una conexión entrante, extraemos la información del socket, para poder
     * almacenarla en BBDD una vez completado el proceso de saludo.
     *
     */
    private final InetAddress peerAddress;
    private final int port;

    /**El socket lo recibimos creado si el nodo actua de servidor durante el proceso de saludo, si somos
    los que actuamos de cliente (iniciando la conexión) lo crearemos en el constructor correspondiente.
     **/
    private Socket peerSocket;
    private BufferedReader readerIn;
    private DataOutputStream outputStream;

    /**
     * El momento en que se inicia la comunicación con el peer, usado para determinar si el socket
     * ha hecho timeout y liberar recursos. En caso de conexión exitosa (proceso de saludo completado exitosamente)
     * también sirve para actualizar la última fecha en que tuvimos contacto con el peer, y determinar con
     * exactitud que peers conocidos son activos.
     */
    private Instant lastMessageTime = Instant.now();

    public PeerConnection(InetAddress peerAddres, int port) throws IOException {
        this.peerAddress = peerAddres;
        this.port = port;
        doConnect(); // abrimos el socket
    }

    public PeerConnection(Socket peerSocket) {
        this.peerSocket = peerSocket;
        this.peerAddress = peerSocket.getInetAddress();
        this.port = peerSocket.getPort();
    }


    @Override
    public void run() {
        // TODO Implementar el método run:
        //  organizar el comportamiento durante el flujo de mensajes en métodos privados para poder testear mejor
    }


    /**
     * Este método se llama en el constructor cuando se le pasan como argumentos
     * una InetAddress y un puerto, para crear el socket.
     *
     */
    private void doConnect() throws IOException{

        // TODO Sería mejor dejar pasar la excepción, y que le llegue al hilo que lanza a este?
        try{
            this.peerSocket = new Socket(peerAddress, port);

            this.readerIn = new BufferedReader(new InputStreamReader(peerSocket.getInputStream()));
            this.outputStream = new DataOutputStream(peerSocket.getOutputStream());


        }catch (IOException e){

            // TODO habría que incluir un logger aquí
            System.out.println("Error de conexión con el peer: " + peerAddress.getHostAddress());
            System.out.println(Arrays.toString(e.getStackTrace()));

        }finally {
            // Nos aseguramos de cerrar el socket siempre
            doCloseSocket();
        }
    }

    /**
     * Este método se ocupa de cerrar el socket y gestionar los posibles errores que surjan al hacerlo.
     */
    private void doCloseSocket(){
        try {
            peerSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            // Aqui deberíamos emitir algún tipo de callback para notificar que se ha cerrado
            // el socket, y poder abrir uno nuevo. Probablemente un ThreadExecutor tenga esto
            // implementado ya
            System.out.println("Socket cerrado"); // da error si no hay nada en el finally
        }
    }

    /**
     * Escribe el mensaje convertido en bytes en la salida del socket.
     * @param msg Instancia de {@link ConcreteMessage} o subclases de esta que implemente el método {@code getBytes()}
     *            declarado abstracto en {@link com.obcamp.OBCCrypto.network.core.messages.base.AbstractMessage}.
     * @throws IOException Si ocurre un error de I/O
     */
    public synchronized void sendMessage(ConcreteMessage msg) throws IOException{
        outputStream.write(msg.getBytes());
    }

    /**
     * Envia el mensaje versión, iniciando la comunicación o respondiendo a un mensaje versión recibido
     * si es el peer quien inicia la conexión.
     */
    private void sendVersionMessage(){
        // TODO Implementar este método cuando estén listas las clases de mensajes.
        // llamara a sendMessage pasandole un nuevo mensaje de tipo version

    }


    /**
     * Cuando el nodo actua como "servidor" (ha aceptado un socket entrante), el proceso de saludo
     * requiere el envío de un mensaje verack (version acknoledge -> Reconocimiento de la versión)
     * para indicarle al peer que conoce el protocolo de comunicación, o una versión compatible del mismo.
     * La recepión de este mensaje por el peer implica que termina el proceso de saludo y comienza el de
     * sincronización.
     */
    private void sendVerackMessage(){
        // TODO Implementar este método cuando esten listas las clases de mensajes
    }


}
