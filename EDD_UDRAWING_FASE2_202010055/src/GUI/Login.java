package GUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class Login extends JFrame implements ActionListener{

    JButton boton1, boton2;
    JPanel panel;
    JTextField usuario;

    public Login(){
        this.setSize(600,600);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setLayout(null);
        inicializar();
        this.setVisible(true);
    }

    private void inicializar(){


        boton1 = new JButton();
        this.add(boton1);
        boton1.setText("Iniciar Sesion");
        boton1.setBounds(200, 300, 200, 40);
        boton1.addActionListener(this);

        usuario = new JTextField();
        this.add(usuario);
        usuario.setBounds(200,250,200,40);
        

        boton2 = new JButton();
        this.add(boton2);
        boton2.setBounds(200, 100, 200, 40);
        boton2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
        if(e.getSource() == boton1){
            System.out.println("Here");
        }
        if(e.getSource() == boton2){
            System.out.println("Not Here");
        }
    }

    


}
