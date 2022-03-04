package Nodos;

import Models.Cliente;
import Models.Imagen;
import listas2.PilaImagenes;

public class NodoVentanilla {
    private int id;
    private NodoVentanilla siguiente;
    private PilaImagenes imagenes;
    private Cliente clienteActual;

    public NodoVentanilla(int id){
        this.id = id;
        this.siguiente = null;
        this.imagenes = new PilaImagenes();
        this.clienteActual = null;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NodoVentanilla getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoVentanilla siguiente) {
        this.siguiente = siguiente;
    }

    public boolean disponible(){
        return clienteActual == null;
    }

    public PilaImagenes getImagenes() {
        return this.imagenes;
    }

    public void setImagenes(PilaImagenes imagenes) {
        this.imagenes = imagenes;
    }

    public Cliente getClienteActual() {
        return this.clienteActual;
    }

    public void setClienteActual(Cliente clienteActual) {
        this.clienteActual = clienteActual;
    }

    public void recibirImagen(Imagen nueva){
        imagenes.stack(nueva);
    }

    public PilaImagenes enviarImagenes(){
        PilaImagenes pila_aux = new PilaImagenes();
        while(!imagenes.vacia()){
            pila_aux.stack(imagenes.remover().getValor());
        }
        return pila_aux;
    }

}



 