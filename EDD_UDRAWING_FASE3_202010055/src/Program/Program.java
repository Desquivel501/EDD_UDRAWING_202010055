package Program;

import ArbolB.ArbolB;
import Blockchain.Bloque;
import Grafo.Grafo;
import Lector.Lector;
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
    public static Lista<Bloque> lista_bloques = new Lista<Bloque>();
    public static ArbolMerkle arbolMerkle = new ArbolMerkle();
    public static int INDEX = 0;
    public static String PREVIOUSHASH = "0000";
    public static int noCero = 4;

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

    public static void crearArbol(){
        arbolMerkle.generarArbol();
        arbolMerkle.graficar();
    }

    public static void crearBloque(){
        arbolMerkle.generarArbol();
        arbolMerkle.graficar();
        var raiz = arbolMerkle.raiz.getHash();
        Bloque nuevoBloque = new Bloque(lista_entregas,raiz);
        nuevoBloque.save();
        lista_bloques.insertar(nuevoBloque);

        lista_entregas = new Lista<Entrega>();
        arbolMerkle = new ArbolMerkle();

    }
}
