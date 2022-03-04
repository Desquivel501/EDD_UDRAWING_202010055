package Models;

public class Imagen {
    String tipo;
    String dueño;
    int tiempoImpresion;
    boolean impresa;


    public Imagen(String tipo, String dueño) {
        this.tipo = tipo;
        this.dueño = dueño;
        if(tipo == "color"){
            this.tiempoImpresion = 2;
        }else{
            this.tiempoImpresion = 1;
        }

        this.impresa = false;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDueño() {
        return this.dueño;
    }

    public void setDueño(String dueño) {
        this.dueño = dueño;
    }

    public int getTiempoImpresion() {
        return this.tiempoImpresion;
    }

    public void setTiempoImpresion(int tiempoImpresion) {
        this.tiempoImpresion = tiempoImpresion;
    }

    public boolean isImpresa() {
        return this.impresa;
    }

    public boolean getImpresa() {
        return this.impresa;
    }

    public void setImpresa(boolean impresa) {
        this.impresa = impresa;
    }

    public void reducirTiempo(){
        tiempoImpresion--;
    }

}
