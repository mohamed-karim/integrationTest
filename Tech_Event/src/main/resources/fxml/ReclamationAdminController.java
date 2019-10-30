/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Entite.Reclamation;
import Service.ReclamationService;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class ReclamationAdminController implements Initializable {
    
    
    
    public static String titreRec;
    public static String contenuRec;
    
    
    @FXML
    private AnchorPane bp;

    @FXML
    private TableView<Reclamation> table;
    
    @FXML
    private TextField ft_titre;
    
    @FXML
    private TableColumn<Reclamation, String> col_titre;

    @FXML
    private TableColumn<Reclamation, String> col_contenu;

    @FXML
    private TableColumn<Reclamation, String> col_etat;

    @FXML
    private TableColumn<Reclamation, LocalDate> col_dateR;

    

    @FXML
    private Button btn_envoyer; 
    @Override
    
    
    public void initialize(URL url, ResourceBundle rb) {
     ReclamationService re = new ReclamationService();
     ObservableList<Object> data = FXCollections.observableArrayList();
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
       col_dateR.setCellValueFactory(new PropertyValueFactory<Reclamation,LocalDate>("date_R")); 
    }
    
    
    

    
    
    
 public void SendMail(String UserEmail, String MessageText){
        try{
            String host ="smtp.gmail.com" ;
            String user = "mohamedkarim.rebey@esprit.tn";
            String pass = "183JMT2582";
            String to = UserEmail;
            String from = "mohamedkarim.rebey@esprit.tn";
            String subject = "Commentaire ";
            String messageText = MessageText;
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }

    }

    
 // @FXML
   // void envoyerReclamation(ActionEvent event) {
 //String titre=ft_titre.getText();
   //   String reponse=faReponse.getText();
        
     // SendMail("mohamedkarim.rebey@esprit.tn", reponse );
   // }
        
    
    
    
    
    
    
    
    @FXML
    void envoyerReclamation(ActionEvent event) throws IOException {
 
        Reclamation song = table.getSelectionModel().getSelectedItem();
        ReclamationService e = new ReclamationService();
        
        if(song != null){
        titreRec=song.getTitre_Reclamation();
        contenuRec=song.getContenu_Reclamation();
        
        
        
        Stage stage = (Stage) bp.getScene().getWindow();
                System.err.println("bbb2");
           // Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("fxml/ModifierE.fxml"));
            AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/ReclamationAdminRepondre.fxml"));
				bp.getChildren().clear();
				bp.getChildren().add(newLoadedPane);
    }
    
    
    }
    
    
}
