import Models.*;
import Program.Program;
import TablaHash.TablaHash;

import java.util.ArrayList;

import javax.swing.UIManager;

import org.springframework.security.crypto.bcrypt.BCrypt;

import Blockchain.Bloque;
import Cola.ColaP;
import GUI.Admin;
import GUI.ClienteG;
import GUI.Login;
import Grafo.Grafo;
import Lector.Lector;
import Lista.Lista_recorrido;
import Models.*;
public class App {
    public static void main(String[] args){

        try{
            UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme");
        } catch(Exception e){
            e.printStackTrace();
        }

        try {
            Program.deserializar();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Lector.leerBloques();
        Program.graficarBloques();
        Program.start();
        
        String hashedPass = BCrypt.hashpw("1234", BCrypt.gensalt(10));
        var nuevo = new Cliente(1234L,"Desquivel",hashedPass,"Derek Esquivel" ,"correo" ,"5545652008","direccion",15);

        Program.arbolClientes.insertar(1234L, nuevo);
        Program.loggedUser = nuevo;
        // new ClienteG();
        new Admin();
        // new Login();
    }
}
