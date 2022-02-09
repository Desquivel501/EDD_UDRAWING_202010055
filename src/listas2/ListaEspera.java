package listas2;

import Models.Cliente;
import Nodos.NodoClienteD;

public class ListaEspera {
    private NodoClienteD head;
    private int largo;

    public ListaEspera() {
        this.head = null;
        this.largo = 0;
    }


    public boolean vacia(){
        return this.head == null;
    }

    public int getLargo() {
        return this.largo;
    }

    public NodoClienteD getHead(){
        return this.head;
    }

    public void insertar(Cliente cliente){
        NodoClienteD nuevo = new NodoClienteD(cliente);
        if(head == null){
            nuevo.setSiguiente(nuevo);
            nuevo.setAnterior(nuevo);
            this.head = nuevo;
            largo++;
            return;
        }
        NodoClienteD last = head.getAnterior();
        nuevo.setSiguiente(head);
        head.setAnterior(nuevo);
        nuevo.setAnterior(last);
        last.setSiguiente(nuevo);
        largo++;

    }

 

    public void remover(int indice){
        NodoClienteD nodo = this.head;

        for(int i = 0; i<largo; i++){
            if(nodo.getValor().getId() == indice){
                break;
            }
            nodo = nodo.getSiguiente();
        }
        NodoClienteD pre = nodo.getAnterior();
        NodoClienteD aft = nodo.getSiguiente();
        pre.setSiguiente(aft); 
        aft.setAnterior(pre);

        if(head==nodo){
            head = pre;
        }
        this.largo--;
    }


    public NodoClienteD buscar(int indice){
        NodoClienteD aux = this.head;
        int k = 0;
        while(k < indice){
            aux = aux.getSiguiente();
            k++;
            if(aux == null){
                System.out.print("Error: Indice no encontrado");
                return null;
            }
        }
        return aux;
    } 

}
