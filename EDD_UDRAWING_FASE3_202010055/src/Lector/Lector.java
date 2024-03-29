package Lector;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.crypto.bcrypt.BCrypt;

import ABB.*;
import AVL.*;
import Blockchain.Bloque;
import Lista.Lista;
import Matriz.*;
import Merkle.ArbolMerkle;
import Models.*;
import Program.*;

public class Lector {
    public boolean leerMensajero(File archivo){
        boolean error = false;

        try {
            Reader reader = new FileReader(archivo);
            JSONParser parser = new JSONParser();
            Object obj  = parser.parse(reader);

            JSONArray array = (JSONArray) obj;

            for(int i = 0; i < array.size(); i++){

                JSONObject objetoCliente = (JSONObject) array.get(i);
                String dpi = (String) objetoCliente.get("dpi");
                String nombre = (String) objetoCliente.get("nombres");
                String apellido = (String) objetoCliente.get("apellidos");
                String licencia = (String) objetoCliente.get("tipo_licencia");
                String genero = (String) objetoCliente.get("genero");
                String direccion = (String) objetoCliente.get("direccion");
                String telefono = (String) objetoCliente.get("telefono");

                Mensajero nuevo = new Mensajero(Long.parseLong(dpi), nombre, apellido, licencia, genero, telefono, direccion);
                Program.tablaMensajeros.insertar(nuevo);

            }

        } catch (Exception e) {
            error = true;
            e.printStackTrace();
        }
        return error;
    }

    public boolean leerMatriz(File archivo){
        boolean error = false;
            if(archivo != null){
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

                } catch (Exception e) {
                    error = true;
                }

            }
        return error;
    }

    public boolean leerImagenes(File archivo){
        boolean error = false;
        if(archivo != null){
            try {
                Reader reader = new FileReader(archivo);
                JSONParser parser = new JSONParser();
                Object obj  = parser.parse(reader);

                JSONArray array = (JSONArray) obj;

                for(int i = 0; i < array.size(); i++){

                    JSONObject objetoImagen= (JSONObject) array.get(i);

                    long id = (long) objetoImagen.get("id");
                    JSONArray listaCapas = (JSONArray) objetoImagen.get("capas");

                    ArbolBinario arbol = new ArbolBinario();

                    for(int j = 0; j < listaCapas.size();j++){
                        long noCapa = (long) listaCapas.get(j);
                        MatrizCapa actual = Program.loggedUser.getArbolCapas().getCapa((int) noCapa);
                        if(actual != null){
                            arbol.insertar((int) noCapa, actual);
                        }
                    }

                    Imagen nueva = new Imagen((int)id,arbol);
                    Program.loggedUser.getArbolImagenes().insertar((int)id, nueva);
                }

            } catch (Exception e) {
                error = true;
            }
        }
        return error;
    }

    public boolean leerAlbumes(File archivo){

        boolean error = false;

        if(archivo != null){
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

            } catch (Exception e) {
                // e.printStackTrace();
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

                String dpi = (String) objetoCliente.get("dpi");
                String nombre_cliente = (String) objetoCliente.get("nombre_cliente");
                String usuario = (String) objetoCliente.get("usuario");
                String correo = (String) objetoCliente.get("correo");
                String password = (String) objetoCliente.get("password");
                String telefono = (String) objetoCliente.get("telefono");
                String direccion = (String) objetoCliente.get("direccion");
                long id_municipio = (long) objetoCliente.get("id_municipio");

                String hashedPass = BCrypt.hashpw(password, BCrypt.gensalt(10));

                Cliente actual = new Cliente(Long.parseLong(dpi), usuario, hashedPass, nombre_cliente, correo, telefono, direccion, (int) id_municipio);
                // System.out.println(actual);

                if(arbolClientes.buscar(Long.parseLong(dpi)) == null){
                    arbolClientes.insertar(Long.parseLong(dpi), actual);
                    Program.hayUsuario = true;
                }else{
                   System.out.println("Repetido - " + dpi);
                }
            }

            arbolClientes.graficar();
        } catch (Exception e) {
            e.printStackTrace();
            error = true;
        }
        return error;
    }

    public boolean leerLugares(File archivo){
        boolean error = false;

        try {
            Reader reader = new FileReader(archivo);
            JSONParser parser = new JSONParser();
            JSONObject obj  = (JSONObject) parser.parse(reader);

            JSONArray array = (JSONArray) obj.get("Lugares");

            for(int i = 0; i < array.size(); i++){

                JSONObject objetoLugar = (JSONObject) array.get(i);
                long id = (long) objetoLugar.get("id");
                String departamento = (String) objetoLugar.get("departamento");
                String nombre = (String) objetoLugar.get("nombre");
                String sn_sucursal = (String) objetoLugar.get("sn_sucursal");

                boolean sucursal = (sn_sucursal.equals("si")) ? true : false;

                Lugar nuevo = new Lugar((int) id, departamento, nombre, sucursal);
                Program.grafoLugares.insertarLugar(nuevo);
            }

        } catch (Exception e) {
            e.printStackTrace();
            error = true;
        }
        return error;
    }

    public boolean leerRutas(File archivo){
        boolean error = false;

        try {
            Reader reader = new FileReader(archivo);
            JSONParser parser = new JSONParser();
            JSONObject obj  = (JSONObject) parser.parse(reader);

            JSONArray array = (JSONArray) obj.get("Grafo");

            for(int i = 0; i < array.size(); i++){

                JSONObject objetoLRuta = (JSONObject) array.get(i);

                long inicio = (long) objetoLRuta.get("inicio");
                long final_ = (long) objetoLRuta.get("final");
                long peso = (long) objetoLRuta.get("peso");

                Program.grafoLugares.insertarRuta((int)inicio, (int)final_, (int)peso);
            }

        } catch (Exception e) {
            e.printStackTrace();
            error = true;
        }
        return error;
    }

    public static void leerBloques(){
        File folder = new File("C:\\udrawing\\blockchain\\Bloques\\");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                try {
                    Reader reader = new FileReader(file);
                    JSONParser parser = new JSONParser();
                    JSONObject obj  = (JSONObject) parser.parse(reader);

                    long INDEX = (long) obj.get("INDEX");
                    String TIMESTAMP = (String) obj.get("TIMESTAMP");

                    JSONArray array = (JSONArray) obj.get("DATA");
                    Lista<Entrega> DATA  = new Lista<>();

                    for(int i = 0; i < array.size(); i++){

                        JSONObject objetoData = (JSONObject) array.get(i);

                        String cliente = (String) objetoData.get("cliente");
                        String mensajero = (String) objetoData.get("mensajero");
                        String datetime = (String) objetoData.get("datetime");
                        String sede = (String) objetoData.get("sede");
                        String destino = (String) objetoData.get("destino");

                        DATA.insertar(new Entrega(sede, destino, datetime, cliente, mensajero));
                    }

                    String ROOTMERKLE = (String) obj.get("ROOTMERKLE");
                    String PREVIOUSHASH = (String) obj.get("PREVIOUSHASH");
                    long NONCE = (long) obj.get("NONCE");
                    String HASH = (String) obj.get("HASH");

                    String raiz;
                    if(DATA.vacia()){
                        raiz = DigestUtils.sha256Hex("-1");
                    } else{
                        ArbolMerkle arbolMerkle = new ArbolMerkle();
                        arbolMerkle.generarArbol(DATA);
                        raiz = arbolMerkle.raiz.getHash();
                    }
                    String cadena2 = INDEX + TIMESTAMP + Program.PREVIOUSHASH + raiz + NONCE;
                    String hash2 = DigestUtils.sha256Hex(cadena2);

                    Bloque actual = new Bloque((int)INDEX, TIMESTAMP, (int)NONCE, DATA, Program.PREVIOUSHASH, raiz, hash2);
                    actual.setValido(true);

                    if(!HASH.equals(hash2)) actual.setValido(false);
                    if(!ROOTMERKLE.equals(raiz)) actual.setValido(false);
                    if(!PREVIOUSHASH.equals(Program.PREVIOUSHASH)) actual.setValido(false);
                    Program.lista_bloques.insertar(actual);

                    Program.PREVIOUSHASH = hash2;
                    Program.INDEX = (int)INDEX +1;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
