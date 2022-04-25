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
import ArbolB.NodoB;
import Lector.*;
import Models.*;
import Program.Program;
import java.awt.Image;


public class Admin extends JFrame implements ActionListener{

    Cliente cliente;

    JTabbedPane tp, cp;
    JPanel p1,p2,p3 ,c1,c2,c3,r, panelB, panelC;
    JButton cargaCliente, regresar, buscarBtn, nuevoBtn, actualizarBtn, eliminarBtn, buscarBtn1, buscarBtn2, generarImagen, listaClientes;
    JLabel cargaMasiva, buscarLbl, usuarioLabel, passLabel, dpiLbl, buscarLbl2, usuarioLabel2, passLabel2, dpiLbl2, buscarLbl1, usuarioLabel1, passLabel1, dpiLbl1, imagen;
    JTextField barraBusqueda, nameJT, passJT, dpiJt, nameJT1, passJT1, dpiJt1, nameJT2, passJT2, dpiJt2, barraBusqueda2, barraBusqueda1;
    JScrollPane panelImagen, panelImagen2;
    JRadioButton r1,r2,r3, r4;
    ButtonGroup bg;
    JTable tablaImagenes;
    

    public Admin(){
        this.setTitle("Administardor");
        this.setSize(1000,700);
        this.setLayout(null);
        inicializar();
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
        tp.setBounds(10,10,980,780);
        this.add(tp); 

        p1 = new JPanel();  
        p1.setLayout(null); 
        p2 = new JPanel(); 
        p2.setLayout(null); 
        p3 = new JPanel();  
        p3.setLayout(null); 

        c1 = new JPanel();  
        c1.setLayout(null); 
        c2 = new JPanel(); 
        c2.setLayout(null); 
        c3 = new JPanel();  
        c3.setLayout(null); 

        r = new JPanel();  
        r.setLayout(null); 

        //-----------------------------------------------------------

        buscarLbl = new JLabel("Buscar Cliente");
        c2.add(buscarLbl);
        buscarLbl.setBounds(260,25,100,15);

        barraBusqueda = new JTextField();
        c2.add(barraBusqueda);
        barraBusqueda.setBounds(260,40,300,40);

        buscarBtn = new JButton("Buscar");
        c2.add(buscarBtn);
        buscarBtn.setBounds(570,40,150,40);
        buscarBtn.addActionListener(this);

        //--------------------------------------------

        usuarioLabel = new JLabel("Nombre de Usuario");
        c2.add(usuarioLabel);
        usuarioLabel.setBounds(375,140,250,30);

        nameJT = new JTextField();
        c2.add(nameJT);
        nameJT.setBounds(375,165,250,40);

         //--------------------------------------------

        dpiLbl = new JLabel("DPI");
        c2.add(dpiLbl);
        dpiLbl.setBounds(375,215,250,30);

        dpiJt = new JTextField();
        c2.add(dpiJt);
        dpiJt.setBounds(375,240,250,40);
        dpiJt.setEnabled(false);

        //--------------------------------------------

        passLabel = new JLabel("Contraseña");
        c2.add(passLabel);
        passLabel.setBounds(375,290,250,30);

        passJT = new JTextField();
        c2.add(passJT);
        passJT.setBounds(375,315,250,40);

        //--------------------------------------------

        actualizarBtn = new JButton();
        c2.add(actualizarBtn);
        actualizarBtn.setText("Actualizar");
        actualizarBtn.setBounds(375, 400, 250, 40);
        actualizarBtn.addActionListener(this);

        //---------------------------------------------------
        //---------------------------------------------------

        usuarioLabel1 = new JLabel("Nombre de Usuario");
        c1.add(usuarioLabel1);
        usuarioLabel1.setBounds(375,100,250,30);

        nameJT1 = new JTextField();
        c1.add(nameJT1);
        nameJT1.setBounds(375,125,250,40);

         //--------------------------------------------

        dpiLbl1 = new JLabel("DPI");
        c1.add(dpiLbl1);
        dpiLbl1.setBounds(375,175,250,30);

        dpiJt1 = new JTextField();
        c1.add(dpiJt1);
        dpiJt1.setBounds(375,200,250,40);

        //--------------------------------------------

        passLabel1 = new JLabel("Contraseña");
        c1.add(passLabel1);
        passLabel1.setBounds(375,250,250,30);

        passJT1 = new JTextField();
        c1.add(passJT1);
        passJT1.setBounds(375,275,250,40);

        //--------------------------------------------

        nuevoBtn = new JButton();
        c1.add(nuevoBtn);
        nuevoBtn.setText("Crear Usuario");
        nuevoBtn.setBounds(375, 335, 250, 40);
        nuevoBtn.addActionListener(this);

        cargaCliente = new JButton("Carga Masiva");
        c1.add(cargaCliente);
        cargaCliente.setBounds(375,390,250,40);
        cargaCliente.addActionListener(this);


        //------------------------------------------------
        //-------------------------------------------------

        
        buscarLbl2 = new JLabel("Buscar Cliente");
        c3.add(buscarLbl2);
        buscarLbl2.setBounds(260,25,100,15);

        barraBusqueda2 = new JTextField();
        c3.add(barraBusqueda2);
        barraBusqueda2.setBounds(260,40,300,40);

        buscarBtn2 = new JButton("Buscar");
        c3.add(buscarBtn2);
        buscarBtn2.setBounds(570,40,150,40);
        buscarBtn2.addActionListener(this);

        //--------------------------------------------

        usuarioLabel2 = new JLabel("Nombre de Usuario");
        c3.add(usuarioLabel2);
        usuarioLabel2.setBounds(375,140,250,30);

        nameJT2 = new JTextField();
        c3.add(nameJT2);
        nameJT2.setBounds(375,165,250,40);
        nameJT2.setEnabled(false);

         //--------------------------------------------

        dpiLbl2 = new JLabel("DPI");
        c3.add(dpiLbl2);
        dpiLbl2.setBounds(375,215,250,30);

        dpiJt2 = new JTextField();
        c3.add(dpiJt2);
        dpiJt2.setBounds(375,240,250,40);
        dpiJt2.setEnabled(false);

        //--------------------------------------------

        passLabel2 = new JLabel("Contraseña");
        c3.add(passLabel2);
        passLabel2.setBounds(375,290,250,30);

        passJT2 = new JTextField();
        c3.add(passJT2);
        passJT2.setBounds(375,315,250,40);
        passJT2.setEnabled(false);

        //--------------------------------------------

        eliminarBtn = new JButton();
        c3.add(eliminarBtn);
        eliminarBtn.setText("Eliminar Usuario");
        eliminarBtn.setBounds(375, 400, 250, 40);
        eliminarBtn.addActionListener(this);
        eliminarBtn.setEnabled(false);

        //--------------------------------------------
        //--------------------------------------------

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

        panelB = new JPanel();
        panelB.setBorder(new TitledBorder("Buscar Cliente"));
        r.add(panelB);
        panelB.setBounds(140, 10, 330, 80);
        panelB.setLayout(null);

        barraBusqueda1 = new JTextField();
        panelB.add(barraBusqueda1);
        barraBusqueda1.setBounds(10,30,200,30);

        buscarBtn1 = new JButton("Buscar");
        panelB.add(buscarBtn1);
        buscarBtn1.setBounds(220,30,100,30);
        buscarBtn1.addActionListener(this);

        //--------------------------------------------------------------------------

        panelC = new JPanel();
        panelC.setBorder(new TitledBorder("Listar Clientes"));
        r.add(panelC);
        panelC.setBounds(480, 10, 330, 80);
        panelC.setLayout(null);

        listaClientes = new JButton("Generar");
        panelC.add(listaClientes);
        listaClientes.setBounds(65, 30 , 200, 30);
        listaClientes.addActionListener(this);



        panelImagen2 = new JScrollPane();

        //----------------------------------------------------------------------


        tp.add("Reporte Usuarios",p1); 
        tp.add("Nuevo Usuario",c1); 
        tp.add("Modificar Usuario",c2); 
        tp.add("Borrar Usuario",c3);  
        tp.add("Reportes", r);   

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
        //             if(!lector.leerClientes(archivo)){
        //                 JOptionPane.showMessageDialog(this,
        //                 "Se han cargado los clientes.",
        //                 "Cargado",
        //                 JOptionPane.INFORMATION_MESSAGE);
        //             }
        //             else{
        //                 JOptionPane.showMessageDialog(this,
        //                 "Ha ocurrido un error abriendo el archivo.",
        //                 "Error",
        //                 JOptionPane.ERROR_MESSAGE);
        //             }
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
        
        // if(e.getSource() == regresar){
        //     new Login();
        //     setVisible(false);
        //     dispose();
        // }

        // if(e.getSource() == buscarBtn){
        //     if(Program.arbolClientes.vacio()){
        //         JOptionPane.showMessageDialog(this,
        //         "No hay clientes ingresados en el sistema.",
        //         "Error",
        //         JOptionPane.ERROR_MESSAGE);
        //         return;
        //     }
        //     Long dpi = Long.parseLong(barraBusqueda.getText().toString());

        //     var arbolClientes= Program.arbolClientes;
        //     NodoB encontrado = arbolClientes.buscar(dpi);

        //     if(encontrado != null){
        //         Cliente cliente = encontrado.getCliente();
        //         nameJT.setText(cliente.getNombre());
        //         dpiJt.setText(Long.toString(cliente.getDpi()));
        //         passJT.setText(cliente.getContraseña());
        //         this.cliente = cliente;
                
        //     }
        // }

        // if(e.getSource() == buscarBtn2){
        //     if(Program.arbolClientes.vacio()){
        //         JOptionPane.showMessageDialog(this,
        //         "No hay clientes ingresados en el sistema.",
        //         "Error",
        //         JOptionPane.ERROR_MESSAGE);
        //         return;
        //     }
        //     Long dpi = Long.parseLong(barraBusqueda2.getText().toString());

        //     var arbolClientes= Program.arbolClientes;
        //     NodoB encontrado = arbolClientes.buscar(dpi);

        //     if(encontrado != null){
        //         Cliente cliente = encontrado.getCliente();
        //         nameJT2.setText(cliente.getNombre());
        //         dpiJt2.setText(Long.toString(cliente.getDpi()));
        //         passJT2.setText(cliente.getContraseña());
        //         this.cliente = cliente;
        //     }
        // }

        // if(e.getSource() == actualizarBtn){

        //     if(Program.arbolClientes.vacio()){
        //         JOptionPane.showMessageDialog(this,
        //         "No hay clientes ingresados en el sistema.",
        //         "Error",
        //         JOptionPane.ERROR_MESSAGE);
        //         return;
        //     }

        //     if(this.cliente == null){
        //         JOptionPane.showMessageDialog(this,
        //         "No se ha seleccionado ningun cliente.",
        //         "Error",
        //         JOptionPane.ERROR_MESSAGE);
        //         return;
        //     }

        //     if(nameJT.getText().equals("")){
        //         JOptionPane.showMessageDialog(this,
        //         "Ingrese un nombre.",
        //         "Error",
        //         JOptionPane.ERROR_MESSAGE);
        //         return;
        //     }

        //     if(passJT.getText().equals("")){
        //         JOptionPane.showMessageDialog(this,
        //         "Ingrese una contraseña.",
        //         "Error",
        //         JOptionPane.ERROR_MESSAGE);
        //         return;
        //     }

        //     this.cliente.setNombre(nameJT.getText().toString());
        //     this.cliente.setContraseña(passJT.getText().toString());

        //     JOptionPane.showMessageDialog(this,
        //         "Se ha actualizado el cliente.",
        //         "Completado",
        //     JOptionPane.INFORMATION_MESSAGE);
            
        // }

        // if(e.getSource() == nuevoBtn){

        //     if(nameJT1.getText().equals("")){
        //         JOptionPane.showMessageDialog(this,
        //         "Ingrese un nombre.",
        //         "Error",
        //         JOptionPane.ERROR_MESSAGE);
        //         return;
        //     }

        //     if(dpiJt1.getText().equals("")){
        //         JOptionPane.showMessageDialog(this,
        //         "Ingrese un DPI.",
        //         "Error",
        //         JOptionPane.ERROR_MESSAGE);
        //         return;
        //     }

        //     if(passJT1.getText().equals("")){
        //         JOptionPane.showMessageDialog(this,
        //         "Ingrese una contraseña.",
        //         "Error",
        //         JOptionPane.ERROR_MESSAGE);
        //         return;
        //     }

        //     String nombre = nameJT1.getText().toString();
        //     Long dpi = -999l;
        //     String pass = passJT1.getText().toString();

        //     try{
        //         dpi = Long.parseLong(dpiJt1.getText().toString());
        //     }catch (Exception ex){
        //         JOptionPane.showMessageDialog(this,
        //         "El DPI no es valido.",
        //         "Error",
        //         JOptionPane.ERROR_MESSAGE);
        //         return;
        //     }

        //     var arbol = Program.arbolClientes;

        //     NodoB nodoDPI = arbol.buscar(dpi);
        //     if(nodoDPI != null){
        //         JOptionPane.showMessageDialog(this,
        //         "Ya existe un usuario con ese DPI.",
        //         "Error",
        //         JOptionPane.ERROR_MESSAGE);
        //         return;
        //     }

        //     Cliente nuevo = new Cliente(dpi,nombre,pass);
        //     arbol.insertar(dpi, nuevo);
        //     JOptionPane.showMessageDialog(this,
        //         "Se ha creado el cliente.",
        //         "Completado",
        //     JOptionPane.INFORMATION_MESSAGE);

        // }
        
        // if(e.getSource() == generarImagen){
        //     if(Program.arbolClientes.vacio()){
        //         JOptionPane.showMessageDialog(this,
        //         "No hay clientes ingresados en el sistema.",
        //         "Error",
        //         JOptionPane.ERROR_MESSAGE);
        //         return;
        //     }

        //     var arbol = Program.arbolClientes;

        //     String nombre = arbol.graficar();

        //     if(nombre.equals("")){
        //         JOptionPane.showMessageDialog(this,
        //         "Ha ocurrrido un error al generar la imagen.",
        //         "Error",
        //         JOptionPane.ERROR_MESSAGE);
        //         return;
        //     }
        //     generarImagen(nombre);

        // }
    
        // if(e.getSource() == buscarBtn1){
        //     if(Program.arbolClientes.vacio()){
        //         JOptionPane.showMessageDialog(this,
        //         "No hay clientes ingresados en el sistema.",
        //         "Error",
        //         JOptionPane.ERROR_MESSAGE);
        //         return;
        //     }

        //     Long dpi = 0l;
        //     try{
        //         dpi = Long.parseLong(barraBusqueda1.getText().toString());
        //     }catch (NumberFormatException ex){
        //         JOptionPane.showMessageDialog(this,
        //         "DPI no valido.",
        //         "Error",
        //         JOptionPane.ERROR_MESSAGE);
        //         return;
        //     }

        //     var nodo = Program.arbolClientes.buscar(dpi);
        //     if(nodo != null){
        //         var cliente = nodo.getCliente();
        //         DefaultTableModel model = new DefaultTableModel();

        //         model.addColumn("Parametro");
        //         model.addColumn("Datos");

        //         tablaImagenes = new JTable(model);

        //         model.addRow(new Object[]{"Nombre" ,cliente.getNombre()});
        //         model.addRow(new Object[]{"DPI" ,cliente.getDpi()});
        //         model.addRow(new Object[]{"Contraseña" ,cliente.getContraseña()});
                
        //         var albumes = cliente.getListaAlbum();
        //         model.addRow(new Object[]{"Cantidad Albumes" ,albumes.getLargo()});

        //         if(!albumes.vacia()){
        //             model.addRow(new Object[]{"Lista Albumes" ,"Imagenes"});
        //             var nodoAlbum = albumes.getHead();
        //             while(nodoAlbum != null){
        //                 var album = nodoAlbum.getAlbum();
        //                 StringBuilder str = new StringBuilder();
        //                 var nodoImagen = album.getPrimero();
                        
        //                 if(nodoImagen != null){
        //                     while(nodoImagen != null){
        //                         str.append("Imagen " + nodoImagen.getImagen().getId() + ", ");
        //                         nodoImagen = nodoImagen.getSiguiente();
        //                     }
        //                     str.deleteCharAt(str.lastIndexOf(","));
        //                 }

        //                 model.addRow(new Object[]{"   -> " + nodoAlbum.getAlbum().getNombre(), str.toString()});
        //                 nodoAlbum = nodoAlbum.getSiguiente();
        //             }
        //         }

        //         model.addRow(new Object[]{"Cantidad Imagenes" ,cliente.getArbolImagenes().noImagenes()});
        //         model.addRow(new Object[]{"Cantidad Capas" ,cliente.getArbolCapas().largo});


        //         panelImagen2.setSize(0, 0);
        //         panelImagen2 = new JScrollPane(tablaImagenes);
        //         r.add(panelImagen2);
        //         panelImagen2.setBounds(265,130,450,350);
        //         panelImagen2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //         panelImagen2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //     }else{
        //         JOptionPane.showMessageDialog(this,
        //         "No se ha encontrado el cliente..",
        //         "Error",
        //         JOptionPane.ERROR_MESSAGE);
        //         return;
        //     }
        // }
    
        // if(e.getSource() == listaClientes){

        //     if(Program.arbolClientes.vacio()){
        //         JOptionPane.showMessageDialog(this,
        //         "No hay clientes ingresados en el sistema.",
        //         "Error",
        //         JOptionPane.ERROR_MESSAGE);
        //         return;
        //     }

        //     var lista = Program.arbolClientes.recorrer();
        //     var nodo = lista.getHead();

        //     DefaultTableModel model = new DefaultTableModel();
        //     model.addColumn("No.");
        //     model.addColumn("Nombre");
        //     model.addColumn("DPI");
        //     model.addColumn("No. Imagenes");

        //     tablaImagenes = new JTable(model);
        //     int i = 1;
        //     while(nodo != null){
        //         Cliente actual = nodo.getValor();
        //         model.addRow(new Object[]{i,actual.getNombre(), actual.getDpi(),actual.getArbolImagenes().noImagenes()});
        //         nodo = nodo.getSiguiente();
        //         i++;
        //     }

        //     DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        //     centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
        //     tablaImagenes.setDefaultRenderer(Object.class, centerRenderer);

        //     panelImagen2.setSize(0, 0);
        //     panelImagen2 = new JScrollPane(tablaImagenes);
        //     r.add(panelImagen2);
        //     panelImagen2.setBounds(200,130,580,400);
        //     panelImagen2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // }

    }

    private void generarImagen(String nombre){
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

                    imagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    imagen.setIcon(new ImageIcon(imagenResize));
                    
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
    
}
