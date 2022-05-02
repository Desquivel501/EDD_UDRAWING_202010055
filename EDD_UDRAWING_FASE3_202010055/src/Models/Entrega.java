package Models;

import Lista.Lista_recorrido;
import Program.Program;

public class Entrega implements Comparable<Entrega>{
    private String sede;
    private String destino;
    private String datetime;
    private String cliente;
    private String mensajero;


    public Entrega() {
    }

    public Entrega(String sede, String destino, String datetime, String cliente, String mensajero) {
        this.sede = sede;
        this.destino = destino;
        this.datetime = datetime;
        this.cliente = cliente;
        this.mensajero = mensajero;
    }

    public String getSede() {
        return this.sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getDestino() {
        return this.destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDatetime() {
        return this.datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getCliente() {
        return this.cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getMensajero() {
        return this.mensajero;
    }

    public void setMensajero(String mensajero) {
        this.mensajero = mensajero;
    }

    public String getDatos(){
        return sede + destino + datetime + cliente + mensajero;
    }

    @Override
    public int compareTo(Entrega o) {
        return 0;
    }

}
