package listas2;

import Models.Imagen;
import Nodos.NodoImagen;

public class ColaImpresion {
    private NodoImagen head;
    private NodoImagen tail;
    private int largo;

    public ColaImpresion() {
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

    public void enqueue(Imagen valorNuevo){
        NodoImagen nuevo = new NodoImagen(valorNuevo);
        if (this.head == null){
            this.head = nuevo;
            this.tail = nuevo;
            largo++;
            return;
        }
        this.tail.setSiguiente(nuevo);
        this.tail = nuevo;
        largo++;
    }

    public NodoImagen dequeue(){
        if(this.head != null){
            NodoImagen aux = this.head;
            this.head = this.head.getSiguiente();
            largo--;
            return aux;
        }
        else{
            return null;
        }
    }

    public NodoImagen peek(){
        return this.head;
    }

    public void recibirImagenes(PilaImagenes pila){
        while(!pila.vacia()){
            enqueue(pila.remover().getValor());
        }
    }

    public NodoImagen getHead(){
        return this.head;
    }

    public String graficar(String nombre, int id){
        StringBuilder dot = new StringBuilder();
        dot.append("subgraph cluster_3{\n");

        StringBuilder nombresNodos = new StringBuilder();
        StringBuilder conexiones = new StringBuilder();

        dot.append("fontname = \"Bitstream Vera Sans\"\n");
        dot.append("fontsize = 8\n");
        dot.append("node [fontname = \"Bitstream Vera Sans\"fontsize = 8 shape = \"square\"]\n");

        int nodoImpresora = this.hashCode();

        nombresNodos.append("Nodo" + nodoImpresora + "[label= \"" + nombre +"\"; shape=Msquare];\n");
        nombresNodos.append("Nodo" + nodoImpresora + "[group=" + id +"]\n");

        NodoImagen imagen = this.head;
            
        if(imagen != null){
            conexiones.append(String.format("Nodo%d -> Nodo%d;\n", nodoImpresora, + imagen.hashCode()));

            while(imagen != null){
                String tipo = imagen.getValor().getTipo();
                if(tipo == "bw"){
                    tipo="en Blanco y Negro";
                }else{
                    tipo="a Color";
                }

                nombresNodos.append("Nodo" + imagen.hashCode() + "[label= \"Imagen " + tipo +"\"];\n");
                nombresNodos.append("Nodo" + imagen.hashCode() + "[group=" + id +"]\n");
                    
                if(imagen.getSiguiente() != null){
                        conexiones.append(String.format("Nodo%d -> Nodo%d;\n", imagen.hashCode(), + imagen.getSiguiente().hashCode()));
                }    
                imagen = imagen.getSiguiente();
            }
        }
        
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("label = \"Cola de Impresion\";\n");
        dot.append("rankdir=TB;\n");
        dot.append("}\n");

        return dot.toString();
    }


}
