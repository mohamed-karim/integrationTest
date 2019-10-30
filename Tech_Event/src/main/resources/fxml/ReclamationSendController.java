/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Entite.Reclamation;
import Service.ReclamationService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class ReclamationSendController implements Initializable {

   @FXML
    private TextField objet;

    @FXML
    private TextArea contenu;

    @FXML
    private DatePicker dtreclamation;

    @FXML
    private Button btn_envoie;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    
    @FXML
    void sendRecl(ActionEvent event) throws SQLException {
int id=AuthentificationController.test;
        Reclamation reclamtion = new Reclamation();
        ReclamationService re = new ReclamationService();
        reclamtion.setTitre_Reclamation(objet.getText());
        reclamtion.setContenu_Reclamation(contenu.getText());
       reclamtion.setId_User(id);
        reclamtion.setDate_R(dtreclamation.getValue());
        re.creerreclamation(reclamtion);
        
        //Image img = new Image("/aa.png");
            Notifications notificationBuilder = Notifications.create()
                    .title("Reclamation envoyer avec succées")
                    .text("Mercie pour attendre la réponse")
                   // .graphic(new ImageView(img))
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);

            notificationBuilder.show();
        
        
        
    }
}
