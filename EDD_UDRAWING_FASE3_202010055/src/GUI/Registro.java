package GUI;

import javax.swing.*;

import ArbolB.NodoB;
import Models.Cliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import Program.Program;

public class Registro extends JFrame implements ActionListener{
    JButton registro, regresar;
    JPanel panel;
    JTextField nameJT, passJT, dpiJT, nombreJT, correoJT, teleJT, dirJT, munJT, userJT;
    JLabel usuarioLabel, passLabel, tituloLbl, dpiLabel, nombreLbl, correoLbl, teleLbl, dirLbl, munLbl;
    JPasswordField password;

    public Registro(){
        this.setTitle("Registro");
        this.setSize(550,800);
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

        nombreLbl = new JLabel("Nombre Completo");
        this.add(nombreLbl);
        nombreLbl.setBounds(150,175,250,30);

        nombreJT = new JTextField();
        this.add(nombreJT);
        nombreJT.setBounds(150,200,250,30);

        //---------------------------------------------------

        usuarioLabel = new JLabel("Usuario");
        this.add(usuarioLabel);
        usuarioLabel.setBounds(150,245,250,30);

        userJT = new JTextField();
        this.add(userJT);
        userJT.setBounds(150,270,250,30);

        //---------------------------------------------------

        correoLbl = new JLabel("Correo");
        this.add(correoLbl);
        correoLbl.setBounds(150,315,250,30);

        correoJT = new JTextField();
        this.add(correoJT);
        correoJT.setBounds(150,340,250,30);

        //---------------------------------------------------

        passLabel = new JLabel("Contraseña");
        this.add(passLabel);
        passLabel.setBounds(150,385,250,30);

        password = new JPasswordField();
        this.add(password);
        password.setBounds(150,410,250,30);

        //--------------------------------------------------------

        teleLbl = new JLabel("Telefono");
        this.add(teleLbl);
        teleLbl.setBounds(150,455,250,30);

        teleJT = new JTextField();
        this.add(teleJT);
        teleJT.setBounds(150,480,250,30);

        //---------------------------------------------------

        dirLbl = new JLabel("Direccion");
        this.add(dirLbl);
        dirLbl.setBounds(150,525,250,30);

        dirJT = new JTextField();
        this.add(dirJT);
        dirJT.setBounds(150,550,250,30);

        //---------------------------------------------------

        munLbl = new JLabel("ID Municipio");
        this.add(munLbl);
        munLbl.setBounds(150,595,250,30);

        munJT = new JTextField();
        this.add(munJT);
        munJT.setBounds(150,620,250,30);

        //---------------------------------------------------

        

        registro = new JButton();
        this.add(registro);
        registro.setText("Registrarse");
        registro.setBounds(150, 670, 250, 40);
        registro.addActionListener(this);

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

        if(e.getSource() == registro){
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
            var busqueda = Program.arbolClientes.buscarNombre(userJT.getText());
            if(busqueda != null){
                JOptionPane.showMessageDialog(this,
                    "El nombre de usuario ya esta en uso",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            Cliente nuevo = new Cliente(dpi, userJT.getText(), new String(password.getPassword()), nombreJT.getText(), correoJT.getText() ,teleJT.getText(), dirJT.getText(), munJT.getText());
            NodoB nodo = Program.arbolClientes.buscar(dpi);
            if(nodo == null){

                JOptionPane.showMessageDialog(this,
                    "Se ha creado el usuario.",
                    "Completado",
                    JOptionPane.INFORMATION_MESSAGE);

                Program.arbolClientes.insertar(dpi, nuevo);
                // Program.loggedUser =  nuevo;
                // new ClienteG();
                // setVisible(false);
                // dispose();
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
