import Models.*;
import Program.Program;
import TablaHash.TablaHash;
import javax.swing.UIManager;

import GUI.Login;
import Models.*;
public class App {
    public static void main(String[] args){

        try{
            UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme");
        } catch(Exception e){
            System.out.println(e);
        }
        // var nuevo = new Cliente(1234L,"Desquivel","1234","Derek Esquivel" ,"correo" ,"5545652008","direccion","15");
        // Program.arbolClientes.insertar(1234L, nuevo);
        // new Login();

        Mensajero m1 = new Mensajero();
        m1.setNombres("1");
        m1.setDpi(4L);

        Mensajero m2 = new Mensajero();
        m2.setNombres("2");
        m2.setDpi(4L);

        Mensajero m3 = new Mensajero();
        m3.setNombres("3");
        m3.setDpi(4L);

        Mensajero m4 = new Mensajero();
        m4.setNombres("4");
        m4.setDpi(4L);

        Mensajero m5 = new Mensajero();
        m5.setNombres("5");
        m5.setDpi(4L);

        Mensajero m6 = new Mensajero();
        m6.setNombres("6");
        m6.setDpi(4L);

        TablaHash tabla = new TablaHash();
        tabla.insertar(m1);
        tabla.insertar(m2);
        tabla.insertar(m3);
        tabla.insertar(m4);
        tabla.insertar(m5);
        tabla.insertar(m6);

        tabla.imprimir();
    }
}
