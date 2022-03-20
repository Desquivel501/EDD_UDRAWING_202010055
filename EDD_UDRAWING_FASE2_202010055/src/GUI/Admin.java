package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import java.awt.Font;

import Lector.*;
import Models.*;
import Program.Program;

public class Admin extends JFrame implements ActionListener{

    Cliente cliente;

    JTabbedPane tp;
    JPanel p1,p2,p3;
    JButton cargaCliente, regresar, buscarBtn, nuevoBtn, actualizarBtn, eliminarBtn;
    JLabel cargaMasiva, buscarLbl, usuarioLabel, passLabel, dpiLbl;
    JTextField barraBusqueda, nameJT, passJT, dpiJt;

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

        //-----------------------------------------------------------
        
        cargaMasiva = new JLabel("Carga Masiva de Clientes", SwingConstants.CENTER);
        cargaMasiva.setFont(new java.awt.Font("Arial", Font.BOLD, 30));
        p3.add(cargaMasiva);
        cargaMasiva.setBounds(300,100,400,100);


        cargaCliente = new JButton("Cargar");
        p3.add(cargaCliente);
        cargaCliente.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
        cargaCliente.setBounds(400,200,200,100);
        cargaCliente.addActionListener(this);

        //-----------------------------------------------------------

        buscarLbl = new JLabel("Buscar Cliente");
        p2.add(buscarLbl);
        buscarLbl.setBounds(260,25,100,15);

        barraBusqueda = new JTextField();
        p2.add(barraBusqueda);
        barraBusqueda.setBounds(260,40,300,40);

        buscarBtn = new JButton("Buscar");
        p2.add(buscarBtn);
        buscarBtn.setBounds(570,40,150,40);
        buscarBtn.addActionListener(this);

        //--------------------------------------------

        usuarioLabel = new JLabel("Nombre de Usuario");
        p2.add(usuarioLabel);
        usuarioLabel.setBounds(375,140,250,30);

        nameJT = new JTextField();
        p2.add(nameJT);
        nameJT.setBounds(375,165,250,40);
        nameJT.addActionListener(this);

         //--------------------------------------------

        dpiLbl = new JLabel("DPI");
        p2.add(dpiLbl);
        dpiLbl.setBounds(375,215,250,30);

        dpiJt = new JTextField();
        p2.add(dpiJt);
        dpiJt.setBounds(375,240,250,40);
        dpiJt.addActionListener(this);

        //--------------------------------------------

        passLabel = new JLabel("Contraseña");
        p2.add(passLabel);
        passLabel.setBounds(375,290,250,30);

        passJT = new JTextField();
        p2.add(passJT);
        passJT.setBounds(375,315,250,40);
        passJT.addActionListener(this);

        //--------------------------------------------

        nuevoBtn = new JButton();
        p2.add(nuevoBtn);
        nuevoBtn.setText("Nuevo");
        nuevoBtn.setBounds(190, 400, 200, 40);
        nuevoBtn.addActionListener(this);

        actualizarBtn = new JButton();
        p2.add(actualizarBtn);
        actualizarBtn.setText("Actualizar");
        actualizarBtn.setBounds(400, 400, 200, 40);
        actualizarBtn.addActionListener(this);

        eliminarBtn = new JButton();
        p2.add(eliminarBtn);
        eliminarBtn.setText("Eliminar");
        eliminarBtn.setBounds(610, 400, 200, 40);
        eliminarBtn.addActionListener(this);

        

        tp.add("Reporte Usuarios",p1); 
        tp.add("Operaciones",p2); 
        tp.add("Carga Masiva",p3);
           
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
                    lector.leerClientes(archivo);
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
            String nombre = barraBusqueda.getText().toString();
            var listaClientes = Program.listaClientes;
            boolean found = false;
            for(int i = 0; i < listaClientes.size();i++){
                if(nombre.equals(listaClientes.get(i).getNombre())){
                    this.cliente = listaClientes.get(i);
                    nameJT.setText(listaClientes.get(i).getNombre());
                    dpiJt.setText(Integer.toString(listaClientes.get(i).getDpi()));
                    passJT.setText(listaClientes.get(i).getContraseña());
                    found = true;
                }
            }
            if(!found){
                JOptionPane.showMessageDialog(this,
                "No se ha encontrado el cliente.",
                "Advertencia",
                JOptionPane.WARNING_MESSAGE);
            }
        }

        if(e.getSource() == nuevoBtn){
            String nombre = nameJT.getText().toString();
            int dpi = Integer.parseInt(dpiJt.getText());
            var listaClientes = Program.listaClientes;
            boolean found = false;
            for(int i = 0; i < listaClientes.size();i++){
                if(nombre.equals(listaClientes.get(i).getNombre())){
                    found = true;
                    JOptionPane.showMessageDialog(this,
                        "Un usuario ya existe con ese nombre de usuario.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(dpi == listaClientes.get(i).getDpi()){
                    found = true;
                    JOptionPane.showMessageDialog(this,
                        "Un usuario ya existe con ese DPI.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            if(!found){
                Cliente nuevo = new Cliente(dpi,nombre,passJT.getText());
                Program.listaClientes.add(nuevo);
                JOptionPane.showMessageDialog(this,
                        "El usuario se ha creado.",
                        "Exito",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        }
        
    }
    
}
