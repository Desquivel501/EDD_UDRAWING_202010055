package AVL;

import Models.Imagen;

public class NodoAVL{
    int valor;
    int alto;
    NodoAVL derecha;
    NodoAVL izquierda;
    Imagen imagen;

    public NodoAVL(int valor, Imagen imagen ){
        this.valor = valor;
        alto = 0;
        derecha = null;
        izquierda = null;
        this.imagen = imagen;
    }

    public NodoAVL(int valor, int alto, NodoAVL derecha, NodoAVL izquierda, Imagen imagen) {
        this.valor = valor;
        this.alto = alto;
        this.derecha = derecha;
        this.izquierda = izquierda;
        this.imagen = imagen;
    }

    public int getValor() {
        return this.valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getAlto() {
        return this.alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public NodoAVL getDerecha() {
        return this.derecha;
    }

    public void setDerecha(NodoAVL derecha) {
        this.derecha = derecha;
    }

    public NodoAVL getIzquierda() {
        return this.izquierda;
    }

    public void setIzquierda(NodoAVL izquierda) {
        this.izquierda = izquierda;
    }

    public Imagen getImagen() {
        return this.imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public NodoAVL valor(int valor) {
        setValor(valor);
        return this;
    }

    public NodoAVL alto(int alto) {
        setAlto(alto);
        return this;
    }

    public NodoAVL derecha(NodoAVL derecha) {
        setDerecha(derecha);
        return this;
    }

    public NodoAVL izquierda(NodoAVL izquierda) {
        setIzquierda(izquierda);
        return this;
    }

    public NodoAVL imagen(Imagen imagen) {
        setImagen(imagen);
        return this;
    }

    public StringBuilder graficar(StringBuilder dot){
        int idPadre = this.hashCode();
        dot.append(String.format("Nodo%d[label=\"%d\"  shape=box];\n",idPadre,this.valor));
       

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

    public StringBuilder graficar(StringBuilder dot, int imagen){
        int idPadre = this.hashCode();
        dot.append(String.format("Nodo%d[label=\"%d\"  shape=box];\n",idPadre,this.valor));
       

        if(this.izquierda != null){
            int idIz = izquierda.hashCode();
            dot = izquierda.graficar(dot, imagen);
            dot.append(String.format("Nodo%d -> Nodo%d;\n", idPadre, idIz));
        }
        if(this.valor == imagen){
            if (this.imagen.getArbolCapas().getRaiz() != null){
                dot = this.imagen.getArbolCapas().getRaiz().graficar(dot);
                dot.append(String.format("Nodo%d -> Nodo%d;\n", idPadre, this.imagen.getArbolCapas().getRaiz().hashCode()));
            }
    
        }

        if(this.derecha != null){
            int idDer = derecha.hashCode();
            dot = derecha.graficar(dot, imagen);
            dot.append(String.format("Nodo%d -> Nodo%d;\n", idPadre, idDer));
        }
        return dot;
    }

    
}