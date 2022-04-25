package Matriz;

public class NodoE {
    private int id;
    private NodoE anterior;
    private NodoE siguiente;
    private NodoM accesoNodo;


    public NodoE() {
    }

    public NodoE(int id) {
        this.id = id;
        this.anterior = null;
        this.siguiente = null;
        this.accesoNodo = null;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NodoE getAnterior() {
        return this.anterior;
    }

    public void setAnterior(NodoE anterior) {
        this.anterior = anterior;
    }

    public NodoE getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoE siguiente) {
        this.siguiente = siguiente;
    }

    public NodoM getAccesoNodo() {
        return this.accesoNodo;
    }

    public void setAccesoNodo(NodoM accesoNodo) {
        this.accesoNodo = accesoNodo;
    }




}
