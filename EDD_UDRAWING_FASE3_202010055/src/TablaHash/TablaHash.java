package TablaHash;

import Models.Mensajero;

public class TablaHash {
    static final int SIZE = 29;
    private Mensajero []tabla;
    private int noElementos;

    public TablaHash(){
        tabla  = new Mensajero[SIZE];
        for(int i = 0; i < SIZE; i++){
            tabla[i] = null;
        }
        this.noElementos = 0;
    }
    
    public static int indice(long llv){
        long v = llv % SIZE;
        return (int)v;
    }

    public void insertar(Mensajero valor){
        int pos = indice(valor.getDpi());
        tabla[pos] = valor;
        noElementos++;
    }

    // private int colision(int i){
    //     int s = 
    // }

    public void imprimir(){
        for(int i = 0; i < SIZE; i++){
            if(tabla[i] != null){
                System.out.println(i + " -> " + tabla[i].getNombres());
            }else{
                // System.out.println(i + " -> null");
            }
            
        }
    }


}
