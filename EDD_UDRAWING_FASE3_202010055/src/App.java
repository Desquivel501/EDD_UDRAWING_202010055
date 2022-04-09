import java.awt.*;
import java.io.File;

import javax.swing.*;

import Lector.Lector;

public class App {
    public static void main(String[] args) throws Exception {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setPreferredSize( new Dimension(700, 600));

        if(fileChooser.showOpenDialog(null) == JFileChooser.OPEN_DIALOG){
            File archivo = fileChooser.getSelectedFile();

            if(archivo != null){
                Lector lector = new Lector();
                if(!lector.leerMensajero(archivo)){
                    JOptionPane.showMessageDialog(null,
                    "Se han cargado los clientes.",
                    "Cargado",
                    JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null,
                    "Ha ocurrido un error abriendo el archivo.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null,
                "Ha ocurrido un error abriendo el archivo.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null,
            "No se ha seleccionado ningun archivo.",
            "Advertencia",
            JOptionPane.WARNING_MESSAGE);
        }
    }
}
