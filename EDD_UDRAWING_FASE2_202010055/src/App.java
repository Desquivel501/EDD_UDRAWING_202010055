import java.util.ArrayList;

import Matriz.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        ArrayList<MatrizCapa> listaCapas = new ArrayList<>();

        Lector lector = new Lector();

        lector.leerMatriz(listaCapas);


    }
}
