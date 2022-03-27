package GUI;

import javax.swing.*;

import ArbolB.NodoB;
import Models.Cliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import Program.Program;

public class Registro extends JFrame implements ActionListener{
    JButton boton1, boton2, regresar;
    JPanel panel;
    JTextField nameJT, passJT, dpiJT;
    JLabel usuarioLabel, passLabel, tituloLbl, dpiLabel;
    JPasswordField password;

    public Registro(){
        this.setTitle("Registro");
        this.setSize(550,500);
        this.setLayout(null);
        inicializar();
        setLocationRelativeTo(null);

        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void inicializar(){

        tituloLbl = new JLabel("Nuevo Usuario", SwingConstants.CENTER);
        this.add(tituloLbl);
        tituloLbl.setFont(new java.awt.Font("Arial", Font.BOLD, 28));   
        tituloLbl.setBounds(150,60,250,30);

        //---------------------------------------------------

        dpiLabel = new JLabel("DPI");
        this.add(dpiLabel);
        dpiLabel.setBounds(150,105,250,30);

        dpiJT = new JTextField();
        this.add(dpiJT);
        dpiJT.setBounds(150,130,250,30);

        //---------------------------------------------------

        usuarioLabel = new JLabel("Usuario");
        this.add(usuarioLabel);
        usuarioLabel.setBounds(150,175,250,30);

        nameJT = new JTextField();
        this.add(nameJT);
        nameJT.setBounds(150,200,250,30);

        //---------------------------------------------------

        passLabel = new JLabel("Contraseña");
        this.add(passLabel);
        passLabel.setBounds(150,250,250,30);

        password = new JPasswordField();
        this.add(password);
        password.setBounds(150,275,250,30);

        //--------------------------------------------------------

        boton1 = new JButton();
        this.add(boton1);
        boton1.setText("Registrarse");
        boton1.setBounds(150, 335, 250, 40);
        boton1.addActionListener(this);

        regresar = new JButton("Regresar");
        this.add(regresar);
        regresar.setBounds(10,8,120,30);
        regresar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == regresar){
            new Login();
            Program.loggedUser = null;
            setVisible(false);
            dispose();
        }
 
        if(e.getSource() == boton1){
            long dpi = 0l;
            try{
                dpi = Long.parseLong(dpiJT.getText());
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this,
                    "DPI no Valido",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            Cliente nuevo = new Cliente(dpi, nameJT.getText(), new String(password.getPassword()));
            NodoB nodo = Program.arbolClientes.buscar(dpi);
            if(nodo == null){

                JOptionPane.showMessageDialog(this,
                    "Se ha creado el usuario.",
                    "Completado",
                    JOptionPane.INFORMATION_MESSAGE);

                Program.arbolClientes.insertar(dpi, nuevo);
                Program.loggedUser =  nuevo;
                new ClienteG();
                setVisible(false);
                dispose();
                return;

            }else{
                JOptionPane.showMessageDialog(this,
                    "El DPI ya esta en uso",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
 
    }

}
