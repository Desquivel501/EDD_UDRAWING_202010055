package AVL;

import java.util.ArrayList;

import javax.management.RuntimeErrorException;

import ABB.ArbolBinario;
import Matriz.MatrizCapa;
import Models.Imagen;

class Nodo{
    int valor;
    int alto;
    Nodo derecha;
    Nodo izquierda;
    Imagen imagen;

    public Nodo(int valor, Imagen imagen ){
        this.valor = valor;
        alto = 0;
        derecha = null;
        izquierda = null;
        this.imagen = imagen;
    }

    public Nodo() {
    }

    public Nodo(int valor, int alto, Nodo derecha, Nodo izquierda, Imagen imagen) {
        this.valor = valor;
        this.alto = alto;
        this.derecha = derecha;
        this.izquierda = izquierda;
        this.imagen = imagen;
    }

    public int getValor() {
        return this.valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getAlto() {
        return this.alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public Nodo getDerecha() {
        return this.derecha;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }

    public Nodo getIzquierda() {
        return this.izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public Imagen getImagen() {
        return this.imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public Nodo valor(int valor) {
        setValor(valor);
        return this;
    }

    public Nodo alto(int alto) {
        setAlto(alto);
        return this;
    }

    public Nodo derecha(Nodo derecha) {
        setDerecha(derecha);
        return this;
    }

    public Nodo izquierda(Nodo izquierda) {
        setIzquierda(izquierda);
        return this;
    }

    public Nodo imagen(Imagen imagen) {
        setImagen(imagen);
        return this;
    }
}

public class AVL {
    private Nodo raiz;

    public AVL(){
        raiz = null;
    }

    public void actualizarAltura(Nodo actual){
        actual.alto = 1 + Math.max(altura(actual.izquierda), altura(actual.derecha));
    }

    private int altura(Nodo actual){
        if(actual == null){
            return -1;
        }else{
            return actual.alto;
        }
    }

    private int equilibrio(Nodo actual){
        if(actual == null){
            return 0;
        }else{
            return altura(actual.derecha) - altura(actual.izquierda);
        }
    }

    private Nodo rotacionDerecha(Nodo n){
        Nodo n1 = n.izquierda;
        Nodo n2 = n1.derecha;
        n1.derecha = n;
        n.izquierda = n2;
        actualizarAltura(n);
        actualizarAltura(n1);
        
        return n1;
    }

    private Nodo rotacionIzquierda(Nodo n){
        Nodo n1 = n.derecha;
        Nodo n2 = n1.izquierda;
        n1.izquierda = n;
        n.derecha = n2;
        actualizarAltura(n);
        actualizarAltura(n1);
        
        return n1;
    }

    private Nodo balancear(Nodo n){
        actualizarAltura(n);
        int equilibrio = equilibrio(n);
        if(equilibrio > 1){
            if(altura(n.derecha.derecha) > altura(n.derecha.izquierda)){
                n = rotacionIzquierda(n);
            }else{
                n.derecha = rotacionDerecha(n.derecha);
                n = rotacionIzquierda(n);
            }

        }else if(equilibrio < -1){
            if(altura(n.izquierda.izquierda) > altura(n.izquierda.derecha)){
                n = rotacionDerecha(n);
            }else{
                n.izquierda = rotacionIzquierda(n.izquierda);
                n = rotacionDerecha(n);
            }
        }
        return n;
    }

    private Nodo insertarR(Nodo n, int valor, Imagen imagen ){
        if(n == null){
            return new Nodo(valor,imagen);
        }
        else if(n.valor > valor){
            n.izquierda = insertarR(n.izquierda, valor,imagen);
        }else if(n.valor < valor){
            n.derecha = insertarR(n.derecha, valor,imagen);
        }else{
            return n;
        }
        return balancear(n);
    }

    public void insertar(int valor, Imagen imagen ){
        raiz = insertarR(raiz, valor, imagen);
        raiz = balancear(raiz);
    }

    public Nodo getRaiz(){
        return raiz;
    }

    private ArrayList<Integer> preOrderR(Nodo actual , ArrayList<Integer> visitados){
        if(actual == null){
            return visitados;
        }
        visitados.add(actual.valor);
        visitados = preOrderR(actual.izquierda, visitados);
        visitados = preOrderR(actual.derecha, visitados);
        return visitados;

    }

    public void preOrder(){
        ArrayList<Integer> visitados = new ArrayList<Integer>();
        visitados = preOrderR(raiz, visitados);
        System.out.println(visitados.toString());
    }

    private ArrayList<Integer> postOrderR(Nodo actual , ArrayList<Integer> visitados){
        if(actual == null){
            return visitados;
        }
        visitados = postOrderR(actual.izquierda, visitados);
        visitados = postOrderR(actual.derecha, visitados);
        visitados.add(actual.valor);
        return visitados;

    }

    public void postOrder(){
        ArrayList<Integer> visitados = new ArrayList<Integer>();
        visitados = postOrderR(raiz, visitados);
        System.out.println(visitados.toString());
    }


    private void insertarCapasR(Nodo actual , ArbolBinario arbolCompleto){
        if(actual == null){
            return;
        }
        actual.imagen.setArbolCapas(arbolCompleto);
        // System.out.println("Imagen: " + actual.valor + ", " + actual.imagen.getListaCapas());
        insertarCapasR(actual.izquierda, arbolCompleto);
        insertarCapasR(actual.derecha, arbolCompleto);
    }


    public void insertarCapas(ArbolBinario arbolCompleto){
        insertarCapasR(raiz, arbolCompleto);
        // System.out.println(visitados.toString());
    }

    private void generarImageneR(Nodo actual){
        if(actual == null){
            return;
        }
        actual.imagen.generarImagen();
        generarImageneR(actual.izquierda);
        generarImageneR(actual.derecha);
    }

    public void generarImagenes(){
        generarImageneR(raiz);
    }

    private Nodo buscarR(Nodo actual, int valor){
        if(actual == null){
            return null;
        }
        if(valor == actual.valor){
            return actual;
        }
        if(valor < actual.valor){
            actual = buscarR(actual.izquierda, valor);
        }else{
            actual = buscarR(actual.derecha, valor);
        }
        return actual;
    }

    public Nodo buscar(int valor){
        return buscarR(raiz, valor);
    }

    private Nodo eliminarR(Nodo actual, int valor){
        if(actual == null){
            return null;
        }
        else if(valor < actual.valor){
            actual.izquierda = eliminarR(actual.izquierda, valor);
        }else if(valor > actual.valor){
            actual.derecha = eliminarR(actual.derecha, valor);
        }else{
            if(actual.izquierda == null || actual.derecha == null){
                if(actual.izquierda == null){
                    actual = actual.derecha;
                }else{
                    actual = actual.izquierda;
                }
            }else if(actual.izquierda == null && actual.derecha == null){
                actual = null;
            }else{
                Nodo n1 = masIzquierda(actual.derecha);
                actual.valor = n1.valor;
                actual.imagen = n1.imagen;
                actual.derecha = eliminarR(actual.derecha, actual.valor);
             }
        }
        if(actual != null){
            actual = balancear(actual);
        }
        return actual;
    }

    public void eliminar(int valor){
        eliminarR(raiz,valor);
    }

    private Nodo eliminarNodo(Nodo n){
        if(n.izquierda == null || n.derecha == null){
           if(n.izquierda == null){
               n = n.derecha;
           }else{
               n = n.izquierda;
           }
        }else{
            Nodo n1 = masIzquierda(n.derecha);
            n.valor = n1.valor;
            n.imagen = n1.imagen;
            n.derecha = eliminarR(n.derecha, n.valor);
        }
        
        return n;
    }

    private Nodo masIzquierda(Nodo n){
        Nodo aux = n;
        while(aux.izquierda != null){
            aux = aux.izquierda;
        }
        return aux;
    }
}
