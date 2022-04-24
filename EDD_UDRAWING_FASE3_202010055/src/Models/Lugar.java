package Models;

import Lista.Lista;
import Lista.Nodo;

public class Lugar implements Comparable<Lugar>{
    private int id;
    private String departamento;
    private String nombre;
    private boolean sucursal;

    public Lugar(int id, String departamento, String nombre, boolean sucursal) {
        this.id = id;
        this.departamento = departamento;
        this.nombre = nombre;
        this.sucursal = sucursal;
    }

    public Lugar(){
        
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isSucursal() {
        return this.sucursal;
    }

    public boolean getSucursal() {
        return this.sucursal;
    }

    public void setSucursal(boolean sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", departamento='" + getDepartamento() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", sucursal='" + isSucursal() + "'" +
            "}";
    }

    @Override
    public int compareTo(Lugar o) {
        return 0;
    }

}
