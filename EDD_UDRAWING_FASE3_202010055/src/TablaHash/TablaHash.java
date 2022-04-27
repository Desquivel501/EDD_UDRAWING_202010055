package TablaHash;

import Models.Mensajero;

public class TablaHash {
    static int SIZE = 37;
    private Mensajero []tabla;
    private int noElementos;

    public TablaHash(){
        tabla  = new Mensajero[SIZE];
        for(int i = 0; i < SIZE; i++){
            tabla[i] = null;
        }
        this.noElementos = 0;
    }

    public Mensajero[] getTabla(){
        return tabla;
    }
    
    public static int indice(long llv){
        long v = llv % SIZE;
        return (int)v;
    }

    public void insertar(Mensajero valor){
        System.out.println(".");
        int pos = indice(valor.getDpi());
        if(tabla[pos] != null){
            pos = colision(valor.getDpi(), pos);
        }
        tabla[pos] = valor;
        noElementos++;

        if(noElementos > SIZE*0.75) resize();

    }

    private void resize(){
        SIZE = SIZE * 2;

        Mensajero aux[] = new Mensajero[SIZE];

        for(int i = 0; i < SIZE; i++){
            aux[i] = null;
        }

        for(int i = 0; i < tabla.length; i++){
            aux[i] = tabla[i];
        }

        tabla = aux;
    }

    private int colision(long llv, int i){
        
        while(tabla[i] != null){
            System.out.println("----------------------");
            long l = ((llv % 7) + 1) + i;
            i = (int) l % SIZE;
        }
        return i;
    }

    public void imprimir(){
        for(int i = 0; i < SIZE; i++){
            if(tabla[i] != null){
                System.out.println(i + " -> " + tabla[i].getNombres());
            }else{
                // System.out.println(i + " -> null");
            }
        }
    }

    public Mensajero buscar(long dpi){
        for(int i = 0; i < tabla.length; i++){
            if(tabla[i].getDpi().equals(dpi)){
                return tabla[i];
            }
        }
        return null;
    }

}
