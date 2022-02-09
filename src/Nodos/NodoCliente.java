package Nodos;

import Models.Cliente;

public class NodoCliente {
    private Cliente valor;
    private NodoCliente siguiente;

    public NodoCliente(Cliente valor){
        this.valor = valor;
        this.siguiente = null;
    }

    public Cliente getValor() {
        return this.valor;
    }

    public void setValor(Cliente valor) {
        this.valor = valor;
    }

    public NodoCliente getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoCliente siguiente) {
        this.siguiente = siguiente;
    }


}
