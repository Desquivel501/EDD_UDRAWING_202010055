package Grafo;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import Cola.ColaP;
import Lista.*;
import Models.*;

public class Grafo {
    private Lista<Lugar> listaLugares;
    private Lista<Ruta> listaRutas;

    public Grafo() {
        listaLugares = new Lista<Lugar>();
        listaRutas = new Lista<Ruta>();
    }

    public Lista<Lugar> getLugares(){
        return listaLugares;
    }

    public boolean valido(){
        return !(listaLugares.vacia() && listaRutas.vacia());
    }

    public void imprimirLugares(){
        listaLugares.imprimir();
    }

    public void insertarLugar(Lugar nuevo){
        if(buscarLugar(nuevo.getId()) != null) return;
        listaLugares.insertar(nuevo);
    }

    public Lugar buscarLugar(int id){
        Nodo<Lugar> aux = listaLugares.getHead();
        while(aux != null){
            if(aux.getValor().getId() == id){
                return aux.getValor();
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    public Lugar buscarLugar(String nombre){
        Nodo<Lugar> aux = listaLugares.getHead();
        while(aux != null){
            if(aux.getValor().getNombre().equals(nombre)){
                return aux.getValor();
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    public void insertarRuta(int id_inicio, int id_final, int peso){
        Lugar inicio = buscarLugar(id_inicio);
        Lugar final_ = buscarLugar(id_final);

        if(inicio == null || final_ == null){
            return;
        }
        if(buscarRuta(id_inicio, id_final) != null) {
            return;
        } 
        Ruta ruta = new Ruta(inicio,final_,peso);
        listaRutas.insertar(ruta);
    }

    public Ruta buscarRuta(int id_inicio, int id_final){
        Nodo<Ruta> aux = listaRutas.getHead();
        while(aux != null){
            var actual = aux.getValor();
            if(actual.getInicio().getId() == id_inicio && actual.getFinal_().getId() == id_final){
                return actual;
            }
            if(actual.getInicio().getId() == id_final && actual.getFinal_().getId() == id_inicio){
                return actual;
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    public Lista_recorrido dijkstra(int inicio, int final_, boolean mostrar){

        if(buscarLugar(inicio) == null || buscarLugar(final_) == null)  return null;

        Dist_ dist = new Dist_();
        Prev prev = new Prev();
        ColaP cola = new ColaP();
        
        var lugar = listaLugares.getHead();
        while(lugar != null){
            dist.insertar(new Dist(lugar.getValor().getId(), Integer.MAX_VALUE));
            prev.insertar(lugar.getValor().getId(), Integer.MIN_VALUE);
            cola.insertar(new Dist(lugar.getValor().getId(), Integer.MAX_VALUE));
            lugar = lugar.getSiguiente();
        }
        dist.actualizar(inicio, 0);
        cola.actualizar(inicio, 0);

        while(!cola.vacia()){
            
            var actual = cola.dequeue().getValor();
            var lugar_sig = listaLugares.getHead();
            while(lugar_sig != null){
                
                var ruta_posible = buscarRuta(actual.id, lugar_sig.getValor().getId());
                if(ruta_posible != null){
                    
                    int dist_alt = actual.dist + ruta_posible.getPeso();
                    if(dist_alt < 0) dist_alt = Integer.MAX_VALUE;
                    int dist_anterior = dist.buscar(lugar_sig.getValor().getId()).dist;

                    if(dist_alt < dist_anterior){
                        dist.actualizar(lugar_sig.getValor().getId(), dist_alt);
                        cola.actualizar(lugar_sig.getValor().getId(), dist_alt);
                        prev.actualizar(lugar_sig.getValor().getId(), actual.id);
                    }
                }
                lugar_sig = lugar_sig.getSiguiente();
            }
        }  

        if(dist.buscar(final_).dist == Integer.MAX_VALUE || prev.buscar(final_).prev == Integer.MIN_VALUE){
            System.out.println("Nodo destino no pudo ser alcanzado");
            return null;
        }

        System.out.println(String.format("largo de %d -> %d = %d",inicio,final_, dist.buscar(final_).dist));

        var lugar_c = buscarLugar(final_);
        Lista<Lugar> camino = new Lista<Lugar>();
        Lista_recorrido lista = new Lista_recorrido();
        lista.setTiempo(dist.buscar(final_).dist);

        while(lugar_c != null){
            camino.insertar_inicio(lugar_c);
            lista.insertar(lugar_c.getId(), lugar_c.getNombre());

            var prev_id = prev.buscar(lugar_c.getId());
            if(prev_id == null) break;
            lugar_c = buscarLugar(prev_id.prev);
        }
       String titulo = String.format("Ruta de %d -> %d",inicio,final_);
        
        var aux = camino.getHead();
        StringBuilder camino_str = new StringBuilder();
        while(aux != null){
            camino_str.append(aux.getValor().getId());
            if(aux.getSiguiente() != null) camino_str.append("->");
            aux = aux.getSiguiente();
        }
        
        camino_str.append(String.format("\nTiempo utilizado: %d minutos",dist.buscar(final_).dist));
        System.out.println(camino_str);

       if(mostrar){
            JOptionPane.showMessageDialog(null,
            camino_str,
            titulo,
            JOptionPane.INFORMATION_MESSAGE);
       }

        return lista;
    }

    public String graficarGrafo(){
        StringBuilder dot = new StringBuilder();
        dot.append("digraph G{\n");
        dot.append(String.format("label=\"%s\"\n", "Grafo de Rutas"));
        dot.append("labelloc = \"t\"\n");
        dot.append("edge[arrowhead=\"none\"]\n");

        Nodo<Ruta> aux = listaRutas.getHead();
        while(aux != null){
            var actual = aux.getValor();
            dot.append(String.format("%d -> %d[label=\" %s\"]\n", actual.getInicio().getId(), actual.getFinal_().getId(), actual.getPeso()));
            aux = aux.getSiguiente();
        }
        dot.append("}\n");
        System.out.println(dot);

        return generarImagen(dot.toString(),"Grafo");
    }

    public String graficarLista(){
        StringBuilder dot = new StringBuilder();
        dot.append("digraph G{\n");
        dot.append(String.format("label=\"%s\"\n", "Lista de Adyacencia"));
        dot.append("labelloc = \"t\"\n");
        dot.append("rankdir = \"LR\"\n");
        dot.append("node [shape=record]\n");
        dot.append("edge[tailclip=false, arrowtail=dot, dir=both, arrowhead=normal]\n");

        StringBuilder lista = new StringBuilder();
        // lista.append("lista[label=\"");

        StringBuilder conexion = new StringBuilder();

        var lugar = listaLugares.getHead();
        while(lugar != null){
            dot.append(String.format("nodo%d[label=\"{ %d | <f%d>}\"] ", lugar.getValor().getId(),lugar.getValor().getId(),lugar.getValor().getId()));
            var ruta = listaRutas.getHead();

            StringBuilder sub_lista = new StringBuilder();
            sub_lista.append(String.format("rutas%d[label=\"{", lugar.getValor().getId()));

            while(ruta != null){
                if(lugar.getValor().getId() == ruta.getValor().getInicio().getId()){
                    sub_lista.append(ruta.getValor().getFinal_().getId());
                    sub_lista.append("|");
                    ruta = ruta.getSiguiente();
                    continue;
                }
                if(lugar.getValor().getId() == ruta.getValor().getFinal_().getId()){
                    sub_lista.append(ruta.getValor().getInicio().getId());
                    sub_lista.append("|");
                    ruta = ruta.getSiguiente();
                    continue;
                }
                ruta = ruta.getSiguiente();
            }

            sub_lista.deleteCharAt(sub_lista.lastIndexOf("|"));
            sub_lista.append("}\"]\n");
            dot.append(sub_lista);

            conexion.append(String.format("nodo%d:f%d:c -> rutas%d\n", lugar.getValor().getId(),lugar.getValor().getId(),lugar.getValor().getId()));

            if(lugar.getSiguiente() != null){
                // lista.append("|");
            }
            lugar = lugar.getSiguiente();
        }
        // lista.append("\"]\n");
        dot.append(lista);
        dot.append(conexion);
        dot.append("}\n");
        System.out.println(dot);

        return generarImagen(dot.toString(),"Lista");
    }

    private String generarImagen(String dot, String nombre){
        String nombre_return  =  "imagenes/" + nombre  + ".png";
        try {

            File anterior = new File(nombre_return);
            if(anterior.isFile()){
                try{
                    anterior.delete();
                }catch (Exception ex){
                    
                }
            }

            FileWriter fileWriter = new FileWriter("imagenes/" + nombre  + ".dot");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(dot);
            printWriter.close();

            String[] command = {"dot", "-Tpng","imagenes/" + nombre  + ".dot", "-o", "imagenes/" + nombre  + ".png" };
            new ProcessBuilder(command).start();
        }catch (Exception e){
            e.printStackTrace();
            nombre_return = null;
        }

        return nombre_return;
    }
}


class Prev {
    public nodoP head;
    public nodoP tail;
    public int prev;

    public Prev(){
        head = null;
        tail = null;
    }

    public void insertar(int ini, int prev){
        nodoP nuevo = new nodoP(ini, prev);
        if(this.head == null){
            this.head = nuevo;
            this.tail = nuevo;
            return;
        }
        this.tail.siguiente = nuevo;
        this.tail = nuevo;
    }

    public nodoP buscar(int ini){
        var aux = this.head;
        while(aux != null){
            if(aux.ini == ini){
                return aux;
            }
            aux = aux.siguiente;
        }
        return null;
    }

    public void actualizar(int ini, int prev){
        var aux = this.head;
        while(aux != null){
            if(aux.ini == ini){
                aux.prev = prev;
                return;
            }
            aux = aux.siguiente;
        }
    }

}

class nodoP{
    public int ini;
    public int prev;
    public nodoP siguiente;

    public nodoP(int ini, int prev){
        this.ini = ini;
        this.prev = prev;
    }
}

class Dist_ {
    public nodoD head;
    public nodoD tail;
    public int prev;

    public Dist_(){
        head = null;
        tail = null;
    }

    public void insertar(Dist valor){
        nodoD nuevo = new nodoD(valor);
        if(this.head == null){
            this.head = nuevo;
            this.tail = nuevo;
            return;
        }
        this.tail.siguiente = nuevo;
        this.tail = nuevo;
    }

    public Dist buscar(int id){
        var aux = this.head;
        while(aux != null){
            if(aux.valor.id == id){
                return aux.valor;
            }
            aux = aux.siguiente;
        }
        return null;
    }

    public void actualizar(int id, int dist){
        var aux = this.head;
        while(aux != null){
            if(aux.valor.id == id){
                aux.valor.dist = dist;
                return;
            }
            aux = aux.siguiente;
        }
    }

}

class nodoD{
    public Dist valor;
    public nodoD siguiente;

    public nodoD(Dist valor){
        this.valor = valor;
    }
}

