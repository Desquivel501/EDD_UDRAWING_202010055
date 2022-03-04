package Nodos;

import Models.Cliente;

public class NodoClienteD {
    private Cliente valor;
    private NodoClienteD siguiente;
    private NodoClienteD anterior;

    public NodoClienteD(Cliente valor){
        this.valor = valor;
        this.siguiente = null;
        this.anterior = null;
    }

    public Cliente getValor() {
        return this.valor;
    }

    public void setValor(Cliente valor) {
        this.valor = valor;
    }

    public NodoClienteD getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoClienteD siguiente) {
        this.siguiente = siguiente;
    }

    public NodoClienteD getAnterior() {
        return this.anterior;
    }

    public void setAnterior(NodoClienteD anterior) {
        this.anterior = anterior;
    }

}
