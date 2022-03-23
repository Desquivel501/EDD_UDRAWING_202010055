package Lista;

import Matriz.MatrizCapa;

public class Lista{

    private Nodo head;
    private int largo;
    private Nodo tail;

    public Lista() {
        this.head = null;
        this.largo = 0;
        this.tail = null;
    }

    public Nodo getHead(){
        return head;
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
            Nodo aux = this.head;
            while(aux != null){
                cadena += aux.getValor() + ", ";
                aux = aux.getSiguiente();
            }
            System.out.print(cadena);
        }
    }

    public void insertar(MatrizCapa valorNuevo){
        Nodo nuevo = new Nodo(valorNuevo);
        if (this.head == null){
            this.head = nuevo;
            this.tail = nuevo;
            return;
        }

        this.tail.setSiguiente(nuevo);
        this.tail = nuevo;
        this.largo++;
    }

}
