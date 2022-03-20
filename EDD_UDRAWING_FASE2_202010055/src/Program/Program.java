package Program;
import java.util.ArrayList;

import Models.Cliente;

public class Program {
    public static Program program;
    public static ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
    public static Cliente loggedUser;

    public Program(){
    }

    public static Program getInstancia() {
        if (program == null) {
            program = new Program();
            listaClientes = new ArrayList<>(); 
        }
        return program;
    }

}
