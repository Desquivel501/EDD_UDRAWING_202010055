package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import java.awt.Font;

import Lector.*;

public class ClienteG extends JFrame implements ActionListener{

    JTabbedPane tp;
    JPanel p1,p2,p3;
    JButton cargaCapas,cargaImagenes,cargaAlbumes, regresar;
    JLabel capasLbl, imagenesLbl, albumesLbl;

    public ClienteG(){
        this.setTitle("Cliente");
        this.setSize(1000,800);
        this.setLayout(null);
        inicializar();
        setLocationRelativeTo(null);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void inicializar(){

        regresar = new JButton("Cerrar Sesion");
        this.add(regresar);
        regresar.setBounds(850,8,120,30);
        regresar.addActionListener(this);

        tp = new JTabbedPane();  
        tp.setBounds(10,10,980,780);
        this.add(tp); 

        p1 = new JPanel();  
        p1.setLayout(null); 
        p2 = new JPanel(); 
        p2.setLayout(null); 
        p3 = new JPanel();  
        p3.setLayout(null); 

        capasLbl = new JLabel("Carga Masiva de Clientes", SwingConstants.CENTER);
        capasLbl.setFont(new java.awt.Font("Arial", Font.BOLD, 30));
        p2.add(capasLbl);
        capasLbl.setBounds(50,100,400,100);

        cargaCapas = new JButton("Cargar");
        p2.add(cargaCapas);
        cargaCapas.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
        cargaCapas.setBounds(150,200,200,100);
        cargaCapas.addActionListener(this);

        //------------------------------------------------------------------------------

        imagenesLbl = new JLabel("Carga Masiva de Imagenes", SwingConstants.CENTER);
        imagenesLbl.setFont(new java.awt.Font("Arial", Font.BOLD, 30));
        p2.add(imagenesLbl);
        imagenesLbl.setBounds(510,100,400,100);

        cargaImagenes = new JButton("Cargar");
        p2.add(cargaImagenes);
        cargaImagenes.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
        cargaImagenes.setBounds(600,200,200,100);
        cargaImagenes.addActionListener(this);
        cargaImagenes.setEnabled(false);

        //------------------------------------------------------------------------------

        albumesLbl = new JLabel("Carga Masiva de Albumes", SwingConstants.CENTER);
        albumesLbl.setFont(new java.awt.Font("Arial", Font.BOLD, 30));
        p2.add(albumesLbl);
        albumesLbl.setBounds(300,400,400,100);

        cargaAlbumes = new JButton("Cargar");
        p2.add(cargaAlbumes);
        cargaAlbumes.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
        cargaAlbumes.setBounds(400,500,200,100);
        cargaAlbumes.addActionListener(this);
        cargaAlbumes.setEnabled(false);


        tp.add("Gestion Imagenes",p1); 
        tp.add("Carga Masiva",p2); 
        tp.add("Reportes",p3);
           
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // if(e.getSource() == cargaCliente){
        //     JFileChooser fileChooser = new JFileChooser();
        //     fileChooser.setPreferredSize( new Dimension(700, 600));
            
        //     if(fileChooser.showOpenDialog(this) == JFileChooser.OPEN_DIALOG){
        //         File archivo = fileChooser.getSelectedFile();

        //         if(archivo != null){
        //             Lector lector = new Lector();
        //             lector.leerClientes(archivo);
        //         }else{
        //             JOptionPane.showMessageDialog(this,
        //             "Ha ocurrido un error abriendo el archivo.",
        //             "Error",
        //             JOptionPane.ERROR_MESSAGE);
        //         }
        //     }else{
        //         JOptionPane.showMessageDialog(this,
        //         "No se ha seleccionado ningun archivo.",
        //         "Advertencia",
        //         JOptionPane.WARNING_MESSAGE);
        //     }
        // }

        if(e.getSource() == regresar){
            new Login();
            setVisible(false);
            dispose();
        }

        if(e.getSource() == regresar){
            new Login();
            setVisible(false);
            dispose();
        }
        
    }
    
}
