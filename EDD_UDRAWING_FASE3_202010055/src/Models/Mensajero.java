package Models;

public class Mensajero implements Comparable<Mensajero> {
    private Long dpi;
    private String nombres;
    private String apellidos;
    private String licencia;
    private String genero;
    private String telefono;
    private String direccion;
    

    public Mensajero(){
    }

    public Mensajero(Long dpi, String nombres, String apellidos, String licencia, String genero, String telefono, String direccion) {
        this.dpi = dpi;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.licencia = licencia;
        this.genero = genero;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Long getDpi() {
        return this.dpi;
    }

    public void setDpi(Long dpi) {
        this.dpi = dpi;
    }

    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getLicencia() {
        return this.licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "{" +
            " dpi='" + getDpi() + "'" +
            ", nombres='" + getNombres() + "'" +
            ", apellidos='" + getApellidos() + "'" +
            ", licencia='" + getLicencia() + "'" +
            ", genero='" + getGenero() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", direccion='" + getDireccion() + "'" +
            "}";
    }

    @Override
    public int compareTo(Mensajero o) {
        // TODO Auto-generated method stub
        return 0;
    }
}