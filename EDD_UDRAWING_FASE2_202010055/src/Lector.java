import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ABB.ArbolBinario;
import Matriz.MatrizCapa;

public class Lector {
    

    public Lector() {
    }

    public ArbolBinario leerMatriz(ArbolBinario arbolMatriz){

            Scanner scan = new Scanner(System.in);
            System.out.println("Ingrese la ruta de la matriz: ");
            String path = scan.nextLine();
            scan.close();

            // File archivo = new File("D:\\Documents\\USAC\\Lab EDD\\Proyecto 2\\Mario.json");
            File archivo = new File(path);

            if(archivo != null){
                System.out.println("Here");
                try {
                    Reader reader = new FileReader(archivo);
                    JSONParser parser = new JSONParser();
                    Object obj  = parser.parse(reader);
                    
                    JSONArray array = (JSONArray) obj;
  
                    for(int i = 0; i < array.size(); i++){

                        JSONObject objetoCapa = (JSONObject) array.get(i);

                        long id = (long) objetoCapa.get("id_capa");
                        MatrizCapa matrizActual = new MatrizCapa("Capa " + (int)id);
                        matrizActual.setId((int)id);

                        JSONArray listaPixeles = (JSONArray) objetoCapa.get("pixeles");
                        for(int j = 0; j < listaPixeles.size(); j++){

                            JSONObject objetoPixeles = (JSONObject) listaPixeles.get(j);
                            long columna = (long) objetoPixeles.get("columna");
                            long fila = (long) objetoPixeles.get("fila");
                            String valor = (String) objetoPixeles.get("color");

                            matrizActual.insertar((int)columna, (int)fila, valor);
                        }   
                        // listaMatriz.add(matrizActual);
                        arbolMatriz.insertar((int)id, matrizActual);
                    }

                    // listaMatriz.get(0).graficar();
                    // System.out.println(listaMatriz.size());
                    
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }                
                
            }

        return arbolMatriz;
    }



}
