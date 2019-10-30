/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entite.Reclamation;
import Entite.User;
import Service.ReclamationService;
import Service.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import fxml.AuthentificationController;
import fxml.GstionCompteAdminController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import org.controlsfx.control.Rating;


/**
 * FXML Controller class
 *
 * @author ACER
 */
public class ReclamationUserController implements Initializable {
    @FXML
    private AnchorPane bp;
    
    @FXML
    private Button reclamer;
   @FXML
    private TableView<Reclamation> table;

    @FXML
    private TableColumn<Reclamation, String> col_titre;

    @FXML
    private TableColumn<Reclamation, String> col_contenu;
    
    @FXML
    private TableColumn<Reclamation, String> col_etat;

    @FXML
    private TableColumn<Reclamation, LocalDate> col_date;
   
    @FXML
    private TextField recherche;
    
    @FXML
    private Label label_titre;

    @FXML
    private Button saveRating;

  
     @FXML
    private Rating rating;

    @FXML
    private Label label_rating;
     @FXML
    private Button btn_send;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        ReclamationService re = new ReclamationService();
        
        ObservableList<Object> data = FXCollections.observableArrayList();
        rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
              label_rating.setText(t1.toString());
            }
            
        });
         
        
        
        saveRating.setVisible(false);
        label_rating.setVisible(false);
        rating.setVisible(false);
        label_titre.setVisible(false);
       aff();
           
        
       
            }    
    
    
    
    
    
   private void aff() {
       ReclamationService us = new ReclamationService();
       int id= AuthentificationController.test;
       ArrayList<Reclamation> arrayList = (ArrayList<Reclamation>) us.readReclamtion();
       ObservableList obs = FXCollections.observableArrayList(arrayList);
       table.setItems(obs);
       col_titre.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("Titre_Reclamation"));
       col_contenu.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("Contenu_Reclamation"));
       col_etat.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("Etat_Reclamation"));
       col_date.setCellValueFactory(new PropertyValueFactory<Reclamation,LocalDate>("date_R")); 
    }
    
  
   
   @FXML
    private void recherche_function(javafx.scene.input.KeyEvent event) {

        ReclamationService fs = new ReclamationService();
        ArrayList<Reclamation> formations = new ArrayList<>();
        try {
            formations = (ArrayList<Reclamation>) fs.rechercheEvennement(
                    recherche.getText());
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Reclamation> obs = FXCollections.observableArrayList(formations);
       table.setItems(obs);
       col_titre.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("Titre_Reclamation"));
       col_contenu.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("Contenu_Reclamation"));
       col_etat.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("Etat_Reclamation"));
       col_date.setCellValueFactory(new PropertyValueFactory<Reclamation,LocalDate>("date_R")); ;
       
        
        // prixtotale.setCellValueFactory(new PropertyValueFactory<>("5"));

    }
    
    @FXML
    private void reclamerUser(ActionEvent event) throws SQLException, IOException {
        saveRating.setVisible(true);
        label_rating.setVisible(false);
        rating.setVisible(true);
        label_titre.setVisible(false);
        reclamer.setVisible(false);
        btn_send.setVisible(false);
        
        ReclamationService rs = new ReclamationService();
        String testRate = label_rating.getText();
        int id =AuthentificationController.test;
        Reclamation test = table.getSelectionModel().getSelectedItem();
        
        String testTitre=test.getTitre_Reclamation();
        label_titre.setText(testTitre);
        
        
       
        
    }
    
@FXML
private void sendReclamation(ActionEvent event) throws IOException{
    Stage stage = (Stage) bp.getScene().getWindow();
                System.err.println("bbb2");
           // Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("fxml/ModifierE.fxml"));
            AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/ReclamationSend.fxml"));
				bp.getChildren().clear();
				bp.getChildren().add(newLoadedPane);
}
private String rateretour(){
    ObservableList<Object> data = FXCollections.observableArrayList();
        rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
              label_rating.setText("Rating : "+ t1.toString());
            }
            
        });
        String test =  label_rating.getText();
        return test;
}
    

@FXML
   private void SaveRec(ActionEvent event) throws IOException{
        ReclamationService rs = new ReclamationService();
        
        
        int id =AuthentificationController.test;
        System.err.println("id"+id);
        String titreSave=label_titre.getText();
        System.out.println("titre"+titreSave);
        String titre = label_rating.getText();
         System.out.println("titre"+titre);
        rs.modifierRate(id, titreSave, titre);
         JOptionPane.showMessageDialog(null,"rating faite de "+titreSave);
        
      
    }
    
}
   

