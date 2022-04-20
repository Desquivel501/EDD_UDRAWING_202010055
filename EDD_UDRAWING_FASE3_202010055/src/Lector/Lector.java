package Lector;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Models.*;
import Program.Program;

public class Lector {
    // public boolean leerMensajero(File archivo){
    //     boolean error = false;

    //     var lista = Program.listaMensajeros;

    //     try {
    //         Reader reader = new FileReader(archivo);
    //         JSONParser parser = new JSONParser();
    //         Object obj  = parser.parse(reader);
                    
    //         JSONArray array = (JSONArray) obj;
  
    //         for(int i = 0; i < array.size(); i++){

    //             JSONObject objetoCliente = (JSONObject) array.get(i);
                
    //             String dpi = (String) objetoCliente.get("dpi");
    //             String nombre = (String) objetoCliente.get("nombres");
    //             String apellido = (String) objetoCliente.get("apellidos");
    //             String licencia = (String) objetoCliente.get("tipo_licencia");
    //             String genero = (String) objetoCliente.get("genero");
    //             String direccion = (String) objetoCliente.get("direccion");

    //             System.out.println(nombre);

    //             Mensajero nuevo = new Mensajero(Long.parseLong(dpi), nombre, apellido, licencia, genero, "0", direccion);
    //             lista.insertar(nuevo);


    //         }

    //     } catch (Exception e) {
    //         error = true;
    //     }                
    //     return error;
    // }

}
