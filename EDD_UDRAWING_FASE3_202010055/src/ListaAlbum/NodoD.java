package ListaAlbum;

import Models.Album;

public class NodoD {
    private Album valor;
    private NodoD siguiente;
    private NodoD anterior;

    public NodoD(Album valor){
        this.valor = valor;
        this.siguiente = null;
        this.anterior = null;
    }

    public Album getAlbum() {
        return this.valor;
    }

    public void setAlbum(Album valor) {
        this.valor = valor;
    }

    public NodoD getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoD siguiente) {
        this.siguiente = siguiente;
    }

    public NodoD getAnterior() {
        return this.anterior;
    }

    public void setAnterior(NodoD anterior) {
        this.anterior = anterior;
    }

}
