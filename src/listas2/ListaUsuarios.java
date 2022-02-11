package listas2;

import Models.Cliente;
import Nodos.NodoCliente;

public class ListaUsuarios{

    private NodoCliente head;
    private int largo;
    private NodoCliente tail;

    public ListaUsuarios() {
        this.head = null;
        this.largo = 0;
        this.tail = null;
    }

    public NodoCliente getHead(){
        return head;
    }

    public NodoCliente getTail(){
        return tail;
    }

    public boolean vacia(){
        return this.head == null;
    }

    public int getLargo(){
        return this.largo;
    }

    // public void imprimir(){
    //     NodoCliente aux = this.head;

    //     do{

    //     }while(aux != null);
    // }

    public void insertar(Cliente valorNuevo){
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

    public String graficar(){
        StringBuilder dot = new StringBuilder();
        dot.append("subgraph ListaUsuarios{\n");

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
        dot.append("rankdir=TB;\n");
        dot.append("}");

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
