package proyecto1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Models.Cliente;
import Models.ClienteRandom;
import Nodos.NodoCliente;
import Nodos.NodoClienteD;
import Nodos.NodoImagen;
import Nodos.NodoVentanilla;
import lector.Lector;
import listas2.ColaImpresion;
import listas2.ColaRecepcion;
import listas2.ListaAtendidos;
import listas2.ListaEspera;
import listas2.ListaUsuarios;
import listas2.ListaVentanillas;
import listas2.PilaImagenes;

import java.awt.Desktop;

public class Controller {

    public static boolean archivoValido = false;

    ColaRecepcion colaRecepcion = new ColaRecepcion();
    ListaUsuarios listaUsuarios = new ListaUsuarios();
    ListaVentanillas listaVentanillas = new ListaVentanillas();
    ListaEspera listaEspera = new ListaEspera();
    ColaImpresion colaImpresionBW = new ColaImpresion();
    ColaImpresion colaImpresionColor = new ColaImpresion();
    ListaAtendidos listaAtendidos = new ListaAtendidos();

    int contadorPasos = 1;


    public void menu(){
        Scanner scan = new Scanner(System.in);
        Lector lector = new Lector();
        boolean run = true;
        
        while(run){
            System.out.println("==============DRAWING PAPER==============");
            System.out.println("|| 1. PARAMETROS INICIALES             ||");
            System.out.println("|| 2. EJECUTAR PASO                    ||");
            System.out.println("|| 3. ESTADO EN MEMORIA                ||"); 
            System.out.println("|| 4. REPORTE                          ||");
            System.out.println("|| 5. ACERCA DE...                     ||");
            System.out.println("|| 6. SALIR                            ||");
            System.out.println("=========================================");
            String menu = scan.nextLine();

            switch(menu){
                case "1":
                    while(true){
                        colaRecepcion = lector.abrir();
                        listaUsuarios = colaRecepcion.dump();
                        if(archivoValido){
                            break;
                        }else{
                            System.out.println("Archivo no valido; pruebe otra vez");
                        }
                    }

                    boolean ventValido = false;
                    while(!ventValido){
                        System.out.println("Ingrese el numero de ventanillas a utilizar:");
                        String num_ventanillas = scan.nextLine();
                        if(Integer.parseInt(num_ventanillas)>0){
                            listaVentanillas.crearVentanillas(Integer.parseInt(num_ventanillas));
                            ventValido = true;
                        }else{
                            System.out.println("Numero de ventanillas no valido");
                        }
                    }
                    break;
                
                case "2":
                    if(archivoValido){
                        Algoritmo();
                    } else{
                        System.out.println("No se ha realizado la carga masiva de usuarios");
                    }
                    break;
                
                case "3":
                    graficar();
                    System.out.println("Reporte generado.");
                    break;
                
                case "4":
                    boolean run2 = true;

                    while(run2){
                        System.out.println("===============================REPORTES===============================");
                        System.out.println("|| 1. TOP CLIENTES CON MAYOR CANTIDAD DE IMAGENES A COLOR            ||");
                        System.out.println("|| 2. TOP CLIENTES CON MENOR CANTIDAD DE IMAGENES EN BLANCO Y NEGRO  ||");
                        System.out.println("|| 3. INFORMACION DEL CLIENTE QUE MAS PASOS ESTUVO EN EL SISTEMA     ||"); 
                        System.out.println("|| 4. DATOS DE UN CLIENTE EN ESPECIFICO                              ||");
                        System.out.println("|| 5. REGRESAR                                                       ||");
                        System.out.println("=======================================================================");
                        String menu2 = scan.nextLine();
                        switch(menu2){
                            case "1":
                                listaUsuarios.ordenarImagen("c");
                                listaUsuarios.graficar("Clientes con mas imagenes a color","TopColor","c");
                                System.out.println("Reporte generado.");
                                break;
                                
                            case "2":
                                listaUsuarios.ordenarImagen("bw");
                                listaUsuarios.graficar("Clientes con mas imagenes en blanco y negro","TopBW","bw");
                                System.out.println("Reporte generado.");
                                break;
                                
                            case "3":
                                listaUsuarios.ordenarTiempo();
                                listaUsuarios.graficar("Clientes con mas tiempo en el sistema","TopTiempo","t");
                                System.out.println("Reporte generado.");
                                break;
                            case "4":
                                System.out.println("Ingrese el ID del cliente a buscar:");
                                String id = scan.nextLine();
                                listaUsuarios.buscar(Integer.parseInt(id),this.contadorPasos);
                            case "5":
                                run2 = false;
                        } 
                    }
                    break;

                case "5":
                    System.out.println("Nombre: Derek Esquivel Diaz");
                    System.out.println("Carnet: 202010055");
                    System.out.println("Laboratorio Estructura de Datos - Seccion A");
                    break;
                    
                case "6":
                    run = false;
                    break;

                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
        scan.close();
    }

    public void Algoritmo(){
        System.out.println("------------------PASO " + contadorPasos + "-----------------");

        //Mira si ya hay un  cliente al que ya se le entregaron todas sus imagenes
        if(!listaEspera.vacia()){
            NodoClienteD clienteEspera = listaEspera.getHead();

            for(int i=0;i<listaEspera.getLargo();i++){
                int id = clienteEspera.getValor().getId();
                if(checkTerminado(id)){

                    listaEspera.remover(clienteEspera.getValor().getId());
                    listaAtendidos.insertarFinal(clienteEspera.getValor()); 
                    clienteEspera.getValor().setTerminado(true);
                    clienteEspera.getValor().setTiempoSalida(contadorPasos);
                    System.out.println("Ya se le han entregado todas las imagenes a " + clienteEspera.getValor().getNombre() + " por lo que deja el establecimiento");
                
                }
                clienteEspera = clienteEspera.getSiguiente();
            }
        }

        //Imprmime imagenes en blanco y negro
        if(!colaImpresionBW.vacia()){
            NodoImagen imagenActual = colaImpresionBW.peek();
            //Imprime la imagen
            imagenActual.getValor().reducirTiempo(); 
            
            NodoClienteD clienteReceptor = buscarCliente(imagenActual.getValor().getDueño());
            clienteReceptor.getValor().recibirImagen(imagenActual.getValor());
            colaImpresionBW.dequeue();
            imagenActual.getValor().setImpresa(true);
            System.out.println("Se ha impreso una imagen en blanco y negro de " + getNombre(Integer.parseInt(imagenActual.getValor().getDueño())));
            actualizaCliente(clienteReceptor.getValor());
        }

        //Imprime imagen a color
        if(!colaImpresionColor.vacia()){
            NodoImagen imagenActual = colaImpresionColor.peek();
            //Imprime la imagen
            imagenActual.getValor().reducirTiempo();
            
            if(imagenActual.getValor().getTiempoImpresion() <= 0){
                NodoClienteD clienteReceptor = buscarCliente(imagenActual.getValor().getDueño());
                clienteReceptor.getValor().recibirImagen(imagenActual.getValor());
                colaImpresionColor.dequeue();
                imagenActual.getValor().setImpresa(true);
                System.out.println("Se ha impreso una imagen a color de " + getNombre(Integer.parseInt(imagenActual.getValor().getDueño())));
                actualizaCliente(clienteReceptor.getValor());
            }else{
                System.out.println("Se esta imprimiendo una imagen a color de " + getNombre(Integer.parseInt(imagenActual.getValor().getDueño())));
            }
        }

        //Cliente es atendido en la ventanilla
        if(listaVentanillas.atendiendo()){
            NodoVentanilla ventanilla_aux = listaVentanillas.getHead();
            while (ventanilla_aux != null){
                if(!ventanilla_aux.disponible()){
                    Cliente cliente_aux = ventanilla_aux.getClienteActual();
                    if(cliente_aux.conImagenes()){ //Si tiene imagen la entrega en la ventanilla
                        ventanilla_aux.recibirImagen(cliente_aux.darImagen());
                        System.out.println(cliente_aux.getNombre() + " dio una imagen en la ventanilla " + ventanilla_aux.getId());
                    }else{//Si ya no tiene imagenes el cliente se mueve a la cola de espera y se envian las imagenes a la cola de impresion
                        listaEspera.insertar(cliente_aux);
                        ventanilla_aux.setClienteActual(null);  
                         
                        separarImagenes(ventanilla_aux.enviarImagenes());
                        System.out.println(cliente_aux.getNombre() + " entrego todas sus imagenes en la ventanilla " + ventanilla_aux.getId() + ", pasa a la sala de espera");
                    }
                }
                ventanilla_aux = ventanilla_aux.getSiguiente();
            }
        }

        //Cliente avanza de la cola de recepcion a las ventanillas
        if(!colaRecepcion.vacia()){
            NodoCliente clienteActual = colaRecepcion.getHead();
            if(listaVentanillas.buscarDisponible() != null){ //Si hay una ventanilla disponible el cliente avanza a esta
                NodoVentanilla ventanillaActual = listaVentanillas.buscarDisponible();
                ventanillaActual.setClienteActual(clienteActual.getValor());
                colaRecepcion.dequeue();
                System.out.println("El cliente " + clienteActual.getValor().getNombre() + " entra a la ventanilla " + ventanillaActual.getId());
            }
        }

        //Crea 0-3 usuarios nuevos aleatorios
        agregarUsuarios();
        contadorPasos++;
    }

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void separarImagenes(PilaImagenes pilaCompleta){
        PilaImagenes pilaColor = new PilaImagenes();
        PilaImagenes pilaBW = new PilaImagenes();

        NodoImagen aux = pilaCompleta.remover();
        while(aux != null){
            if(aux.getValor().getTipo() == "color"){
                pilaColor.stack(aux.getValor());
            }else if(aux.getValor().getTipo() == "bw"){
                pilaBW.stack(aux.getValor());
            }
            aux = aux.getSiguiente();
        }
        colaImpresionBW.recibirImagenes(pilaBW);
        colaImpresionColor.recibirImagenes(pilaColor);
    }

    public NodoClienteD buscarCliente(String id){
        NodoClienteD aux = listaEspera.getHead();
        for(int i= 0; i<listaEspera.getLargo();i++){
            if(aux.getValor().getId() == Integer.parseInt(id)){
                return aux;
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    public boolean checkTerminado(int id){
        NodoImagen auxBW = colaImpresionBW.getHead();

        while(auxBW != null){
            if(Integer.parseInt(auxBW.getValor().getDueño()) == id){
                return false;
            }
            auxBW = auxBW.getSiguiente();
        }

        NodoImagen auxCl = colaImpresionColor.getHead();
        while(auxCl != null){
            if(Integer.parseInt(auxCl.getValor().getDueño()) == id){
                return false;
            }
            auxCl = auxCl.getSiguiente();
        }
        return true;
    }

    public String getNombre(int id){
        NodoCliente aux = this.listaUsuarios.getHead();
        while(aux != null){
            if(aux.getValor().getId() == id){
                return aux.getValor().getNombre();
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    public void actualizaCliente(Cliente cliente){
        int id = cliente.getId();
        NodoClienteD aux = listaEspera.getHead();
        for(int i= 0; i<listaEspera.getLargo();i++){
            if(aux.getValor().getId() == id){
               aux.setValor(cliente);
            }
            aux = aux.getSiguiente();
        }
    }

    public void agregarUsuarios(){
        ClienteRandom rand = new ClienteRandom();
        int numClientes = rand.numClientes();
        int id = listaUsuarios.getTail().getValor().getId()+1;
        for(int i = 0; i<numClientes;i++){
            String nombre = rand.nombre()+ " " + rand.apellido();
            int img_color = rand.numImagenes();
            int img_bw = 4-img_color;
            Cliente nuevo = new Cliente(nombre, id, false, img_color, img_bw, contadorPasos);
            listaUsuarios.insertar(nuevo);
            colaRecepcion.enqueue(nuevo);
            id++;
        }
    }

    public void graficar(){
        StringBuilder dot = new StringBuilder();
        dot.append("digraph G{\n");
        dot.append(colaRecepcion.graficar());
        dot.append(listaVentanillas.graficar());
        dot.append(listaEspera.graficar());
        dot.append(colaImpresionBW.graficar("Impresora Blanco y Negro", 1));
        dot.append(colaImpresionColor.graficar("Impresora a Color", 2));
        dot.append(listaAtendidos.graficar());

        dot.append("}");

        try {
            generarArchivo(dot.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generarArchivo(String dot) throws IOException {
        FileWriter fileWriter = new FileWriter("Reportes\\Estado.dot");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(dot);
        printWriter.close();

        String[] command = {"dot", "-Tsvg", "Reportes\\Estado.dot", "-o", "Reportes\\Estado.svg" };
        new ProcessBuilder(command).start();

        File file = new File("Reportes\\Estado.svg");

        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);

    }

}
