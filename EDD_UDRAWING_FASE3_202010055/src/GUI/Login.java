package GUI;

import javax.swing.*;
import ArbolB.NodoB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import Program.Program;

public class Login extends JFrame implements ActionListener{

    JButton login, registro;
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

        usuarioLabel = new JLabel("Nombre de Usuario");
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

        login = new JButton();
        this.add(login);
        login.setText("Iniciar Sesion");
        login.setBounds(150, 305, 120, 40);
        login.addActionListener(this);

        registro = new JButton();
        this.add(registro);
        registro.setText("Registrarse");
        registro.setBounds(280, 305, 120, 40);
        registro.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == login){
            // login(nameJT.getText(), new String(password.getPassword()));
            var lista = Program.arbolClientes.recorrer();
            lista.imprimir();
        }
        
        if(e.getSource() == registro){
            new Registro();
            setVisible(false);
            dispose();
            return;
        }
    }

}