package listas2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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

    public void ordenarTiempo(){
        NodoCliente aux = this.head;
        for(int i=0; i < largo -1; i++){
            NodoCliente maximo = aux;
            NodoCliente punt = aux;
            while(punt != null){
                if(punt.getValor().getTiempo() > maximo.getValor().getTiempo()){
                    maximo = punt;
                } 
                punt = punt.getSiguiente();
            }
            Cliente temp = aux.getValor();
            aux.setValor(maximo.getValor());
            maximo.setValor(temp);
            aux = aux.getSiguiente();
        }
    }

    public void ordenarImagen(String tipo){
        NodoCliente aux = this.head;
        for(int i=0; i < largo -1; i++){
            NodoCliente maximo = aux;
            NodoCliente punt = aux;
            while(punt != null){
                int numMaximo = 0;
                int numPunt = 0;
                switch(tipo){
                    case "c":
                        numMaximo = maximo.getValor().getImg_color();
                        numPunt = punt.getValor().getImg_color();
                        break;
                    case "bw":
                    numMaximo = maximo.getValor().getImg_bw();
                    numPunt = punt.getValor().getImg_bw();
                    break;
                }
                if(numPunt > numMaximo){
                    maximo = punt;
                } 
                punt = punt.getSiguiente();
            }
            Cliente temp = aux.getValor();
            aux.setValor(maximo.getValor());
            maximo.setValor(temp);
            aux = aux.getSiguiente();
        }
    }

    public void graficar(String nombre, String archivo, String tipo){
        StringBuilder dot = new StringBuilder();

        StringBuilder nombresNodos = new StringBuilder();
        StringBuilder conexiones = new StringBuilder();

        dot.append("digraph G{\n");
        dot.append("fontname = \"Bitstream Vera Sans\"\n");
        dot.append("fontsize = 8\n");
        dot.append("node [fontname = \"Bitstream Vera Sans\"fontsize = 8shape = \"record\"]\n");

        NodoCliente aux = this.head;
        System.out.println(aux.getValor().getNombre());
        for(int i = 0; i < 5;i++){
            if(aux == null){break;}
            Cliente actual = aux.getValor();

            if(tipo == "t"){
                if(!actual.isTerminado()){
                    System.out.println("OUT");
                    continue;
                }
            }

            nombresNodos.append("Nodo" + aux.hashCode() + "[label=" + buidNodo(actual, i+1,tipo) +"];\n");
            if(aux.getSiguiente() != null && i < 4){
                conexiones.append(String.format("Nodo%d -> Nodo%d;\n", aux.hashCode(), aux.getSiguiente().hashCode()));
            }
            aux = aux.getSiguiente();
            System.out.println("here");
        }
        
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("label = \""+nombre+"\";");
        dot.append("rankdir=TB;\n");
        dot.append("}");
        try {
            generarArchivo(dot.toString(), archivo);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String buidNodo(Cliente actual, int rank, String tipo){
        String nombre = actual.getNombre();
        String id = Integer.toString(actual.getId());
        int img_color = actual.getImg_color();
        int img_bw = actual.getImg_bw();
        int tiempo = actual.getTiempoSalida() - actual.getTiempoEntrada();

        switch(tipo){
            case "t":
                String labelT = String.format("\"{#%d | Nombre: %s\\l| Tiempo en el Sistema: %d\\l}\"", 
                                    rank,nombre,tiempo);
                return labelT;
            case "c":
                String labelC = String.format("\"{#%d | Nombre: %s\\l| Imagenes a Color: %d\\l}\"", 
                                    rank,nombre,img_color);
                return labelC;
            case "bw":
                String labelBW = String.format("\"{#%d | Nombre: %s\\l| Imagenes en Blanco y Negro: %d\\l}\"", 
                                    rank,nombre,img_bw);
                return labelBW;
        }
        return null;  
    }

    public void generarArchivo(String dot, String nombre) throws IOException {
        FileWriter fileWriter = new FileWriter(nombre + ".dot");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(dot);
        printWriter.close();

        String[] command = {"dot", "-Tpng", nombre + ".dot", "-o", nombre + ".png" };
        new ProcessBuilder(command).start();

    }

}
