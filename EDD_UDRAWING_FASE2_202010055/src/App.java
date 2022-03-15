import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import ABB.ArbolBinario;
import AVL.AVL;
import GUI.Login;
import ListaAlbum.ListaAlbum;
import Matriz.*;

public class App {
    public static void main(String[] args) throws Exception {

        

        Lector lector = new Lector();
        ArbolBinario arbol = new ArbolBinario();
        arbol = lector.leerMatriz(arbol);


        // MatrizCapa completa = new MatrizCapa("Completa");
        // completa = arbol.unirPreOrder(completa);
        // completa.graficar();
        // completa.graficarHTML();

        arbol.preOrder();

        AVL arbolImagenes = new AVL();
        arbolImagenes = lector.leerImagenes(arbolImagenes);
        arbolImagenes.insertarCapas(arbol);
        arbolImagenes.preOrder();
        arbolImagenes.generarImagenes();

        ListaAlbum albumes = new ListaAlbum();
        albumes = lector.leerAlbumes(albumes,arbolImagenes);
        albumes.graficar();
        
        // arbolImagenes.eliminar(8);
        // System.out.println("-----------------------------------------");
        // arbolImagenes.preOrder();
        // arbolImagenes.postOrder();
        
        // Login nuevo = new Login();
    }


}
