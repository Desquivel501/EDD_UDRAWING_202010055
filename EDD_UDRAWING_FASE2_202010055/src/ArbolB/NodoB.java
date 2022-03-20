package ArbolB;

import Models.Cliente;

public class NodoB {
    int id;
    NodoB anterior;
    NodoB siguiente;
    Pagina derecha;
    Pagina izquierda;
    Cliente cliente;

    public NodoB(int id, Cliente cliente){
        this.id = id;
        this.anterior = null;
        this.siguiente = null;
        this.derecha = null;
        this.izquierda = null;
        this.cliente = cliente;
    }

    public NodoB(int id, Cliente cliente,  Pagina derecha, Pagina izquierda){
        this.id = id;
        this.anterior = null;
        this.siguiente = null;
        this.derecha = derecha;
        this.izquierda = izquierda;
        this.cliente = cliente;
    }




    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NodoB getAnterior() {
        return this.anterior;
    }

    public void setAnterior(NodoB anterior) {
        this.anterior = anterior;
    }

    public NodoB getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoB siguiente) {
        this.siguiente = siguiente;
    }

    public Pagina getDerecha() {
        return this.derecha;
    }

    public void setDerecha(Pagina derecha) {
        this.derecha = derecha;
    }

    public Pagina getIzquierda() {
        return this.izquierda;
    }

    public void setIzquierda(Pagina izquierda) {
        this.izquierda = izquierda;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    

}
