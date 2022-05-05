package Program;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;

import ArbolB.ArbolB;
import Blockchain.Bloque;
import Grafo.Grafo;
import Lector.Lector;
import Lista.*;
import Merkle.ArbolMerkle;
import Models.*;
import Models.Mensajero;
import TablaHash.TablaHash;

public class Program {
    public static Program program;
    public static Cliente loggedUser;
    public static ArbolB arbolClientes = new ArbolB();
    public static boolean hayUsuario = false;
    public static TablaHash tablaMensajeros  = new TablaHash();
    public static Grafo grafoLugares = new Grafo();
    public static Lista<Entrega> lista_entregas = new Lista<Entrega>();
    public static Lista<Entrega> lista_entregas_completa = new Lista<Entrega>();
    public static Lista<Bloque> lista_bloques = new Lista<Bloque>();
    public static ArbolMerkle arbolMerkle = new ArbolMerkle();
    public static int INDEX = 0;
    public static String PREVIOUSHASH = "0000";
    public static int noCero = 4;
    public static int tiempo = 10000000;
    private static Thread timer;
    private static boolean running = true;

    public Program(){
    }

    public static Program getInstancia() {
        if (program == null) {
            program = new Program();
            arbolClientes = new ArbolB();
            tablaMensajeros = new TablaHash();
            hayUsuario = false;
            loggedUser = null;
        }
        return program;
    }

    public static String crearArbol(){
        arbolMerkle.generarArbol(lista_entregas);
        String nombre = arbolMerkle.graficar();
        serializar();
        return nombre;
    }

    public static void crearBloque(){
        System.out.println("here");
        lista_entregas.imprimir();

        if(lista_entregas.vacia()){
            System.out.println("vacio");
            Bloque nuevoBloque = new Bloque(lista_entregas,"fab4c10f3aba981b80513696a28c904ba88ebdd658acdaf2a8bb34145b85a8c4");
            nuevoBloque.save();
            lista_bloques.insertar(nuevoBloque);

            lista_entregas = new Lista<Entrega>();
            arbolMerkle = new ArbolMerkle();
            serializar();
        }else{
            arbolMerkle.generarArbol(lista_entregas);
            // arbolMerkle.graficar();
            var raiz = arbolMerkle.raiz.getHash();
            System.out.println("i -> " + arbolMerkle.raiz.getHash());

            Bloque nuevoBloque = new Bloque(lista_entregas,raiz);
            nuevoBloque.save();
            lista_bloques.insertar(nuevoBloque);

            lista_entregas = new Lista<Entrega>();
            arbolMerkle = new ArbolMerkle();
            serializar();
        }

    }

    public static String graficarBloques(){
        StringBuilder dot = new StringBuilder();
        dot.append("digraph G{\n");
        dot.append(String.format("label=\"%s\"\n", "Tabla Hash"));
        dot.append("labelloc = \"t\"\n");
        dot.append("node [shape=record]\n");

        var bloque = lista_bloques.getHead();
        while(bloque != null){
            dot = bloque.getValor().graficar(dot);
            bloque = bloque.getSiguiente();
        }
        dot.append("}");

        try{
            FileWriter fileWriter = new FileWriter("imagenes/Bloques.dot");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(dot.toString());
            printWriter.close();   

            String[] command = {"dot", "-Tpng" ,"imagenes/Bloques.dot", "-o","imagenes/Bloques.png" };
            new ProcessBuilder(command).start();
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
        return "imagenes/Bloques.png";
    }

    public static void start(){
        timer = new Thread(new Runnable() {
            @Override
            public void run() {
                while(running){
                    try {
                        Thread.sleep(tiempo);
                        crearBloque();
                    }
                    catch (InterruptedException e) {
                        running = false;
                        serializar();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        timer.start();
    }

    public static void stop(){
        timer.interrupt();
    }

    public static String reporteClientes(){
        Lista<nodoReporte> lista = new Lista<nodoReporte>();
        var nodo = lista_entregas_completa.getHead();
        while(nodo != null){
            lista = insertar(lista, nodo.getValor().getCliente());
            nodo = nodo.getSiguiente();
        }
        lista.ordenar();

        StringBuilder dot = new StringBuilder();
        dot.append("digraph G{\n");
        dot.append(String.format("label=\"%s\"\n", "Clientes con mayor cantidad de solicitudes de entrega"));
        dot.append("labelloc = \"t\"\n");
        dot.append("node [shape=record]\n"); 

        dot.append("a[ label =<\n");
        dot.append("<TABLE style=\"border-collapse: collapse; width: 100%; height: 126px;\" border=\"1\">\n");
        dot.append("<TR>\n");
        dot.append("<TD style=\"text-align: center;\" bgcolor=\"#6082b6\">Nombre</TD>\n");
        dot.append("<TD style=\"text-align: center;\" bgcolor=\"#6082b6\">No. Pedidos</TD>\n");
        dot.append("</TR>\n");

        
        var aux = lista.getHead();
        int i= 0;
        while(aux != null && i < 10){
            dot.append("<TR>");
            dot.append("<TD style=\"text-align: center;\">" + aux.getValor().nombre +"</TD>\n");
            dot.append("<TD style=\"text-align: center;\">" + aux.getValor().numero +"</TD>\n");
            dot.append("</TR>\n");
            aux = aux.getSiguiente();
            i++;
        }
        dot.append("</TABLE>\n");
        dot.append(">]\n");
        dot.append("}");

        String nombre = "TopCliente" + dot.hashCode();

        try{
            FileWriter fileWriter = new FileWriter("imagenes/"+nombre+".dot");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(dot.toString());
            printWriter.close();   

            String[] command = {"dot", "-Tpng" ,"imagenes/"+nombre+".dot", "-o","imagenes/"+nombre+".png" };
            new ProcessBuilder(command).start();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return "imagenes/"+nombre+".png";
        
    }

    public static String reporteMensajero(){
        Lista<nodoReporte> lista = new Lista<nodoReporte>();
        var nodo = lista_entregas_completa.getHead();
        while(nodo != null){
            lista = insertar(lista, nodo.getValor().getMensajero());
            nodo = nodo.getSiguiente();
        }
        lista.ordenar();

        StringBuilder dot = new StringBuilder();
        dot.append("digraph G{\n");
        dot.append(String.format("label=\"%s\"\n", "Mensajeros con la mayor cantidad de entregas"));
        dot.append("labelloc = \"t\"\n");
        dot.append("node [shape=record]\n"); 

        dot.append("a[ label =<\n");
        dot.append("<TABLE style=\"border-collapse: collapse; width: 100%; height: 126px;\" border=\"1\">\n");
        dot.append("<TR>\n");
        dot.append("<TD style=\"text-align: center;\" bgcolor=\"#6082b6\">Nombre</TD>\n");
        dot.append("<TD style=\"text-align: center;\" bgcolor=\"#6082b6\">No. Entregas</TD>\n");
        dot.append("</TR>\n");

        
        var aux = lista.getHead();
        int i= 0;
        while(aux != null && i < 10){
            dot.append("<TR>\n");
            dot.append("<TD style=\"text-align: center;\">" + aux.getValor().nombre +"</TD>\n");
            dot.append("<TD style=\"text-align: center;\">" + aux.getValor().numero +"</TD>\n");
            dot.append("</TR>\n");
            aux = aux.getSiguiente();
            i++;
        }
        dot.append("</TABLE>\n");
        dot.append(">]\n");
        dot.append("}");

        String nombre = "TopMensajero" + dot.hashCode();

        try{
            FileWriter fileWriter = new FileWriter("imagenes/"+nombre+".dot");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(dot.toString());
            printWriter.close();   

            String[] command = {"dot", "-Tpng" ,"imagenes/"+nombre+".dot", "-o","imagenes/"+nombre+".png" };
            new ProcessBuilder(command).start();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return "imagenes/"+nombre+".png";
    }

    public static Lista<nodoReporte> insertar(Lista<nodoReporte> lista, String nombre){
        var aux = lista.getHead();
        while(aux != null){
            if(aux.getValor().getNombre().equals(nombre)){
                aux.getValor().numero++;
                return lista;
            }
            aux = aux.getSiguiente();
        }
        lista.insertar(new nodoReporte(nombre,1));
        return lista;
    }

    public static String topViajes(){
        lista_entregas_completa.ordenar();
        StringBuilder dot = new StringBuilder();
        dot.append("digraph G{\n");
        dot.append(String.format("label=\"%s\"\n", "Entregas con mas distancia"));
        dot.append("labelloc = \"t\"\n");
        dot.append("node [shape=record]\n"); 

        dot.append("a[ label =<\n");
        dot.append("<TABLE style=\"border-collapse: collapse; width: 100%; height: 126px;\" border=\"1\">\n");
        dot.append("<TR>\n");
        dot.append("<TD style=\"text-align: center;\" bgcolor=\"#6082b6\">Viaje</TD>\n");
        dot.append("<TD style=\"text-align: center;\" bgcolor=\"#6082b6\">Duracion</TD>\n");
        dot.append("</TR>\n");

        var aux = lista_entregas_completa.getHead();
        int i= 0;
        while(aux != null && i < 10){
            dot.append("<TR>\n");
            dot.append("<TD style=\"text-align: center;\">" + aux.getValor().getSede() + " a " + aux.getValor().getDestino() + "</TD>\n");
            dot.append("<TD style=\"text-align: center;\">" + aux.getValor().getDistancia() +" minutos</TD>\n");
            dot.append("</TR>\n");
            aux = aux.getSiguiente();
            i++;
        }
        dot.append("</TABLE>\n");
        dot.append(">]\n");
        dot.append("}");

        String nombre = "TopViajes" + dot.hashCode();

        try{
            FileWriter fileWriter = new FileWriter("imagenes/"+nombre+".dot");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(dot.toString());
            printWriter.close();   

            String[] command = {"dot", "-Tpng" ,"imagenes/"+nombre+".dot", "-o","imagenes/"+nombre+".png" };
            new ProcessBuilder(command).start();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return "imagenes/"+nombre+".png";
        
    }

    public static String simularRuta(int inicio, int destino){
        Lista_recorrido lista = grafoLugares.dijkstra(inicio, destino, false);
        if(lista == null) return null;

        StringBuilder dot = new StringBuilder();
        dot.append("digraph G{\n");
        dot.append("rankdir = \"LR\"\n");
        dot.append("node [shape=record]\n"); 

        var nodo = lista.getHead();
        while(nodo!=null){
            dot.append(String.format("nodo%d[label=\"%d\"]\n",nodo.getId_lugar(),nodo.getId_lugar()));
            if(nodo.getSiguiente() != null){
                dot.append(String.format("nodo%d -> nodo%d\n",nodo.getId_lugar(),nodo.getSiguiente().getId_lugar()));
            }
            nodo = nodo.getSiguiente();
        }  
        dot.append(String.format("label=\"%s\"\n", "Simulacion Ruta: De '" + lista.getHead().getNombre() + "'' a '" + lista.getTail().getNombre() +"'\\nDuracion: " + lista.getTiempo() + " minutos"));
        dot.append("}");

        String nombre = "Ruta" + lista.hashCode();

        try{
            FileWriter fileWriter = new FileWriter("imagenes/"+nombre+".dot");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(dot.toString());
            printWriter.close();   

            String[] command = {"dot", "-Tpng" ,"imagenes/"+nombre+".dot", "-o","imagenes/"+nombre+".png" };
            new ProcessBuilder(command).start();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return "imagenes/"+nombre+".png";
    }

    public static void serializar(){
        try {
            ObjectOutputStream data1 = new ObjectOutputStream(new FileOutputStream("C:\\udrawing\\data\\Entregas.edd"));
            data1.writeObject(Program.lista_entregas);
            data1.close();

            ObjectOutputStream data2 = new ObjectOutputStream(new FileOutputStream("C:\\udrawing\\data\\Entregas_completa.edd"));
            data2.writeObject(Program.lista_entregas_completa);
            data2.close();

        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Serializar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
//            Logger.getLogger(Serializar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deserializar() throws FileNotFoundException, IOException, ClassNotFoundException{
        try
        {
            ObjectInputStream profLoad = new ObjectInputStream(new FileInputStream("C:\\udrawing\\data\\Entregas.edd"));
            Program.lista_entregas = (Lista) profLoad.readObject();
            Program.lista_entregas.imprimir();
            profLoad.close();

            ObjectInputStream profLoad2 = new ObjectInputStream(new FileInputStream("C:\\udrawing\\data\\Entregas_completa.edd"));
            Program.lista_entregas_completa = (Lista) profLoad2.readObject();
            Program.lista_entregas_completa.imprimir();
            profLoad2.close();

        } 
        catch (IOException ioe) 
        {
           ioe.printStackTrace();
        } 
        catch (ClassNotFoundException c) 
        {
//            System.out.println("Class not found");
           c.printStackTrace();
        }
    }
}

class nodoReporte implements Comparable<nodoReporte>{
    String nombre;
    int numero;

    public nodoReporte(String nombre, int numero){
        this.nombre = nombre;
        this.numero = numero;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public int compareTo(nodoReporte o) {
        return this.numero - o.numero;
    }



}
