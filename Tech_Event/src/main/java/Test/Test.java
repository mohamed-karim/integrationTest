
package Test;


import Entite.User;
import Service.ReclamationService;
import Service.UserService;
import java.sql.SQLException;
import java.util.List;
import Entite.Reclamation;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;


public class Test {


    
    public static void main(String[] args) throws SQLException {
UserService us = new UserService();
      ReclamationService r = new ReclamationService();
      LocalDate currentTime = LocalDate.now(); 
        
        
        LocalDate date1 = currentTime.withDayOfMonth(7).withYear(2010).withMonth(12);
        User u5= new User("karimR", "karim");
       // us.authentification(u5);
      // us.getUserByid(1);
      //  us.modifieruser(2, "houssem", "ali", "ar@gmail.com", 7);
// us.creerUser(new User("777","777","ka","ka","ka",7,"ka"));
     //String test=us.rechercherparrole(1);
        System.err.println("***");
      //System.out.println(us.readAll());  
      //User u1 =new User(5,"1","ka","hama@esprit.tn","hama","farid",01,"bara","fara");
      //us.modifierUser(u1);
   //   us.supprimerUser(u1);
      //String m="karim";
      //boolean test= us.rechercherparNom(m);
      
      //r.creerreclamation(new Reclamation(50,"event", "objet", "null",date1,1));
        //System.out.println(r.readAll());
        
        //r.supprimerReclamation(r1);
       // r.modifierReclamation(r1);
       // r.rechercherparNom("tech");
       ReclamationService rec =new ReclamationService();
      // Reclamation ra1=new Reclamation(2,"dddd","ddd","dddd",date1);
      // rec.creerreclamation(ra1);
        //rec.readReclamtionUser(2);
        System.err.println(rec.readReclamtionUser(32));
        rec.modifierRate(2, "dddd", "4.0");
    }
    
    
    
}
