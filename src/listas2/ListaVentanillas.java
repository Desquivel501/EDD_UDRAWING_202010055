package listas2;

import Models.Cliente;
import Nodos.NodoImagen;
import Nodos.NodoVentanilla;

public class ListaVentanillas{

    private NodoVentanilla head;
    private int largo;
    private NodoVentanilla tail;

    public ListaVentanillas() {
        this.head = null;
        this.largo = 0;
        this.tail = null;
    }

    public boolean vacia(){
        return this.head == null;
    }

    public int getLargo(){
        return this.largo;
    }

    // public void imprimir(){
    //     if (!vacia()){
    //         String cadena = "";
    //         Ventanilla aux = this.head;
    //         while(aux != null){
    //             cadena += aux.getValor() + ", ";
    //             aux = aux.getSiguiente();
    //         }
    //         System.out.print(cadena);
    //     }
    // }

    public void crearVentanillas(int num){
        for(int i = 0; i < num; i++ ){
            NodoVentanilla nuevo = new NodoVentanilla(this.largo + 1);
            if (this.head == null){
                this.head = nuevo;
                this.tail = nuevo;
            }
            this.tail.setSiguiente(nuevo);
            this.tail = nuevo;
            this.largo++;
        }
    }

    public NodoVentanilla buscarDisponible(){
        NodoVentanilla aux = this.head;
        while(aux != null){
            if(aux.disponible()){
                return(aux);
            }
            aux = aux.getSiguiente();
        }
        return null;
    } 

    public boolean atendiendo(){
        NodoVentanilla aux = this.head;
        while(aux != null){
            if(!aux.disponible()){
                return(true);
            }
            aux = aux.getSiguiente();
        }
        return false;
    }

    public NodoVentanilla getHead(){
        return this.head;
    }

    public String graficar(){
        StringBuilder dot = new StringBuilder();
        dot.append("subgraph cluster_1{\n");

        StringBuilder nombresNodos = new StringBuilder();
        StringBuilder conexiones = new StringBuilder();
        StringBuilder ranks = new StringBuilder();

        dot.append("fontname = \"Bitstream Vera Sans\"\n");
        dot.append("fontsize = 8\n");
        dot.append("node [fontname = \"Bitstream Vera Sans\"fontsize = 8shape = \"record\"]\n");

        ranks.append("{rank=\"same\"; ");

        NodoVentanilla aux = this.head;
        while(aux != null){
            // nombresNodos.append("Nodo" + aux.hashCode() + "[label=\"" + aux.getValor().getNombre() +"\"];\n");
            String groupName = "g" + aux.getId();

            Cliente actual = aux.getClienteActual();

            int nodoVentanilla = aux.hashCode();
            nombresNodos.append("Nodo" + nodoVentanilla + "[label= \"Ventanilla " + aux.getId() +"\"; shape=Msquare];\n");
            nombresNodos.append("Nodo" + nodoVentanilla + "[group=" + groupName +"]");

            int nodoCliente = actual.hashCode();
            String label = String.format("\"{Cliente %d | Nombre: %s\\l| Imagenes a Color: %s\\l Imagenes en blanco y negro: %s\\l}\"", 
            actual.getId(),actual.getNombre(),actual.getImg_color(), actual.getImg_bw());
            nombresNodos.append("Nodo" + nodoCliente + "[label=" + label +"];\n");
            nombresNodos.append("Nodo" + nodoCliente + "[group=" + groupName +"]");
            

            nombresNodos.append(String.format("Nodo%d -> Nodo%d;\n", nodoCliente, nodoVentanilla));

            NodoImagen imagen = aux.getImagenes().peek();
            
            if(imagen != null){
                conexiones.append(String.format("Nodo%d -> Nodo%d;\n", nodoVentanilla, + imagen.hashCode()));
                int i = 1;
                while(imagen != null){
                    nombresNodos.append("Nodo" + imagen.hashCode() + "[label= \"Imagen " + i +"\"];\n");
                    nombresNodos.append("Nodo" + imagen.hashCode() + "[group=" + groupName +"]");
                    i++;
                    
                    if(imagen.getSiguiente() != null){
                        conexiones.append(String.format("Nodo%d -> Nodo%d;\n", imagen.hashCode(), + imagen.getSiguiente().hashCode()));
                    }
                        
                    imagen = imagen.getSiguiente();
                }
            }

            
            if(aux.getSiguiente() != null){
                conexiones.append(String.format("Nodo%d -> Nodo%d;\n", nodoVentanilla, aux.getSiguiente().hashCode()));
                ranks.append("Nodo" + nodoVentanilla + ",");
            }else{
                ranks.append("Nodo" + nodoVentanilla + "}");
            }
            aux = aux.getSiguiente();
        }

        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append(ranks);
        dot.append("label = \"Lista de Ventanillas\";");
        dot.append("rankdir=TB;\n");
        dot.append("}\n");

        return dot.toString();
    }

    public String buidNodo(Cliente actual){
        String nombre = actual.getNombre();
        String id = Integer.toString(actual.getId());
        String img_color = Integer.toString(actual.getImg_color());
        String img_bw = Integer.toString(actual.getImg_bw());
        String tiempo = Integer.toString(actual.getTiempoEspera());
        String label = String.format("\"{Cliente %s | Nombre: %s\\l| Imagenes a Color: %s\\l Imagenes en blanco y negro: %s\\l| Tiempo Espera: %s\\l}\"", 
                                        id,nombre,img_color,img_bw,tiempo);

        return label;
    }


}
