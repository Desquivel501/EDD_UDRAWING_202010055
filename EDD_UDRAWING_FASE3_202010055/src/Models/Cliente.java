package Models;

// import ABB.*;
// import AVL.*;
// import ListaAlbum.*;

public class Cliente implements Comparable<Cliente>{
    private Long dpi;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String address;
    private String id_municipio;


    public Cliente(Long dpi, String username, String password, String name, String phone, String address, String id_municipio) {
        this.dpi = dpi;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.id_municipio = id_municipio;
    }

    public Cliente() {
    }

    public Long getDpi() {
        return this.dpi;
    }

    public void setDpi(Long dpi) {
        this.dpi = dpi;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId_municipio() {
        return this.id_municipio;
    }

    public void setId_municipio(String id_municipio) {
        this.id_municipio = id_municipio;
    }

    @Override
    public String toString() {
        return "{" +
            " dpi='" + getDpi() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", name='" + getName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", address='" + getAddress() + "'" +
            ", id_municipio='" + getId_municipio() + "'" +
            "}";
    }


    @Override
    public int compareTo(Cliente o) {
        return 0;
    }

}
