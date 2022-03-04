package Models;

import java.util.Random;

public class ClienteRandom {
    Random rand = new Random();

    String[] listaApellidos = {"Domminguez", "Perez", "Herrera", "Connor", "Rocha","Ponce","Walker"
                            ,"Moran","Miranda","Bravo","Vazquez","Castro","Spears"
                            ,"Livingston","Long","Vargas","Montes","Lawson","Garza"
                            ,"Peralta","Snow","Pineda","Enriquez","Harrington","Ali"
                            ,"Arroyo","Mustafa","Paterson","Dawson","Rojas","Diaz"
                            ,"Morgan","Marston","Grimshaw","Milton","Gonzales","Guzman"
                            ,"Aguilar","Hetfield","Castillo","Hawkins","de Astora","Kim"};
    
    String[] listaNombre = {"Aarron", "Alexandra ", "Karina ", "Pedro ", "Jessie","Francis","Janet"
                            ,"Irving", "Luis", "Marco", "Lucia ", "Mariana","Federico","Mel"
                            ,"Elouise", "Cassandra", "Santiago", "Betty", "Olivia","Rose","Laylah"
                            ,"Johnny", "Paige", "Ilia", "Gwynevere", "Tony","Gael","Friede"
                            ,"Solaire", "Logan", "Sam", "Andre ", "Priscilla","Kirk","Elizabeth"
                            ,"Anri", "Karla", "Rosaria", "Yoel ", "Sarah","Carlos","Mercedes"
                            ,"Arthur", "John", "Charles", "Juan ", "Jim","Mario","Kelly"
                            ,"Maria", "Jose", "Fransisco", "Diego", "Sofia","Fernanda","Abigail"};



    public String nombre(){
        int int_nombre = rand.nextInt(this.listaApellidos.length);
        return listaNombre[int_nombre];
    }

    public String apellido(){
        int int_apellido = rand.nextInt(this.listaApellidos.length);
        return listaApellidos[int_apellido];
    }

    public int numImagenes(){
        return rand.nextInt(4)+1;
    }

    public int numClientes(){
        return rand.nextInt(4);
    }
}
