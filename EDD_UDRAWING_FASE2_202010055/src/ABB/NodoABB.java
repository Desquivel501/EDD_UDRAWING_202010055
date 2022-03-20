package ABB;

import Matriz.MatrizCapa;

public class NodoABB{
    int valor;
    NodoABB izquierda;
    NodoABB derecha;
    MatrizCapa capa;

    NodoABB(int valor, MatrizCapa capa){
        this.valor = valor;
        this.capa = capa;
        izquierda = null;
        derecha = null;
    }

    public StringBuilder graficar(StringBuilder dot){
        int idPadre = this.hashCode();
        dot.append(String.format("Nodo%d[label=\"%d\" shape=ellipse];\n",idPadre,this.valor));
        if(this.izquierda != null){
            int idIz = izquierda.hashCode();
            dot = izquierda.graficar(dot);
            dot.append(String.format("Nodo%d -> Nodo%d;\n", idPadre, idIz));
        }
        if(this.derecha != null){
            int idDer = derecha.hashCode();
            dot = derecha.graficar(dot);
            dot.append(String.format("Nodo%d -> Nodo%d;\n", idPadre, idDer));
        }
        return dot;
    }
}