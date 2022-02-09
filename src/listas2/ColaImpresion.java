package listas2;

import Models.Imagen;
import Nodos.NodoImagen;

public class ColaImpresion {
    private NodoImagen head;
    private NodoImagen tail;
    private int largo;

    public ColaImpresion() {
        this.head = null;
        this.tail = null;
        this.largo = 0;
    }

    public boolean vacia(){
        return this.head == null;
    }

    public int getLargo(){
        return this.largo;
    }

    public void imprimir(){
        if (!vacia()){
            String cadena = "";
            NodoImagen aux = this.head;
            while(aux != null){
                cadena += aux.getValor() + ", ";
                aux = aux.getSiguiente();
            }
            System.out.print(cadena);
        }
    }

    public void enqueue(Imagen valorNuevo){
        NodoImagen nuevo = new NodoImagen(valorNuevo);
        if (this.head == null){
            this.head = nuevo;
            this.tail = nuevo;
            largo++;
            return;
        }
        this.tail.setSiguiente(nuevo);
        this.tail = nuevo;
        largo++;
    }

    public NodoImagen dequeue(){
        if(this.head != null){
            NodoImagen aux = this.head;
            this.head = this.head.getSiguiente();
            largo--;
            return aux;
        }
        else{
            return null;
        }
    }

    public NodoImagen peek(){
        return this.head;
    }

    public void recibirImagenes(PilaImagenes pila){
        while(!pila.vacia()){
            enqueue(pila.remover().getValor());
        }
    }

    public NodoImagen getHead(){
        return this.head;
    }
}
