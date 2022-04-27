package Lista;
public class NodoR {
    private int id_lugar;
    private String nombre;
    private NodoR siguiente;


    public NodoR(int id_lugar, String nombre) {
        this.id_lugar = id_lugar;
        this.nombre = nombre;
        this.siguiente = null;
    }

    public int getId_lugar() {
        return this.id_lugar;
    }

    public void setId_lugar(int id_lugar) {
        this.id_lugar = id_lugar;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public NodoR getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoR siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return "{" +
            " id_lugar='" + getId_lugar() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", siguiente='" + getSiguiente() + "'" +
            "}";
    }


}



 