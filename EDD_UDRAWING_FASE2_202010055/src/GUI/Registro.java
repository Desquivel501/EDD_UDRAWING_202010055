package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;

public class Registro extends JFrame implements ActionListener{
    JButton boton1, boton2;
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
        
        tituloLbl.setBounds(150,50,250,30);

        //---------------------------------------------------

        dpiLabel = new JLabel("DPI");
        this.add(dpiLabel);
        dpiLabel.setBounds(150,95,250,30);

        dpiJT = new JTextField();
        this.add(dpiJT);
        dpiJT.setBounds(150,120,250,30);

        //---------------------------------------------------

        usuarioLabel = new JLabel("Usuario");
        this.add(usuarioLabel);
        usuarioLabel.setBounds(150,165,250,30);

        nameJT = new JTextField();
        this.add(nameJT);
        nameJT.setBounds(150,190,250,30);

        //---------------------------------------------------

        passLabel = new JLabel("Contraseña");
        this.add(passLabel);
        passLabel.setBounds(150,240,250,30);

        password = new JPasswordField();
        this.add(password);
        password.setBounds(150,265,250,30);

        //--------------------------------------------------------

        boton1 = new JButton();
        this.add(boton1);
        boton1.setText("Registrarse");
        boton1.setBounds(150, 325, 250, 40);
        boton1.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
        if(e.getSource() == boton1){
            System.out.println("Usuario: " + nameJT.getText());
            System.out.println("Contraseña: " + new String(password.getPassword()));
        }
        if(e.getSource() == boton2){
            System.out.println("Not Here");
        }
    }

}
