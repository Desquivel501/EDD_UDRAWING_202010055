package Models;

import ListaAlbum.NodoI;

public class Album {
    private String nombre;
    private NodoI primero;
    private NodoI ultimo;


    public Album(String nombre) {
        this.nombre = nombre;
        this.primero = null;
        this.ultimo = null;
    }


    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public NodoI getUltimo() {
        return this.ultimo;
    }

    public void setUltimo(NodoI ultimo) {
        this.ultimo = ultimo;
    }

    public NodoI getPrimero() {
        return this.primero;
    }

    public void setPrimero(NodoI primero) {
        this.primero = primero;
    }

    public void insertar(Imagen imagen){
        NodoI nuevo = new NodoI(imagen);
        if(primero == null){
            primero = nuevo;
            ultimo = nuevo;
            return;
        }
        ultimo.setSiguiente(nuevo);
        ultimo = nuevo;
    }


    public Imagen getImagen(int id){
        NodoI aux = primero;
        while(aux != null){
            if(id == aux.getImagen().getId()){
                return aux.getImagen();
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    public StringBuilder graficar(int padre, StringBuilder dot){
        NodoI aux = primero;
        while(aux != null){
            dot.append(String.format("Nodo%d[label=\"Imagen %d\" shape=box];\n",aux.hashCode(),aux.getImagen().getId()));
            dot.append(String.format("Nodo%d -> Nodo%d;\n", padre, aux.hashCode()));
            padre = aux.hashCode();
            aux = aux.getSiguiente();
        }
        return dot;
    }
}
