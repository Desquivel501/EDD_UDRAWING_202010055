package ListaAlbum;

import java.io.FileWriter;
import java.io.PrintWriter;

import Models.Album;
import Models.Imagen;

public class ListaAlbum{
    private NodoD head;
    private int largo;
    private NodoD tail;


    public ListaAlbum() {
        this.head = null;
        this.largo = 0;
        this.tail = null;
    }


    public boolean vacia(){
        return this.head == null;
    }

    public int getLargo() {
        return this.largo;
    }


    public void insertar(Album valorNuevo){
        NodoD nuevo = new NodoD(valorNuevo);
        if(head == null){
            head = nuevo;
            tail = nuevo;
            return;
        }
        tail.setSiguiente(nuevo);
        tail = nuevo;
        largo++;
    }


    public NodoD buscar(int indice){
        NodoD aux = this.head;
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
        StringBuilder rank = new StringBuilder();
        rank.append("{rank=same;");
        dot.append("digraph G{\n");
        dot.append(String.format("label=\"%s\"\n", "Lista Albumes"));
        dot.append("labelloc = \"t\"\n");
        NodoD aux = head;
        int anterior = aux.hashCode();
        while(aux != null){
            dot.append(String.format("Nodo%d[label=\"%s\" shape=tab];\n",aux.hashCode(), aux.getAlbum().getNombre() ));
            dot = aux.getAlbum().graficar(aux.hashCode(), dot);
            if(anterior != aux.hashCode()){
                dot.append(String.format("Nodo%d -> Nodo%d;\n", anterior, aux.hashCode()));
                anterior = aux.hashCode();
            }
            rank.append("Nodo" + aux.hashCode() +";");
            aux = aux.getSiguiente();
        }
        rank.append("}\n");
        dot.append(rank);
        dot.append("}\n");

        int num = this.hashCode();

        try{
            FileWriter fileWriter = new FileWriter("imagenes/albumes" + num + ".dot");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(dot.toString());
            printWriter.close();

            String[] command = {"dot", "-Tpng" ,"imagenes/albumes" + num + ".dot", "-o","imagenes/albumes" + num + ".png" };
            new ProcessBuilder(command).start();
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
        return "imagenes/albumes" + num + ".png";
    }

}
