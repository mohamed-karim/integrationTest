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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class AcceuilArticleController implements Initializable {

    public static String nomAr;
    public static String coAr;
    public static String imgAr;
    public static String titreev;
    public static String date;
   public static Article modifiedArticle ;
    
    @FXML
    private  TableView<Article> ttable;
    
    @FXML
    private TableColumn<Article, String> idnomArticle;
    @FXML
    private TableColumn<Article, String> idContenueArticle;
    @FXML
    private TableColumn<Article, String> idImageArticle;
    @FXML
    private TableColumn<Article, String> idTitreEvent;
    @FXML
    private TableColumn<Article, String> IdEdition;
    @FXML
    private TableColumn<Article, String> IdDatearticle;
    @FXML
    private TextField tfrecherche;
    @FXML
    private Button btAjouter;
    @FXML
    private Button btModifier;
    @FXML
    private Button btSupprimer;
    @FXML
    private AnchorPane ac;
    @FXML
    private Button idvue;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        ArticleService es = new ArticleService();
        // System.out.println("fxml.AfficherEController.initialize()");
        ArrayList<Article> arrayList = null;
        arrayList = (ArrayList<Article>) es.readArticle();
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        ttable.setItems(obs);
        idnomArticle.setCellValueFactory(new PropertyValueFactory<Article, String>("Nom_Article"));
        idContenueArticle.setCellValueFactory(new PropertyValueFactory<Article, String>("Contenu_Article"));
        idImageArticle.setCellValueFactory(new PropertyValueFactory<Article, String>("Image_Article"));

        idTitreEvent.setCellValueFactory(new PropertyValueFactory<Article, String>("Titre_Event"));
        IdEdition.setCellValueFactory(new PropertyValueFactory<Article, String>("Edition"));
        IdDatearticle.setCellValueFactory(new PropertyValueFactory<Article, String>("Date_Article"));

    }
    

    @FXML
    private void ajouter(ActionEvent event) {
        if (event.getTarget() == btAjouter) {
            try {
                AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/AjouArticle.fxml"));
                ac.getChildren().clear();
                ac.getChildren().add(newLoadedPane);
            } catch (IOException ex) {
                Logger.getLogger(AcceuilArticleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    
    
    
    @FXML
    private void modifier(ActionEvent event) {
        
         
               
       

 Article song = ttable.getSelectionModel().getSelectedItem();
 modifiedArticle = song ;

 ArticleService e = new ArticleService();
 if (song != null){

                nomAr=song.getNom_Article();
                coAr=song.getContenu_Article();
                imgAr=song.getImage_Article();
                titreev=song.getTitre_Event();
                
                
                
            if (event.getTarget() == btModifier) {
            try {
                AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/ModifierArticle.fxml"));
                ac.getChildren().clear();
                ac.getChildren().add(newLoadedPane);
          
            } catch (IOException ex) {
                Logger.getLogger(AcceuilArticleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
   }        
        
    }

    
    
    
    
    
    
    @FXML
    private void supprimer(ActionEvent event) {
        Article song = ttable.getSelectionModel().getSelectedItem();
        System.out.println(song);
        ArticleService e = new ArticleService();
        ttable.getItems().removeAll(ttable.getSelectionModel().getSelectedItem());
        if (song != null) {
            JOptionPane.showMessageDialog(null, "Article Supprimee");
            e.supprimerArticle(song);
        }

    }

    
    
    @FXML
    private void rechercherNom(javafx.scene.input.KeyEvent event) {

        ArticleService fs = new ArticleService();
        ArrayList<Article> formations = new ArrayList<>();
        try {
            formations = (ArrayList<Article>) fs.rechercherNom(
                    tfrecherche.getText());
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Article> obsl = FXCollections.observableArrayList(formations);
        ttable.setItems(obsl);
        idnomArticle.setCellValueFactory(new PropertyValueFactory<Article, String>("Nom_Article"));
        idContenueArticle.setCellValueFactory(new PropertyValueFactory<Article, String>("Contenu_Article"));
        idImageArticle.setCellValueFactory(new PropertyValueFactory<Article, String>("Image_Article"));
        idTitreEvent.setCellValueFactory(new PropertyValueFactory<Article, String>("Titre_Event"));
        IdEdition.setCellValueFactory(new PropertyValueFactory<Article, String>("Edition"));
        IdDatearticle.setCellValueFactory(new PropertyValueFactory<Article, String>("Date_Article"));

        // prixtotale.setCellValueFactory(new PropertyValueFactory<>("5"));
    }
 @FXML
    void vue(ActionEvent event) throws IOException {
        
 Article song = ttable.getSelectionModel().getSelectedItem();
 modifiedArticle = song ;

 ArticleService e = new ArticleService();
 if (song != null){

                nomAr=song.getNom_Article();
                coAr=song.getContenu_Article();
                imgAr=song.getImage_Article();
                titreev=song.getTitre_Event();
                date=song.getDate_Article();
                
                
         
        
                AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/Affichage.fxml"));
                ac.getChildren().clear();
                ac.getChildren().add(newLoadedPane);
          
         
        } 
   } 

    
}
