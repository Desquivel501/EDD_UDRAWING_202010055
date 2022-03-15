package Models;

import java.util.ArrayList;

import ABB.ArbolBinario;
import Matriz.MatrizCapa;

public class Imagen {
    private int id;
    private ArrayList<Integer> listaCapas;
    private ArbolBinario arbolCapas;
    private MatrizCapa completa;

    public Imagen(int id, ArrayList<Integer> listaCapas) {
        this.id = id;
        this.listaCapas = listaCapas;
        this.arbolCapas = new ArbolBinario();
        completa = null;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getListaCapas() {
        return this.listaCapas;
    }

    public void setListaCapas(ArrayList<Integer> listaCapas) {
        this.listaCapas = listaCapas;
    }

    public ArbolBinario getArbolCapas() {
        return this.arbolCapas;
    }

    public void setArbolCapas(ArbolBinario arbolCompleto) {
        System.out.println(listaCapas);
        for(int i = 0; i < listaCapas.size();i++){
            MatrizCapa actual = arbolCompleto.getCapa(listaCapas.get(i));
            if(actual != null){
                arbolCapas.insertar(actual.getId(), actual);      
            }
        }
    }

    public void imprimirCapas(){
        arbolCapas.preOrder();
    }

    public void generarImagen(){
        if(completa == null){
            this.completa = new MatrizCapa("Imagen" + this.id);
            this.completa = this.arbolCapas.unirInOrder(completa);
            completa.graficarHTML();
        }else{
            completa.graficarHTML();
        }
    }

    public void actualizarImagen(){
        this.completa = new MatrizCapa("Imagen" + this.id);
        this.completa = this.arbolCapas.unirInOrder(completa);
        completa.graficarHTML();
    }

    

}
