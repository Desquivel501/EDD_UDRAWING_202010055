import Models.*;
import Program.Program;
import TablaHash.TablaHash;
import javax.swing.UIManager;

import Cola.ColaP;
import GUI.Login;
import Grafo.Grafo;
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

        // Mensajero m1 = new Mensajero();
        // m1.setNombres("1");
        // m1.setDpi(4L);

        // Mensajero m2 = new Mensajero();
        // m2.setNombres("2");
        // m2.setDpi(4L);

        // Mensajero m3 = new Mensajero();
        // m3.setNombres("3");
        // m3.setDpi(4L);

        // Mensajero m4 = new Mensajero();
        // m4.setNombres("4");
        // m4.setDpi(4L);

        // Mensajero m5 = new Mensajero();
        // m5.setNombres("5");
        // m5.setDpi(4L);

        // Mensajero m6 = new Mensajero();
        // m6.setNombres("6");
        // m6.setDpi(4L);

        // TablaHash tabla = new TablaHash();
        // tabla.insertar(m1);
        // tabla.insertar(m2);
        // tabla.insertar(m3);
        // tabla.insertar(m4);
        // tabla.insertar(m5);
        // tabla.insertar(m6);

        // tabla.imprimir();

        Grafo grafoRutas = new Grafo();
        Lugar l1 = new Lugar();
        l1.setId(1);

        Lugar l2 = new Lugar();
        l2.setId(2);

        Lugar l3 = new Lugar();
        l3.setId(3);

        Lugar l4 = new Lugar();
        l4.setId(4);

        Lugar l5 = new Lugar();
        l5.setId(5);

        Lugar l6 = new Lugar();
        l6.setId(6);

        grafoRutas.insertarLugar(l1);
        grafoRutas.insertarLugar(l2);
        grafoRutas.insertarLugar(l3);
        grafoRutas.insertarLugar(l4);
        grafoRutas.insertarLugar(l5);
        grafoRutas.insertarLugar(l6);

        grafoRutas.insertarRuta(1, 6, 6);
        grafoRutas.insertarRuta(1, 2, 5);
        grafoRutas.insertarRuta(2, 5, 9);
        grafoRutas.insertarRuta(2, 3, 1);
        grafoRutas.insertarRuta(3, 4, 11);
        grafoRutas.insertarRuta(4, 5, 3);
        grafoRutas.insertarRuta(6, 4, 2);
        grafoRutas.insertarRuta(6, 2, 3);
   

        grafoRutas.graficarGrafo();
        grafoRutas.dijkstra(1, 5);

    }
}
