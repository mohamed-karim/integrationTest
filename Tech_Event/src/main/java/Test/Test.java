
package Test;

import Entite.Article;
import Entite.Categorie;
import Entite.Commentaire;
import Entite.Sujet;
import Service.ArticleService;
import Service.CommentaireService;
import Service.SujetService;
import java.sql.SQLException;
import java.util.List;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Test {


    
    public static void main(String[] args) throws SQLException {
         ArticleService Ar = new ArticleService();
     //   Article A1 =new Article(4,"article2","bonjour cest le premier article","C:\\Users\\aymen\\Desktop\\googlecloud","titreevnt",1,"16/01/2019");
         Article A2 =new Article(16,4,"article2","bonjour cest le premier article","C:\\Users\\aymen\\Desktop\\googlecloud","titreevnt",1,"16/01/2019");
      
          Ar.supprimerArticle(A2);  
 
        //System.out.println(Ar.rechercherTitre("test1")); 
    }
    
  

   

    
    
}
