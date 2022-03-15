package Cola;

public class NodoC<E> {
    private E valor;
    private NodoC<E> siguiente;

    public NodoC(E valor){
        this.valor = valor;
        this.siguiente = null;
    }

    public E getValor() {
        return this.valor;
    }

    public void setValor(E valor) {
        this.valor = valor;
    }

    public NodoC<E> getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoC<E> siguiente) {
        this.siguiente = siguiente;
    }


}



 