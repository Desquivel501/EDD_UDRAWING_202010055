package Merkle;

import java.io.FileWriter;
import java.io.PrintWriter;

import org.apache.commons.codec.digest.DigestUtils;

import Cola.Cola;
import Lista.Lista;
import Models.Entrega;
import Program.Program;

public class ArbolMerkle {
    public NodoMerkle raiz;

    public ArbolMerkle(){
        raiz = null;
    }

    public String funcionHash(String cadena){
        String hash = DigestUtils.sha256Hex(cadena);
        return hash;
    }

    public void generarArbol(Lista<Entrega> datos){
        Lista<NodoMerkle> nodos = new Lista<>();

        var aux = datos.getHead();
        while(aux != null){
            var valor = aux.getValor();
            String hash = funcionHash(valor.getDatos());
            nodos.insertar(new NodoMerkle(null, null, hash, valor));
            aux = aux.getSiguiente();
        }
        int nivel = 0;
        Lista<NodoMerkle> padres = new Lista<>();
        while(nodos.getLargo() != 1){
            int contador = 0;
            int largo = nodos.getLargo();

            while(contador < largo){
                NodoMerkle izquierdo = nodos.get(contador);
                NodoMerkle derecho = null;

                if((contador + 1) < largo){
                    derecho = nodos.get(contador + 1);
                }else{
                    derecho = new NodoMerkle(null, null, "-1", null);

                    int i = (nivel%2 == 0) ? nivel+1:nivel;
                    
                    Cola<NodoMerkle> cola = new Cola<>();
                    cola.enqueue(derecho);

                    while(!cola.vacia()){
                        var nodo = cola.dequeue();
                        if(i > 0){
                            System.out.println(i);
                            nodo.getValor().setDerecha(new NodoMerkle(null, null, "-1", null));
                            nodo.getValor().setIzquierda(new NodoMerkle(null, null, "-1", null));
                            cola.enqueue(nodo.getValor().getIzquierda());
                            cola.enqueue(nodo.getValor().getDerecha());
                        }
                        i--;
                    }
                }
                String hashPadre = funcionHash(izquierdo.getHash() + derecho.getHash());
                NodoMerkle nodoPadre = new NodoMerkle(izquierdo,derecho,hashPadre,null);
                padres.insertar(nodoPadre);
                contador += 2;
            }
            nodos = padres;
            padres = new Lista<NodoMerkle>();
            nivel++;
        }
        raiz = nodos.get(0);
    }

    public String graficar(){
        StringBuilder dot = new StringBuilder();
        dot.append("digraph G{\n");
        dot.append(String.format("label=\"%s\"\n", "Arbol Merkle"));
        dot.append("labelloc = \"t\"\n");
        dot.append("node [shape=box]\n");
        dot = raiz.graficar(dot);
        dot.append("}\n");

        int int_random = this.hashCode();

        try{
            FileWriter fileWriter = new FileWriter("imagenes/Merkle" + int_random + ".dot");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(dot.toString());
            printWriter.close();   

            String[] command = {"dot", "-Tpng" ,"imagenes/Merkle" + int_random + ".dot", "-o","imagenes/Merkle" + int_random + ".png" };
            new ProcessBuilder(command).start();
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
        return "imagenes/Merkle" + int_random + ".png";
    }
}
