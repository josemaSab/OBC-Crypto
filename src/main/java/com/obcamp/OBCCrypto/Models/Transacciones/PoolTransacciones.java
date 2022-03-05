package com.obcamp.OBCCrypto.Models.Transacciones;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Proyecto OBC-Crypto
 * Clase Pool de transacciones.
 * @author Josema
 * @version 1.0
 * Equipo: Josema, Adrian, Manu
 */
public class PoolTransacciones {

    //ATRIBUTOS

    private FileOutputStream fileOutputStream;
    private ObjectOutputStream salida;

    //CONSTRUCTORES

    public PoolTransacciones() {
    }

    //METODOS

    public boolean addTransaccion(Transaccion transaccion){
        if(this.crearFlujo()){
            try {
                this.salida.writeObject(transaccion);
                System.out.println("Se agregó la transacción al pool de transacciones.");
                System.out.println("--------------------------------------------------");
                System.out.println(transaccion.toString());
                return true;
            } catch (IOException e) {
                System.err.println("Ocurrió un error al intentar guardar la transacción. " + e.getMessage());
                return false;
            }finally {
                this.cerrarFlujo();
            }
        }
        System.out.println("No se pudo agregar la transacción al pool de transacciones.");
        return false;

    }

    /**
     * Metodo que crea un flujo de salida hacia el disco para trabajar con el archivo del pool de transacciones
     * @return true si el flujo se ha creado o existe y false si hubo algun problema
     */
    private boolean crearFlujo(){
        if(this.salida == null){
            try {
                this.fileOutputStream = new FileOutputStream("pool.dat");
                this.salida = new ObjectOutputStream(this.fileOutputStream);
                System.out.println("Se ha abierto un flujo de salida.");
                return true;
            } catch (FileNotFoundException e) {
                System.err.println("El archivo pool.dat no se encuentra en el sistema. " + e.getMessage());
                return false;
            } catch (IOException e) {
                System.err.println("Ocurrió un error al intentar crear el flujo de salida." + e.getMessage());
                return false;
            }
        }
        System.out.println("El flujo ya esta creado.");
        return true;
    }

    private boolean cerrarFlujo(){
        if(this.salida != null){
            try {
                this.salida.close();
                this.fileOutputStream.close();
                System.out.println("El flujo se cerró correctamente.");
                return true;
            } catch (IOException e) {
                System.err.println("Ocurrió un erro al cerrar el flujo. " + e.getMessage());;
                return false;
            }
        }
        System.out.println("El flujo ya estaba cerrado.");
        return true;
    }

    //GETTER Y SETTER


    public FileOutputStream getFileOutputStream() {
        return fileOutputStream;
    }

    public void setFileOutputStream(FileOutputStream fileOutputStream) {
        this.fileOutputStream = fileOutputStream;
    }

    public ObjectOutputStream getSalida() {
        return salida;
    }

    public void setSalida(ObjectOutputStream salida) {
        this.salida = salida;
    }
}
