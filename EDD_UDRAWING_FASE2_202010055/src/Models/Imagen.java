package Models;

import java.util.ArrayList;

import ABB.ArbolBinario;
import Matriz.MatrizCapa;

public class Imagen implements Comparable<Imagen>{
    private int id;
    private ArbolBinario arbolCapas;
    private MatrizCapa completa;

    public Imagen(int id, ArbolBinario arbol) {
        this.id = id;
        this.arbolCapas = arbol;
        completa = null;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArbolBinario getArbolCapas() {
        return this.arbolCapas;
    }

    public String generarImagen(){
        String nombre = "";
        if(completa == null){
            this.completa = new MatrizCapa("Imagen" + this.id);
            this.completa = this.arbolCapas.unirLevel(completa);
            nombre = completa.graficarHTML();
        }else{
            nombre = completa.graficarHTML();
        }
        return nombre;
    }

    @Override
    public int compareTo(Imagen o) {
        return this.arbolCapas.largo - o.arbolCapas.largo;
    }

    @Override
    public String toString() {
        return "[Imagen " + this.id + " - Capas " + this.getArbolCapas().largo + "]";
    }

    
}
