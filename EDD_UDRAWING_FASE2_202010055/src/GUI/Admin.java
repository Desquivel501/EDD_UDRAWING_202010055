package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import ArbolB.NodoB;

import java.awt.Font;

import Lector.*;
import Models.*;
import Program.Program;
import java.awt.Image;


public class Admin extends JFrame implements ActionListener{

    Cliente cliente;

    JTabbedPane tp, cp;
    JPanel p1,p2,p3 ,c1,c2,c3,r;
    JButton cargaCliente, regresar, buscarBtn, nuevoBtn, actualizarBtn, eliminarBtn, buscarBtn1, buscarBtn2, generarImagen;
    JLabel cargaMasiva, buscarLbl, usuarioLabel, passLabel, dpiLbl, buscarLbl2, usuarioLabel2, passLabel2, dpiLbl2, buscarLbl1, usuarioLabel1, passLabel1, dpiLbl1, imagen;
    JTextField barraBusqueda, nameJT, passJT, dpiJt, barraBusqueda1, nameJT1, passJT1, dpiJt1, barraBusqueda2, nameJT2, passJT2, dpiJt2;
    JScrollPane panelImagen;
    

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

         //--------------------------------------------

        dpiLbl2 = new JLabel("DPI");
        c3.add(dpiLbl2);
        dpiLbl2.setBounds(375,215,250,30);

        dpiJt2 = new JTextField();
        c3.add(dpiJt2);
        dpiJt2.setBounds(375,240,250,40);

        //--------------------------------------------

        passLabel2 = new JLabel("Contraseña");
        c3.add(passLabel2);
        passLabel2.setBounds(375,290,250,30);

        passJT2 = new JTextField();
        c3.add(passJT2);
        passJT2.setBounds(375,315,250,40);

        //--------------------------------------------

        eliminarBtn = new JButton();
        c3.add(eliminarBtn);
        eliminarBtn.setText("Eliminar Usuario");
        eliminarBtn.setBounds(375, 400, 250, 40);
        eliminarBtn.addActionListener(this);

        //--------------------------------------------
        //--------------------------------------------

        generarImagen = new JButton("Generar Imagen");
        p1.add(generarImagen);
        generarImagen.setBounds(375, 10, 250, 30);
        generarImagen.addActionListener(this);

        imagen = new JLabel();

        panelImagen = new JScrollPane(imagen);
        p1.add(panelImagen);
        panelImagen.setBounds(10,50,950,650);
        // panelImagen.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        panelImagen.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  

        tp.add("Reporte Usuarios",p1); 
        tp.add("Nuevo Usuario",c1); 
        tp.add("Modificar Usuario",c2); 
        tp.add("Borrar Usuario",c3);  
        tp.add("Reportes", r);   

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
            System.out.println("here");
            Long dpi = Long.parseLong(barraBusqueda.getText().toString());

            var arbolClientes= Program.arbolClientes;
            NodoB encontrado = arbolClientes.buscar(dpi);

            if(encontrado != null){
                Cliente cliente = encontrado.getCliente();
                nameJT.setText(cliente.getNombre());
                dpiJt.setText(Long.toString(cliente.getDpi()));
                passJT.setText(cliente.getContraseña());
            }
        }

        if(e.getSource() == buscarBtn1){
            System.out.println("here");
            Long dpi = Long.parseLong(barraBusqueda.getText().toString());

            var arbolClientes= Program.arbolClientes;
            NodoB encontrado = arbolClientes.buscar(dpi);

            if(encontrado != null){
                Cliente cliente = encontrado.getCliente();
                nameJT1.setText(cliente.getNombre());
                dpiJt1.setText(Long.toString(cliente.getDpi()));
                passJT1.setText(cliente.getContraseña());
            }
        }

        if(e.getSource() == buscarBtn2){
            System.out.println("here");
            Long dpi = Long.parseLong(barraBusqueda.getText().toString());

            var arbolClientes= Program.arbolClientes;
            NodoB encontrado = arbolClientes.buscar(dpi);

            if(encontrado != null){
                Cliente cliente = encontrado.getCliente();
                nameJT2.setText(cliente.getNombre());
                dpiJt2.setText(Long.toString(cliente.getDpi()));
                passJT2.setText(cliente.getContraseña());
            }
        }

        if(e.getSource() == actualizarBtn){

            Long dpi = Long.parseLong(barraBusqueda.getText().toString());
            var arbolClientes= Program.arbolClientes;
            NodoB encontrado = arbolClientes.buscar(dpi);

            if(encontrado != null){
                JOptionPane.showMessageDialog(this,
                "El nombre de usuario ya esta en uso.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            }else{
                this.cliente.setNombre(nameJT.getText().toString());
                this.cliente.setContraseña(passJT.getText().toString());
            }

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

            String nombre = nameJT1.getText().toString();
            Long dpi = -999l;
            String pass = passJT1.getText().toString();

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

            NodoB nodoNombre = arbol.buscarNombre(nombre);
            if(nodoNombre != null){
                JOptionPane.showMessageDialog(this,
                "El nombre ingresado ya esta en uso.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }

            NodoB nodoDPI = arbol.buscar(dpi);
            if(nodoDPI != null){
                JOptionPane.showMessageDialog(this,
                "Ya existe un usuario con ese DPI.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }

            Cliente nuevo = new Cliente(dpi,nombre,pass);
            arbol.insertar(dpi, nuevo);

        }
        
        if(e.getSource() == generarImagen){
            var arbol = Program.arbolClientes;

            String nombre = arbol.graficar();

            if(nombre.equals("")){
                JOptionPane.showMessageDialog(this,
                "Ha ocurrrido un error al generar la imagen.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            generarImagen(nombre);

        }
    }

    private void generarImagen(String nombre){
        try{
            boolean found = false;
            long startTime = System.currentTimeMillis();

            while(System.currentTimeMillis() < startTime + 30000) {
                try{
                    BufferedImage imagenOr = ImageIO.read(new File(nombre));
                    float altura = ((float)950/imagenOr.getWidth()) * imagenOr.getHeight();
                    Image imagenResize = imagenOr.getScaledInstance(950, Math.round(altura), Image.SCALE_SMOOTH);
                    imagen.setIcon(new ImageIcon(imagenResize));
                    
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
