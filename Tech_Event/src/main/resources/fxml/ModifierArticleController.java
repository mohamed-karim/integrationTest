/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Entite.Article;
import Service.ArticleService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class ModifierArticleController implements Initializable {


    @FXML
    private AnchorPane Panemodif;

    @FXML
    private TextField tfMContenue;

    @FXML
    private TextField tfMimage;

    @FXML
    private Button btmodif;

    @FXML
    private TextField idnomarticle;

    @FXML
    private TextField idtitreevent;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        
        idnomarticle.setText(AcceuilArticleController.nomAr);
        tfMContenue.setText(AcceuilArticleController.coAr);
        tfMimage.setText(AcceuilArticleController.imgAr);
        idtitreevent.setText(AcceuilArticleController.titreev);
    
        
        
        /*try {
            ArticleService  es = new ArticleService();
           // System.out.println("fxml.AfficherEController.initialize()");
            ArrayList<Article> arrayList = null;
            arrayList = (ArrayList<Article>) es.readTitre();
            ObservableList obs = FXCollections.observableArrayList(arrayList);
            tb_Modif.setItems(obs);
            tcolum.setCellValueFactory(new PropertyValueFactory<Article,String>("Titre_Event"));
            
                    
        } catch (SQLException ex) {
            Logger.getLogger(ModifierArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        
      
        
        } 

    

 
       
    /* @FXML
    private void rechercherTitre(javafx.scene.input.KeyEvent event) {

        ArticleService fs = new ArticleService();
        ArrayList<Article> formations = new ArrayList<>();
        try {
            formations = (ArrayList<Article>) fs.rechercherTitre(
                    tfrechercheT.getText());
        } catch (SQLException ex) {
            Logger.getLogger(ModifierArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Article> obsl = FXCollections.observableArrayList(formations);
        tb_Modif.setItems(obsl);
        
        tcolum.setCellValueFactory(new PropertyValueFactory<Article, String>("Titre_Event"));
       

    }
    
      @FXML
    void set(ActionEvent event) {
        
Article song = tb_Modif.getSelectionModel().getSelectedItem();
 System.out.println(song);
 ArticleService e = new ArticleService();
 if (song != null){
  tfMtitreEvchoisi.setText(song.getTitre_Event());

   }

    }*/
   
  
   @FXML
    void modifierArticle(ActionEvent event) throws SQLException {
        
         ArticleService es = new ArticleService();
         Article E1 = AcceuilArticleController.modifiedArticle ;
         Article E2 = new Article();
         E2 = E1 ; 
         E2.setNom_Article(idnomarticle.getText());
         E2.setContenu_Article(tfMContenue.getText());
         E2.setImage_Article(tfMimage.getText());
         E2.setTitre_Event(idtitreevent.getText());
         System.out.println(E2);
//Evennement E = new Evennement( DESCmm.getText(),imageEmm.getText(),Titremm.getText(),datd.getValue(),datf.getValue(),empmm.getText(),);
  // Article E1 = new Article( idnomarticle.getText(),tfMContenue.getText(),tfMimage.getText(),idtitreevent.getText());     
    es.modifierArticle(E2);

    }
    
    
    
    
    
}
