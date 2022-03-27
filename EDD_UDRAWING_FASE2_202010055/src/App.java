import javax.swing.UIManager;
import GUI.Login;
public class App {
    
    public static void main(String[] args) throws Exception {   
        try{
            UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme");
        } catch(Exception e){
            System.out.println(e);
        }
        new Login();
    }


}
