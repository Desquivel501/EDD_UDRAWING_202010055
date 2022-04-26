package Program;

import ArbolB.ArbolB;
import Grafo.Grafo;
import Lista.Lista;
import Models.Cliente;
import Models.Mensajero;
import TablaHash.TablaHash;

public class Program {
    public static Program program;
    public static Cliente loggedUser;
    public static ArbolB arbolClientes = new ArbolB();
    public static boolean hayUsuario = false;
    public static TablaHash tablaMensajeros  = new TablaHash();
    public static Grafo grafoLugares = new Grafo();
    

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

}
