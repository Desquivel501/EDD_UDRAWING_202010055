package lector;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Models.Cliente;
import listas2.ColaRecepcion;

public class Lector {
    JSONParser parser = new JSONParser();

    public void cargaMasiva(){

    }

    public ColaRecepcion abrir(){
        JFrame frame = new JFrame();
        frame.setSize(0,0);//400 width and 500 height  
        frame.setVisible(true);

        try{
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Ingrese la lista de Clientes");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON Files", "json");
            chooser.setFileFilter(filter);
            
            chooser.showOpenDialog(frame);
            File file = chooser.getSelectedFile();

            if(file != null){
                ColaRecepcion colaRecepcion = new ColaRecepcion();
                Reader reader = new FileReader(file);
                JSONArray listaRecepcion = (JSONArray) parser.parse(reader);
                
                
            
                for(int i = 0; i < listaRecepcion.size() ;i++ ){
                    JSONObject listaClientes = (JSONObject) listaRecepcion.get(i);
                    JSONObject clienteActual = (JSONObject) listaClientes.get("Cliente" + (i+1));
                    
                    String id_cliente = (String) clienteActual.get("id_cliente");
                    String nombre_cliente = (String) clienteActual.get("nombre_cliente");
                    String img_color = (String) clienteActual.get("img_color");
                    String img_bw = (String) clienteActual.get("img_bw");

                    Cliente nuevo = new Cliente(nombre_cliente, Integer.parseInt(id_cliente), false, Integer.parseInt(img_color), Integer.parseInt(img_bw));

                    colaRecepcion.enqueue(nuevo);
                }
                // colaRecepcion.imprimir();
                System.out.println("Archivo JSON leido exitosamente");
                return colaRecepcion;
            }

        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,
                "No se ha podido abrir el archivo",
                    "Advertencia",JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
        }
        frame.dispose();
        return null;
    }

    
}
