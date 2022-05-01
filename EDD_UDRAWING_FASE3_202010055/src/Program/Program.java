package Program;

import ArbolB.ArbolB;
import Grafo.Grafo;
import Lista.Lista;
import Merkle.ArbolMerkle;
import Models.*;
import Models.Mensajero;
import TablaHash.TablaHash;

public class Program {
    public static Program program;
    public static Cliente loggedUser;
    public static ArbolB arbolClientes = new ArbolB();
    public static boolean hayUsuario = false;
    public static TablaHash tablaMensajeros  = new TablaHash();
    public static Grafo grafoLugares = new Grafo();
    public static Lista<Entrega> lista_entregas = new Lista<Entrega>();
    public static ArbolMerkle arbolMerkle = new ArbolMerkle();
    

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
