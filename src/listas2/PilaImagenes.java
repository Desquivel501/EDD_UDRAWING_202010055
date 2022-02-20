package listas2;

import Models.Imagen;
import Nodos.NodoImagen;

public class PilaImagenes {
    private NodoImagen head;
    private int largo;

    public PilaImagenes() {
        this.head = null;
        this.largo = 0;
    }

    public boolean vacia(){
        return this.head == null;
    }

    public int getLargo(){
        return this.largo;
    }
    
    public void stack(Imagen valorNuevo){
        NodoImagen nuevo = new NodoImagen(valorNuevo);
        if(this.head == null){
            this.head = nuevo;
            largo++;
            return;
        }
        nuevo.setSiguiente(this.head);
        this.head = nuevo;
        largo++;
    }

    public NodoImagen remover(){
        if(this.head != null){
            NodoImagen aux = this.head;
            this.head = this.head.getSiguiente();
            return aux;
        }
        else{
            return null;
        }
    }

    public NodoImagen peek(){
        return this.head;
    }
}
