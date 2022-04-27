package Lista;
public class Lista_recorrido{

    private NodoR head;
    private int largo;
    private int tiempo;

    public Lista_recorrido() {
        this.head = null;
        this.largo = 0;
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

    public boolean vacia(){
        return this.head == null;
    }

    public int getLargo(){
        return this.largo;
    }

    public void imprimir(){
        if (!vacia()){
            String cadena = "";
            NodoR aux = this.head;
            while(aux != null){
                cadena += aux + "\n";
                aux = aux.getSiguiente();
            }
            System.out.println(cadena);
        }
    }

    public void insertar(int id_lugar, String nombre){
        NodoR nuevo = new NodoR(id_lugar,nombre);
        if (this.head == null){
            this.head = nuevo;
            return;
        }
        nuevo.setSiguiente(this.head);
        this.head = nuevo;
        this.largo++;
    }

}
