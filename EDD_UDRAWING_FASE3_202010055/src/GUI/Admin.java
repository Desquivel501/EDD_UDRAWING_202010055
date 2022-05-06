package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.springframework.security.crypto.bcrypt.BCrypt;

import ArbolB.NodoB;
import Lector.*;
import Models.*;
import Program.Program;
import java.awt.Image;


public class Admin extends JFrame implements ActionListener{

    Cliente cliente;

    JTabbedPane tp, cp;
    JPanel p1,p2,p3 ,c1,c2,c3,r, panelB, panelC, l1 , leerLugar, verLugar, g;
    JButton cargaCliente, regresar, buscarBtn, nuevoBtn, bloqueBtn, eliminarBtn, buscarBtn1, buscarBtn2, generarImagen, listaClientes, cargarLugar, cargarRuta, verGrafo, verLista, nuevoMen, cargaMen, actBtn, blockBtn, hashBtn, arbolBtn;
    JLabel cargaMasiva, buscarLbl, usuarioLabel, passLabel, tempoLbl, buscarLbl2, usuarioLabel2, passLabel2, dpiLbl2, buscarLbl1, usuarioLabel1, passLabel1, dpiLbl1, imagen, imagen2, imagen3, imagen4;
    JTextField barraBusqueda, nameJT, passJT, dpiJt, nameJT1, passJT1, dpiJt1, nameJT2, passJT2, dpiJt2, barraBusqueda2, barraBusqueda1;
    JScrollPane panelImagen, panelImagen2, panelImagen3, panelImagen4;
    JRadioButton r1,r2,r3, r4;
    ButtonGroup bg;
    JTable tablaImagenes;
    JSpinner tiempoSpin, ceroSpin, viajeInicio, viajeFin;
    JButton topMensajero, topViajes, reporteRuta;

    JLabel correoLbl1, nameLbl1, telLbl1, id_munLbl1, dirLbl1, correoLbl2, nameLbl2, telLbl2, id_munLbl2, dirLbl2, correoLbl, ceroLbl, telLbl, id_munLbl, dirLbl, dpiJLbl3,nombreLbl,apellidoLbl,licencialbl,genLbl, telLbl3,dirLbl3;
    JTextField correoJT1, userJT1, telJT1, id_munJT1, dirJT1, correoJT2, userJT2, telJT2, id_munJT2, dirJT2, correoJT, userJT, telJT, id_munJT, dirJT, dpiJT3, nombreJT, apellidoJT, genJT, dirJT3, telJT3;
    JComboBox<String> licenciaJT;


    public Admin(){
        this.setTitle("Administardor");
        this.setSize(1010,700);
        this.setLayout(null);
        inicializar();
        getConfig();
        setLocationRelativeTo(null);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void inicializar(){

        regresar = new JButton("Cerrar Sesion");
        this.add(regresar);
        regresar.setBounds(850,8,120,30);
        regresar.addActionListener(this);

        tp = new JTabbedPane();
        tp.setBounds(10,10,990,780);
        this.add(tp);

        p1 = new JPanel();
        p1.setLayout(null);

        c1 = new JPanel();
        c1.setLayout(null);
        c2 = new JPanel();
        c2.setLayout(null);
        c3 = new JPanel();
        c3.setLayout(null);

        r = new JPanel();
        r.setLayout(null);

        l1 = new JPanel();
        l1.setLayout(null);

        g = new JPanel();
        g.setLayout(null);


        // //------------------------------------------------------------------------------------------------------------------------------------

        tempoLbl = new JLabel("Tiempo para creacion de bloque");
        c2.add(tempoLbl);
        tempoLbl.setBounds(225,100,250,30);

        tiempoSpin = new JSpinner();
        c2.add(tiempoSpin);
        tiempoSpin.setBounds(225,125,250,40);

        // //--------------------------------------------

        ceroLbl = new JLabel("Prueba de Trabajo");
        c2.add(ceroLbl);
        ceroLbl.setBounds(500,100,250,30);

        ceroSpin = new JSpinner();
        c2.add(ceroSpin);
        ceroSpin.setBounds(500,125,250,40);

        //  //------------------------------------------------------------------------------------------------------------------------------------

        actBtn = new JButton();
        c2.add(actBtn);
        actBtn.setText("Actualizar");
        actBtn.setBounds(350, 190, 300, 40);
        actBtn.addActionListener(this);


        //  //------------------------------------------------------------------------------------------------------------------------------------

        bloqueBtn = new JButton();
        c2.add(bloqueBtn);
        bloqueBtn.setText("Crear Bloque");
        bloqueBtn.setBounds(350, 240, 300, 40);
        bloqueBtn.addActionListener(this);

        // //---------------------------------------------------------------------------------------------------------------------------------------------------------
        // //---------------------------------------------------------------------------------------------------------------------------------------------------------


        dpiLbl1 = new JLabel("DPI");
        c1.add(dpiLbl1);
        dpiLbl1.setBounds(225,75,250,30);

        dpiJt1 = new JTextField();
        c1.add(dpiJt1);
        dpiJt1.setBounds(225,100,250,40);

        //--------------------------------------------

        nameLbl1 = new JLabel("Nombre Completo");
        c1.add(nameLbl1);
        nameLbl1.setBounds(500,75,250,30);

        nameJT1 = new JTextField();
        c1.add(nameJT1);
        nameJT1.setBounds(500,100,250,40);


         //------------------------------------------------------------------------------------------------------------------------------------

        usuarioLabel1 = new JLabel("Nombre de Usuario");
        c1.add(usuarioLabel1);
        usuarioLabel1.setBounds(225,150,250,30);

        userJT1 = new JTextField();
        c1.add(userJT1);
        userJT1.setBounds(225,175,250,40);

        //--------------------------------------------

        passLabel1 = new JLabel("Contraseña");
        c1.add(passLabel1);
        passLabel1.setBounds(500,150,250,30);

        passJT1 = new JTextField();
        c1.add(passJT1);
        passJT1.setBounds(500,175,250,40);

        //------------------------------------------------------------------------------------------------------------------------------------


        correoLbl1 = new JLabel("Correo");
        c1.add(correoLbl1);
        correoLbl1.setBounds(225,225,250,30);

        correoJT1 = new JTextField();
        c1.add(correoJT1);
        correoJT1.setBounds(225,250,250,40);

         //--------------------------------------------

        telLbl1 = new JLabel("Telefono");
        c1.add(telLbl1);
        telLbl1.setBounds(500,225,250,30);

        telJT1 = new JTextField();
        c1.add(telJT1);
        telJT1.setBounds(500,250,250,40);

        //------------------------------------------------------------------------------------------------------------------------------------

        dirLbl1 = new JLabel("Direccion");
        c1.add(dirLbl1);
        dirLbl1.setBounds(225,300,250,30);

        dirJT1 = new JTextField();
        c1.add(dirJT1);
        dirJT1.setBounds(225,325,250,40);

        //--------------------------------------------

        id_munLbl1 = new JLabel("Id Municipio");
        c1.add(id_munLbl1);
        id_munLbl1.setBounds(500,300,250,30);

        id_munJT1 = new JTextField();
        c1.add(id_munJT1);
        id_munJT1.setBounds(500,325,250,40);

        //------------------------------------------------------------------------------------------------------------------------------------

        nuevoBtn = new JButton();
        c1.add(nuevoBtn);
        nuevoBtn.setText("Crear Usuario");
        nuevoBtn.setBounds(350, 400, 300, 40);
        nuevoBtn.addActionListener(this);

        cargaCliente = new JButton("Carga Masiva");
        c1.add(cargaCliente);
        cargaCliente.setBounds(350,460,300,40);
        cargaCliente.addActionListener(this);


        //------------------------------------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------


        generarImagen = new JButton("Generar Imagen");
        p1.add(generarImagen);
        generarImagen.setBounds(375, 10, 250, 30);
        generarImagen.addActionListener(this);

        imagen = new JLabel();
        panelImagen = new JScrollPane(imagen);
        p1.add(panelImagen);
        panelImagen.setBounds(10,50,950,555);
        panelImagen.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //--------------------------------------------------------------------------

        leerLugar = new JPanel();
        leerLugar.setBorder(new TitledBorder("Carga Masiva"));
        l1.add(leerLugar);
        leerLugar.setBounds(40, 10, 430, 60);
        leerLugar.setLayout(null);

        cargarLugar = new JButton("Cargar Lugares");
        leerLugar.add(cargarLugar);
        cargarLugar.setBounds(10, 20, 200, 30);
        cargarLugar.addActionListener(this);

        cargarRuta = new JButton("Cargar Rutas");
        leerLugar.add(cargarRuta);
        cargarRuta.setBounds(220, 20, 200, 30);
        cargarRuta.addActionListener(this);


        verLugar = new JPanel();
        verLugar.setBorder(new TitledBorder("Visualizar"));
        l1.add(verLugar);
        verLugar.setBounds(490, 10, 430, 60);
        verLugar.setLayout(null);

        verGrafo = new JButton("Ver Grafo");
        verLugar.add(verGrafo);
        verGrafo.setBounds(10, 20, 200, 30);
        verGrafo.addActionListener(this);

        verLista = new JButton("Ver Lista de Adyacencia");
        verLugar.add(verLista);
        verLista.setBounds(220, 20, 200, 30);
        verLista.addActionListener(this);


        imagen2 = new JLabel();
        panelImagen2 = new JScrollPane(imagen2);
        l1.add(panelImagen2);
        panelImagen2.setBounds(10,75,950,535);
        panelImagen2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //------------------------------------------------------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------------------------------------------------


        dpiJLbl3 = new JLabel("DPI");
        c3.add(dpiJLbl3);
        dpiJLbl3.setBounds(370,75,250,30);

        dpiJT3 = new JTextField();
        c3.add(dpiJT3);
        dpiJT3.setBounds(370,100,250,40);

         //------------------------------------------------------------------------------------------------------------------------------------

        nombreLbl = new JLabel("Nombres");
        c3.add(nombreLbl);
        nombreLbl.setBounds(225,150,250,30);

        nombreJT = new JTextField();
        c3.add(nombreJT);
        nombreJT.setBounds(225,175,250,40);

        //--------------------------------------------

        apellidoLbl = new JLabel("Apellidos");
        c3.add(apellidoLbl);
        apellidoLbl.setBounds(500,150,250,30);

        apellidoJT = new JTextField();
        c3.add(apellidoJT);
        apellidoJT.setBounds(500,175,250,40);

        //------------------------------------------------------------------------------------------------------------------------------------


        licencialbl = new JLabel("Tipo Licencia");
        c3.add(licencialbl);
        licencialbl.setBounds(225,225,250,30);

        String licencias[]={"A", "B", "C"};
        licenciaJT = new JComboBox<>(licencias);
        c3.add(licenciaJT);
        licenciaJT.setBounds(225,250,250,40);

         //--------------------------------------------

        genLbl = new JLabel("Genero");
        c3.add(genLbl);
        genLbl.setBounds(500,225,250,30);

        genJT = new JTextField();
        c3.add(genJT);
        genJT.setBounds(500,250,250,40);

        //------------------------------------------------------------------------------------------------------------------------------------

        telLbl3 = new JLabel("Telefono");
        c3.add(telLbl3);
        telLbl3.setBounds(225,300,250,30);

        telJT3 = new JTextField();
        c3.add(telJT3);
        telJT3.setBounds(225,325,250,40);

        //--------------------------------------------

        dirLbl3 = new JLabel("Direccion");
        c3.add(dirLbl3);
        dirLbl3.setBounds(500,300,250,30);

        dirJT3 = new JTextField();
        c3.add(dirJT3);
        dirJT3.setBounds(500,325,250,40);

        //------------------------------------------------------------------------------------------------------------------------------------

        nuevoMen = new JButton();
        c3.add(nuevoMen);
        nuevoMen.setText("Crear Mensajero");
        nuevoMen.setBounds(350, 400, 300, 40);
        nuevoMen.addActionListener(this);

        cargaMen = new JButton("Carga Masiva");
        c3.add(cargaMen);
        cargaMen.setBounds(350,460,300,40);
        cargaMen.addActionListener(this);


        //------------------------------------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------


        hashBtn = new JButton("Tabla Hash");
        g.add(hashBtn);
        hashBtn.setBounds(100, 10, 250, 30);
        hashBtn.addActionListener(this);

        arbolBtn = new JButton("Arbol");
        g.add(arbolBtn);
        arbolBtn.setBounds(360, 10, 250, 30);
        arbolBtn.addActionListener(this);

        blockBtn = new JButton("Blockchain");
        g.add(blockBtn);
        blockBtn.setBounds(620, 10, 250, 30);
        blockBtn.addActionListener(this);

        imagen3 = new JLabel();
        panelImagen3 = new JScrollPane(imagen3);
        g.add(panelImagen3);
        panelImagen3.setBounds(10,50,950,555);
        panelImagen3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        //------------------------------------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------


        panelC = new JPanel();
        panelC.setBorder(new TitledBorder("Top Clientes"));
        r.add(panelC);
        panelC.setBounds(740, 195, 220, 60);
        panelC.setLayout(null);

        listaClientes = new JButton("Generar");
        panelC.add(listaClientes);
        listaClientes.setBounds(10, 20 , 200, 30);
        listaClientes.addActionListener(this);

       //-------------------------------------------------------------------------------------------------------------------------------------------------

        panelC = new JPanel();
        panelC.setBorder(new TitledBorder("Top Mensajeros"));
        r.add(panelC);
        panelC.setBounds(740, 265, 220, 60);
        panelC.setLayout(null);

        topMensajero = new JButton("Generar");
        panelC.add(topMensajero);
        topMensajero.setBounds(10, 20 , 200, 30);
        topMensajero.addActionListener(this);

        //-------------------------------------------------------------------------------------------------------------------------------------------------

        panelC = new JPanel();
        panelC.setBorder(new TitledBorder("Viajes mas Largos"));
        r.add(panelC);
        panelC.setBounds(740, 335, 220, 60);
        panelC.setLayout(null);

        topViajes = new JButton("Generar");
        panelC.add(topViajes);
        topViajes.setBounds(10, 20 , 200, 30);
        topViajes.addActionListener(this);

    //-------------------------------------------------------------------------------------------------------------------------------------------------

        panelC = new JPanel();
        panelC.setBorder(new TitledBorder("Simular Ruta"));
        r.add(panelC);
        panelC.setBounds(740, 10, 220, 175);
        panelC.setLayout(null);


        genLbl = new JLabel("Inicio");
        panelC.add(genLbl);
        genLbl.setBounds(10,20,200,30);

        viajeInicio = new JSpinner();
        panelC.add(viajeInicio);
        viajeInicio.setBounds(10,40,200,30);


        genLbl = new JLabel("Fin");
        panelC.add(genLbl);
        genLbl.setBounds(10,75,200,30);

        viajeFin = new JSpinner();
        panelC.add(viajeFin);
        viajeFin.setBounds(10,95,200,30);

        reporteRuta = new JButton("Generar");
        panelC.add(reporteRuta);
        reporteRuta.setBounds(10, 135 , 200, 30);
        reporteRuta.addActionListener(this);


        imagen4 = new JLabel();
        panelImagen4 = new JScrollPane(imagen4);
        r.add(panelImagen4);
        panelImagen4.setBounds(10,10,720,600);
        panelImagen4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        tp.add("Nuevo Cliente",c1);
        tp.add("Mensajeros",c3);
        tp.add("Entregas", l1);
        tp.add("Blockchain",c2);
        tp.add("Reportes 1", r);
        tp.add("Reportes 2", g);
       
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Program.stop();
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cargaCliente){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setPreferredSize( new Dimension(700, 600));

            if(fileChooser.showOpenDialog(this) == JFileChooser.OPEN_DIALOG){
                File archivo = fileChooser.getSelectedFile();

                if(archivo != null){
                    Lector lector = new Lector();
                    if(!lector.leerClientes(archivo)){
                        JOptionPane.showMessageDialog(this,
                        "Se han cargado los clientes.",
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
            setVisible(false);
            dispose();
        }

        if(e.getSource() == buscarBtn){
            if(Program.arbolClientes.vacio()){
                JOptionPane.showMessageDialog(this,
                "No hay clientes ingresados en el sistema.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            Long dpi = Long.parseLong(barraBusqueda.getText().toString());

            var arbolClientes= Program.arbolClientes;
            NodoB encontrado = arbolClientes.buscar(dpi);

            if(encontrado != null){
                Cliente cliente = encontrado.getCliente();

                dpiJt.setText(Long.toString(cliente.getDpi()));
                nameJT.setText(cliente.getName());
                userJT.setText(cliente.getUsername());
                correoJT.setText(cliente.getCorreo());
                passJT.setText(cliente.getPassword());
                telJT.setText(cliente.getPhone());
                dirJT.setText(cliente.getAddress());
                id_munJT.setText(Integer.toString(cliente.getId_municipio()));

                this.cliente = cliente;

            }else{
                JOptionPane.showMessageDialog(this,
                "No se ha encontrado el cliente.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if(e.getSource() == bloqueBtn){
            Program.crearBloque();
        }

        if(e.getSource() == actBtn){
            Program.noCero = (int)ceroSpin.getValue();
            Program.tiempo =(int)((int)tiempoSpin.getValue() * 60000);

            Program.stop();
            Program.start();
        }

        if(e.getSource() == nuevoBtn){

            if(nameJT1.getText().equals("")){
                JOptionPane.showMessageDialog(this,
                "Ingrese un nombre.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(dpiJt1.getText().equals("")){
                JOptionPane.showMessageDialog(this,
                "Ingrese un DPI.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(passJT1.getText().equals("")){
                JOptionPane.showMessageDialog(this,
                "Ingrese una contraseña.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }

            String username = userJT1.getText().toString();
            Long dpi = -999l;
            String password = passJT1.getText().toString();

            try{
                dpi = Long.parseLong(dpiJt1.getText().toString());
            }catch (Exception ex){
                JOptionPane.showMessageDialog(this,
                "El DPI no es valido.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }

            var arbol = Program.arbolClientes;

            NodoB nodoDPI = arbol.buscar(dpi);
            if(nodoDPI != null){
                JOptionPane.showMessageDialog(this,
                "Ya existe un usuario con ese DPI.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }

            NodoB nodoNombre = arbol.buscarNombre(username);
            if(nodoNombre != null){
                JOptionPane.showMessageDialog(this,
                "Ya existe un usuario con ese Username.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            String hashedPass = BCrypt.hashpw(password, BCrypt.gensalt(10));

            Cliente nuevo = new Cliente(dpi, username, hashedPass, nameJT1.getText(), correoJT1.getText(), telJT1.getText(), dirJT1.getText(), Integer.parseInt(id_munJT1.getText()));
            arbol.insertar(dpi, nuevo);
            JOptionPane.showMessageDialog(this,
                "Se ha creado el cliente.",
                "Completado",
            JOptionPane.INFORMATION_MESSAGE);

        }

        if(e.getSource() == generarImagen){
            if(Program.arbolClientes.vacio()){
                JOptionPane.showMessageDialog(this,
                "No hay clientes ingresados en el sistema.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }

            var arbol = Program.arbolClientes;

            String nombre = arbol.graficar();

            if(nombre.equals("")){
                JOptionPane.showMessageDialog(this,
                "Ha ocurrrido un error al generar la imagen.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            generarImagen(nombre,1);

        }

        if(e.getSource() == buscarBtn1){
            if(Program.arbolClientes.vacio()){
                JOptionPane.showMessageDialog(this,
                "No hay clientes ingresados en el sistema.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }

            Long dpi = 0l;
            try{
                dpi = Long.parseLong(barraBusqueda1.getText().toString());
            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(this,
                "DPI no valido.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }

            var nodo = Program.arbolClientes.buscar(dpi);
            if(nodo != null){
                var cliente = nodo.getCliente();
                DefaultTableModel model = new DefaultTableModel();

                model.addColumn("Parametro");
                model.addColumn("Datos");

                tablaImagenes = new JTable(model);

                model.addRow(new Object[]{"Nombre" ,cliente.getName()});
                model.addRow(new Object[]{"DPI" ,cliente.getDpi()});
                model.addRow(new Object[]{"Contraseña" ,cliente.getPassword()});

                var albumes = cliente.getListaAlbum();
                model.addRow(new Object[]{"Cantidad Albumes" ,albumes.getLargo()});

                if(!albumes.vacia()){
                    model.addRow(new Object[]{"Lista Albumes" ,"Imagenes"});
                    var nodoAlbum = albumes.getHead();
                    while(nodoAlbum != null){
                        var album = nodoAlbum.getAlbum();
                        StringBuilder str = new StringBuilder();
                        var nodoImagen = album.getPrimero();

                        if(nodoImagen != null){
                            while(nodoImagen != null){
                                str.append("Imagen " + nodoImagen.getImagen().getId() + ", ");
                                nodoImagen = nodoImagen.getSiguiente();
                            }
                            str.deleteCharAt(str.lastIndexOf(","));
                        }

                        model.addRow(new Object[]{"   -> " + nodoAlbum.getAlbum().getNombre(), str.toString()});
                        nodoAlbum = nodoAlbum.getSiguiente();
                    }
                }

                model.addRow(new Object[]{"Cantidad Imagenes" ,cliente.getArbolImagenes().noImagenes()});
                model.addRow(new Object[]{"Cantidad Capas" ,cliente.getArbolCapas().largo});


                panelImagen2.setSize(0, 0);
                panelImagen2 = new JScrollPane(tablaImagenes);
                r.add(panelImagen2);
                panelImagen2.setBounds(265,130,450,350);
                panelImagen2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                panelImagen2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            }else{
                JOptionPane.showMessageDialog(this,
                "No se ha encontrado el cliente..",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if(e.getSource() == cargarLugar){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setPreferredSize( new Dimension(700, 600));

            if(fileChooser.showOpenDialog(this) == JFileChooser.OPEN_DIALOG){
                File archivo = fileChooser.getSelectedFile();

                if(archivo != null){
                    Lector lector = new Lector();
                    if(!lector.leerLugares(archivo)){
                        JOptionPane.showMessageDialog(this,
                        "Se han cargado los lugares.",
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

        if(e.getSource() == cargarRuta){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setPreferredSize( new Dimension(700, 600));

            if(fileChooser.showOpenDialog(this) == JFileChooser.OPEN_DIALOG){
                File archivo = fileChooser.getSelectedFile();

                if(archivo != null){
                    Lector lector = new Lector();
                    if(!lector.leerRutas(archivo)){
                        JOptionPane.showMessageDialog(this,
                        "Se han cargado las rutas.",
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

        if(e.getSource() == verGrafo){
            if(!Program.grafoLugares.valido()){
                JOptionPane.showMessageDialog(this,
                "No se han ingresado datos del grafo.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }

            var grafo = Program.grafoLugares;

            String nombre = grafo.graficarGrafo();

            if(nombre == null){
                JOptionPane.showMessageDialog(this,
                "Ha ocurrrido un error al generar la imagen.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            generarImagen(nombre, 2);

        }

        if(e.getSource() == verLista){
            if(!Program.grafoLugares.valido()){
                JOptionPane.showMessageDialog(this,
                "No se han ingresado datos del grafo.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }

            var grafo = Program.grafoLugares;

            String nombre = grafo.graficarLista();

            if(nombre == null){
                JOptionPane.showMessageDialog(this,
                "Ha ocurrrido un error al generar la imagen.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            generarImagen(nombre, 2);

        }

        if(e.getSource() == nuevoMen){

            long dpi = -999l;
            try{
                dpi = Long.parseLong(dpiJT3.getText().toString());
            }catch (Exception ex){
                JOptionPane.showMessageDialog(this,
                "El DPI no es valido.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }

            var tabla = Program.tablaMensajeros;
            var busqueda = tabla.buscar(dpi);
            if(busqueda != null){
                JOptionPane.showMessageDialog(this,
                "Ya existe un mensajero con ese DPI.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }

            Mensajero nuevo = new Mensajero(dpi, nombreJT.getText(), apellidoJT.getText(), licenciaJT.getSelectedItem().toString(), genJT.getText(), telJT3.getText(), dirJT3.getText());
            tabla.insertar(nuevo);
            JOptionPane.showMessageDialog(this,
                "Se ha creado el cliente.",
                "Completado",
            JOptionPane.INFORMATION_MESSAGE);

        }

        if(e.getSource() == cargaMen){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setPreferredSize( new Dimension(700, 600));

            if(fileChooser.showOpenDialog(this) == JFileChooser.OPEN_DIALOG){
                File archivo = fileChooser.getSelectedFile();

                if(archivo != null){
                    Lector lector = new Lector();
                    if(!lector.leerMensajero(archivo)){
                        JOptionPane.showMessageDialog(this,
                        "Se han cargado los mensajeros.",
                        "Cargado",
                        JOptionPane.INFORMATION_MESSAGE);
                        Program.tablaMensajeros.graficar();
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

        if(e.getSource() == hashBtn){

            var hash = Program.tablaMensajeros;

            String nombre = hash.graficar();

            if(nombre == null){
                JOptionPane.showMessageDialog(this,
                "Ha ocurrrido un error al generar la imagen.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            generarTabla(nombre);

        }

        if(e.getSource() == blockBtn){
            Program.recargarBloques();
            String nombre = Program.graficarBloques();

            if(nombre == null){
                JOptionPane.showMessageDialog(this,
                "Ha ocurrrido un error al generar la imagen.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            generarImagen(nombre, 3);

        }

        if(e.getSource() == topViajes){

            String nombre = Program.topViajes();

            if(nombre == null){
                JOptionPane.showMessageDialog(this,
                "Ha ocurrrido un error al generar la imagen.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            generarImagen(nombre, 4);

        }

        if(e.getSource() == listaClientes){

            String nombre = Program.reporteClientes();

            if(nombre == null){
                JOptionPane.showMessageDialog(this,
                "Ha ocurrrido un error al generar la imagen.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            generarImagen(nombre, 4);

        }

        if(e.getSource() == topMensajero){

            String nombre = Program.reporteMensajero();

            if(nombre == null){
                JOptionPane.showMessageDialog(this,
                "Ha ocurrrido un error al generar la imagen.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            generarImagen(nombre, 4);

        }

        if(e.getSource() == arbolBtn){
            String nombre = Program.crearArbol();

            if(nombre == null){
                JOptionPane.showMessageDialog(this,
                "Ha ocurrrido un error al generar la imagen.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            generarImagen(nombre, 3);
        }

        if(e.getSource() == reporteRuta){
            int inicio = (int) viajeInicio.getValue();
            int fin = (int) viajeFin.getValue();

            String nombre = Program.simularRuta(inicio,fin);

            if(nombre == null){
                JOptionPane.showMessageDialog(this,
                "Ha ocurrrido un error al generar la imagen.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            generarImagen(nombre, 4);

        }
    }

    private void generarImagen(String nombre, int id){
        try{
            boolean found = false;
            long startTime = System.currentTimeMillis();

            while(System.currentTimeMillis() < startTime + 30000) {
                try{
                    BufferedImage imagenOr = ImageIO.read(new File(nombre));
                    float largo = ((float)555/imagenOr.getHeight()) * imagenOr.getWidth();

                    Image imagenResize;
                    if(imagenOr.getWidth() < 950){
                        imagenResize = imagenOr.getScaledInstance(imagenOr.getWidth(), imagenOr.getHeight(), Image.SCALE_SMOOTH);
                    }else{
                        // imagenResize = imagenOr.getScaledInstance(950, Math.round(altura), Image.SCALE_SMOOTH);
                        imagenResize = imagenOr.getScaledInstance(Math.round(largo), 555, Image.SCALE_SMOOTH);
                    }

                    switch(id){
                        case 1:
                            imagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                            imagen.setIcon(new ImageIcon(imagenResize));
                            break;
                        case 2:
                            imagen2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                            imagen2.setIcon(new ImageIcon(imagenOr));
                            break;
                        case 3:
                            imagen3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                            imagen3.setIcon(new ImageIcon(imagenResize));
                            break;
                        case 4:
                            imagen4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                            imagen4.setIcon(new ImageIcon(imagenOr));
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


    private void generarTabla(String nombre){
        try{
            boolean found = false;
            long startTime = System.currentTimeMillis();

            while(System.currentTimeMillis() < startTime + 30000) {
                try{
                    BufferedImage imagenOr = ImageIO.read(new File(nombre));
                    float alto = ((float)930/imagenOr.getWidth()) * imagenOr.getHeight();

                    Image imagenResize;
                    if(imagenOr.getWidth() < 555){
                        imagenResize = imagenOr.getScaledInstance(imagenOr.getWidth(), imagenOr.getHeight(), Image.SCALE_SMOOTH);
                    }else{
                        // imagenResize = imagenOr.getScaledInstance(950, Math.round(altura), Image.SCALE_SMOOTH);
                        imagenResize = imagenOr.getScaledInstance(930, Math.round(alto), Image.SCALE_SMOOTH);
                    }

                    imagen3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    imagen3.setIcon(new ImageIcon(imagenResize));

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


    private void getConfig(){
        ceroSpin.setValue(Program.noCero);
        tiempoSpin.setValue(Program.tiempo/60000);
    }
}
