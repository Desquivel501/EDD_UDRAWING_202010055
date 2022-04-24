package Cola;

import Models.Dist;

public class ColaP {
    private NodoPr head;
    private NodoPr tail;
    private int largo;

    public ColaP() {
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
            NodoPr aux = this.head;
            while(aux != null){
                cadena += aux.getValor() + ", ";
                aux = aux.getSiguiente();
            }
            System.out.print(cadena);
        }
    }

    public void actualizar(int id, int dist){
        var aux = this.head;
        while(aux != null){
            if(aux.getValor().id == id){
                aux.getValor().dist = dist;
                return;
            }
            aux = aux.getSiguiente();
        }
    }

    public void insertar(Dist valorNuevo){
        NodoPr nuevo = new NodoPr(valorNuevo);
        var nodo = this.head;
        if (nodo == null){
            this.head = nuevo;
            this.tail = nuevo;
            return;
        }
        if(valorNuevo.dist < nodo.getValor().dist){
            nuevo.setSiguiente(nodo);
            this.head = nuevo;
            return;
        }
        
        while(nodo.getSiguiente() != null){
            if(valorNuevo.dist <= nodo.getSiguiente().getValor().dist){
                nuevo.setSiguiente(nodo.getSiguiente());
                nodo.setSiguiente(nuevo);
                return;
            }
            nodo = nodo.getSiguiente();
        }
        nodo.setSiguiente(nuevo);
    }

    public NodoPr dequeue(){
        if(this.head != null){
            NodoPr aux = this.head;
            this.head = this.head.getSiguiente();
            return aux;
        }
        else{
            return null;
        }
    }

    public NodoPr peek(){
        return this.head;
    }
}
