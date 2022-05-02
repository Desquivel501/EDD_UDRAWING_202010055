package Merkle;

import Models.Entrega;

public class NodoMerkle implements Comparable<NodoMerkle> {
    private NodoMerkle izquierda, derecha;
    private String hash;
    private Entrega dato;


    public NodoMerkle(){
    }

    public NodoMerkle(NodoMerkle izquierda, NodoMerkle derecha, String hash, Entrega dato) {
        this.izquierda = izquierda;
        this.derecha = derecha;
        this.hash = hash;
        this.dato = dato;
    }

    public NodoMerkle getIzquierda() {
        return this.izquierda;
    }

    public void setIzquierda(NodoMerkle izquierda) {
        this.izquierda = izquierda;
    }

    public NodoMerkle getDerecha() {
        return this.derecha;
    }

    public void setDerecha(NodoMerkle derecha) {
        this.derecha = derecha;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Entrega getDato() {
        return this.dato;
    }

    public void setDato(Entrega dato) {
        this.dato = dato;
    }

    @Override
    public int compareTo(NodoMerkle o) {
        return 0;
    }
    

    public StringBuilder graficar(StringBuilder dot){
        int idPadre = this.hashCode();
        dot.append(String.format("Nodo%d[label=\"%s\" shape=ellipse];\n",idPadre,this.hash));
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
