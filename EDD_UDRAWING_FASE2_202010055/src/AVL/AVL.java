package AVL;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import Cola.*;
import Lista.*;
import Models.Imagen;

public class AVL {
    private NodoAVL raiz;
    int noImagenes;

    public AVL(){
        raiz = null;
        noImagenes = 0;
    }

    public int noImagenes(){
        return noImagenes;
    }

    public Lista<Imagen> getImagenes(){
        Lista<Imagen> lista = new Lista<Imagen>();
        lista = getImagenesR(raiz, lista);
        return lista;
    }

    public Lista<Imagen> getImagenesR(NodoAVL actual, Lista<Imagen> lista){
        if(actual == null){
            return lista;
        }
        lista.insertar(actual.getImagen());
        lista = getImagenesR(actual.izquierda, lista);
        lista = getImagenesR(actual.derecha, lista);
        return lista;
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
            noImagenes++;
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

    public NodoAVL eliminar(int valor){
        return eliminarR(raiz,valor);
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
            if(actual.izquierda != null){
                cola.enqueue(actual.izquierda);
            }
            if(actual.derecha != null){
                cola.enqueue(actual.derecha);
            }
        }
    }

    public String graficar(){
        StringBuilder dot = new StringBuilder();
        dot.append("digraph G{\n");
        dot.append(String.format("label=\"%s\"\n", "Arbol Imagenes"));
        dot.append("labelloc = \"t\"\n");
        dot = getRaiz().graficar(dot);
        dot.append("}\n");

        int int_random = this.hashCode(); 

        try{
            File anterior = new File("imagenes/AVL" + int_random + ".png");
            if(anterior.isFile()){
                try{
                    anterior.delete();
                }catch (Exception ex){
                    
                }
            }

            FileWriter fileWriter = new FileWriter("imagenes/AVL" + int_random + ".dot");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(dot.toString());
            printWriter.close();   

            String[] command = {"dot", "-Tpng" ,"imagenes/AVL" + int_random + ".dot", "-o","imagenes/AVL" + int_random + ".png" };
            new ProcessBuilder(command).start();
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
        return "imagenes/AVL" + int_random + ".png";
    }

    public String graficar(int imagen){
        StringBuilder dot = new StringBuilder();
        dot.append("digraph G{\n");
        dot.append(String.format("label=\"%s\"\n", "Arbol Imagenes, Capa " + imagen));
        dot.append("labelloc = \"t\"\n");
        dot = getRaiz().graficar(dot, imagen);
        dot.append("}\n");

        int int_random = this.hashCode();

        try{

            File anterior = new File("imagenes/AVL" + int_random + "_capa" + imagen + ".png");
            if(anterior.isFile()){
                try{
                    anterior.delete();
                }catch (Exception ex){
                    
                }
            }


            FileWriter fileWriter = new FileWriter("imagenes/AVL" + int_random + "_capa" + imagen + ".dot");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(dot.toString());
            printWriter.close();   

            String[] command = {"dot", "-Tpng" ,"imagenes/AVL" + int_random + "_capa" + imagen + ".dot", "-o","imagenes/AVL" + int_random + "_capa" + imagen + ".png"};
            new ProcessBuilder(command).start();
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
        return "imagenes/AVL" + int_random + "_capa" + imagen + ".png";
    }


    public String[][] topImagenes(){
        Lista<Imagen> lista  = getImagenes();
        lista.ordenar();
        Nodo<Imagen> aux = lista.getHead();
        int i = 0;
        String[][] data = new String[5][2];
        while(aux != null){
            if(i == 5) break;
            data[i][0] = Integer.toString(aux.getValor().getId());
            data[i][1] =  Integer.toString(aux.getValor().getArbolCapas().largo);
            i++;
            aux = aux.getSiguiente();
        }
        return data;
    }

}
