package ABB;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import Cola.Cola;
import Lista.*;
import Matriz.ListaEncabezado;
import Matriz.MatrizCapa;

public class ArbolBinario {
    NodoABB raiz;
    public int largo = 0;

    public ArbolBinario(){
    }

    public NodoABB getRaiz(){
        return raiz;
    }

    private NodoABB insertarR(NodoABB actual, int valor, MatrizCapa capa){
        if(actual == null){
            largo++;
            return new NodoABB(valor,capa);
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

    private NodoABB buscarR(NodoABB actual, int valor){
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

    public NodoABB buscar(int valor){
        return buscarR(raiz, valor);
    }

    public MatrizCapa getCapa(int valor){
        NodoABB n = buscarR(raiz, valor);
        if(n != null){
            return n.capa;
        }else{
            return null;
        }
    }

    private ArrayList<Integer> inOrderR(NodoABB actual , ArrayList<Integer> visitados){
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

    private ArrayList<Integer> preOrderR(NodoABB actual , ArrayList<Integer> visitados){
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

    private ArrayList<Integer> postOrderR(NodoABB actual , ArrayList<Integer> visitados){
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




    public MatrizCapa unirPreOrder(MatrizCapa completa, int num){
        completa = unirPreOrderR(raiz, completa, num);
        return completa;
    }

    private MatrizCapa unirPreOrderR(NodoABB actual , MatrizCapa completa, int num){
        if(actual == null){
            return completa;
        }

        completa.combinarMatriz(actual.capa);
        num--;
        System.out.println(num);
        if(num == 0) return completa;

        completa = unirPreOrderR(actual.izquierda, completa, num);
        completa = unirPreOrderR(actual.derecha, completa, num);
        return completa;
    }

    //--------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------

    public MatrizCapa unirInOrder(MatrizCapa completa, int num){
        Lista listaRecorrido = new Lista();
        listaRecorrido = unirInOrderR(raiz, listaRecorrido);
        Nodo aux = listaRecorrido.getHead();
        while(aux != null && num > 0){
            completa.combinarMatriz(aux.getValor());
            aux = aux.getSiguiente();
            num--;
        }
        return completa;
    }

    private Lista unirInOrderR(NodoABB actual , Lista listaRecorrido){
        if(actual == null){
            return listaRecorrido;
        }
        listaRecorrido = unirInOrderR(actual.izquierda, listaRecorrido);
        listaRecorrido.insertar(actual.capa);
        listaRecorrido = unirInOrderR(actual.derecha, listaRecorrido);
        return listaRecorrido;
    }

    //--------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------

    public MatrizCapa unirPostOrder(MatrizCapa completa, int num){
        Lista listaRecorrido = new Lista();
        listaRecorrido = unirPostOrderR2(raiz, listaRecorrido);
        Nodo aux = listaRecorrido.getHead();
        while(aux != null && num > 0){
            completa.combinarMatriz(aux.getValor());
            aux = aux.getSiguiente();
            num--;
        }
        return completa;
    }

    private Lista unirPostOrderR2(NodoABB actual , Lista listaRecorrido){
        if(actual == null){
            return listaRecorrido;
        }
        listaRecorrido = unirPostOrderR2(actual.izquierda, listaRecorrido);
        listaRecorrido = unirPostOrderR2(actual.derecha, listaRecorrido);
        listaRecorrido.insertar(actual.capa);
        return listaRecorrido;
    }

    //--------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------

    public MatrizCapa unirLevel(MatrizCapa completa){
        Lista listaRecorrido = new Lista();
        Cola<NodoABB> cola = new Cola<>();
        cola.enqueue(raiz);
        while(!cola.vacia()){
            NodoABB actual = cola.dequeue().getValor();
            listaRecorrido.insertar(actual.capa);
            if(actual.izquierda != null){
                cola.enqueue(actual.izquierda);
            }
            if(actual.derecha != null){
                cola.enqueue(actual.derecha);
            }
        }

        Nodo aux = listaRecorrido.getHead();
        while(aux != null){
            completa.combinarMatriz(aux.getValor());
            aux = aux.getSiguiente();
        }

        return completa;
    }

    public String graficar(){
        StringBuilder dot = new StringBuilder();
        dot.append("digraph G{\n");
        dot.append(String.format("label=\"%s\"\n", "Arbol Capas"));
        dot.append("labelloc = \"t\"\n");
        dot = getRaiz().graficar(dot);
        dot.append("}\n");

        int int_random = this.hashCode();

        try{
            FileWriter fileWriter = new FileWriter("imagenes/ABB" + int_random + ".dot");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(dot.toString());
            printWriter.close();   

            String[] command = {"dot", "-Tpng" ,"imagenes/ABB" + int_random + ".dot", "-o","imagenes/ABB" + int_random + ".png" };
            new ProcessBuilder(command).start();
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
        return "imagenes/ABB" + int_random + ".png";
    }

    

    
}
