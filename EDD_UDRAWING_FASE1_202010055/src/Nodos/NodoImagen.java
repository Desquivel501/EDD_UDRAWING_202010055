package Nodos;

import Models.Imagen;

public class NodoImagen {
    private Imagen valor;
    private NodoImagen siguiente;

    public NodoImagen(Imagen valor){
        this.valor = valor;
        this.siguiente = null;
    }

    public Imagen getValor() {
        return this.valor;
    }

    public void setValor(Imagen valor) {
        this.valor = valor;
    }

    public NodoImagen getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoImagen siguiente) {
        this.siguiente = siguiente;
    }


}