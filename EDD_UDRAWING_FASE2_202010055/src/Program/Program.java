package Program;

import ArbolB.ArbolB;
import Models.Cliente;

public class Program {
    public static Program program;
    public static Cliente loggedUser;
    public static ArbolB arbolClientes = new ArbolB();
    public static boolean hayUsuario = false;

    public Program(){
    }

    public static Program getInstancia() {
        if (program == null) {
            program = new Program();
            arbolClientes = new ArbolB();
            hayUsuario = false;
            loggedUser = null;
        }
        return program;
    }

}
