package listas2;

import Models.Cliente;
import Nodos.NodoCliente;

public class ListaTops{

    private NodoCliente head;
    private int largo;
    private NodoCliente tail;

    public ListaTops() {
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

    // public void ordenTiempo(Cliente nuevo){
    //     NodoCliente nodoNuevo = new NodoCliente(nuevo);
    //     int tiempoNuevo = nuevo.getTiempo();
    //     int tiempoHead = 0;

    //     if(this.head == null){
    //         this.head = nodoNuevo;
    //         this.tail = nodoNuevo;
    //         this.largo++;
    //         return;
    //     }else{
    //         tiempoHead = this.head.getValor().getTiempo();
    //     }
    //     NodoCliente pre = this.head;

    //     if(pre.getSiguiente() == null){
        
    //         if(tiempoHead < tiempoNuevo){
    //             nodoNuevo.setSiguiente(this.head);
    //             this.head = nodoNuevo;
    //             largo++;
    //             return;
    //         }else{
    //             this.tail.setSiguiente(nodoNuevo);
    //             this.tail = nodoNuevo;
    //             this.largo++;
    //             return;
    //         }
    //     }
    //     int i = 0;
    //     while(pre.getSiguiente() != null){
    //         int tiempoPre = pre.getValor().getTiempo();
    //         int tiempoSig = pre.getSiguiente().getValor().getTiempo();

    //         if(tiempoHead < tiempoNuevo){
    //             nodoNuevo.setSiguiente(this.head);
    //             this.head = nodoNuevo;
    //             largo++;
    //             return;
    //         }
    //         else if(tiempoPre > tiempoNuevo && tiempoSig < tiempoNuevo){
    //             nodoNuevo.setSiguiente(pre.getSiguiente());
    //             pre.setSiguiente(nodoNuevo);
    //             largo++;
    //             return;
    //         }
    //         pre = pre.getSiguiente();
    //         System.out.println("Here");
    //         i++;
    //         if (i > largo){break;}
    //     }
    //     this.tail.setSiguiente(nodoNuevo);
    //     this.tail = nodoNuevo;
    //     this.largo++;
    // }



    public void remover(int indice){
        NodoCliente pre = this.head;
        int k = 0;

        while (k < indice-1){
            pre = pre.getSiguiente();
            k++;
            if (pre==null){
                System.out.print("Error: Indice no encontrado");
                return;
            }
        }
        NodoCliente borrar = pre.getSiguiente();
        NodoCliente aft = borrar.getSiguiente();
        pre.setSiguiente(aft); 
        this.largo--;
    }

    public String graficar(){
        StringBuilder dot = new StringBuilder();

        StringBuilder nombresNodos = new StringBuilder();
        StringBuilder conexiones = new StringBuilder();

        dot.append("fontname = \"Bitstream Vera Sans\"\n");
        dot.append("fontsize = 8\n");
        dot.append("node [fontname = \"Bitstream Vera Sans\"fontsize = 8shape = \"record\"]\n");

        NodoCliente aux = this.head;
        int i = 0;

        while(aux != null){
            if(i > 5){break;}
            Cliente actual = aux.getValor();
            
            nombresNodos.append("Nodo" + aux.hashCode() + "[label=" + buidNodo(actual, i+1) +"];\n");
            if(aux.getSiguiente() != null){
                conexiones.append(String.format("Nodo%d -> Nodo%d;\n", aux.hashCode(), aux.getSiguiente().hashCode()));
            }
            aux = aux.getSiguiente();
            i++;
        }
        
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("label = \"Listado Atendidos\";");
        dot.append("rankdir=TB;\n");

        return dot.toString();
    }

    public String buidNodo(Cliente actual, int rank){
        String nombre = actual.getNombre();
        String id = Integer.toString(actual.getId());
        String img_color = Integer.toString(actual.getImg_color());
        String img_bw = Integer.toString(actual.getImg_bw());
        int tiempo = actual.getTiempoSalida() - actual.getTiempoEntrada();
        String label = String.format("\"{#%d | Nombre: %s\\l| Imagenes a Color: %s\\l Imagenes en blanco y negro: %s\\l| Tiempo en el Sistema: %d\\l}\"", 
                                    rank,id,nombre,img_color,img_bw,tiempo);

        return label;
    }
}
