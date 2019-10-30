/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Entite.User;
import Service.UserService;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class GestionCompteMembreController implements Initializable {

    
    
     @FXML
    private Label LabelLogin;

    @FXML
    private Button btnmanage;

    @FXML
    private TextField ftnom;

    @FXML
    private TextField ftprenom;

    @FXML
    private TextField ftemail;

    @FXML
    private TextField ftnumtelephone;

    @FXML
    private ImageView imagemembre;

    @FXML
    private Button btnupdate;
    
    int id= AuthentificationController.test;
    
    
    
    
    
 @Override
    public void initialize(URL url, ResourceBundle rb) {
           
         aff();
            
    }    

    
    
    
    void aff(){
     try {
                btnupdate.setVisible(false);
            
                
                User u = new User();
                UserService us = new UserService();
                System.err.println("runnnnnnnnnnnn");
                u=us.getUserByid(id);
                ftnom.setText(u.getNomU());
                ftprenom.setText(u.getPrenomU());
                ftemail.setText(u.getE_mailU());
                ftnumtelephone.setText(Integer.toString(u.getNumTel()));
                String path;
         path = "C:\\wamp64\\www"+u.getPhotoProfil();
                Image imag = new Image("file:"+path); 
            System.err.println("test1");
            imagemembre.setImage(imag);
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(GestionCompteMembreController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    

    
    
    @FXML
    private void manage(ActionEvent event) {
        btnupdate.setVisible(true);
        
        btnmanage.setVisible(false);
        
        User u = new User();
        UserService us = new UserService();
        System.err.println("runnnnnnnnnnnn");
        ftnom.setText(u.getNomU());
        ftprenom.setText(u.getPrenomU());
        ftemail.setText(u.getE_mailU());
        ftnumtelephone.setText(Integer.toString(u.getNumTel()));
    }
    
    
    
    
    
    @FXML
    private void updatemembre(ActionEvent event) throws SQLException {
    try{ 
                 btnupdate.setVisible(false);
                
                 btnmanage.setVisible(true);
            
               
                User u = new User();
                UserService us = new UserService();
                System.err.println("runnnnnnnnnnnn");
                u=us.getUserByid(id);
                
                u.setNomU(ftnom.getText());
                u.setPrenomU(ftprenom.getText());
                u.setE_mailU(ftemail.getText());
              u.setNumTel(parseInt(ftnumtelephone.getText())); 
                
                
                us.modifieruser(id, u);
                aff();
                
                
            } catch (SQLException ex) {
                Logger.getLogger(GestionCompteMembreController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
 
    
    
    
}
