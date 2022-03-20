package Models;

import ABB.*;
import AVL.*;
import ListaAlbum.*;

public class Cliente {
    private int dpi;
    private String nombre;
    private String contraseña;
    private ListaAlbum listaAlbum;
    private AVL arbolImagenes;
    private ArbolBinario arbolCapas;
    
    public Cliente(int dpi, String nombre, String contraseña) {
        this.dpi = dpi;
        this.nombre = nombre;
        this.contraseña = contraseña;
        listaAlbum = new ListaAlbum();
        arbolImagenes = new AVL();
        arbolCapas = new ArbolBinario();
    }

    public int getDpi() {
        return this.dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return this.contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }


    public ListaAlbum getListaAlbum() {
        return this.listaAlbum;
    }

    public void setListaAlbum(ListaAlbum listaAlbum) {
        this.listaAlbum = listaAlbum;
    }

    public AVL getArbolImagenes() {
        return this.arbolImagenes;
    }

    public void setArbolImagenes(AVL arbolImagenes) {
        this.arbolImagenes = arbolImagenes;
    }

    public ArbolBinario getArbolCapas() {
        return this.arbolCapas;
    }

    public void setArbolCapas(ArbolBinario arbolCapas) {
        this.arbolCapas = arbolCapas;
    }



}
