package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import AVL.NodoAVL;
import Cola.Cola;

import java.awt.Font;

import Lector.*;
import Matriz.MatrizCapa;
import Models.Cliente;
import Program.Program;


import java.awt.Image;



public class ClienteG extends JFrame implements ActionListener{

    Cliente clienteActual;
    JTabbedPane tp;
    JPanel p1,p2,p3,p4, panelR, panelC, panelA ,verAVL, verABB, verAlbum, verCapa;
    JButton cargaCapas,cargaImagenes,cargaAlbumes, regresar, recBtn, imgBtn, capaBtn , verAVLBtn, verABBBtn, verAlbumBtn, verCapaBtn;
    JLabel capasLbl, imagenesLbl, albumesLbl, imagen, imagen2;
    JSpinner recJT, imgJT, verC, verC2;
    JTextField capaJT;
    JRadioButton r1,r2,r3, r4;
    ButtonGroup bg;
    JScrollPane panelImagen, panelImagen2;


    public ClienteG(Cliente clienteActual){
        this.clienteActual = clienteActual;
        this.setTitle("Cliente");
        this.setSize(1200,1000);
        this.setLayout(null);
        inicializar();
        setLocationRelativeTo(null);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void inicializar(){

        regresar = new JButton("Cerrar Sesion");
        this.add(regresar);
        regresar.setBounds(1050,8,120,30);
        regresar.addActionListener(this);

        tp = new JTabbedPane();  
        tp.setBounds(10,10,1170,980);
        this.add(tp); 

        p1 = new JPanel();  
        p1.setLayout(null); 
        p2 = new JPanel(); 
        p2.setLayout(null); 
        p3 = new JPanel();  
        p3.setLayout(null); 
        p4 = new JPanel();  
        p4.setLayout(null); 

        capasLbl = new JLabel("Carga Masiva de Capas", SwingConstants.CENTER);
        capasLbl.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
        p2.add(capasLbl);
        capasLbl.setBounds(400,150,400,50);

        cargaCapas = new JButton("Cargar");
        p2.add(cargaCapas);
        cargaCapas.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
        cargaCapas.setBounds(500,210,200,40);
        cargaCapas.addActionListener(this);

        //------------------------------------------------------------------------------

        imagenesLbl = new JLabel("Carga Masiva de Imagenes", SwingConstants.CENTER);
        imagenesLbl.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
        p2.add(imagenesLbl);
        imagenesLbl.setBounds(400,300,400,50);

        cargaImagenes = new JButton("Cargar");
        p2.add(cargaImagenes);
        cargaImagenes.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
        cargaImagenes.setBounds(500,360,200,40);
        cargaImagenes.addActionListener(this);


        //------------------------------------------------------------------------------

        albumesLbl = new JLabel("Carga Masiva de Albumes", SwingConstants.CENTER);
        albumesLbl.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
        p2.add(albumesLbl);
        albumesLbl.setBounds(400,450,400,50);

        cargaAlbumes = new JButton("Cargar");
        p2.add(cargaAlbumes);
        cargaAlbumes.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
        cargaAlbumes.setBounds(500,510,200,40);
        cargaAlbumes.addActionListener(this);


        //------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------

        panelR = new JPanel();
        panelR.setBorder(new TitledBorder("Recorrido de Imagenes"));
        p1.add(panelR);
        panelR.setBounds(10, 10, 380, 130);
        panelR.setLayout(null);

        recJT = new JSpinner();
        panelR.add(recJT);
        recJT.setBounds(10, 20, 240, 30);

        recBtn = new JButton("Generar");
        panelR.add(recBtn);
        recBtn.setBounds(260, 20 , 100, 30);
        recBtn.addActionListener(this);

        r1 = new JRadioButton("PreOrder");  
        panelR.add(r1);
        r1.setBounds(20,60,100,20);  
        r1.setSelected(true);

        r2 = new JRadioButton("InOrder");  
        panelR.add(r2);
        r2.setBounds(20,80,100,20);  

        r3 = new JRadioButton("PostOrder");  
        panelR.add(r3);
        r3.setBounds(20,100,100,20);  

        bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);

        //-----------------------------------------------------------------------------------

        panelA = new JPanel();
        panelA.setBorder(new TitledBorder("Por Arbol de Imagenes"));
        p1.add(panelA);
        panelA.setBounds(400, 10, 380, 63);
        panelA.setLayout(null);

        imgJT = new JSpinner();
        panelA.add(imgJT);
        imgJT.setBounds(10, 20, 240, 30);

        imgBtn = new JButton("Generar");
        panelA.add(imgBtn);
        imgBtn.setBounds(260, 20 , 100, 30);
        imgBtn.addActionListener(this);



        //-----------------------------------------------------------------------------------

        panelC = new JPanel();
        panelC.setBorder(new TitledBorder("Por Capa"));
        p1.add(panelC);
        panelC.setBounds(790, 10, 370, 63);
        panelC.setLayout(null);

        capaJT = new JTextField();
        panelC.add(capaJT);
        capaJT.setBounds(10, 20, 240, 30);

        capaBtn = new JButton("Generar");
        panelC.add(capaBtn);
        capaBtn.setBounds(260, 20 , 93, 30);
        capaBtn.addActionListener(this);

        //-------------------------------------------------------------------------------

        imagen = new JLabel();
        panelImagen = new JScrollPane(imagen);
        p1.add(panelImagen);
        panelImagen.setBounds(10,145,1150,760);
        panelImagen.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  


        //------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------

        verAVL = new JPanel();
        verAVL.setBorder(new TitledBorder("Ver Arbol de Imagenes"));
        p4.add(verAVL);
        verAVL.setBounds(10, 10, 280, 100);
        verAVL.setLayout(null);

        verC = new JSpinner();
        verAVL.add(verC);
        verC.setBounds(10, 25, 140, 30);

        verAVLBtn = new JButton("Generar");
        verAVL.add(verAVLBtn);
        verAVLBtn.setBounds(160, 25 , 100, 30);
        verAVLBtn.addActionListener(this);

        r4 = new JRadioButton("Ver Capas");  
        verAVL.add(r4);
        r4.setBounds(20,65,100,20);  
        r4.setSelected(true);

        //------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------

        verABB = new JPanel();
        verABB.setBorder(new TitledBorder("Ver Arbol de Capas"));
        p4.add(verABB);
        verABB.setBounds(300, 10, 280, 80);
        verABB.setLayout(null);

        verABBBtn = new JButton("Generar");
        verABB.add(verABBBtn);
        verABBBtn.setBounds(40, 30 , 200, 30);
        verABBBtn.addActionListener(this);

        //------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------

        verAlbum = new JPanel();
        verAlbum.setBorder(new TitledBorder("Ver Listado de Albumess"));
        p4.add(verAlbum);
        verAlbum.setBounds(590, 10, 280, 80);
        verAlbum.setLayout(null);

        verAlbumBtn = new JButton("Generar");
        verAlbum.add(verAlbumBtn);
        verAlbumBtn.setBounds(40, 30 , 200, 30);
        verAlbumBtn.addActionListener(this);

        //------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------

        verCapa = new JPanel();
        verCapa.setBorder(new TitledBorder("Ver Capa"));
        p4.add(verCapa);
        verCapa.setBounds(880, 10, 280, 80);
        verCapa.setLayout(null);

        verC2 = new JSpinner();
        verCapa.add(verC2);
        verC2.setBounds(10, 30, 140, 30);

        verCapaBtn = new JButton("Generar");
        verCapa.add(verCapaBtn);
        verCapaBtn.setBounds(160, 30 , 100, 30);
        verCapaBtn.addActionListener(this);

        imagen2 = new JLabel();
        panelImagen2 = new JScrollPane(imagen2);
        p4.add(panelImagen2);
        panelImagen2.setBounds(10,115,1150,790);
        panelImagen2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  


        tp.add("Gestion Imagenes",p1); 
        tp.add("Carga Masiva",p2);
        tp.add("Estructuras",p4); 
        tp.add("Reportes",p3);
        
           
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == cargaCapas){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setPreferredSize( new Dimension(700, 600));
            
            if(fileChooser.showOpenDialog(this) == JFileChooser.OPEN_DIALOG){
                File archivo = fileChooser.getSelectedFile();

                if(archivo != null){
                    Lector lector = new Lector();
                    if(!lector.leerMatriz(archivo)){
                        JOptionPane.showMessageDialog(this,
                        "Se han cargado las capas.",
                        "Cargado",
                        JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(this,
                        "Ha ocurrido un error abriendo el archivo.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(this,
                    "Ha ocurrido un error abriendo el archivo.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(this,
                "No se ha seleccionado ningun archivo.",
                "Advertencia",
                JOptionPane.WARNING_MESSAGE);
            }
        }

        if(e.getSource() == cargaImagenes){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setPreferredSize( new Dimension(700, 600));
            
            if(fileChooser.showOpenDialog(this) == JFileChooser.OPEN_DIALOG){
                File archivo = fileChooser.getSelectedFile();

                if(archivo != null){
                    Lector lector = new Lector();
                    if(!lector.leerImagenes(archivo)){
                        JOptionPane.showMessageDialog(this,
                        "Se han cargado las imagenes.",
                        "Cargado",
                        JOptionPane.INFORMATION_MESSAGE);
                        Program.loggedUser.getArbolImagenes().insertarCapas( Program.loggedUser.getArbolCapas());
                    }
                    else{
                        JOptionPane.showMessageDialog(this,
                        "Ha ocurrido un error abriendo el archivo.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(this,
                    "Ha ocurrido un error abriendo el archivo.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(this,
                "No se ha seleccionado ningun archivo.",
                "Advertencia",
                JOptionPane.WARNING_MESSAGE);
            }
        }

        if(e.getSource() == cargaAlbumes){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setPreferredSize( new Dimension(700, 600));
            
            if(fileChooser.showOpenDialog(this) == JFileChooser.OPEN_DIALOG){
                File archivo = fileChooser.getSelectedFile();

                if(archivo != null){
                    Lector lector = new Lector();
                    if(!lector.leerAlbumes(archivo)){
                        JOptionPane.showMessageDialog(this,
                        "Se han cargado los albumes.",
                        "Cargado",
                        JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(this,
                        "Ha ocurrido un error abriendo el archivo.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(this,
                    "Ha ocurrido un error abriendo el archivo.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(this,
                "No se ha seleccionado ningun archivo.",
                "Advertencia",
                JOptionPane.WARNING_MESSAGE);
            }
        }

        if(e.getSource() == regresar){
            new Login();
            Program.loggedUser = null;
            setVisible(false);
            dispose();
        }

        if(e.getSource() == recBtn){

            int num = (int) recJT.getValue();
            MatrizCapa completa = new MatrizCapa("Completa");

            if(r1.isSelected()){
                completa = Program.loggedUser.getArbolCapas().unirPreOrder(completa, num);
            }else if(r2.isSelected()){
                completa = Program.loggedUser.getArbolCapas().unirInOrder(completa, num);
            }else if(r3.isSelected()){
                completa = Program.loggedUser.getArbolCapas().unirPostOrder(completa, num);
            }

            String nombre = completa.graficarHTML();
            System.out.println(nombre);
            if(nombre.equals("")){
                JOptionPane.showMessageDialog(this,
                "Ha ocurrrido un error al generar la imagen.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            generarImagen(nombre, 0);
        }

        if(e.getSource() == imgBtn){

            int num = (int) imgJT.getValue();
            NodoAVL nodo = Program.loggedUser.getArbolImagenes().buscar(num);

            if(nodo != null){
                String nombre = nodo.getImagen().generarImagen();
                if(nombre.equals("")){
                    JOptionPane.showMessageDialog(this,
                    "Ha ocurrrido un error al generar la imagen.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                    return;
                }

                generarImagen(nombre,0);

            }else{
                JOptionPane.showMessageDialog(this,
                "No existe una imagen con ese ID.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }

        }

        if(e.getSource() == capaBtn){
            String numString = capaJT.getText().toString();
            Cola<Integer> cola = new Cola<Integer>();

            for(int i = 0; numString.length() > i;i++){
                try{
                    int val = Integer.parseInt(numString.split(",")[i].strip());
                    cola.enqueue(val);
                    System.out.println(val);
                }catch (Exception ex){

                }
            }

            MatrizCapa completa = new MatrizCapa("Completa");
            while(!cola.vacia()){
                MatrizCapa capaActual = Program.loggedUser.getArbolCapas().getCapa(cola.dequeue().getValor());
                if(capaActual != null){
                    completa.combinarMatriz(capaActual);
                }
            }
            String nombre = completa.graficarHTML();
            if(nombre.equals("")){
                JOptionPane.showMessageDialog(this,
                "Ha ocurrrido un error al generar la imagen.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            generarImagen(nombre, 0);
            
        }   
        
        if(e.getSource() == verAVLBtn){
            var arbol = Program.loggedUser.getArbolImagenes();
            String nombre = "";
            if(r4.isSelected()){
                int num = (int) verC.getValue();
                nombre = arbol.graficar(num);
                if(nombre.equals("")){
                    JOptionPane.showMessageDialog(this,
                    "Ha ocurrrido un error al generar la imagen.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }else{
                nombre = arbol.graficar();
                if(nombre.equals("")){
                    JOptionPane.showMessageDialog(this,
                    "Ha ocurrrido un error al generar la imagen.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            System.out.println(nombre);
            generarImagen(nombre,1);
        }

        if(e.getSource() == verABBBtn){
            var arbol = Program.loggedUser.getArbolCapas();
            String nombre = "";

            nombre = arbol.graficar();
            if(nombre.equals("")){
                JOptionPane.showMessageDialog(this,
                "Ha ocurrrido un error al generar la imagen.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            generarImagen(nombre,1);
        }

        if(e.getSource() == verAlbumBtn){
            var arbol = Program.loggedUser.getListaAlbum();
            String nombre = "";

            nombre = arbol.graficar();
            if(nombre.equals("")){
                JOptionPane.showMessageDialog(this,
                "Ha ocurrrido un error al generar la imagen.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            generarImagen(nombre,1);
        }

        if(e.getSource() == verCapaBtn){
            var arbol = Program.loggedUser.getArbolCapas();
            int num = (int) verC2.getValue();

            var nodo = arbol.buscar(num);

            if(nodo != null){
                String nombre = nodo.getCapa().graficar();
                if(nombre.equals("")){
                    JOptionPane.showMessageDialog(this,
                    "Ha ocurrrido un error al generar la imagen.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                    return;
                }
                generarImagen(nombre,1);
            }else{
                JOptionPane.showMessageDialog(this,
                    "No existe una capa con ese ID.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                    return;
            }
        }
    }

    private void generarImagen(String nombre, int panel){
        try{
            boolean found = false;
            long startTime = System.currentTimeMillis();

            while(System.currentTimeMillis() < startTime + 30000) {
                try{
                    BufferedImage imagenOr = ImageIO.read(new File(nombre));
                    float altura = ((float)1140/imagenOr.getWidth()) * imagenOr.getHeight();
                    Image imagenResize = imagenOr.getScaledInstance(1140, Math.round(altura), Image.SCALE_SMOOTH);

                    switch(panel){
                        case 0:
                            imagen.setIcon(new ImageIcon(imagenResize));
                            break;
                        case 1:
                            imagen2.setIcon(new ImageIcon(imagenResize));
                            break;
                    }
                    
                    found = true;
                    break;
                }catch (Exception ex1){
                    System.err.println(".");
                }  
            }
            System.err.println("out");
            if(!found){
                JOptionPane.showMessageDialog(this,
                "Ha ocurrrido un error al generar la imagen.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
}
