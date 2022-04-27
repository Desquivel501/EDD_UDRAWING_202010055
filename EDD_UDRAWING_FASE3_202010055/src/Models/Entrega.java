package Models;

import Lista.Lista;
import Lista.Lista_recorrido;

public class Entrega implements Comparable<Entrega>{
    private int id_inicio;
    private int id_destino;
    private String fecha;
    private Cliente cliente;
    private Mensajero mensajero;
    private Lista_recorrido camino;


    public Entrega(int id_inicio, int id_destino, String fecha, Cliente cliente, Mensajero mensajero, Lista_recorrido camino) {
        this.id_inicio = id_inicio;
        this.id_destino = id_destino;
        this.fecha = fecha;
        this.cliente = cliente;
        this.mensajero = mensajero;
        this.camino = camino;
    }

    public int getId_inicio() {
        return this.id_inicio;
    }

    public void setId_inicio(int id_inicio) {
        this.id_inicio = id_inicio;
    }

    public int getId_destino() {
        return this.id_destino;
    }

    public void setId_destino(int id_destino) {
        this.id_destino = id_destino;
    }

    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Mensajero getMensajero() {
        return this.mensajero;
    }

    public void setMensajero(Mensajero mensajero) {
        this.mensajero = mensajero;
    }

    public Lista_recorrido getCamino() {
        return this.camino;
    }

    public void setCamino(Lista_recorrido camino) {
        this.camino = camino;
    }

    @Override
    public int compareTo(Entrega o) {
        // TODO Auto-generated method stub
        return 0;
    }

}
