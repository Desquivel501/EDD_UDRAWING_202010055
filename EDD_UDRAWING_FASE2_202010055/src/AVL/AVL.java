package AVL;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Queue;

import javax.management.RuntimeErrorException;

import ABB.ArbolBinario;
import Cola.Cola;
import Matriz.MatrizCapa;
import Models.Imagen;

public class AVL {
    private NodoAVL raiz;

    public AVL(){
        raiz = null;
    }

    public void actualizarAltura(NodoAVL actual){
        actual.alto = 1 + Math.max(altura(actual.izquierda), altura(actual.derecha));
    }

    private int altura(NodoAVL actual){
        if(actual == null){
            return -1;
        }else{
            return actual.alto;
        }
    }

    private int equilibrio(NodoAVL actual){
        if(actual == null){
            return 0;
        }else{
            return altura(actual.derecha) - altura(actual.izquierda);
        }
    }

    private NodoAVL rotacionDerecha(NodoAVL n){
        NodoAVL n1 = n.izquierda;
        NodoAVL n2 = n1.derecha;
        n1.derecha = n;
        n.izquierda = n2;
        actualizarAltura(n);
        actualizarAltura(n1);
        
        return n1;
    }

    private NodoAVL rotacionIzquierda(NodoAVL n){
        NodoAVL n1 = n.derecha;
        NodoAVL n2 = n1.izquierda;
        n1.izquierda = n;
        n.derecha = n2;
        actualizarAltura(n);
        actualizarAltura(n1);
        
        return n1;
    }

    private NodoAVL balancear(NodoAVL n){
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

    private NodoAVL insertarR(NodoAVL n, int valor, Imagen imagen ){
        if(n == null){
            return new NodoAVL(valor,imagen);
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

    public NodoAVL getRaiz(){
        return raiz;
    }

    private ArrayList<Integer> preOrderR(NodoAVL actual , ArrayList<Integer> visitados){
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

    private ArrayList<Integer> postOrderR(NodoAVL actual , ArrayList<Integer> visitados){
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


    private void insertarCapasR(NodoAVL actual , ArbolBinario arbolCompleto){
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

    private void generarImageneR(NodoAVL actual){
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

    private NodoAVL buscarR(NodoAVL actual, int valor){
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

    public NodoAVL buscar(int valor){
        return buscarR(raiz, valor);
    }

    private NodoAVL eliminarR(NodoAVL actual, int valor){
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
                NodoAVL n1 = masIzquierda(actual.derecha);
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

    private NodoAVL masIzquierda(NodoAVL n){
        NodoAVL aux = n;
        while(aux.izquierda != null){
            aux = aux.izquierda;
        }
        return aux;
    }

    public void recorridoNiveles(){
        Cola<NodoAVL> cola = new Cola<>();
        cola.enqueue(raiz);
        while(!cola.vacia()){
            NodoAVL actual = cola.dequeue().getValor();
            System.out.println("Imagen " + actual.getValor());
            if(actual.izquierda != null){
                cola.enqueue(actual.izquierda);
            }
            if(actual.derecha != null){
                cola.enqueue(actual.derecha);
            }
        }
    }

    public void graficar(){
        StringBuilder dot = new StringBuilder();
        dot.append("digraph G{\n");
        dot = getRaiz().graficar(dot);
        dot.append("}\n");

        try{
            FileWriter fileWriter = new FileWriter("imagenes/arbolImagenes.dot");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(dot.toString());
            printWriter.close();

            String[] command = {"dot", "-Tpng" ,"imagenes/arbolImagenes.dot", "-o","imagenes/arbolImagenes.png" };
            new ProcessBuilder(command).start();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void graficar(int imagen){
        StringBuilder dot = new StringBuilder();
        dot.append("digraph G{\n");
        dot = getRaiz().graficar(dot, imagen);
        dot.append("}\n");

        try{
            FileWriter fileWriter = new FileWriter("imagenes/arbol_imagen" + imagen + ".dot");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(dot.toString());
            printWriter.close();

            String[] command = {"dot", "-Tpng" ,"imagenes/arbol_imagen" + imagen + ".dot", "-o","imagenes/arbol_imagen" + imagen + ".png" };
            new ProcessBuilder(command).start();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
