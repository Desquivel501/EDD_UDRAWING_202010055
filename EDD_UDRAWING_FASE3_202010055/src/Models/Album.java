package Models;

import ListaAlbum.NodoI;
public class Album {
    private String nombre;
    private NodoI primero;
    private NodoI ultimo;
    private int largo;


    public Album(String nombre) {
        this.nombre = nombre;
        this.primero = null;
        this.ultimo = null;
        this.largo =0;
    }

    public int getLargo(){
        return largo;
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
            largo++;
            return;
        }
        ultimo.setSiguiente(nuevo);
        ultimo = nuevo;
        largo++;
    }

    public void eliminar(int id){
        if(primero==null) return;
        if(primero.getImagen().getId() == id){
            primero = primero.getSiguiente();
            return;
        }

        NodoI aux = primero;
        while(aux.getSiguiente() != null){
            if(aux.getSiguiente().getImagen().getId() == id){
                NodoI aft = aux.getSiguiente().getSiguiente();
                aux.setSiguiente(aft);
                return;
            }
            aux = aux.getSiguiente();
        }
    }


    public Imagen getImagen(int id){
        if(primero==null) return null;
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
