package Lector;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;

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
import Models.Cliente;
import Models.Imagen;
import Program.*;

public class Lector {
    Scanner scan = new Scanner(System.in);

    public Lector() {
    }

    public boolean leerMatriz(File archivo){
        boolean error = false;

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
                        Program.loggedUser.getArbolCapas().insertar((int)id, matrizActual);
                    }
                    
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                    error = true;
                }                
                
            }

        return error;
    }

    public boolean leerImagenes(File archivo){
        boolean error = false;

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
                    Program.loggedUser.getArbolImagenes().insertar((int)id, nueva);
                }
                    
            } catch (IOException | ParseException e) {
                e.printStackTrace();
                error = true;
            }                
                
            }
            return error;
    }

    public boolean leerAlbumes(File archivo){

        boolean error = false;

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
                        NodoAVL imgActual = Program.loggedUser.getArbolImagenes().buscar((int)noimg);
                        if(imgActual != null){
                            nuevo.insertar(imgActual.getImagen());
                        }
                    }
                    Program.loggedUser.getListaAlbum().insertar(nuevo);
                }
                    
            } catch (IOException | ParseException e) {
                e.printStackTrace();
                error = true;
            }                
                
        }
        return error;
    }

    public boolean leerClientes(File archivo){
        boolean error = false;
        var arbolClientes = Program.arbolClientes;

        try {
            Reader reader = new FileReader(archivo);
            JSONParser parser = new JSONParser();
            Object obj  = parser.parse(reader);
                    
            JSONArray array = (JSONArray) obj;
  
            for(int i = 0; i < array.size(); i++){

                JSONObject objetoCliente = (JSONObject) array.get(i);
                

                String nombre = (String) objetoCliente.get("nombre_cliente");
                String dpi = (String) objetoCliente.get("dpi");
                String pass = (String) objetoCliente.get("password");
                
                System.out.println(nombre);

                Cliente actual = new Cliente(Long.parseLong(dpi), nombre, pass);

                if(arbolClientes.buscarNombre(nombre) == null){
                    arbolClientes.insertar(Long.parseLong(dpi), actual);
                    Program.hayUsuario = true;
                }else{
                    System.out.println("Nombre repetido: " + nombre);
                }

                
            }
        
            arbolClientes.graficar();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            error = true;
        }                
        return error;
    }



}
