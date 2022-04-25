package Models;

import ABB.ArbolBinario;
import AVL.AVL;
import ListaAlbum.ListaAlbum;

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
    private String correo;
    private ListaAlbum listaAlbum;
    private AVL arbolImagenes;
    private ArbolBinario arbolCapas;


    public Cliente(Long dpi, String username, String password, String name, String correo, String phone, String address, String id_municipio) {
        this.dpi = dpi;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.id_municipio = id_municipio;
        this.correo = correo;
        listaAlbum = new ListaAlbum();
        arbolImagenes = new AVL();
        arbolCapas = new ArbolBinario();
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

    public String getCorreo(){
        return this.correo;
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

    public void setCorreo(String correo){
        this.correo = correo;
    }

    public ListaAlbum getListaAlbum() {
        return this.listaAlbum;
    }

    public void setListaAlbum(ListaAlbum listaAlbum) {
        this.listaAlbum = listaAlbum;
    }

    public AVL getArbolImagenes() {
        return this.arbolImagenes;
    }

    public void setArbolImagenes(AVL arbolImagenes) {
        this.arbolImagenes = arbolImagenes;
    }

    public ArbolBinario getArbolCapas() {
        return this.arbolCapas;
    }

    public void setArbolCapas(ArbolBinario arbolCapas) {
        this.arbolCapas = arbolCapas;
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
