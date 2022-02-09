package Models;

import listas2.PilaImagenes;

public class Cliente {
    String nombre;
    int id;
    boolean terminado;
    int img_color;
    int img_bw;
    int tiempoEspera;
    PilaImagenes imagenes;


    public Cliente(String nombre, int id, boolean terminado, int img_color, int img_bw) {
        this.nombre = nombre;
        this.id = id;
        this.terminado = terminado;
        this.img_color = img_color;
        this.img_bw = img_bw;
        this.tiempoEspera = 0;
        this.imagenes = new PilaImagenes();
        crearImagenes();
    }
    

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isTerminado() {
        return this.terminado;
    }

    public boolean getTerminado() {
        return this.terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

    public int getImg_color() {
        return this.img_color;
    }

    public void setImg_color(int img_color) {
        this.img_color = img_color;
    }

    public int getImg_bw() {
        return this.img_bw;
    }

    public void setImg_bw(int img_bw) {
        this.img_bw = img_bw;
    }

    public int getTiempoEspera() {
        return this.tiempoEspera;
    }

    public void setTiempoEspera(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    public PilaImagenes getImagenes() {
        return this.imagenes;
    }

    public void setImagenes(PilaImagenes imagenes) {
        this.imagenes = imagenes;
    }

    private void crearImagenes(){
        for(int i = 0; i<img_color;i++){
            Imagen nueva = new Imagen("color", Integer.toString(this.id));
            imagenes.stack(nueva);
        }
        for(int i = 0; i<img_bw;i++){
            Imagen nueva = new Imagen("bw", Integer.toString(this.id));
            imagenes.stack(nueva);
        }
    }

    public boolean conImagenes(){
        return !imagenes.vacia();
    }

    public Imagen darImagen(){
        return imagenes.remover().getValor();
    }

    public void recibirImagen(Imagen nueva){
        imagenes.stack(nueva);
    }

    public void aumentarTiempo(){
        tiempoEspera++;
    }
}
