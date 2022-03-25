package GUI;
import javax.swing.*;

import ArbolB.NodoB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;
import java.awt.Font;
import Program.Program;

public class Login extends JFrame implements ActionListener{

    JButton boton1, boton2;
    JPanel panel;
    JTextField nameJT, passJT;
    JLabel usuarioLabel, passLabel, tituloLbl;
    JPasswordField password;

    public Login(){
        this.setTitle("Login");
        this.setSize(550,500);
        // this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.setLayout(null);
        inicializar();
        setLocationRelativeTo(null);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void inicializar(){

        tituloLbl = new JLabel("UDrawing Paper");
        this.add(tituloLbl);
        tituloLbl.setFont(new java.awt.Font("Arial", Font.BOLD, 30));
        tituloLbl.setBounds(150,75,250,40);

        //---------------------------------------------------

        usuarioLabel = new JLabel("Usuario");
        this.add(usuarioLabel);
        usuarioLabel.setBounds(150,145,250,30);

        nameJT = new JTextField();
        this.add(nameJT);
        nameJT.setBounds(150,170,250,30);

        //---------------------------------------------------

        passLabel = new JLabel("Contraseña");
        this.add(passLabel);
        passLabel.setBounds(150,220,250,30);

        password = new JPasswordField();
        this.add(password);
        password.setBounds(150,245,250,30);

        //--------------------------------------------------------

        boton1 = new JButton();
        this.add(boton1);
        boton1.setText("Iniciar Sesion");
        boton1.setBounds(150, 305, 120, 40);
        boton1.addActionListener(this);

        boton2 = new JButton();
        this.add(boton2);
        boton2.setText("Registrarse");
        boton2.setBounds(280, 305, 120, 40);
        boton2.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if(e.getSource() == boton1){
            System.out.println("DPI: " + nameJT.getText());
            System.out.println("Contraseña: " + new String(password.getPassword()));
            login(nameJT.getText(), new String(password.getPassword()));
        }
        
        if(e.getSource() == boton2){
            new Registro();
            setVisible(false);
            dispose();
            return;
        }
    }

    public void login(String usuario, String pass){
        if(usuario.equals("Admin") && pass.equals("1")){
            System.out.println("Admin");
            new Admin();
            setVisible(false);
            dispose();
            return;
        }

        var arbolClientes= Program.arbolClientes;
        Long dpi = 0l;
        try{
            dpi = Long.parseLong(nameJT.getText().toString());
        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(this,
                "DPI no Valido",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        NodoB encontrado = arbolClientes.buscar(dpi);
        if(encontrado != null){
            if(encontrado.getCliente().getContraseña().equals(new String(password.getPassword()))){
                Program.loggedUser = encontrado.getCliente();
                new ClienteG();
                setVisible(false);
                dispose();
                return;
            }else{
                JOptionPane.showMessageDialog(this,
                "Contraseña Incorrecta",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            }
        }else{
            int respuesta = JOptionPane.showConfirmDialog(this,
                "El usuario no existe.\n ¿Desea registrar un nuevo usuario?",
                "Error",
                JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.YES_OPTION){
                new Registro();
                setVisible(false);
                dispose();
                return;
            }    
        }  
    }



}
