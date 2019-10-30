
package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;
import Entite.User;
//import IService.IUserService;

public class UserService {
    
    Connection c = DataSource.getInstance().getConnection();
       
    Statement ste;

    public UserService() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
        public void creerUser(User u) throws SQLException{
      
            String req1="INSERT INTO `user` (`Login`, `Mdp`, `E_mailU`, `NomU`, `PrenomU`,`NumTel`, `PhotoProfil`, `RoleU`) VALUES ('"+u.getLogin()+"', '"+u.getMdp()+"', '"+u.getE_mailU()+"', '"+u.getNomU()+"', '"+u.getPrenomU()+"', '"+u.getNumTel()+"', '"+u.getPhotoProfil()+"', '"+u.getRoleU()+"');";
            
            ste.executeUpdate(req1);
            System.out.println("elment insert");     
    }
    
        public List<User> readAll() throws SQLException
    {List<User> list=new ArrayList<>();
    ResultSet res=ste.executeQuery("select * from user");
    User per=null;
    while (res.next()) {            
      per=new User(res.getInt(1), res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getInt(9),res.getString(10),res.getString(11));
      list.add(per);
        }
    return list;
    } 
        
        public void modifierUser(User u) {
        
         
        
   String sql="UPDATE user SET `Login`=?,`Mdp`=?,`E_mailU`=? ,`NomU`=? ,`PrenomU`=?,`NumTel`=?,`PhotoProfil`=?,`RoleU`=? WHERE Id_User="+u.getId_User();
   PreparedStatement ps;
        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, u.getLogin());
            ps.setString(2, u.getMdp());
            ps.setString(3, u.getE_mailU());
            ps.setString(4, u.getNomU());
            ps.setString(5, u.getPrenomU());
            ps.setInt(8, u.getNumTel());
            ps.setString(9, u.getPhotoProfil());
            ps.setString(10, u.getRoleU());
            
            ps.executeUpdate();
            int rowsUpdated=ps.executeUpdate();
            if (rowsUpdated>0){
            System.out.println("La modification de user"+u.getId_User()+" a été éffectué avec succée ");
            }}
        catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
         public void supprimerUser(User p) {
            
        
        try { 
            String req="DELETE FROM `user` WHERE `user`.`Id_User` = ?";
            PreparedStatement ps = c.prepareStatement(req);
            ps.setInt(1, p.getId_User());
            ps.executeUpdate();
            System.out.println("element supprimer");
         
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        public boolean rechercherparNom(String nom) {
        boolean test=false;
        String req = "SELECT * from user where NomU='"+nom+"'";
             
        try {
           
            ResultSet stp=ste.executeQuery(req);
            stp.last();
            if(stp.getRow()!=0)
            {
                test=true;
                System.out.println(test+" user trouver");
            }
            else
            {
                test=false;
                System.out.println(test+" user non trouver");
            }
        }
            catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;
        
    } 
    
        
        public int authentification(User u){
        int test = 0;
          try {
          String req2="select `Id_User`,`Login`, `Mdp` from user";
          ResultSet res=  ste.executeQuery(req2);
          while (res.next() && (test==0)) {
            if (u.getLogin().equals((res.getString("Login"))) && (res.getString("Mdp").equals(u.getMdp()))){
                
                 test=res.getInt(1);
                 System.err.println(test);
            }
            
            else{
           System.err.println("erreur");

            test=0;
            }
         
    
         
          }
    }   catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;
        }
}
