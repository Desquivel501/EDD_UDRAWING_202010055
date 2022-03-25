package ArbolB;

public class Pagina {
    boolean hoja;
    int noNodos;
    NodoB head;

    public Pagina(){
        this.head = null;
        this.noNodos = 0;
        this.hoja = true;
    }

    public Pagina(NodoB nodo){
        this.head = null;
        this.noNodos = 0;
        this.hoja = true;
        insertar(nodo);
    }

    public NodoB getHead(){
        return head;
    }

    public void insertar(NodoB nuevo){
        if(head == null){
            head = nuevo;
            noNodos++;
            return;
        }
        
        NodoB aux = head;
        while(head != null){
            if(aux.id.equals(nuevo.id)){
                System.out.println("Nodo ya existe");
                return;
            }
            
            if(aux.id > nuevo.id){
                if(aux == head){
                    nuevo.siguiente = aux;
                    aux.anterior = nuevo;

                    aux.izquierda = nuevo.derecha;
                    nuevo.derecha = null;
                    head = nuevo;
                    noNodos++;
                    return;
                }   
                nuevo.siguiente = aux;
                aux.izquierda = nuevo.derecha;
                nuevo.derecha = null;
                nuevo.anterior = aux.anterior;
                aux.anterior.siguiente = nuevo;
                aux.anterior = nuevo;
                noNodos++;
                return;
            }
            if(aux.siguiente == null){
                aux.siguiente = nuevo;
                nuevo.anterior = aux;
                noNodos++;
                return;
            }

            aux = aux.siguiente;

        }
    }

    public NodoB buscar(Long id){
        NodoB aux = head;
        while(head != null){
            if(aux.id.equals(id)){
                System.out.println("Found: Cliente " + aux.cliente.getNombre());
                return aux;
            }

            if(id < aux.id){
                if(!this.hoja){
                    if(aux.izquierda != null){
                        return aux.izquierda.buscar(id);
                    }else{
                        return null;
                    }
                }else{
                    return null;
                }
            }
            
            if(aux.siguiente == null){
                if(id > aux.id){
                    if(!this.hoja){
                        if(aux.izquierda != null){
                            return aux.derecha.buscar(id);
                        }else{
                            return null;
                        }
                    }else{
                        return null;
                    }
                }
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    public NodoB buscarNombre(String nombre){
        NodoB aux = head;
        NodoB nodo = null;
        while(aux != null){
            if(aux.getCliente().getNombre().equals(nombre)){
                System.out.println("Usuario: " + nombre);
                return aux;
            }   

            if(aux.izquierda != null){
                nodo =  aux.izquierda.buscarNombre(nombre);
                if(nodo != null){
                    return nodo;
                }
            }
            if(aux.derecha != null){
                nodo = aux.izquierda.buscarNombre(nombre);
                if(nodo != null){
                    return nodo;
                }
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    public StringBuilder graficarPagina(StringBuilder dot){
        NodoB aux = head;
        int cont = 0;
        StringBuilder nodo = new StringBuilder();
        nodo.append(String.format("nodo%d[label=\"",this.hashCode()));

        while(aux != null){
            nodo.append(String.format("<p%d>|%d|",cont,aux.cliente.getDpi()));
            if(aux.izquierda != null){
                dot = aux.izquierda.graficarPagina(dot);
                dot.append(String.format("nodo%d:p%d -> nodo%d\n", this.hashCode(), cont, aux.izquierda.hashCode()));
            }
            cont++;

            if(aux.siguiente == null){
                nodo.append(String.format("<p%d>\"]\n", cont));
                if(aux.derecha != null){
                    dot = aux.derecha.graficarPagina(dot);
                    dot.append(String.format("nodo%d:p%d -> nodo%d\n", this.hashCode(), cont, aux.derecha.hashCode()));
                }

            } 
            aux = aux.siguiente;
        }
        dot.append(nodo);
        return dot;
    }

}
