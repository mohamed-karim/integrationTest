/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AcceuilTechController implements Initializable {

    @FXML
    private HBox id_evta;
    @FXML
    private Label evnta;
    @FXML
    private HBox resa;
    @FXML
    private Label reserva;
    @FXML
    private HBox arta;
    @FXML
    private HBox forma;
    @FXML
    private Pane content1;
    @FXML
    private Pane content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_afficherTouse(MouseEvent event) {
        
         if (event.getTarget() == id_evta) {
			try {
				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/afficherE.fxml"));
				content.getChildren().clear();
				content.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(AcceuilTechController.class.getName()).log(Level.SEVERE, null, ex);
			}
         }
        
    }
    
}
