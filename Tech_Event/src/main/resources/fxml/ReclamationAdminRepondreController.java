/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class ReclamationAdminRepondreController implements Initializable {

    @FXML
    private TextField ft_mail;
    @FXML
    private TextField ft_titre;
    @FXML
    private TextArea faContenu;
    @FXML
    private TextArea faReponse;
    @FXML
    private Button btn_envoyerr;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      String contenu=ReclamationAdminController.contenuRec;
     String titre= ReclamationAdminController.titreRec;
     ft_titre.setText(titre);
     faContenu.setText(contenu);
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
    
    
    
    
    
    
    
    
    @FXML
    private void repondreReclamationuSER(ActionEvent event) {
        String mail=ft_mail.getText();
        String titre=ft_titre.getText();
     String reponse=faReponse.getText();
        
      SendMail(mail, reponse );
    }

    
    
}
