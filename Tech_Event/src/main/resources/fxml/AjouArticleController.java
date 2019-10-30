    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Entite.Article;
import Entite.Evennement;
import Service.ArticleService;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class AjouArticleController implements Initializable {
int id=AuthentificationController.test;
    @FXML
    private TableView<Article> tb_table;
    @FXML
    private TableColumn<Article, String> cl_titre;
    @FXML
    private TextField fttitreArticle;
    @FXML
    private TextField ftCotenu;
    @FXML
    private TextField fttitreEvent;
    @FXML
    private TextField ftimage;
    @FXML
    private Button btnajoutArticle;
    @FXML
    private Button btset;
    @FXML
    private Button btupload;
    @FXML
    private ImageView imgAr;
      @FXML
    private Label filePath;
    
    @FXML
    private AnchorPane ac;
    static String k ; 
    /**
     * Initializes the controller class.
     */
     static Stage primaryStage = new Stage();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        try {
            ArticleService  es = new ArticleService();
           // System.out.println("fxml.AfficherEController.initialize()");
            ArrayList<Article> arrayList = null;
            arrayList = (ArrayList<Article>) es.readTitre();
            ObservableList obs = FXCollections.observableArrayList(arrayList);
            tb_table.setItems(obs);
            
            cl_titre.setCellValueFactory(new PropertyValueFactory<Article,String>("Titre_Event"));
        } catch (SQLException ex) {
            Logger.getLogger(AjouArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }    

    @FXML
    private void addArticle(ActionEvent event) {
        
         Article song = tb_table.getSelectionModel().getSelectedItem();
         Article a = new Article();
 System.out.println(song);
 ArticleService e = new ArticleService();
// tb_table.getItems().removeAll(tb_table.getSelectionModel().getSelectedItem());
 if (song != null){
             try {
                
                 
                 a.setNom_Article(fttitreArticle.getText());
                 a.setContenu_Article(ftCotenu.getText());
                // a.setDate_Article(ftdate.getText());
                 a.setImage_Article(ftimage.getText());
                 a.setId_User(id);
                 a.setTitre_Event(song.getTitre_Event());
              if(fttitreArticle.getText().equals(""))
                     JOptionPane.showMessageDialog(null,"Saisir Un Nom");
              else if (ftCotenu.getText().equals(""))
                  JOptionPane.showMessageDialog(null,"Saisir le contenue D'article");
              else if (ftimage.getText().equals(""))
                  JOptionPane.showMessageDialog(null,"Saisir L'image D'article");
               else if (a.getTitre_Event().equals(""))
                  JOptionPane.showMessageDialog(null,"Saisir L'image D'article");
            //  else if (fttitreArticle.getText().equals("")|| ftCotenu.getText().equals("") || ftimage.getText().equals("") )
               //   JOptionPane.showMessageDialog(null,"Saisir Les champ D'articles");
              else{
              e.creerArticle(a);
               JOptionPane.showMessageDialog(null,"Article ajouté");}
             } catch (SQLException ex) {
                 Logger.getLogger(AjouArticleController.class.getName()).log(Level.SEVERE, null, ex);
             }
        
        
        
        
    }
    
    }


    
    @FXML
    void set(ActionEvent event) {
        
Article song = tb_table.getSelectionModel().getSelectedItem();
 System.out.println(song);
 ArticleService e = new ArticleService();
 if (song != null){
  fttitreEvent.setText(song.getTitre_Event());


 
 
 }


    }
    
     @FXML
    void btupload(ActionEvent event) throws IOException {
            
            
            FileChooser fc = new FileChooser();
         fc.getExtensionFilters().addAll(
                 new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"));
        String path ="C:\\wamp64\\www";
        Window stage = null;
        File selectedFile = fc.showOpenDialog(stage);
                        
        if(selectedFile!=null){
            ftimage.setVisible(true);
            ftimage.setText(selectedFile.getName());
            filePath.setText(selectedFile.getAbsolutePath());
           
          //  File fichier =new File(filePath.getText());
          

            Image imag = new Image("file:"+filePath.getText()); 
            System.err.println("test1");
            imgAr.setImage(imag);
            Files.copy(selectedFile.toPath(),new File(path+"\\"+selectedFile.getName()).toPath());
        
            
            
            
        
        } 
            /*
     Stage primary = new Stage();
        
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Upload");
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = filechooser.showOpenDialog(primary);
        String path ="C:\\wamp64\\www\\imageAd";
        ftimage.setVisible(true);
        ftimage.setText(file.getName());
        
        
       // File p = new File(path);
            
        //Image image=new Image(path.toURI().toString());

         imgAr.setImage(image);
        
        
        if(file!=null)
        {
            try {
                Files.copy(file.toPath(),new File(path+"\\"+file.getName()).toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
     
    }

    private void snap(ActionEvent event) throws IOException, InterruptedException {
        
   /*     Webcam webcam=  Webcam.getDefault();
       

				    Dimension[] nonStandardResolutions = new Dimension[] {
				 WebcamResolution.PAL.getSize(),
					WebcamResolution.HD.getSize(),
					new Dimension(2000, 1000),
					new Dimension(1000, 500), };

				webcam.setCustomViewSizes(nonStandardResolutions);
				webcam.setViewSize(WebcamResolution.HD.getSize());

				webcam.open();

				startWebCamStream();*/
   // get default webcam and open it
		/*Webcam webcam = Webcam.getDefault();
		webcam.open();

		// get image
		      BufferedImage image = webcam.getImage();

		// save image to PNG file
		      ImageIO.write(image, "PNG", new File("C://wamp64/www/imageAd/xxx.png"));*/
     
                      
                     
        
              Parent root = FXMLLoader.load(getClass().getResource("/fxml/WebCamPreview.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Webcam");
        primaryStage.setScene(scene);
        primaryStage.show();
      
        
      
    }
    
      
          
    
    
    
    
    
    
    
    
    
    
    
}



