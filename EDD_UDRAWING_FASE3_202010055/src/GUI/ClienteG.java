package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.*;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ABB.ArbolBinario;
import ABB.NodoABB;
import AVL.NodoAVL;
import Cola.Cola;
import Lector.Lector;
import Lista.Lista;
import Lista.Nodo;
import Matriz.MatrizCapa;
import Models.Entrega;
import Models.Imagen;
import Models.Lugar;
import Models.Mensajero;
import Program.Program;



public class ClienteG extends JFrame implements ActionListener{

    JTabbedPane tp;
    JPanel p1,p2,p3,p4,p5,p6, panelR, panelC, panelA ,verAVL, verABB, verAlbum, verCapa, panelE;
    JButton cargaCapas,cargaImagenes,cargaAlbumes, regresar, recBtn, imgBtn, ElBtn, capaBtn , verAVLBtn, verABBBtn, verAlbumBtn, verCapaBtn, repTop, repHoja, repProf, repLista, ver1, ver2, ordenarBtn, agregarBtn;
    JLabel capasLbl, imagenesLbl, albumesLbl, imagen, imagen2, idReclbl, idCaplbl, imagen3, sucursalLbl ,mensajeroLbl;
    JSpinner recJT, imgJT, verC, verC2, idRec, idCap, ElJT;
    JTextField capaJT;
    JRadioButton r1,r2,r3, r4;
    ButtonGroup bg;
    JScrollPane panelImagen, panelImagen2, panelImagen3;
    JTable tablaImagenes;
    int numeroImagen = -1;
    JComboBox sucursalCB, mensajeroCB, listaImagen;

    public ClienteG(){
        this.setTitle("Cliente");
        this.setSize(1200,1000);
        this.setLayout(null);
        inicializar();
        setLocationRelativeTo(null);
        this.setVisible(true);
        getSucursales();
        getMensajeros();
        getImagenes();
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
        p5 = new JPanel();  
        p5.setLayout(null);
        p6 = new JPanel();  
        p6.setLayout(null);

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
        panelR.setBorder(new TitledBorder("Generar Por Recorrido de Capas"));
        p1.add(panelR);
        panelR.setBounds(10, 10, 380, 130);
        panelR.setLayout(null);

        idReclbl = new JLabel("ID", SwingConstants.RIGHT);
        panelR.add(idReclbl);
        idReclbl.setBounds(90, 20, 30, 30);

        idRec = new JSpinner();
        panelR.add(idRec);
        idRec.setBounds(130, 20, 120, 30);

        idReclbl = new JLabel("No. Capas");
        panelR.add(idReclbl);
        idReclbl.setBounds(15, 60, 55, 30);

        recJT = new JSpinner();
        panelR.add(recJT);
        recJT.setBounds(80, 60, 170, 30);

        recBtn = new JButton("Generar");
        panelR.add(recBtn);
        recBtn.setBounds(260, 60 , 100, 30);
        recBtn.addActionListener(this);

        ver1 = new JButton("Ver");
        panelR.add(ver1);
        ver1.setBounds(260, 20 , 100, 30);
        ver1.addActionListener(this);

        r1 = new JRadioButton("PreOrder");  
        panelR.add(r1);
        r1.setBounds(20,100,100,20);  
        r1.setSelected(true);

        r2 = new JRadioButton("InOrder");  
        panelR.add(r2);
        r2.setBounds(130,100,100,20);  

        r3 = new JRadioButton("PostOrder");  
        panelR.add(r3);
        r3.setBounds(240,100,100,20);  

        bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);

        //-----------------------------------------------------------------------------------

        panelC = new JPanel();
        panelC.setBorder(new TitledBorder("Generar por Capas"));
        p1.add(panelC);
        panelC.setBounds(400, 25, 380, 100);
        panelC.setLayout(null);

        idCaplbl = new JLabel("ID", SwingConstants.RIGHT);
        panelC.add(idCaplbl);
        idCaplbl.setBounds(90, 20, 30, 30);

        idCap = new JSpinner();
        panelC.add(idCap);
        idCap.setBounds(130, 20, 120, 30);

        idReclbl = new JLabel("Capas", SwingConstants.CENTER);
        panelC.add(idReclbl);
        idReclbl.setBounds(15, 60, 55, 30);

        capaJT = new JTextField();
        panelC.add(capaJT);
        capaJT.setBounds(80, 60, 170, 30);

        ver2 = new JButton("Ver");
        panelC.add(ver2);
        ver2.setBounds(260, 20 , 100, 30);
        ver2.addActionListener(this);

        capaBtn = new JButton("Generar");
        panelC.add(capaBtn);
        capaBtn.setBounds(260, 60 , 100, 30);
        capaBtn.addActionListener(this);

        //-----------------------------------------------------------------------------------

        panelA = new JPanel();
        panelA.setBorder(new TitledBorder("Ver Imagen"));
        p1.add(panelA);
        panelA.setBounds(790, 10, 370, 63);
        panelA.setLayout(null);

        idCaplbl = new JLabel("ID", SwingConstants.RIGHT);
        panelA.add(idCaplbl);
        idCaplbl.setBounds(10, 20, 30, 30);

        imgJT = new JSpinner();
        panelA.add(imgJT);
        imgJT.setBounds(50, 20, 200, 30);

        imgBtn = new JButton("Ver");
        panelA.add(imgBtn);
        imgBtn.setBounds(260, 20 , 100, 30);
        imgBtn.addActionListener(this);

        //-----------------------------------------------------------------------------------

        panelE = new JPanel();
        panelE.setBorder(new TitledBorder("Eliminar Imagen"));
        p1.add(panelE);
        panelE.setBounds(790, 78, 370, 63);
        panelE.setLayout(null);

        idCaplbl = new JLabel("ID", SwingConstants.RIGHT);
        panelE.add(idCaplbl);
        idCaplbl.setBounds(10, 20, 30, 30);

        ElJT = new JSpinner();
        panelE.add(ElJT);
        ElJT.setBounds(50, 20, 200, 30);

        ElBtn = new JButton("Eliminar");
        panelE.add(ElBtn);
        ElBtn.setBounds(260, 20 , 100, 30);
        ElBtn.addActionListener(this);

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
        r4.setSelected(false);

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
        verAlbum.setBorder(new TitledBorder("Ver Listado de Albumes"));
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

        //------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------
        
        verAVL = new JPanel();
        verAVL.setBorder(new TitledBorder("Top 5 imagenes"));
        p3.add(verAVL);
        verAVL.setBounds(10, 10, 280, 80);
        verAVL.setLayout(null);

        repTop= new JButton("Generar");
        verAVL.add(repTop);
        repTop.setBounds(40, 30 , 200, 30);
        repTop.addActionListener(this);

        //------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------

        verABB = new JPanel();
        verABB.setBorder(new TitledBorder("Capas que son Hojas"));
        p3.add(verABB);
        verABB.setBounds(300, 10, 280, 80);
        verABB.setLayout(null);

        repHoja= new JButton("Generar");
        verABB.add(repHoja);
        repHoja.setBounds(40, 30 , 200, 30);
        repHoja.addActionListener(this);

        //------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------

        verAlbum = new JPanel();
        verAlbum.setBorder(new TitledBorder("Profundidad Arbol de Capas"));
        p3.add(verAlbum);
        verAlbum.setBounds(590, 10, 280, 80);
        verAlbum.setLayout(null);

        repProf= new JButton("Generar");
        verAlbum.add(repProf);
        repProf.setBounds(40, 30 , 200, 30);
        repProf.addActionListener(this);

        //------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------

        verCapa = new JPanel();
        verCapa.setBorder(new TitledBorder("Listar Capas"));
        p3.add(verCapa);
        verCapa.setBounds(880, 10, 280, 80);
        verCapa.setLayout(null);

        repLista= new JButton("Generar");
        verCapa.add(repLista);
        repLista.setBounds(40, 30 , 200, 30);
        repLista.addActionListener(this);

        //------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        sucursalLbl = new JLabel("Imagen");
        p5.add(sucursalLbl);
        sucursalLbl.setBounds(400,100,400,30);

        listaImagen = new JComboBox<>();
        p5.add(listaImagen);
        listaImagen.setBounds(400,125,400,40);

        //--------------------------------------------

        sucursalLbl = new JLabel("Sucursal");
        p5.add(sucursalLbl);
        sucursalLbl.setBounds(400,175,400,30);

        sucursalCB = new JComboBox<>();
        p5.add(sucursalCB);
        sucursalCB.setBounds(400,200,400,40);

        //--------------------------------------------

        mensajeroLbl = new JLabel("Nombre Completo");
        p5.add(mensajeroLbl);
        mensajeroLbl.setBounds(400,250,400,30);

        mensajeroCB = new JComboBox<>();
        p5.add(mensajeroCB);
        mensajeroCB.setBounds(400,275,400,40);

        //--------------------------------------------

        agregarBtn= new JButton("Agregar");
        p5.add(agregarBtn);
        agregarBtn.setBounds(450, 335 , 300, 40);
        agregarBtn.addActionListener(this);

        //--------------------------------------------

        ordenarBtn= new JButton("Generar");
        p5.add(ordenarBtn);
        ordenarBtn.setBounds(450, 395 , 300, 40);
        ordenarBtn.addActionListener(this);

        
        panelImagen3 = new JScrollPane();

        tp.add("Gestion Imagenes",p1); 
        tp.add("Estructuras",p4); 
        tp.add("Carga Masiva",p2);
        tp.add("Entrega",p5);
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
                        // Program.loggedUser.getArbolImagenes().insertarCapas( Program.loggedUser.getArbolCapas());
                        getImagenes();
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
            int id = (int) idRec.getValue();
            this.numeroImagen = id;
            int num = (int) recJT.getValue();
            MatrizCapa completa = new MatrizCapa("Completa");
            Imagen nuevaImagen = null;

            if(r1.isSelected()){
                completa = Program.loggedUser.getArbolCapas().unirPreOrder(completa, num);
                nuevaImagen = new Imagen(id, Program.loggedUser.getArbolCapas().getPreOrder(num));
            }else if(r2.isSelected()){
                completa = Program.loggedUser.getArbolCapas().unirInOrder(completa, num);
                nuevaImagen = new Imagen(id, Program.loggedUser.getArbolCapas().getInOrder(num));
            }else if(r3.isSelected()){
                completa = Program.loggedUser.getArbolCapas().unirPostOrder(completa, num);
                nuevaImagen = new Imagen(id, Program.loggedUser.getArbolCapas().getPostOrder(num));
            }

            NodoAVL nodo = Program.loggedUser.getArbolImagenes().buscar(id);
            if(nodo == null && nuevaImagen != null){
                Program.loggedUser.getArbolImagenes().insertar(id, nuevaImagen);
            }else{
                JOptionPane.showMessageDialog(this,
                "Ya existe una imagen con ese ID,\n por lo que no se ha agregado al arbol.",
                "Error",
                JOptionPane.INFORMATION_MESSAGE);
            }

            String nombre = completa.graficarHTML();
            System.out.println(nombre);
            if(nombre.equals("")){
                numeroImagen = -1;
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
            numeroImagen = num;
            NodoAVL nodo = Program.loggedUser.getArbolImagenes().buscar(num);

            if(nodo != null){
                String nombre = nodo.getImagen().generarImagen();
                if(nombre.equals("")){
                    numeroImagen =-1;
                    JOptionPane.showMessageDialog(this,
                    "Ha ocurrrido un error al generar la imagen.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                    return;
                }

                generarImagen(nombre,0);

            }else{
                numeroImagen =-1;
                JOptionPane.showMessageDialog(this,
                "No existe una imagen con ese ID.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }

        }

        if(e.getSource() == capaBtn){
            int id = (int) idCap.getValue();
            String numString = capaJT.getText().toString();
            Cola<Integer> cola = new Cola<Integer>();
            numeroImagen = id;

            for(int i = 0; numString.length() > i;i++){
                try{
                    int val = Integer.parseInt(numString.split(",")[i].strip());
                    cola.enqueue(val);
                    System.out.println(val);
                }catch (Exception ex){

                }
            }
            ArbolBinario nuevoArbol = new ArbolBinario();
            MatrizCapa completa = new MatrizCapa("Completa");
            while(!cola.vacia()){
                MatrizCapa capaActual = Program.loggedUser.getArbolCapas().getCapa(cola.dequeue().getValor());
                if(capaActual != null){
                    completa.combinarMatriz(capaActual);
                    nuevoArbol.insertar(capaActual.getId(), capaActual);
                }
            }
            Imagen nuevaImagen = new Imagen(id,nuevoArbol);
            NodoAVL nodo = Program.loggedUser.getArbolImagenes().buscar(id);
            if(nodo == null){
                Program.loggedUser.getArbolImagenes().insertar(id, nuevaImagen);
            }else{
                JOptionPane.showMessageDialog(this,
                "Ya existe una imagen con ese ID,\n por lo que no se ha agregado al arbol.",
                "Error",
                JOptionPane.INFORMATION_MESSAGE);
            }

            String nombre = completa.graficarHTML();
            if(nombre.equals("")){
                numeroImagen = -1;
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

            if(arbol.getRaiz() == null){
                JOptionPane.showMessageDialog(this,
                "No se han cargado imagenes",
                "Error",
                JOptionPane.INFORMATION_MESSAGE);
                return;
            }

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
     
            generarImagen(nombre,1);
        }

        if(e.getSource() == verABBBtn){
            var arbol = Program.loggedUser.getArbolCapas();

            if(arbol.getRaiz() == null){
                JOptionPane.showMessageDialog(this,
                "No se han cargado capas",
                "Error",
                JOptionPane.INFORMATION_MESSAGE);
                return;
            }

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
            var lista = Program.loggedUser.getListaAlbum();

            if(lista.vacia()){
                JOptionPane.showMessageDialog(this,
                "No se ha creado nigun album",
                "Error",
                JOptionPane.INFORMATION_MESSAGE);
                return;
            }


            String nombre = "";

            nombre = lista.graficar();
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

            if(arbol.getRaiz() == null){
                JOptionPane.showMessageDialog(this,
                "No se han cargado capas",
                "Error",
                JOptionPane.INFORMATION_MESSAGE);
                return;
            }

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

        if(e.getSource() == ElBtn){

            int res = JOptionPane.showConfirmDialog(this,
                "¿Esta segur que desea eliminar esta imagen?",
                "Eliminar",
                JOptionPane.YES_NO_OPTION);
            
            if(res == JOptionPane.NO_OPTION){
                return;
            }

            int num = (int) ElJT.getValue();
            NodoAVL nodo = Program.loggedUser.getArbolImagenes().eliminar(num);

            if(nodo != null){

                if(num == numeroImagen) imagen.setIcon(null);
                Program.loggedUser.getListaAlbum().eliminar(num);
                numeroImagen = -1;
                JOptionPane.showMessageDialog(this,
                "Se ha eliminado la imagen.",
                "Completado",
                JOptionPane.INFORMATION_MESSAGE);
                return;

            }else{
                JOptionPane.showMessageDialog(this,
                "No existe una imagen con ese ID.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if(e.getSource() == repTop){
            var arbol = Program.loggedUser.getArbolImagenes();
            if(arbol.getRaiz() == null){
                JOptionPane.showMessageDialog(this,
                "No se han cargado imagenes",
                "Error",
                JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            tablaImagenes = new JTable(arbol.topImagenes(), new String[]{"Imagen", "No. Capas"});

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
            tablaImagenes.setDefaultRenderer(Object.class, centerRenderer);

            panelImagen3.setSize(0, 0);
            panelImagen3 = new JScrollPane(tablaImagenes);
            p3.add(panelImagen3);
            panelImagen3.setBounds(400,200,350,150);
            panelImagen3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        }

        if(e.getSource() == repHoja){
            var arbol = Program.loggedUser.getArbolCapas();
            if(arbol.getRaiz() == null){
                JOptionPane.showMessageDialog(this,
                "No se han cargado capas",
                "Error",
                JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            Lista<NodoABB> lista = arbol.getNodos();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Capas que son Hojas");
            tablaImagenes = new JTable(model);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
            tablaImagenes.setDefaultRenderer(Object.class, centerRenderer);

            Nodo<NodoABB> aux = lista.getHead();
            while(aux != null){
                if(aux.getValor().isHoja()){
                    model.addRow(new Object[]{"Capa " + aux.getValor().getCapa().getId()});
                }
                aux = aux.getSiguiente();
            }

            panelImagen3.setSize(0, 0);
            panelImagen3 = new JScrollPane(tablaImagenes);
            p3.add(panelImagen3);
            panelImagen3.setBounds(400,200,350,300);
            panelImagen3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            
        }
    
        if(e.getSource() == repProf){
            var arbol = Program.loggedUser.getArbolCapas();
            
            if(arbol.getRaiz() == null){
                JOptionPane.showMessageDialog(this,
                "No se han cargado capas",
                "Error",
                JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            tablaImagenes = new JTable(new String[][]{{Integer.toString(arbol.getProfundidad())}},new String[]{"Profundidad"});

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
            tablaImagenes.setDefaultRenderer(Object.class, centerRenderer);
            
            panelImagen3.setSize(0, 0);
            panelImagen3 = new JScrollPane(tablaImagenes);
            p3.add(panelImagen3);
            panelImagen3.setBounds(500,200,150,70);
        }

        if(e.getSource() == repLista){
            var arbol = Program.loggedUser.getArbolCapas();
            if(arbol.getRaiz() == null){
                JOptionPane.showMessageDialog(this,
                "No se han cargado capas",
                "Error",
                JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String[][] datos = new String[][]{{"Pre Order", arbol.getPreOrder() },{"In Order", arbol.getInOrder() },{"Post Order", arbol.getPostOrder()}};
            tablaImagenes = new JTable(datos, new String[]{"Tipo Recorrido", "Capas"});

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
            tablaImagenes.setDefaultRenderer(Object.class, centerRenderer);

            panelImagen3.setSize(0, 0);
            panelImagen3 = new JScrollPane(tablaImagenes);
            p3.add(panelImagen3);
            panelImagen3.setBounds(200,200,750,150);
            panelImagen3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        }

        if(e.getSource() == ver1){
            int id = (int) idRec.getValue();
            this.numeroImagen = id;
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
                numeroImagen = -1;
                JOptionPane.showMessageDialog(this,
                "Ha ocurrrido un error al generar la imagen.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            generarImagen(nombre, 0);
        }
    
        if(e.getSource() == ver2){
            int id = (int) idCap.getValue();
            String numString = capaJT.getText().toString();
            Cola<Integer> cola = new Cola<Integer>();
            numeroImagen = id;

            for(int i = 0; numString.length() > i;i++){
                try{
                    int val = Integer.parseInt(numString.split(",")[i].strip());
                    cola.enqueue(val);
                    System.out.println(val);
                }catch (Exception ex){

                }
            }
            ArbolBinario nuevoArbol = new ArbolBinario();
            MatrizCapa completa = new MatrizCapa("Completa");
            while(!cola.vacia()){
                MatrizCapa capaActual = Program.loggedUser.getArbolCapas().getCapa(cola.dequeue().getValor());
                if(capaActual != null){
                    completa.combinarMatriz(capaActual);
                    nuevoArbol.insertar(capaActual.getId(), capaActual);
                }
            }
            String nombre = completa.graficarHTML();
            if(nombre.equals("")){
                numeroImagen = -1;
                JOptionPane.showMessageDialog(this,
                "Ha ocurrrido un error al generar la imagen.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            generarImagen(nombre, 0);
            
        } 

        if(e.getSource() == agregarBtn){
            int inicio = Program.loggedUser.getId_municipio();
            var final_ = (ComboSucursal) sucursalCB.getSelectedItem();
            int final_id = final_.getValor().getId();
            var camino = Program.grafoLugares.dijkstra(inicio, final_id);
            var mensajero = (ComboMensajero) mensajeroCB.getSelectedItem();
            LocalDateTime fecha = LocalDateTime.now();
            String fecha_f = fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

            Program.lista_entregas.insertar(new Entrega(inicio, final_id, fecha_f, Program.loggedUser, mensajero.getValor(), camino));

        }

        if(e.getSource() == ordenarBtn){
            Program.arbolMerkle.generarArbol();
            Program.arbolMerkle.graficar();
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
                    
                    Image imagenResize;
                    if(imagenOr.getWidth() < 1140){
                        imagenResize = imagenOr.getScaledInstance(imagenOr.getWidth(), imagenOr.getHeight(), Image.SCALE_SMOOTH);
                    }else{
                        imagenResize = imagenOr.getScaledInstance(1140, Math.round(altura), Image.SCALE_SMOOTH);
                    }
                    
                    switch(panel){
                        case 0:
                            imagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                            imagen.setIcon(new ImageIcon(imagenResize));
                            break;
                        case 1:
                            imagen2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                            imagen2.setIcon(new ImageIcon(imagenResize));
                            break;
                        case 3:
                            imagen3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                            imagen3.setIcon(new ImageIcon(imagenResize));
                            break;
                    }
                    
                    found = true;
                    break;
                }catch (Exception ex1){
                    
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
    
    private void getSucursales(){
        var nodo = Program.grafoLugares.getLugares().getHead();
        int id = 0;
        while(nodo != null){

            System.out.println(nodo.getValor());
            if(nodo.getValor().isSucursal()){
                sucursalCB.addItem(new ComboSucursal(id, nodo.getValor()));
                id++;
            }


            nodo = nodo.getSiguiente();
        }
    }

    private void getMensajeros(){
        var lista = Program.tablaMensajeros.getTabla();
        int id = 0;
        for(int i = 0; i < lista.length; i++){
            if(lista[i] != null){
                mensajeroCB.addItem(new ComboMensajero(id, lista[i]));
                id++;
            }
        }
    }

    private void getImagenes(){
        Lista<Imagen> lista = Program.loggedUser.getArbolImagenes().recorrer();
        var aux = lista.getHead();
        while(aux != null){
            listaImagen.addItem("Imagen " + aux.getValor().getId());
            aux = aux.getSiguiente();
        }
    }
}

class ComboSucursal{
    private int id;
    private Lugar valor;

    public ComboSucursal(int id, Lugar valor) {
        this.id = id;
        this.valor = valor;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Lugar getValor() {
        return this.valor;
    }

    public void setValor(Lugar valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return valor.getNombre();
    }
    
}

class ComboMensajero{
    private int id;
    private Mensajero valor;

    public ComboMensajero(int id, Mensajero valor) {
        this.id = id;
        this.valor = valor;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mensajero getValor() {
        return this.valor;
    }

    public void setValor(Mensajero valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return valor.getNombres() + " " + valor.getApellidos();
    }
    
}
