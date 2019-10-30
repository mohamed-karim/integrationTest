
package Test;
import Entite.Evennement;

import Entite.Categorie;
import Entite.Commentaire;
import Entite.Sujet;
import Service.CommentaireService;
import Service.EvennementService;
import Service.SujetService;
import java.sql.SQLException;
import java.util.List;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Test {


    
    public static void main(String[] args) throws SQLException {
  /*  Commentaire c3=new Commentaire(5,7,77, "sss" ,"bb");
    CommentaireService service=new  CommentaireService();
   // service.ajouterCommentaire(c3);
   // service.ajouterCommentaire(c2);
    //System.out.println(service.readAll());
   // service.supprimerCommentaire(c1);
    service.supprimerCommentaire(c3);
   // service.modifierCommentaire(c3);
    //service.modifierCommentaire(c1);
   Sujet s1=new Sujet(2,1,"mybesttitre", "voir here " ,"madesa3a");
    SujetService servicee=new  SujetService();
   //servicee.ajouterSujet(s1);
   // System.out.println(servicee.readAllS());
    //servicee.supprimerSujet(s1);
   // servicee.modifierSujet(s1);
    boolean a = servicee.rechercherparNom("mybeste");
    }
    
   */
 
  SujetService sujet = new SujetService();
  /* Sujet sujet1 = new Sujet(2, "dtfyj", "jvhhj", Categorie.Astronomie);
   sujet.ajouterSujet(sujet1);
        System.out.println(sujet1);
    */
 CommentaireService commentaireService = new CommentaireService();
        System.out.println(commentaireService.readAll(93));

        EvennementService es = new EvennementService();
        Evennement e = new Evennement();
        System.err.println(es.getnbrr());
    }
    
  
   

    
    
}
