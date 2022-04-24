package Models;

public class Ruta implements Comparable<Ruta>{
    private Lugar inicio;
    private Lugar final_;
    private int peso;


    public Ruta() {
    }

    public Ruta(Lugar inicio, Lugar final_, int peso) {
        this.inicio = inicio;
        this.final_ = final_;
        this.peso = peso;
    }

    public Lugar getInicio() {
        return this.inicio;
    }

    public void setInicio(Lugar inicio) {
        this.inicio = inicio;
    }

    public Lugar getFinal_() {
        return this.final_;
    }

    public void setFinal_(Lugar final_) {
        this.final_ = final_;
    }

    public int getPeso() {
        return this.peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }


    @Override
    public String toString() {
        return "{" +
            " inicio='" + getInicio() + "'" +
            ", final_='" + getFinal_() + "'" +
            ", peso='" + getPeso() + "'" +
            "}";
    }

    @Override
    public int compareTo(Ruta o) {
        return 0;
    }


}
