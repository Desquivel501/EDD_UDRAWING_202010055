package ArbolB;

import Models.Cliente;

public class ArbolB {
    private int orden = 5;
    Pagina raiz;

    public ArbolB(){
        this.raiz = null;
    }

    public void insertar(int id, Cliente cliente){
        NodoB nuevo = new NodoB(id, cliente);
        if(raiz == null){
            raiz = new Pagina(nuevo);
        }else{
            NodoB nodo = insertarPagina(nuevo, raiz);
            if(nodo != null){
                raiz = new Pagina();
                raiz.insertar(nodo);
                raiz.hoja = false;
            }
        }
    }

    public void buscar(int id){
        raiz.buscar(id);
    }


    private NodoB insertarPagina(NodoB nodo, Pagina pagina){
        if(pagina.hoja){
            pagina.insertar(nodo);
            if(pagina.noNodos == orden){
                return dividir(pagina);
            }else{
                return null;
            }
        }

        NodoB aux = pagina.head;
        while(aux != null){
            if(nodo.id == aux.id){
                System.out.println("Nodo repetido");
                return null;
            }
            if(nodo.id < aux.id){
                NodoB nodoAux = insertarPagina(nodo, aux.izquierda);
                if(nodoAux != null){

                    pagina.insertar(nodoAux);
                    if(pagina.noNodos == this.orden){
                        return dividir(pagina);
                    }
                }
                return null;
            }else if(aux.siguiente == null){
                NodoB nodoAux = insertarPagina(nodo, aux.derecha);
                if(nodoAux != null){
                    pagina.insertar(nodoAux);
                    if(pagina.noNodos == this.orden){
                        return dividir(pagina);
                    }
                }
                return null;
            }
            aux = aux.siguiente;
        }

        return null;
    }

    private NodoB dividir(Pagina pagina){
        int val = -1;
        NodoB temp, nuevo;
        NodoB aux = pagina.head;
        Pagina paginaD = new Pagina();
        Pagina paginaI = new Pagina();
        Cliente cliente = null;
        int i = 0;

        while(aux != null){
            i++;
            if(i < 3){
                temp = new NodoB(aux.id, aux.cliente);
                temp.izquierda = aux.izquierda;
                if(i == 2){
                    temp.derecha = aux.siguiente.izquierda;
                }else{
                    temp.derecha = aux.derecha;
                }
                if(temp.derecha != null && temp.izquierda != null) paginaI.hoja = false;
                paginaI.insertar(temp);

            }else if(i == 3){
                val = aux.id;
                cliente = aux.getCliente();
            }else{
                temp = new NodoB(aux.id, aux.cliente);
                temp.izquierda = aux.izquierda;
                temp.derecha = aux.derecha;

                if(temp.derecha != null && temp.izquierda != null) paginaD.hoja = false;
                paginaD.insertar(temp);

            }
            aux = aux.siguiente;
        }
        nuevo = new NodoB(val, cliente, paginaD, paginaI);
        return nuevo;
        
    }

    public void graficar(){
        StringBuilder dot = new StringBuilder();
        dot.append("digraph G {\n");
        dot.append("node[shape=record]\n");
        dot = this.raiz.graficarPagina(dot);
        dot.append("}\n");

        System.out.println(dot);
    }


}
