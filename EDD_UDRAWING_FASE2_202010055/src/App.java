import java.util.ArrayList;

import Matriz.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        ArrayList<MatrizCapa> listaCapas = new ArrayList<>();

        Lector lector = new Lector();

        ArrayList<MatrizCapa> listaMatriz = lector.leerMatriz(listaCapas);

        MatrizCapa completa = unirMatriz(listaMatriz);
        completa.graficar();

    }

    public static MatrizCapa unirMatriz(ArrayList<MatrizCapa> listaMatriz){
        MatrizCapa completa = new MatrizCapa("Completa");

        for(int i = 0; i < listaMatriz.size(); i++){
            MatrizCapa actual = listaMatriz.get(i);
            ListaEncabezado  eFila = actual.getFilas();

            var nodoEncabezado = eFila.getPrimero();

            while(nodoEncabezado != null){
                System.out.println("here1");
                var nodoActual = nodoEncabezado.getAccesoNodo();

                while(nodoActual != null){
                    System.out.println("here2");
                    completa.combinar(nodoActual.getColumna(), nodoActual.getFila(), nodoActual.getValor());
                    nodoActual = nodoActual.getDerecha();
                }
                nodoEncabezado = nodoEncabezado.getSiguiente();
            }
        }
        return completa;
    }
}
