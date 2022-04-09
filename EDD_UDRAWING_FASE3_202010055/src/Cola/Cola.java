package Cola;

public class Cola<E> {
    private NodoC<E> head;
    private NodoC<E> tail;
    private int largo;

    public Cola() {
        this.head = null;
        this.tail = null;
        this.largo = 0;
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
            NodoC<E> aux = this.head;
            while(aux != null){
                cadena += aux.getValor() + ", ";
                aux = aux.getSiguiente();
            }
            System.out.print(cadena);
        }
    }

    public void enqueue(E valorNuevo){
        NodoC<E> nuevo = new NodoC<>(valorNuevo);
        if (this.head == null){
            this.head = nuevo;
            this.tail = nuevo;
            return;
        }
        this.tail.setSiguiente(nuevo);
        this.tail = nuevo;
    }

    public NodoC<E> dequeue(){
        if(this.head != null){
            NodoC<E> aux = this.head;
            this.head = this.head.getSiguiente();
            return aux;
        }
        else{
            return null;
        }
    }

    public NodoC<E> peek(){
        return this.head;
    }
}
