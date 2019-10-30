/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Service.ArticleService;
import com.jfoenix.controls.JFXButton;
import com.sun.javafx.webkit.InputMethodClientImpl;
import com.sun.xml.internal.ws.message.RootElementSniffer;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import static fxml.AcceuilArticleController.imgAr;
import static fxml.AcceuilArticleController.modifiedArticle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.R;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class AffichageController implements Initializable {
    private Facebook facebook;
    @FXML
    private ImageView LBimageview;
    @FXML
    private Label LBContenu;
    @FXML
    private Label LBnomarticle;
    @FXML
    private Label LBDate;
    @FXML
    private Button idbtrr;
    @FXML
    private Label Lbtitreevent;
    @FXML
    private AnchorPane idShare;
    @FXML
    private Button idshare;
    
    @FXML
    private JFXButton idpdf;
    private AnchorPane idNpane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        LBnomarticle.setText("" + AcceuilArticleController.nomAr);
     
        LBContenu.setText("" + AcceuilArticleController.coAr);
       LBDate.setText("" + AcceuilArticleController.date);
       
      //ImageView maPhoto = (ImageView) convertView.findViewById(imgAr) ; //maPhoto.setImage(insertionPhoto.get(position).getMaPhoto()) ;
        //System.out.println("C:\\wamp64\\www\\"+ AcceuilArticleController.imgAr);
   LBimageview.setImage(new Image("http://127.0.0.1/"+ AcceuilArticleController.imgAr));
       
               // Image image = new Image(getClass().getResourceAsStream("" + AcceuilArticleController.imgAr));
              //  LBimageview.setImage(image);
          

                 

       Lbtitreevent.setText("" + AcceuilArticleController.titreev);
    }    

    @FXML
    private void retour(ActionEvent event) {
    }

    @FXML
    private void share(ActionEvent event) {
        facebook = new FacebookFactory().getInstance();
        
        facebook.setOAuthAppId("", "");//ysta3mlouh fazat connexion fb 
        String accessTokenString = "EAAF91IQMzb4BAPqG8OEDQYAnhJA367a4gsqgaI80m0MD6qwmi7UGLdAqgUhyRcRZA5P4irT0OnjGCBlljtjwXpGL8MvSFSP2g2Qp5EYGionVD0D8cGxlykEl8Xh6NTCNEX0O2imMZAg492NagipVnoPuZAT5bJ4Pi4ogjJZCtJwTthyAEKvRx2wima6iM2IZD";
        AccessToken accessToken = new AccessToken(accessTokenString);
        //set access token
        facebook.setOAuthAccessToken(accessToken);
        //ResponseList<Account> accounts = facebook.getAccounts();
       // Account pageAccount = accounts.get(0);
        try{
       facebook.postStatusMessage("Article1 "+AcceuilArticleController.nomAr +AcceuilArticleController.coAr);
        }catch(FacebookException fe){
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Deja partager.");
        alert.setHeaderText(null);
      //  alert.setContentText("La plant  "+p.getNamePlant()+ " a été deja partager.");
        alert.showAndWait();
    }
        }
        
            @FXML
    void convertPDF(ActionEvent event) {
 
 System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(primaryStage); 
        
   // Node root = this.pane;
   Node root = this.idShare;
    
    
           job.printPage(root);
           job.endJob();
            
       

  }

    }
    
   
}
