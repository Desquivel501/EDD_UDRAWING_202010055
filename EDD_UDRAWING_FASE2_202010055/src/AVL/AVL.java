package AVL;

import javax.management.RuntimeErrorException;

class Nodo{
    int valor;
    int alto;
    Nodo derecha;
    Nodo izquierda;

    public Nodo(int valor){
        this.valor = valor;
        alto = 0;
        derecha = null;
        izquierda = null;
    }
}

public class AVL {
    private Nodo raiz;

    public AVL(){
        raiz = null;
    }

    public void actualizarAltura(Nodo actual){
        actual.alto = Math.max(altura(actual.izquierda), altura(actual.derecha));
    }

    private int altura(Nodo actual){
        if(actual == null){
            return 0;
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
        actualizarAltura(n1);
        actualizarAltura(n);
        return n1;
    }

    private Nodo rotacionIzquierda(Nodo n){
        Nodo n1 = n.derecha;
        Nodo n2 = n1.izquierda;
        n1.izquierda = n;
        n.derecha = n2;
        actualizarAltura(n1);
        actualizarAltura(n);
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

    private Nodo insertarR(Nodo n, int valor){
        if(n == null){
            return new Nodo(valor);
        }
        else if(n.valor > valor){
            n.izquierda = insertarR(n.izquierda, valor);
        }else if(n.valor < valor){
            n.derecha = insertarR(n.derecha, valor);
        }else{
            return n;
        }
        return balancear(n);
    }

    public Nodo insertar(int valor){
        if(this.raiz == null){
            Nodo nuevo = new Nodo(valor);
            raiz = nuevo;
            return nuevo;
        }else{
            Nodo n = insertarR(raiz, valor);
            return n;
        }
    }
}
