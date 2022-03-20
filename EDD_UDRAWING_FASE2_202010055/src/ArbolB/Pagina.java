package ArbolB;

public class Pagina {
    boolean hoja;
    int noNodos;
    NodoB head;

    public Pagina(){
        this.head = null;
        this.noNodos = 0;
        this.hoja = true;
    }

    public Pagina(NodoB nodo){
        this.head = null;
        this.noNodos = 0;
        this.hoja = true;
        insertar(nodo);
    }

    public void insertar(NodoB nuevo){
        if(head == null){
            head = nuevo;
            noNodos++;
            return;
        }
        
        NodoB aux = head;
        while(head != null){
            if(aux.id == nuevo.id){
                System.out.println("Nodo ya existe");
                return;
            }
            
            if(aux.id > nuevo.id){
                if(aux == head){
                    nuevo.siguiente = aux;
                    aux.anterior = nuevo;

                    aux.izquierda = nuevo.derecha;
                    nuevo.derecha = null;
                    head = nuevo;
                    noNodos++;
                    return;
                }   
                nuevo.siguiente = aux;
                aux.izquierda = nuevo.derecha;
                nuevo.derecha = null;
                nuevo.anterior = aux.anterior;
                aux.anterior.siguiente = nuevo;
                aux.anterior = nuevo;
                noNodos++;
                return;
            }
            if(aux.siguiente == null){
                aux.siguiente = nuevo;
                nuevo.anterior = aux;
                noNodos++;
                return;
            }

            aux = aux.siguiente;

        }
    }

}
