package Program;

import ArbolB.ArbolB;
import Lista.Lista;
import Models.Cliente;
import Models.Mensajero;

public class Program {
    public static Program program;
    public static Cliente loggedUser;
    public static ArbolB arbolClientes = new ArbolB();
    public static boolean hayUsuario = false;
    public static Lista<Mensajero> listaMensajeros;

    public Program(){
    }

    public static Program getInstancia() {
        if (program == null) {
            program = new Program();
            arbolClientes = new ArbolB();
            listaMensajeros = new Lista<Mensajero>();
            hayUsuario = false;
            loggedUser = null;
        }
        return program;
    }

}
