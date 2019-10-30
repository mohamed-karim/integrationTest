/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Entite.User;
import Service.UserService;
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField tflogin;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfnum;
   @FXML
    private Button btnbrowser;
    @FXML
    private Button btnsingin;
   @FXML
   private ImageView imageview;
       @FXML
    private Label filename;
    @FXML
    private Label filePath;
    @FXML
    private TextField ftImage; 
    @FXML
    private AnchorPane bp; 
int file = 0;
 
    public void initialize(URL url, ResourceBundle rb) {
       file=0;
    }    
    
    
    
            @FXML
    private void handelbrowser(ActionEvent event) throws IOException  {
        
        FileChooser fc = new FileChooser();
         fc.getExtensionFilters().addAll(
                 new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"));
        String path ="C\\wamp64\\www";
        Window stage = null;
        File selectedFile = fc.showOpenDialog(stage);
                        
        if(selectedFile!=null){
            ftImage.setVisible(true);
            ftImage.setText(selectedFile.getName());
            filePath.setText(selectedFile.getAbsolutePath());
           
          //  File fichier =new File(filePath.getText());
            System.err.println(filePath.getText());

            Image imag = new Image("file:"+filePath.getText()); 
            System.err.println("test1");
            imageview.setImage(imag);
            Files.copy(selectedFile.toPath(),new File(path+"\\"+selectedFile.getName()).toPath());
        
            
            
            
        
        }
                
    }            
    public boolean checkNumber(String chaine) {
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e){
			return false;
		}
 
		return true;
	}
    
      @FXML
    private void signin(ActionEvent event) throws SQLException, IOException {
        //Preferences userPreferences = Preferences.userRoot();
              
        System.err.println("test inscription");
        
        User p = new User();
        
        
        p.setLogin(tflogin.getText());
        p.setMdp(tfpassword.getText());
        p.setNomU(tfnom.getText());
        p.setPrenomU(tfprenom.getText());
        p.setNumTel(parseInt(tfnum.getText()));
        p.setPhotoProfil(ftImage.getText());
        UserService sp = new UserService();
        sp.creerUser(p);
          System.err.println("insertion effectue");
       
        Stage stage = (Stage) bp.getScene().getWindow();
                System.err.println("bbb2");
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("fxml/authentification.fxml"));
      
            //System.err.println(info);
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       
    } 
    
    
    
}
