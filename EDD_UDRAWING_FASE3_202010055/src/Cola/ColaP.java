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
                cadena += aux.getValor().id + ", ";
                aux = aux.getSiguiente();
            }
            System.out.println("Cola: "+ cadena);
        }
    }

    public NodoPr buscar(int id){
        var aux = this.head;
        while(aux != null){
            if(aux.getValor().id == id){
                return aux;
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    public void actualizar(int id, int dist){
        var aux = this.head;
        while(aux != null){
            if(aux.getValor().id == id){
                aux.getValor().dist = dist;
                ordenar();
                return;
            }
            aux = aux.getSiguiente();
        }
    }

    public void ordenar(){
        NodoPr aux = this.head;
        while(aux != null){
            NodoPr minimo = aux;
            NodoPr punt = aux;
            while(punt != null){
                if(punt.getValor().dist < minimo.getValor().dist){
                    minimo = punt;
                } 
                punt = punt.getSiguiente();
            }
            var temp = aux.getValor();
            aux.setValor(minimo.getValor());
            minimo.setValor(temp);
            aux = aux.getSiguiente();
        }
        // imprimir();
    }

    public void insertar(Dist valorNuevo){
        NodoPr nuevo = new NodoPr(valorNuevo);
        var nodo = this.head;
        if (nodo == null){
            this.head = nuevo;
            this.tail = nuevo;
            this.largo++;
            return;
        }
        if(valorNuevo.dist < nodo.getValor().dist){
            nuevo.setSiguiente(nodo);
            this.head = nuevo;
            this.largo++;
            return;
        }
        
        while(nodo.getSiguiente() != null){
            if(valorNuevo.dist <= nodo.getSiguiente().getValor().dist){
                nuevo.setSiguiente(nodo.getSiguiente());
                nodo.setSiguiente(nuevo);
                this.largo++;
                return;
            }
            nodo = nodo.getSiguiente();
        }
        this.largo++;
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
