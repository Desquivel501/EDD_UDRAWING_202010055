package Matriz;

public class ListaEncabezado {
    private NodoE primero;
    private int size;


    public ListaEncabezado() {
        this.primero = null;
    }

    public int size(){
        return size;
    }

    public NodoE getPrimero(){
        return primero;
    }

    public void setEncabezado(NodoE nuevo){
        size++;
        if(primero == null){
            this.primero = nuevo;
            return;
        }
        if(nuevo.getId() < primero.getId()){
            nuevo.setSiguiente(primero);
            primero.setAnterior(nuevo);
            this.primero = nuevo;
        }else{
            NodoE actual = primero;
            while(actual.getSiguiente() != null){

                if(nuevo.getId() < actual.getSiguiente().getId()){
                    nuevo.setSiguiente(actual.getSiguiente());
                    actual.getSiguiente().setAnterior(nuevo);
                    nuevo.setAnterior(actual);
                    actual.setSiguiente(nuevo);
                    break;
                }
                actual = actual.getSiguiente();
            }

            if(actual.getSiguiente() == null){
                actual.setSiguiente(nuevo);
                nuevo.setAnterior(actual);
            }
        }
    }

    public NodoE getEncabezado(int id){
        NodoE actual = this.primero;
        while(actual != null){
            if(actual.getId() == id){
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
}
