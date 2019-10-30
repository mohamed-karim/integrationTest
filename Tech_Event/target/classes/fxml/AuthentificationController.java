/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Entite.User;
import Service.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class AuthentificationController implements Initializable {
public static int test ;
    
    @FXML
    private TextField tfLogin;
    @FXML
    private TextField tfpassword;
   @FXML
    private Button btnAuthen;
     @FXML
private AnchorPane bp; 
       @FXML
    private Button btninscrire;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void authentification(ActionEvent event) throws SQLException, IOException {
        //Preferences userPreferences = Preferences.userRoot();
              
        System.err.println("bbb");
        
        User p = new User();
        p.setLogin(tfLogin.getText());
        p.setMdp(tfpassword.getText());
        UserService sp = new UserService();
        test=sp.authentification(p);
          System.err.println(test);
        if(test!=0){
        Stage stage = (Stage) bp.getScene().getWindow();
                System.err.println("bbb2");
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("fxml/AcceuilTech.fxml"));
      
            //System.err.println(info);
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    } 
    @FXML
    private void inscription(ActionEvent event) throws SQLException, IOException {
        //Preferences userPreferences = Preferences.userRoot();
              
        System.err.println("inscrire");
        
        
        Stage stage = (Stage) bp.getScene().getWindow();
                System.err.println("*****");
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Inscription.fxml"));
      
            System.err.println("go to inscription");
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
    }         
}
    
    

