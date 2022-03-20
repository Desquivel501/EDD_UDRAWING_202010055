import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.UIManager;


import ABB.ArbolBinario;
import AVL.AVL;
import ArbolB.ArbolB;
import GUI.*;
import Lector.Lector;
import ListaAlbum.ListaAlbum;
import Matriz.*;
import Models.Cliente;
import Program.Program;

public class App {
    
    public static ArrayList<Cliente> listaClientes;

    public static void main(String[] args) throws Exception {   

        try{
            UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme");
        } catch(Exception e){
            System.out.println(e);
        }

        
        // Lector lector = new Lector();
        // ArbolBinario arbol = new ArbolBinario();
        // arbol = lector.leerMatriz(arbol);

        // arbol.preOrder();

        // AVL arbolImagenes = new AVL();
        // arbolImagenes = lector.leerImagenes(arbolImagenes);
        // arbolImagenes.insertarCapas(arbol);
        // arbolImagenes.preOrder();
        // arbolImagenes.generarImagenes();
        
        // arbolImagenes.graficar();
        // arbolImagenes.graficar(3);

        // ListaAlbum albumes = new ListaAlbum();
        // albumes = lector.leerAlbumes(albumes,arbolImagenes);
        // albumes.graficar();

        ArbolB prueba = new ArbolB();
        
        prueba.insertar(22, new Cliente(22, "e", "a"));
        prueba.insertar(6, new Cliente(6, "d", "a"));
        prueba.insertar(12, new Cliente(12, "e", "a"));
        prueba.insertar(10, new Cliente(10, "f", "a"));
        prueba.insertar(9, new Cliente(9, "g", "a"));
        prueba.insertar(7, new Cliente(7, "h", "a"));
        prueba.insertar(8, new Cliente(8, "i", "a"));
        prueba.insertar(2, new Cliente(2, "j", "a"));
        prueba.insertar(13, new Cliente(13, "k", "a"));
        prueba.insertar(20, new Cliente(20, "e", "a"));
        prueba.insertar(21, new Cliente(21, "e", "a"));
        prueba.insertar(14, new Cliente(14, "l", "a"));
        prueba.insertar(11, new Cliente(11, "m", "a"));
        prueba.insertar(1, new Cliente(1, "n", "a"));
        prueba.insertar(5, new Cliente(5, "c", "a"));
        prueba.insertar(3, new Cliente(3, "a", "a"));
        prueba.insertar(4, new Cliente(4, "b", "a"));
        prueba.insertar(15, new Cliente(15, "e", "a"));

        // prueba.buscar(10);
        prueba.graficar();
    
        // Admin admin = new Admin();
        // new Login();
        // Registro registro = new Registro();
    }


}
