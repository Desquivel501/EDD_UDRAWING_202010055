package ABB;

import java.util.ArrayList;

import Matriz.ListaEncabezado;
import Matriz.MatrizCapa;

class Nodo{
    int valor;
    Nodo izquierda;
    Nodo derecha;
    MatrizCapa capa;

    Nodo(int valor, MatrizCapa capa){
        this.valor = valor;
        this.capa = capa;
        izquierda = null;
        derecha = null;
    }
}

public class ArbolBinario {
    Nodo raiz;

    public ArbolBinario(){
    }

    private Nodo insertarR(Nodo actual, int valor, MatrizCapa capa){
        if(actual == null){
            return new Nodo(valor,capa);
        }
        if(valor < actual.valor){
            actual.izquierda = insertarR(actual.izquierda, valor, capa);
        }else if(valor > actual.valor){
            actual.derecha = insertarR(actual.derecha, valor, capa);
        }else{
            return actual;
        }
        return actual;
    }

    public void insertar(int valor, MatrizCapa capa){
        raiz = insertarR(raiz,valor, capa);
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

    public MatrizCapa getCapa(int valor){
        Nodo n = buscarR(raiz, valor);
        if(n != null){
            return n.capa;
        }else{
            return null;
        }
    }

    private ArrayList<Integer> inOrderR(Nodo actual , ArrayList<Integer> visitados){
        if(actual == null){
            return visitados;
        }
        visitados = inOrderR(actual.izquierda, visitados);
        visitados.add(actual.valor);
        System.out.println("Filas:" + actual.capa.getNoFilas() + ", Columnas: " + actual.capa.getNoColumnas());
        visitados = inOrderR(actual.derecha, visitados);
        return visitados;

    }

    public void inOrder(){
        ArrayList<Integer> visitados = new ArrayList<Integer>();
        visitados = inOrderR(raiz, visitados);
        // System.out.println(visitados.toString());
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




    public MatrizCapa unirPreOrder(MatrizCapa completa){
        completa = unirPreOrderR(raiz, completa);
        return completa;
    }

    private MatrizCapa unirPreOrderR(Nodo actual , MatrizCapa completa){
        if(actual == null){
            return completa;
        }

        completa.combinarMatriz(actual.capa);
        completa = unirPreOrderR(actual.izquierda, completa);
        completa = unirPreOrderR(actual.derecha, completa);
        return completa;
    }

    public MatrizCapa unirInOrder(MatrizCapa completa){
        completa = unirInOrderR(raiz, completa);
        return completa;
    }

    private MatrizCapa unirInOrderR(Nodo actual , MatrizCapa completa){
        if(actual == null){
            return completa;
        }
        completa = unirInOrderR(actual.izquierda, completa);
        completa.combinarMatriz(actual.capa);
        completa = unirInOrderR(actual.derecha, completa);
        return completa;
    }

    public MatrizCapa unirPostOrder(MatrizCapa completa){
        completa = unirPostOrderR(raiz, completa);
        return completa;
    }

    private MatrizCapa unirPostOrderR(Nodo actual , MatrizCapa completa){
        if(actual == null){
            return completa;
        }
        completa = unirPostOrderR(actual.izquierda, completa);
        completa = unirPostOrderR(actual.derecha, completa);
        completa.combinarMatriz(actual.capa);
        return completa;
    }

    
}
