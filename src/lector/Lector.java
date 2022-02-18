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
import proyecto1.Controller;

public class Lector {
    JSONParser parser = new JSONParser();

    public void cargaMasiva(){

    }

    public ColaRecepcion abrir(){
        // JFrame frame = new JFrame();
        // frame.setSize(0,0);//400 width and 500 height  
        // frame.setVisible(true);
        System.out.println("here");
        try{
            // JFileChooser chooser = new JFileChooser();
            // chooser.setDialogTitle("Ingrese la lista de Clientes");
            // FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON Files", "json");
            // chooser.setFileFilter(filter);
            
            // chooser.showOpenDialog(frame);
            // File file = chooser.getSelectedFile();
            File file = new File("D:\\Documents\\Entrada.json");
            if(file != null){;
                ColaRecepcion colaRecepcion = new ColaRecepcion();
                Reader reader = new FileReader(file);                
                JSONParser parser = new JSONParser();
                Object obj  = parser.parse(reader);
                JSONObject listaClientes = (JSONObject) obj;

                for(int i = 0; i < listaClientes.size(); i++){
                    JSONObject clienteActual = (JSONObject) listaClientes.get("Cliente" + (i+1));
                    long id_cliente = (long) clienteActual.get("id_cliente");
                    String nombre_cliente = (String) clienteActual.get("nombre_cliente");
                    long img_color = (long) clienteActual.get("img_color");
                    long img_bw = (long) clienteActual.get("img_bw");

                    Cliente nuevo = new Cliente(nombre_cliente, (int)id_cliente, false,(int)img_color, (int)img_bw,1);
                    colaRecepcion.enqueue(nuevo);
                }
                System.out.println("Archivo JSON leido exitosamente");
                Controller.archivoValido = true;
                return colaRecepcion;
            }

        }catch(Exception ex){
            // JOptionPane.showMessageDialog(null,
            //     "No se ha podido abrir el archivo",
            //         "Advertencia",JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
        }
        // frame.dispose();
        return null;
    }

    
}
