import java.util.ArrayList;

import ABB.ArbolBinario;
import AVL.AVL;
import GUI.Login;
import Matriz.*;

public class App {
    public static void main(String[] args) throws Exception {

        ArbolBinario arbol = new ArbolBinario();

        Lector lector = new Lector();

        ArbolBinario listaMatriz = lector.leerMatriz(arbol);
        // listaMatriz.inOrder();

        // MatrizCapa completa = new MatrizCapa("Completa");
        // completa = arbol.unirPreOrder(completa);
        // // completa.graficar();
        // completa.graficarHTML();

        // arbol.preOrder();

        AVL arbolImagenes = new AVL();
        arbolImagenes = lector.leerImagenes(arbolImagenes);
        arbolImagenes.insertarCapas(arbol);
        arbolImagenes.preOrder();
        // arbolImagenes.generarImagenes();
        arbolImagenes.eliminar(8);
        System.out.println("-----------------------------------------");
        arbolImagenes.preOrder();
        arbolImagenes.postOrder();
        // Login nuevo = new Login();
    }

    // public static MatrizCapa unirMatriz(ArrayList<MatrizCapa> listaMatriz){
    //     MatrizCapa completa = new MatrizCapa("Completa");

    //     for(int i = 0; i < listaMatriz.size(); i++){
    //         MatrizCapa actual = listaMatriz.get(i);
    //         ListaEncabezado  eFila = actual.getFilas();

    //         var nodoEncabezado = eFila.getPrimero();

    //         while(nodoEncabezado != null){
    //             System.out.println("here1");
    //             var nodoActual = nodoEncabezado.getAccesoNodo();

    //             while(nodoActual != null){
    //                 System.out.println("here2");
    //                 completa.combinar(nodoActual.getColumna(), nodoActual.getFila(), nodoActual.getValor());
    //                 nodoActual = nodoActual.getDerecha();
    //             }
    //             nodoEncabezado = nodoEncabezado.getSiguiente();
    //         }
    //     }
    //     return completa;
    // }
}
