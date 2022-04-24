package Cola;

import Models.Dist;

public class NodoPr {
    private Dist valor;
    private NodoPr siguiente;

    public NodoPr(Dist valor){
        this.valor = valor;
        this.siguiente = null;
    }

    public Dist getValor() {
        return this.valor;
    }

    public void setValor(Dist valor) {
        this.valor = valor;
    }

    public NodoPr getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoPr siguiente) {
        this.siguiente = siguiente;
    }


}