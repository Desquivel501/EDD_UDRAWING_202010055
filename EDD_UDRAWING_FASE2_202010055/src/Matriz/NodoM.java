package Matriz;

public class NodoM {
    private NodoM arriba;
    private NodoM abajo;
    private NodoM derecha;
    private NodoM izquierda;
    private String valor;
    private int columna;
    private int fila;


    public NodoM(int columna, int fila, String valor) {
        this.columna = columna;
        this.fila = fila;
        this.valor = valor;
        this.arriba = null;
        this.abajo = null;
        this.izquierda = null;
        this.derecha = null;
    }

    public NodoM getArriba() {
        return this.arriba;
    }

    public void setArriba(NodoM arriba) {
        this.arriba = arriba;
    }

    public NodoM getAbajo() {
        return this.abajo;
    }

    public void setAbajo(NodoM abajo) {
        this.abajo = abajo;
    }

    public NodoM getDerecha() {
        return this.derecha;
    }

    public void setDerecha(NodoM derecha) {
        this.derecha = derecha;
    }

    public NodoM getIzquierda() {
        return this.izquierda;
    }

    public void setIzquierda(NodoM izquierda) {
        this.izquierda = izquierda;
    }

    public String getValor() {
        return this.valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getColumna() {
        return this.columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getFila() {
        return this.fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }



}
