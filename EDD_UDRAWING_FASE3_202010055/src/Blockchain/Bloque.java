package Blockchain;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.simple.*;

import Lista.Lista;

import org.apache.commons.codec.digest.DigestUtils;

import Models.Entrega;
import Program.Program;

public class Bloque implements Comparable<Bloque>{
    private int INDEX;
    private String TIMESTAMP;
    private int NONCE;
    private Lista<Entrega> DATA;
    private String PREVIOUSHASH;
    private String ROOTMERKLE;
    private String HASH;


    public Bloque() {
    }

    public Bloque(Lista<Entrega> DATA, String ROOTMERKLE) {
        this.INDEX = Program.INDEX;
        Program.INDEX++;
        
        LocalDateTime fecha = LocalDateTime.now();
        String fecha_f = fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy::HH:mm:ss"));
        this.TIMESTAMP = fecha_f;
        this.PREVIOUSHASH = Program.PREVIOUSHASH;
        generarHash();
        this.DATA = DATA;
        
        this.ROOTMERKLE = ROOTMERKLE;
    }


    public Bloque(int INDEX, String TIMESTAMP, int NONCE, Lista<Entrega> DATA, String PREVIOUSHASH, String ROOTMERKLE, String HASH) {
        this.INDEX = INDEX;
        this.TIMESTAMP = TIMESTAMP;
        this.NONCE = NONCE;
        this.DATA = DATA;
        this.PREVIOUSHASH = PREVIOUSHASH;
        this.ROOTMERKLE = ROOTMERKLE;
        this.HASH = HASH;
    }

    public void generarHash(){
        this.NONCE = 0;
        
        while(true){
            String cadena = INDEX + TIMESTAMP + PREVIOUSHASH + ROOTMERKLE + NONCE;
            String hash = DigestUtils.sha256Hex(cadena);
            char[] ch = hash.toCharArray();
            boolean valid = true;
            for(int i = 0; i < Program.noCero; i++){
                if(ch[i] != '0'){
                    this.NONCE++;
                    valid = false;
                    break;
                }
            }
            if(valid){
                this.HASH = hash;
                Program.PREVIOUSHASH = hash;
                break;
            } 
        }
    }

    public int getINDEX() {
        return this.INDEX;
    }

    public void setINDEX(int INDEX) {
        this.INDEX = INDEX;
    }

    public String getTIMESTAMP() {
        return this.TIMESTAMP;
    }

    public void setTIMESTAMP(String TIMESTAMP) {
        this.TIMESTAMP = TIMESTAMP;
    }

    public int getNONCE() {
        return this.NONCE;
    }

    public void setNONCE(int NONCE) {
        this.NONCE = NONCE;
    }

    public Lista<Entrega> getDATA() {
        return this.DATA;
    }

    public void setDATA(Lista<Entrega> DATA) {
        this.DATA = DATA;
    }

    public String getPREVIOUSHASH() {
        return this.PREVIOUSHASH;
    }

    public void setPREVIOUSHASH(String PREVIOUSHASH) {
        this.PREVIOUSHASH = PREVIOUSHASH;
    }

    public String getROOTMERKLE() {
        return this.ROOTMERKLE;
    }

    public void setROOTMERKLE(String ROOTMERKLE) {
        this.ROOTMERKLE = ROOTMERKLE;
    }

    public String getHASH() {
        return this.HASH;
    }

    public void setHASH(String HASH) {
        this.HASH = HASH;
    }

    public void save(){

        JSONObject obj = new JSONObject();
        obj.put("INDEX", this.INDEX);
        obj.put("TIMESTAMP", this.TIMESTAMP);
        obj.put("NONCE", this.NONCE);

        JSONArray list = new JSONArray();   
        var aux = DATA.getHead();
        while(aux != null){
            JSONObject innerObj = new JSONObject();
            innerObj.put("sede", aux.getValor().getSede());
            innerObj.put("destino", aux.getValor().getDestino());
            innerObj.put("datetime", aux.getValor().getDatetime());
            innerObj.put("cliente", aux.getValor().getCliente());
            innerObj.put("mensajero", aux.getValor().getMensajero());
            list.add(innerObj);
            aux = aux.getSiguiente();
        }
        obj.put("DATA", list);
        obj.put("PREVIOUSHASH", this.PREVIOUSHASH);
        obj.put("ROOTMERKLE", this.ROOTMERKLE);
        obj.put("HASH", this.HASH);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String nombre = INDEX + "_FECHA.json";

        try (FileWriter writer = new FileWriter("C:\\udrawing\\blockchain\\Bloques\\" + nombre)) {
            gson.toJson(obj, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(Bloque o) {
        return 0;
    }

}
