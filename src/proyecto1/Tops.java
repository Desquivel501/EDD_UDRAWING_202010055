package proyecto1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Models.Cliente;
import Nodos.NodoCliente;
import listas2.ListaTops;
import listas2.ListaUsuarios;

public class Tops {

    private NodoCliente head;

    public Tops() {
    }

    // public void topTiempo(ListaUsuarios listaUsuarios){
    //     ListaTops nuevaLista = new ListaTops();
        
    //     NodoCliente aux = listaUsuarios.getHead();
        
    //     for(int i=0;i<5;i++){
    //         if(aux==null){break;}
    //         if(aux.getValor().isTerminado()){
    //             nuevaLista.ordenTiempo(aux.getValor());
    //         }else{
    //             i--;
    //         }
    //         aux = aux.getSiguiente();
    //     }

    //     StringBuilder dot = new StringBuilder();
    //     dot.append("digraph G{\n");
    //     dot.append(nuevaLista.graficar());
    //     dot.append("}");

    //     try {
    //         generarArchivo(dot.toString(),"TopTiempo");
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    // public void topImgC(ListaUsuarios listaUsuarios){
    //     ListaTops nuevaLista = new ListaTops();
        
    //     NodoCliente aux = listaUsuarios.getHead();
        
    //     for(int i=0;i<5;i++){
    //         if(aux==null){break;}
    //         if(aux.getValor().isTerminado()){
    //             nuevaLista.ordenTiempo(aux.getValor());
    //         }else{
    //             i--;
    //         }
    //         aux = aux.getSiguiente();
    //     }

    //     StringBuilder dot = new StringBuilder();
    //     dot.append("digraph G{\n");
    //     dot.append(nuevaLista.graficar());
    //     dot.append("}");

    //     try {
    //         generarArchivo(dot.toString(),"TopImagenesColor");
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }



    

    public void generarArchivo(String dot, String name) throws IOException {
        String nameDot = name + ".dot";
        String nameSvg = name + ".svg";
        FileWriter fileWriter = new FileWriter(nameDot);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(dot);
        printWriter.close();

        String[] command = {"dot", "-Tsvg", nameDot, "-o", nameSvg };
        new ProcessBuilder(command).start();

    }
    
}
