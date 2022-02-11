package listas2;

import Models.Cliente;
import Nodos.NodoCliente;

public class ColaRecepcion {
    private NodoCliente head;
    private NodoCliente tail;
    private int largo;

    public ColaRecepcion() {
        this.head = null;
        this.tail = null;
        this.largo = 0;
    }

    public NodoCliente getHead() {
        return head;
    }

    public boolean vacia(){
        return this.head == null;
    }

    public int getLargo(){
        return this.largo;
    }

    // public void imprimir(){
    //     if (!vacia()){
    //         NodoCliente aux = this.head;
    //         while(aux != null){
    //             System.out.println("Cliete " + aux.getValor().getId());
    //             System.out.println(aux.getValor().getNombre());
    //             System.out.println("Tiempo: " + aux.getValor().getTiempoEspera());
    //             aux = aux.getSiguiente();
    //         }
    //     }
    // }

    public void enqueue(Cliente valorNuevo){
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

    public NodoCliente dequeue(){
        if(this.head != null){
            NodoCliente aux = this.head;
            this.head = this.head.getSiguiente();
            this.largo--;
            return aux;
        }
        else{
            return null;
        }
    }

    public NodoCliente peek(){
        return this.head;
    }

    // public void crearRandom(){
    //     ClienteRandom rand = new ClienteRandom();
    //     int numClientes = rand.numClientes();

    //     for(int i = 0; i<numClientes; i++){
    //         String nombre = rand.nombre() + " " + rand.apellido();
    //         int img_color = rand.numImagenes();
    //         int img_bw = rand.numImagenes();
    //         if(img_bw==0 && img_color==0){img_bw=1;}
    //         Cliente nuevo = new Cliente(nombre, this.largo + 1, false, img_color, img_bw);
    //         this.enqueue(nuevo);
    //     }
    // }

    public ListaUsuarios dump(){
        ListaUsuarios nuevaLista = new ListaUsuarios();
        
        NodoCliente aux = this.head;
        while(aux != null){
            nuevaLista.insertar(aux.getValor());
            aux = aux.getSiguiente();
        }
        return nuevaLista;
    }

    public String graficar(){
        StringBuilder dot = new StringBuilder();
        dot.append("subgraph cluster_0{\n");

        StringBuilder nombresNodos = new StringBuilder();
        StringBuilder conexiones = new StringBuilder();

        dot.append("fontname = \"Bitstream Vera Sans\"\n");
        dot.append("fontsize = 8\n");
        dot.append("node [fontname = \"Bitstream Vera Sans\"fontsize = 8shape = \"record\"]\n");

        NodoCliente aux = this.head;
        while(aux != null){
            // nombresNodos.append("Nodo" + aux.hashCode() + "[label=\"" + aux.getValor().getNombre() +"\"];\n");
            Cliente actual = aux.getValor();
            
            nombresNodos.append("Nodo" + aux.hashCode() + "[label=" + buidNodo(actual) +"];\n");
            if(aux.getSiguiente() != null){
                conexiones.append(String.format("Nodo%d -> Nodo%d;\n", aux.hashCode(), aux.getSiguiente().hashCode()));
            }
            aux = aux.getSiguiente();
        }
        
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("label = \"Cola de Recepcion\";\n");
        dot.append("rankdir=TB;\n");
        dot.append("}\n");

        return dot.toString();
    }

    public String buidNodo(Cliente actual){
        String nombre = actual.getNombre();
        String id = Integer.toString(actual.getId());
        String img_color = Integer.toString(actual.getImg_color());
        String img_bw = Integer.toString(actual.getImg_bw());
        String label = String.format("\"{Cliente %s | Nombre: %s\\l| Imagenes a Color: %s\\l Imagenes en blanco y negro: %s\\l}\"", 
                                        id,nombre,img_color,img_bw);

        return label;
    }
}



