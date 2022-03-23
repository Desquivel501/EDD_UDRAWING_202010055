package Lista;

import Matriz.MatrizCapa;

public class Nodo {
    private MatrizCapa valor;
    private Nodo siguiente;

    public Nodo(MatrizCapa valor){
        this.valor = valor;
        this.siguiente = null;
    }

    public MatrizCapa getValor() {
        return this.valor;
    }

    public void setValor(MatrizCapa valor) {
        this.valor = valor;
    }

    public Nodo getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

}



 