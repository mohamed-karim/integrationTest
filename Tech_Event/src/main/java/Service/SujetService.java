/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Categorie;
import Entite.Sujet;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;
/**
 *
 * @author hp
 */
public class SujetService {
    private Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public SujetService() {
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);  
        }
    }
    
    public void ajouterSujet(Sujet s) throws SQLException {
          java.util.Date date1= new java.util.Date();  
        String Temps_Sujet = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        s.setTemps_Sujet(Temps_Sujet);
        String req1 = "INSERT INTO `sujet` (`Id_User`, `Titre_Sujet`,`Contenu_Sujet`,`Temps_Sujet`,`Categorie`) VALUES ('" + s.getId_User() + "', '" + s.getTitre_Sujet() + "', '" + s.getContenu_Sujet() + "', '" + s.getTemps_Sujet() +"', '" + s.getCategorie()+ "');";
        ste.executeUpdate(req1);
        System.out.println("elment inseré");

    }
    
    public List<Sujet> readAllS() throws SQLException
    {List<Sujet> list=new ArrayList<>();
    ResultSet res=ste.executeQuery("select * from sujet");
    Sujet com=null;
    while (res.next()) {            
      com=new Sujet(res.getInt(1),res.getInt(2),res.getString(3),res.getString(4),res.getString(5),Categorie.valueOf(res.getString(6)));
      list.add(com);
        }
    return list;
    } 
    
     public List<Sujet> FindSujetByIdUser(int id_user) throws SQLException
    {
        List<Sujet> list1=new ArrayList<>();
    ResultSet res=ste.executeQuery("select * from sujet WHERE Id_User='"+id_user+"'");
    Sujet com=null;
    while (res.next()) {            
      com=new Sujet(res.getInt(1),res.getInt(2),res.getString(3),res.getString(4),res.getString(5),
              Categorie.valueOf(res.getString(6)));
      list1.add(com);
        }
    return list1;
    } 
     
   /*   public void FindSujetByIdSujet(int id_sujet) throws SQLException
    {
    
    } 
     */
    
    public void supprimerSujet(Sujet s) {
            
        
        try { 
            String req="DELETE FROM `sujet` WHERE `sujet`.`Id_Sujet` = ?";
            PreparedStatement ste = con.prepareStatement(req);
            ste.setInt(1, s.getId_Sujet());
            ste.executeUpdate();
            System.out.println("element supprimer");
         
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public void modifierSujet(Sujet s) {
        
         
        
   String sql="UPDATE sujet SET `Id_User`=?,`Titre_Sujet`=?,`Contenu_Sujet`=?,`Temps_Sujet`=?,`Categorie`=? WHERE Id_Sujet="+s.getId_Sujet();
   PreparedStatement ste;
        try {
            ste =con.prepareStatement(sql);
            ste.setInt(1, s.getId_User());
            ste.setString(2, s.getTitre_Sujet());
            
            ste.setString(3, s.getContenu_Sujet());
            ste.setString(4, s.getTemps_Sujet());
            ste.setString(5, s.getCategorie().toString());
                
            ste.executeUpdate();
            int rowsUpdated=ste.executeUpdate();
            if (rowsUpdated>0){
            System.out.println("La modification de produit"+s.getId_Sujet()+" a été éffectué avec succée ");
            }}
        catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
public boolean rechercherparNom(String nom) {
        boolean test=false;
        String req = "SELECT * from sujet where Titre_Sujet='"+nom+"'";
            
     
        try {
           
            ResultSet stp=ste.executeQuery(req);
            stp.last();
            if(stp.getRow()!=0)
            {
                test=true;
                System.out.println(test+"sujet trouver");
            }
            else
            {
                test=false;
                System.out.println(test+"sujet non trouver");
            }
        }
            catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;
      }

    
  
    }
