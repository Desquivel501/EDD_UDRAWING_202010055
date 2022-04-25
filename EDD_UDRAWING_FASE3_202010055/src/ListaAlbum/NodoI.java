package ListaAlbum;

import Models.Imagen;

public class NodoI {
    private Imagen imagen;
    private NodoI siguiente;

    public NodoI(Imagen imagen) {
        this.imagen = imagen;
        this.siguiente = null;
    }

    public Imagen getImagen() {
        return this.imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public NodoI getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoI siguiente) {
        this.siguiente = siguiente;
    }

    public NodoI imagen(Imagen imagen) {
        setImagen(imagen);
        return this;
    }

    public NodoI siguiente(NodoI siguiente) {
        setSiguiente(siguiente);
        return this;
    }
}
