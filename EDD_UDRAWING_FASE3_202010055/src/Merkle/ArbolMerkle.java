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
        double res = potencia(nodos.getLargo());
        for(int i = 0; i < res; i++){
            nodos.insertar( new NodoMerkle(null, null, "-1", null));
        }


        Lista<NodoMerkle> padres = new Lista<>();
        while(nodos.getLargo() != 1){
            int contador = 0;
            int largo = nodos.getLargo();

            while(contador < largo){
                NodoMerkle izquierdo = nodos.get(contador);
                NodoMerkle derecho =  nodos.get(contador + 1);

                String hashPadre;
                if(izquierdo.getHash() == "-1" && derecho.getHash() == "-1"){
                    hashPadre = "-1";
                }else{
                    hashPadre = funcionHash(izquierdo.getHash() + derecho.getHash());
                }

                NodoMerkle nodoPadre = new NodoMerkle(izquierdo,derecho,hashPadre,null);
                padres.insertar(nodoPadre);
                contador += 2;
            }
            nodos = padres;
            padres = new Lista<NodoMerkle>();
        }
        raiz = nodos.get(0);
    }

    public double potencia(int num){
        int i = 0;
        while(true){
            if(Math.pow(2,i) >= num){
                return Math.pow(2,i) - num;
            }
            i++;
        }
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
