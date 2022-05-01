package Lista;
public class Lista<E extends Comparable<E>>{

    private Nodo<E> head;
    private int largo;
    private Nodo<E> tail;

    public Lista() {
        this.head = null;
        this.largo = 0;
        this.tail = null;
    }

    public Nodo<E> getHead(){
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
            Nodo<E> aux = this.head;
            while(aux != null){
                cadena += aux.getValor() + ", ";
                aux = aux.getSiguiente();
            }
            System.out.println(cadena);
        }
    }

    public void insertar(E valorNuevo){
        Nodo<E> nuevo = new Nodo<E>(valorNuevo);
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

    public void insertar_inicio(E valorNuevo){
        Nodo<E> nuevo = new Nodo<E>(valorNuevo);
        if (this.head == null){
            this.head = nuevo;
            this.tail = nuevo;
            this.largo++;
            return;
        }
        nuevo.setSiguiente(this.head);
        this.head = nuevo;
        this.largo++;
    }

    public void ordenar(){
        Nodo<E> aux = this.head;
        for(int i=0; i < largo -1; i++){
            Nodo<E> maximo = aux;
            Nodo<E> punt = aux;
            while(punt != null){
                if(punt.getValor().compareTo(maximo.getValor()) > 0){
                    maximo = punt;
                } 
                punt = punt.getSiguiente();
            }
            E temp = aux.getValor();
            aux.setValor(maximo.getValor());
            maximo.setValor(temp);
            aux = aux.getSiguiente();
        }
    }

    public E get(int indice){
        Nodo<E> aux = this.head;
        int cont = 0;
        while(aux != null){
            if(cont == indice){
                return aux.getValor();
            }
            cont++;
            aux = aux.getSiguiente();
        }
        return null;
    }

}
