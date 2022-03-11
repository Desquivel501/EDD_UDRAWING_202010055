package ABB;

import java.util.ArrayList;

class Nodo{
    int valor;
    Nodo izquierda;
    Nodo derecha;
    

    Nodo(int valor){
        this.valor = valor;
        izquierda = null;
        derecha = null;
    }
}

public class ArbolBinario {
    Nodo raiz;

    ArbolBinario(){
    }

    private Nodo insertarR(Nodo actual, int valor){
        if(actual == null){
            return new Nodo(valor);
        }
        if(valor < actual.valor){
            actual.izquierda = insertarR(actual.izquierda, valor);
        }else if(valor > actual.valor){
            actual.derecha = insertarR(actual.derecha, valor);
        }else{
            return actual;
        }
        return actual;
    }

    public void insertar(int valor){
        raiz = insertarR(raiz,valor);
    }

    private Nodo buscarR(Nodo actual, int valor){
        if(actual == null){
            return null;
        }
        if(valor == actual.valor){
            return actual;
        }
        if(valor < actual.valor){
            buscarR(actual.izquierda, valor);
        }else{
            buscarR(actual.derecha, valor);
        }
        return null;
    }

    public Nodo buscar(int valor){
        return buscarR(raiz, valor);
    }

    private ArrayList<Integer> inOrderR(Nodo actual , ArrayList<Integer> visitados){
        if(actual == null){
            return visitados;
        }
        visitados = inOrderR(actual.izquierda, visitados);
        visitados.add(actual.valor);
        visitados = inOrderR(actual.derecha, visitados);
        return visitados;

    }

    public void inOrder(){
        ArrayList<Integer> visitados = new ArrayList<Integer>();
        visitados = inOrderR(raiz, visitados);
        System.out.println(visitados.toString());
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
}
