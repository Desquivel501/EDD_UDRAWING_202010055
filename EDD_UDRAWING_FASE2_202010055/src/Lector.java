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
import AVL.AVL;
import AVL.NodoAVL;
import ListaAlbum.ListaAlbum;
import Matriz.MatrizCapa;
import Models.Album;
import Models.Imagen;

public class Lector {
    Scanner scan = new Scanner(System.in);

    public Lector() {
    }

    public ArbolBinario leerMatriz(ArbolBinario arbolMatriz){

            System.out.println("Ingrese la ruta de la matriz: ");
            String path = scan.nextLine();

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

    public AVL leerImagenes(AVL arbolImagenes){
        System.out.println("Ingrese la ruta de las imagenes:");
        String path = scan.nextLine();

        File archivo = new File(path);

        if(archivo != null){
            System.out.println("Here");
            try {
                Reader reader = new FileReader(archivo);
                JSONParser parser = new JSONParser();
                Object obj  = parser.parse(reader);
                    
                JSONArray array = (JSONArray) obj;
  
                for(int i = 0; i < array.size(); i++){

                    JSONObject objetoImagen= (JSONObject) array.get(i);

                    long id = (long) objetoImagen.get("id");
                    JSONArray listaCapas = (JSONArray) objetoImagen.get("capas");
                    ArrayList<Integer> lista = new ArrayList<Integer>();
                    for(int j = 0; j < listaCapas.size();j++){
                        long noCapa = (long) listaCapas.get(j);
                        lista.add((int) noCapa);
                    }
                    Imagen nueva = new Imagen((int)id,lista);
                    arbolImagenes.insertar((int)id, nueva);

                }

                    
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }                
                
            }
            return arbolImagenes;
    }

    public ListaAlbum leerAlbumes(ListaAlbum listaAlbum, AVL imagenes){
        System.out.println("Ingrese la ruta de los albumes:");
        String path = scan.nextLine();

        File archivo = new File(path);

        if(archivo != null){
            System.out.println("Here");
            try {
                Reader reader = new FileReader(archivo);
                JSONParser parser = new JSONParser();
                Object obj  = parser.parse(reader);
                    
                JSONArray array = (JSONArray) obj;
  
                for(int i = 0; i < array.size(); i++){

                    JSONObject objetoAlbum = (JSONObject) array.get(i);

                    String nombre = (String) objetoAlbum.get("nombre_album");
                    Album nuevo = new Album(nombre);
                    JSONArray listaImagenes = (JSONArray) objetoAlbum.get("imgs");
                    for(int j = 0; j < listaImagenes.size();j++){
                        long noimg = (long) listaImagenes.get(j);
                        NodoAVL imgActual = imagenes.buscar((int)noimg);
                        if(imgActual != null){
                            nuevo.insertar(imgActual.getImagen());
                        }
                    }
                    listaAlbum.insertar(nuevo);
                }
                    
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }                
                
            }
            return listaAlbum;
    }



}
