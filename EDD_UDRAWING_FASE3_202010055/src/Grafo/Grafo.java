package Grafo;

import Lista.*;
import Models.*;

public class Grafo {
    private Lista<Lugar> listaLugares;
    private Lista<Ruta> listaRutas;

    public Grafo() {
        listaLugares = new Lista<Lugar>();
        listaRutas = new Lista<Ruta>();
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

    public void insertarRuta(int id_inicio, int id_final, int peso){
        Lugar inicio = buscarLugar(id_inicio);
        Lugar final_ = buscarLugar(id_final);

        if(inicio == null){
            return;
        } else if(final_ == null){
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

    public void graficarGrafo(){
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
    }

    public void graficarLista(){
        StringBuilder dot = new StringBuilder();
        dot.append("digraph G{\n");
        dot.append(String.format("label=\"%s\"\n", "Lista de Adyacencia"));
        dot.append("labelloc = \"t\"\n");
        dot.append("rankdir = \"LR\"\n");
        dot.append("node [shape=record]\n");
        dot.append("edge[tailclip=false, arrowtail=dot, dir=both, arrowhead=normal]\n");

        StringBuilder lista = new StringBuilder();
        lista.append("lista[label=\"");

        StringBuilder conexion = new StringBuilder();

        var lugar = listaLugares.getHead();
        while(lugar != null){
            lista.append(String.format("{%d | <f%d>}", lugar.getValor().getId(),lugar.getValor().getId()));
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

            conexion.append(String.format("lista:f%d:c -> rutas%d\n", lugar.getValor().getId(),lugar.getValor().getId(),lugar.getValor().getId()));

            if(lugar.getSiguiente() != null){
                lista.append("|");
            }
            lugar = lugar.getSiguiente();
        }
        lista.append("\"]\n");
        dot.append(lista);
        dot.append(conexion);
        dot.append("}\n");
        System.out.println(dot);
    }
}
