package Matriz;

import java.io.FileWriter;
import java.io.PrintWriter;

public class MatrizCapa implements Comparable<MatrizCapa>{
    ListaEncabezado listaFilas = new ListaEncabezado();
    ListaEncabezado listaColumnas = new ListaEncabezado();
    String nombre;
    int id;
    int filas = 0;
    int columnas = 0;
    int noCeldas = 0;


    public MatrizCapa(String nombre) {
        this.nombre = nombre;
    }

    public ListaEncabezado getFilas(){
        return listaFilas;
    }

    public ListaEncabezado getColumnas(){
        return listaColumnas;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public int getNoFilas(){
        return filas;
    }

    public int getNoColumnas(){
        return columnas;
    }


    public void insertar(int columna, int fila, String valor){
        NodoM nuevo = new NodoM(columna, fila, valor);
        this.filas = (this.filas < fila)?fila:this.filas;
        this.columnas = (this.columnas < columna)?columna:this.columnas;
 
        NodoE eFila = listaFilas.getEncabezado(fila);
        if(eFila == null){
            eFila = new NodoE(fila);
            eFila.setAccesoNodo(nuevo);
            listaFilas.setEncabezado(eFila);
            noCeldas++;
        }else{
            if(nuevo.getColumna() < eFila.getAccesoNodo().getColumna()){
                nuevo.setDerecha(eFila.getAccesoNodo());
                eFila.getAccesoNodo().setIzquierda(nuevo);
                eFila.setAccesoNodo(nuevo);
                noCeldas++;
            }else{
                NodoM actual = eFila.getAccesoNodo();
                while(actual.getDerecha() != null){
                    if(nuevo.getColumna() < actual.getDerecha().getColumna()){
                        nuevo.setDerecha(actual.getDerecha());
                        actual.getDerecha().setIzquierda(nuevo);
                        nuevo.setIzquierda(actual);
                        actual.setDerecha(nuevo);
                        noCeldas++;
                        return;
                    }
                    actual = actual.getDerecha();
                }
                if(actual.getDerecha() == null){
                    actual.setDerecha(nuevo);
                    nuevo.setIzquierda(actual);
                    noCeldas++;
                }
            }
        }

        NodoE eColumna = listaColumnas.getEncabezado(columna);
        if(eColumna == null){
            eColumna = new NodoE(columna);
            eColumna.setAccesoNodo(nuevo);
            listaColumnas.setEncabezado(eColumna);
        }else{
            if(nuevo.getFila() < eColumna.getAccesoNodo().getFila()){
                nuevo.setAbajo(eColumna.getAccesoNodo());
                eColumna.getAccesoNodo().setArriba(nuevo);
                eColumna.setAccesoNodo(nuevo);
            }else{
                NodoM actual = eColumna.getAccesoNodo();
                while(actual.getAbajo() != null){
                    if(nuevo.getFila() < actual.getAbajo().getFila()){
                        nuevo.setAbajo(actual.getAbajo());
                        actual.getAbajo().setArriba(nuevo);
                        nuevo.setArriba(actual);
                        actual.setAbajo(nuevo);
                        break;
                    }
                    actual = actual.getAbajo();
                }
                if(actual.getAbajo() == null){
                    actual.setAbajo(nuevo);
                    nuevo.setArriba(actual);
                }
            }
        }
    }

    public void printFilas(){
        var eFila = listaFilas.getPrimero();
        System.out.println("-----------FILAS-----------");
        while(eFila != null){
            var actual = eFila.getAccesoNodo();
            System.out.println("Fila: " + actual.getFila());
            System.out.println("Columna         Valor");
            
            while(actual != null){
                System.out.println(actual.getColumna() + "          " + actual.getValor());
                actual = actual.getDerecha();
            }
            eFila = eFila.getSiguiente();
        }
        System.out.println("------------FIN------------");
    }

    public void printColumnas(){
        var eColumna = listaFilas.getPrimero();
        System.out.println("----------COLUMNAS----------");
        while(eColumna != null){
            var actual = eColumna.getAccesoNodo();
            System.out.println("Columna: " + actual.getColumna());
            System.out.println("Fila         Valor");
            
            while(actual != null){
                System.out.println(actual.getColumna() + "          " + actual.getValor());
                actual = actual.getAbajo();
            }
            eColumna = eColumna.getSiguiente();
        }
        System.out.println("-------------FIN------------");
    }


    public String graficar(){
        StringBuilder dot = new StringBuilder();
        StringBuilder conexion = new StringBuilder();
        dot.append("digraph L{\n");
        dot.append("node[shape=square style=filled]\n");
        // dot.append("node[style=filled]\n");

        int contGrupo = 0;

        var eFila = listaFilas.getPrimero();
        while(eFila != null){

            StringBuilder rank = new StringBuilder();
            dot.append(String.format("nodo%d[label=\"Fila #%d\", group=%d]\n",eFila.hashCode(), eFila.getId(), contGrupo));
            rank.append("{rank=same; " + String.format("nodo%d",eFila.hashCode()) +"; ");

            var actual = eFila.getAccesoNodo();
            conexion.append(String.format("nodo%d -> nodo%d\n",eFila.hashCode(), actual.hashCode()));
            conexion.append(String.format("nodo%d -> nodo%d [dir = \"back\"]\n",eFila.hashCode(), actual.hashCode()));

            while(actual != null){
                dot.append(String.format("nodo%d[fillcolor=\"%s\", group=%d, label=\"\"]\n",actual.hashCode(), actual.getValor(), actual.getColumna()+1));
                rank.append("nodo" + actual.hashCode()+ "; ");

                if(actual.getDerecha() != null){
                    conexion.append(String.format("nodo%d -> nodo%d \n",actual.hashCode(), actual.getDerecha().hashCode()));
                    conexion.append(String.format("nodo%d -> nodo%d [dir = \"back\"]\n",actual.hashCode(), actual.getDerecha().hashCode()));
                }
                actual = actual.getDerecha();
            }
           
            if(eFila.getSiguiente() != null) {
                conexion.append(String.format("nodo%d -> nodo%d \n",eFila.hashCode(), eFila.getSiguiente().hashCode()));
            }
            eFila = eFila.getSiguiente();
            rank.append("}\n");
            dot.append(rank);
        }

        var eColumna = listaColumnas.getPrimero();
        StringBuilder rankC = new StringBuilder();
        rankC.append("{rank=same; ");

        while(eColumna != null){
            dot.append(String.format("nodo%d[label=\"Columna #%d\", group=%d]\n",eColumna.hashCode(), eColumna.getId(), eColumna.getId()+1));
            rankC.append(String.format("nodo%d; ",eColumna.hashCode()));

            var actual = eColumna.getAccesoNodo();
            conexion.append(String.format("nodo%d -> nodo%d\n",eColumna.hashCode(), actual.hashCode()));
            conexion.append(String.format("nodo%d -> nodo%d [dir = \"back\"]\n",eColumna.hashCode(), actual.hashCode()));

            while(actual != null){
                if(actual.getAbajo() != null){
                    conexion.append(String.format("nodo%d -> nodo%d \n",actual.hashCode(), actual.getAbajo().hashCode()));
                    conexion.append(String.format("nodo%d -> nodo%d [dir = \"back\"]\n",actual.hashCode(), actual.getAbajo().hashCode()));
                }
                actual = actual.getAbajo();

            }
            if(eColumna.getSiguiente() != null){
                conexion.append(String.format("nodo%d -> nodo%d \n",eColumna.hashCode(), eColumna.getSiguiente().hashCode()));
            }
            eColumna = eColumna.getSiguiente();
        }

        rankC.append("}\n");
        dot.append(rankC);
        dot.append(conexion);
        dot.append("}");

        int num = this.hashCode();

        try{

            FileWriter fileWriter = new FileWriter( "imagenes/capa"+ num + ".dot");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(dot);
            printWriter.close();

            if(noCeldas > 5000){
                String[] command = {"dot","-Gnslimit=2", "-Tpng" , "imagenes/capa"+ num + ".dot", "-o", "imagenes/capa"+ num + ".png" };
                new ProcessBuilder(command).start();
            }else{
                String[] command = {"dot","-Tpng" , "imagenes/capa"+ num + ".dot", "-o", "imagenes/capa"+ num + ".png" };
                new ProcessBuilder(command).start();
            }
            
            
        
        }catch(Exception e){
            e.printStackTrace();
            return "";
        }
        return "imagenes/capa"+ num + ".png";
    }

    public String graficarHTML(){
        StringBuilder dot = new StringBuilder();
        dot.append("digraph G {\n");
        // dot.append(String.format("label=\"%s\"\n", "Capa " + this.nombre));
        // dot.append("labelloc = \"t\"\n");

        dot.append("node[shape = plaintext]\n");

        dot.append("a[ label =<\n");

        dot.append("<TABLE cellspacing=\"0\" cellpadding=\"10\">\n");

        var listaFila = this.listaFilas;
        var actual = listaFila.getPrimero();

        while(actual != null){
            dot.append("<TR>\n");
            var nodo = actual.getAccesoNodo();
            for(int i = 0; i < this.columnas+1;i++){
                if(nodo == null){
                    // dot.append("<TD style=\"width: 10px; height: 10px; background-color: white;\"></TD>\n");
                    dot.append("<TD width=\"1\" height=\"1\" bgcolor=\"white\"></TD>\n");
                    continue;
                }
                if(nodo.getColumna() == i){
                    // dot.append("<TD style=\"width: 10px; height: 10px; color: " + nodo.getValor() +" background-color: " + nodo.getValor() +";\"></TD>\n");
                    dot.append("<TD width=\"1\" height=\"1\" bgcolor=\"" + nodo.getValor()  + "\"></TD>\n");
                    // System.out.println("Nodo: " + nodo.getColumna() + "," + nodo.getFila() + "," + nodo.getValor());
                    nodo = nodo.getDerecha();      
                }else{
                    // dot.append("<TD style=\"width: 10px; height: 10px; background-color: white;\"></TD>\n");
                    dot.append("<TD width=\"1\" height=\"1\" bgcolor=\"white\"></TD>\n");
                }
                
            }
            dot.append("</TR>\n");
            actual = actual.getSiguiente();
        }
        
        dot.append("</TABLE>\n");

        dot.append(">]\n");

        dot.append("}\n");

        int int_random = this.hashCode();

        try{
            FileWriter fileWriter = new FileWriter("imagenes/Imagen" + int_random + ".dot");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(dot);
            printWriter.close();

            String[] command = {"dot", "-Tpng" ,"imagenes/Imagen" + int_random + ".dot", "-o", "imagenes/Imagen" + int_random + "HTML.png" };
            new ProcessBuilder(command).start();

        }catch(Exception e){
            e.printStackTrace();
            return "";
        }
        return "imagenes/Imagen" + int_random + "HTML.png";

    }


    private void combinarNodo(int columna, int fila, String valor){
        NodoE eFila = listaFilas.getEncabezado(fila);
        if(eFila == null){
            insertar(columna, fila, valor);
        }else{
            NodoM actual = eFila.getAccesoNodo();
            if(actual == null){
                insertar(columna, fila, valor);
            }
            boolean found = false;
            while(actual != null){
                if(columna == actual.getColumna()){
                    actual.setValor(valor);
                    found = true;
                    break;
                }
                actual = actual.getDerecha();
            }
            if(!found){
                insertar(columna, fila, valor);
            }
        }
    }

    public void combinarMatriz(MatrizCapa actual){

        ListaEncabezado  eFila = actual.getFilas();
        var nodoEncabezado = eFila.getPrimero();

        while(nodoEncabezado != null){
            var nodoActual = nodoEncabezado.getAccesoNodo();

            while(nodoActual != null){
                combinarNodo(nodoActual.getColumna(), nodoActual.getFila(), nodoActual.getValor());
                nodoActual = nodoActual.getDerecha();
            }
            nodoEncabezado = nodoEncabezado.getSiguiente();
        }
    }

    @Override
    public String toString(){
        return "[" + this.nombre  + " - " + noCeldas + "]";
    }

    @Override
    public int compareTo(MatrizCapa capa) {
        return this.noCeldas - capa.noCeldas;
    }

}
