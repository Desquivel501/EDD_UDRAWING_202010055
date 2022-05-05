package Lista;
public class Lista_recorrido{

    private NodoR head, tail;
    private int largo;
    private int tiempo;

    public Lista_recorrido() {
        this.head = null;
        this.largo = 0;
        this.tail = null;
    }

    public void setTiempo(int tiempo){
        this.tiempo = tiempo;
    }

    public int getTiempo(){
        return this.tiempo;
    }

    public NodoR getHead(){
        return head;
    }

    public NodoR getTail(){
        return tail;
    }

    public boolean vacia(){
        return this.head == null;
    }

    public int getLargo(){
        return this.largo;
    }

    public String imprimir(){
        String cadena = "";
        if (!vacia()){
            NodoR aux = this.head;
            while(aux != null){
                cadena += aux + "\n";
                aux = aux.getSiguiente();
            }
            System.out.println(cadena);
        }
        return cadena;
    }

    public void insertar(int id_lugar, String nombre){
        NodoR nuevo = new NodoR(id_lugar,nombre);
        if (this.head == null){
            this.head = nuevo;
            this.tail = nuevo;
            return;
        }
        nuevo.setSiguiente(this.head);
        this.head = nuevo;
        this.largo++;
    }

    @Override
    public String toString() {
        String cadena = "";
        if (!vacia()){
            NodoR aux = this.head;
            while(aux != null){
                cadena += aux;
                if(aux.getSiguiente() != null) cadena += ", ";
                aux = aux.getSiguiente();
            }
            System.out.println(cadena);
        }
        return cadena;
    }

}
