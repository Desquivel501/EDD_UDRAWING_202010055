package listas2;

import Models.Cliente;
import Nodos.NodoCliente;

public class ListaAtendidos{

    private NodoCliente head;
    private int largo;
    private NodoCliente tail;

    public ListaAtendidos() {
        this.head = null;
        this.largo = 0;
        this.tail = null;
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
            NodoCliente aux = this.head;
            while(aux != null){
                cadena += aux.getValor() + ", ";
                aux = aux.getSiguiente();
            }
            System.out.print(cadena);
        }
    }

    public void insertarFinal(Cliente valorNuevo){
        NodoCliente nuevo = new NodoCliente(valorNuevo);
        if (this.head == null){
            this.head = nuevo;
            this.tail = nuevo;
            this.largo++;
            return;
        }
        this.tail.setSiguiente(nuevo);
        this.tail = nuevo;
        this.largo++;
    }

    public void remover(int indice){
        NodoCliente pre = this.head;
        int k = 0;

        while (k < indice-1){
            pre = pre.getSiguiente();
            k++;
            if (pre==null){
                System.out.print("Error: Indice no encontrado");
                return;
            }
        }
        NodoCliente borrar = pre.getSiguiente();
        NodoCliente aft = borrar.getSiguiente();
        pre.setSiguiente(aft); 
        this.largo--;
    }

    public NodoCliente buscar(int indice){
        NodoCliente aux = this.head;
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
