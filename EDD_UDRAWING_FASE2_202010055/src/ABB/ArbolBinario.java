package ABB;

import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import Cola.Cola;
import Lista.*;
import Matriz.MatrizCapa;

public class ArbolBinario {
    NodoABB raiz;
    public int largo = 0;
    int profundidad;

    public ArbolBinario(){
        raiz = null;
        profundidad = 0;
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
        if(num == 0) return completa;

        completa = unirPreOrderR(actual.izquierda, completa, num);
        completa = unirPreOrderR(actual.derecha, completa, num);
        return completa;
    }

    //----------------------------------------------------------------------

    public Lista<NodoABB> getNodospRE(){
        Lista<NodoABB> listaRecorrido = new Lista<>();
        listaRecorrido = getNodosR(raiz, listaRecorrido);
        return listaRecorrido;
    }

    private Lista<NodoABB> getNodosR(NodoABB actual , Lista<NodoABB> listaRecorrido){
        if(actual == null){
            return listaRecorrido;
        }
        listaRecorrido.insertar(actual);
        listaRecorrido = getNodosR(actual.izquierda, listaRecorrido);
        listaRecorrido = getNodosR(actual.derecha, listaRecorrido);
        return listaRecorrido;
    }

    public String getPreOrder(){
        Lista<MatrizCapa> listaRecorrido = new Lista<>();
        listaRecorrido = getPreOrderR(raiz, listaRecorrido);
        StringBuilder cadena = new StringBuilder();
        var aux = listaRecorrido.getHead();
        while(aux != null){
            cadena.append(aux.getValor().getId() + ", ");
            aux = aux.getSiguiente();
        }
        cadena.deleteCharAt(cadena.lastIndexOf(","));
        return cadena.toString();
    }



    public ArbolBinario getPreOrder(int num){
        ArbolBinario nuevo = new ArbolBinario();
        Lista<MatrizCapa> listaRecorrido = new Lista<>();
        listaRecorrido = getPreOrderR(raiz, listaRecorrido);
        Nodo<MatrizCapa> aux = listaRecorrido.getHead();
        StringBuilder cadena = new StringBuilder();
        while(aux != null && num > 0){
            cadena.append(aux.getValor().getId() + ", ");
            MatrizCapa mActual = (MatrizCapa)aux.getValor();
            nuevo.insertar(mActual.getId(), mActual);
            aux = aux.getSiguiente();
            num--;
        }
        cadena.deleteCharAt(cadena.lastIndexOf(","));
        JOptionPane.showMessageDialog(null,
            "El recorrido PreOrder fue: " + cadena,
            "Recorrido",
            JOptionPane.INFORMATION_MESSAGE);

        return nuevo;  
    }

    private Lista<MatrizCapa> getPreOrderR(NodoABB actual , Lista<MatrizCapa> listaRecorrido){
        if(actual == null){
            return listaRecorrido;
        }

        listaRecorrido.insertar(actual.capa);
        listaRecorrido = getPreOrderR(actual.izquierda, listaRecorrido);
        listaRecorrido = getPreOrderR(actual.derecha, listaRecorrido);
        return listaRecorrido;
    }

    //--------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------

    public MatrizCapa unirInOrder(MatrizCapa completa, int num){
        Lista<MatrizCapa> listaRecorrido = new Lista<>();
        listaRecorrido = unirInOrderR(raiz, listaRecorrido);
        Nodo<MatrizCapa> aux = listaRecorrido.getHead();
        StringBuilder cadena = new StringBuilder();
        while(aux != null && num > 0){
            cadena.append(aux.getValor().getId() + ", ");
            completa.combinarMatriz((MatrizCapa)aux.getValor());
            aux = aux.getSiguiente();
            num--;
        }
        cadena.deleteCharAt(cadena.lastIndexOf(","));
        JOptionPane.showMessageDialog(null,
            "El recorrido InOrder fue: " + cadena,
            "Recorrido",
            JOptionPane.INFORMATION_MESSAGE);

        return completa;
    }

    public String getInOrder(){
        Lista<MatrizCapa> listaRecorrido = new Lista<>();
        listaRecorrido = unirInOrderR(raiz, listaRecorrido);
        StringBuilder cadena = new StringBuilder();
        var aux = listaRecorrido.getHead();
        while(aux != null){
            cadena.append(aux.getValor().getId() + ", ");
            aux = aux.getSiguiente();
        }
        cadena.deleteCharAt(cadena.lastIndexOf(","));
        return cadena.toString();
    }

    public ArbolBinario getInOrder(int num){
        ArbolBinario nuevo = new ArbolBinario();
        Lista<MatrizCapa> listaRecorrido = new Lista<>();
        listaRecorrido = unirInOrderR(raiz, listaRecorrido);
        Nodo<MatrizCapa> aux = listaRecorrido.getHead();
        while(aux != null && num > 0){
            MatrizCapa mActual = (MatrizCapa)aux.getValor();
            nuevo.insertar(mActual.getId(), mActual);
            aux = aux.getSiguiente();
            num--;
        }
        return nuevo;
    }

    private Lista<MatrizCapa> unirInOrderR(NodoABB actual , Lista<MatrizCapa> listaRecorrido){
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
        Lista<MatrizCapa> listaRecorrido = new Lista<>();
        listaRecorrido = unirPostOrderR2(raiz, listaRecorrido);
        Nodo<MatrizCapa> aux = listaRecorrido.getHead();
        StringBuilder cadena = new StringBuilder();
        while(aux != null && num > 0){
            cadena.append(aux.getValor().getId() + ", ");
            completa.combinarMatriz(aux.getValor());
            aux = aux.getSiguiente();
            num--;
        }
        cadena.deleteCharAt(cadena.lastIndexOf(","));
        JOptionPane.showMessageDialog(null,
            "El recorrido PostOrder fue: " + cadena,
            "Recorrido",
            JOptionPane.INFORMATION_MESSAGE);

        return completa;
    }

    public ArbolBinario getPostOrder(int num){
        ArbolBinario nuevo = new ArbolBinario();
        Lista<MatrizCapa> listaRecorrido = new Lista<>();
        listaRecorrido = unirPostOrderR2(raiz, listaRecorrido);
        Nodo<MatrizCapa> aux = listaRecorrido.getHead();
        while(aux != null && num > 0){
            MatrizCapa mActual = (MatrizCapa)aux.getValor();
            nuevo.insertar(mActual.getId(), mActual);
            aux = aux.getSiguiente();
            num--;
        }
        return nuevo;
    }

    public String getPostOrder(){
        Lista<MatrizCapa> listaRecorrido = new Lista<>();
        listaRecorrido = unirPostOrderR2(raiz, listaRecorrido);

        StringBuilder cadena = new StringBuilder();
        var aux = listaRecorrido.getHead();
        while(aux != null){
            cadena.append(aux.getValor().getId() + ", ");
            aux = aux.getSiguiente();
        }
        cadena.deleteCharAt(cadena.lastIndexOf(","));
        return cadena.toString();
    }

    private Lista<MatrizCapa> unirPostOrderR2(NodoABB actual , Lista<MatrizCapa> listaRecorrido){
        if(actual == null){
            return listaRecorrido;
        }
        listaRecorrido = unirPostOrderR2(actual.izquierda, listaRecorrido);
        listaRecorrido = unirPostOrderR2(actual.derecha, listaRecorrido);
        listaRecorrido.insertar(actual.capa);
        return listaRecorrido;
    }

    public Lista<NodoABB> getNodos(){
        Lista<NodoABB> listaRecorrido = new Lista<>();
        listaRecorrido = getNodosR(raiz, listaRecorrido);
        return listaRecorrido;
    }

    //--------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------

    public MatrizCapa unirLevel(MatrizCapa completa){
        if(raiz == null)return completa;
        Lista<MatrizCapa> listaRecorrido = new Lista<>();
        Cola<NodoABB> cola = new Cola<>();
        cola.enqueue(raiz);
        while(!cola.vacia()){
            NodoABB actual = cola.dequeue().getValor();
            if(actual.capa != null){
                listaRecorrido.insertar(actual.capa);
                if(actual.izquierda != null){
                    cola.enqueue(actual.izquierda);
                }
                if(actual.derecha != null){
                    cola.enqueue(actual.derecha);
                }
            }
        }

        Nodo<MatrizCapa> aux = listaRecorrido.getHead();
        while(aux != null){
            completa.combinarMatriz(aux.getValor());
            aux = aux.getSiguiente();
        }

        return completa;
    }

    public int getProfundidad(){
        if(this.raiz == null){
            return 0;
        }else{
            return altura(raiz)+1;
        }
    }

    public int altura(NodoABB actual){
        if(actual == null) return 0;
        if(actual.izquierda != null || actual.derecha != null){
            return Math.max(altura(actual.izquierda),altura(actual.derecha))+1;
        }else{
            return 0;
        }
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
