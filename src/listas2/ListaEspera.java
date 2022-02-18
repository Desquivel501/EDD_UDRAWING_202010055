package listas2;

import Models.Cliente;
import Nodos.NodoClienteD;
import Nodos.NodoImagen;

public class ListaEspera {
    private NodoClienteD head;
    private int largo;

    public ListaEspera() {
        this.head = null;
        this.largo = 0;
    }


    public boolean vacia(){
        return this.head == null;
    }

    public int getLargo() {
        return this.largo;
    }

    public NodoClienteD getHead(){
        return this.head;
    }

    public void insertar(Cliente cliente){
        NodoClienteD nuevo = new NodoClienteD(cliente);
        if(head == null){
            nuevo.setSiguiente(nuevo);
            nuevo.setAnterior(nuevo);
            this.head = nuevo;
            largo++;
            return;
        }
        NodoClienteD last = head.getAnterior();
        nuevo.setSiguiente(head);
        head.setAnterior(nuevo);
        nuevo.setAnterior(last);
        last.setSiguiente(nuevo);
        largo++;

    }

 

    public void remover(int indice){
        NodoClienteD nodo = this.head;

        for(int i = 0; i<largo; i++){
            if(nodo.getValor().getId() == indice){
                break;
            }
            nodo = nodo.getSiguiente();
        }
        NodoClienteD pre = nodo.getAnterior();
        NodoClienteD aft = nodo.getSiguiente();
        pre.setSiguiente(aft); 
        aft.setAnterior(pre);

        if(head==nodo){
            head = pre;
        }
        this.largo--;
    }


    public NodoClienteD buscar(int indice){
        NodoClienteD aux = this.head;
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
        dot.append("subgraph cluster_2{\n");

        StringBuilder nombresNodos = new StringBuilder();
        StringBuilder conexiones = new StringBuilder();
        StringBuilder ranks = new StringBuilder();

        dot.append("fontname = \"Bitstream Vera Sans\"\n");
        dot.append("fontsize = 8\n");
        dot.append("node [fontname = \"Bitstream Vera Sans\"fontsize = 8shape = \"record\"]\n");

        ranks.append("{rank=\"same\";");

        NodoClienteD aux = this.head;
        for(int i = 0; i < this.largo; i++){
            
            Cliente actual = aux.getValor();
            String groupName = "g" + actual.getId();

            int nodoCliente = aux.hashCode();

            String label = String.format("\"{Cliente %d | Nombre: %s\\l| Imagenes a Color: %s\\l Imagenes en blanco y negro: %s\\l}\"", 
            actual.getId(),actual.getNombre(),actual.getImg_color(), actual.getImg_bw());
            nombresNodos.append("Nodo" + nodoCliente + "[label=" + label +"];\n");
            nombresNodos.append("Nodo" + nodoCliente + "[group=" + groupName +"]\n");
            

            NodoImagen imagen = actual.getImagenes().peek();
            
            if(imagen != null){
                conexiones.append(String.format("Nodo%d -> Nodo%d;\n", nodoCliente, + imagen.hashCode()));
                while(imagen != null){
                    nombresNodos.append("Nodo" + imagen.hashCode() + "[label= \"Imagen " + imagen.getValor().getTipo() +"\"];\n");
                    nombresNodos.append("Nodo" + imagen.hashCode() + "[group=" + groupName +"]\n");
                    
                    if(imagen.getSiguiente() != null){
                        conexiones.append(String.format("Nodo%d -> Nodo%d;\n", imagen.hashCode(), + imagen.getSiguiente().hashCode()));
                    }
                        
                    imagen = imagen.getSiguiente();
                }
            }

            if(i + 1 < this.largo){
                // conexiones.append(String.format("Nodo%d -> Nodo%d;\n", nodoCliente, aux.getSiguiente().hashCode()));
                ranks.append("Nodo" + nodoCliente + ",");
            }else{
                ranks.append("Nodo" + nodoCliente);
                // conexiones.append(String.format("Nodo%d -> Nodo%d;\n",nodoInicial, nodoCliente));

            }
            aux = aux.getSiguiente();
        }
        ranks.append("}\n");
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append(ranks);
        dot.append("label = \"Lista Espera\";\n");
        dot.append("rankdir=TB;\n");
        dot.append("}\n");

        return dot.toString();
    }

    public String buidNodo(Cliente actual){
        String nombre = actual.getNombre();
        String id = Integer.toString(actual.getId());
        String img_color = Integer.toString(actual.getImg_color());
        String img_bw = Integer.toString(actual.getImg_bw());
        String label = String.format("\"{Cliente %s | Nombre: %s\\l| Imagenes a Color: %s\\l Imagenes en blanco y negro: %s\\l|}\"", 
                                        id,nombre,img_color,img_bw);

        return label;
    }

}
