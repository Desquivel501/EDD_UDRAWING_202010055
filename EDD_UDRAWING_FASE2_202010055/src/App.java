import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.UIManager;


import ABB.ArbolBinario;
import AVL.AVL;
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
    
        Admin admin = new Admin();
        // new Login();
        // Registro registro = new Registro();
    }


}
