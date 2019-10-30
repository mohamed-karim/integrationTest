/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Entite.User;
import Service.UserService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class GstionCompteAdminController implements Initializable {


    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> col_login;
    @FXML
    private TableColumn<User,String> col_email;
    @FXML
    private TableColumn<User,Integer > col_num;
    @FXML
    private TableColumn<User,String > col_role;    
    @FXML
    private TableColumn<User, Integer> col_nbr;
  
    
    @FXML
    private Button btnremove;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btn_export;
    @FXML
    private ComboBox<String> comboRole;
    private FileInputStream fis;
    
    ObservableList<String> list1=FXCollections.observableArrayList("admin","Membre","MembreActif");
                                                                                                                                                                                                  

    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
       
        try {
            Aff();
            comboRole.setItems(list1);
        } catch (SQLException ex) {
            Logger.getLogger(GstionCompteAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    private void Aff() throws SQLException { 
     try {
            UserService us = new UserService();
            int id= AuthentificationController.test;
            ArrayList<User> arrayList = (ArrayList<User>) us.readmembre();
            ObservableList obs = FXCollections.observableArrayList(arrayList);
            table.setItems(obs);
            
            col_login.setCellValueFactory(new PropertyValueFactory<User,String>("Login"));
            col_email.setCellValueFactory(new PropertyValueFactory<User,String>("E_mailU"));
            col_num.setCellValueFactory(new PropertyValueFactory<User,Integer>("NumTel"));
            col_role.setCellValueFactory(new PropertyValueFactory<User,String>("RoleU"));
            col_nbr.setCellValueFactory(new PropertyValueFactory<User,Integer>("nbr_c"));
        } catch (SQLException ex) {
            Logger.getLogger(GstionCompteAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }    
    
    
    
    @FXML
    private void remove(ActionEvent event) throws SQLException, IOException {

                 User S =  table.getSelectionModel().getSelectedItem();
         UserService es= new UserService();        
       es.deleteUser(S.getLogin());
     //  JOptionPane.showMessageDialog(null,"supprimee");
        Aff();
      
        }
    
        @FXML
    private void update(ActionEvent event) throws SQLException, IOException {
        String roletest;
         User S =  table.getSelectionModel().getSelectedItem();
         UserService es= new UserService(); 
         roletest=comboRole.getValue();
          es.modifierRole(S.getLogin(), roletest);
       JOptionPane.showMessageDialog(null,"modification faite");
        Aff();
      
        }
    
    
    
      private Connection c = DataSource.getInstance().getConnection();
      private Statement ste;
     @FXML
    void Export(ActionEvent event) throws FileNotFoundException, IOException, SQLException {
        String query="SELECT * FROM user";
        ste=c.createStatement();
        Statement stm=c.createStatement();
        ResultSet rst=stm.executeQuery(query);
        
        
        
        
        
        User a= new User();
        
        
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet =wb.createSheet("UserList");
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("Login");
        header.createCell(1).setCellValue("Email");
        header.createCell(2).setCellValue("Role");
        header.createCell(3).setCellValue("NbrEvenement");
        int index = 1;
        while(rst.next()){
            XSSFRow row = sheet.createRow(index);
            row.createCell(0).setCellValue(rst.getString("Login"));
            row.createCell(1).setCellValue(rst.getString("E_mailU"));
            row.createCell(2).setCellValue(rst.getString("NumTel"));
            row.createCell(3).setCellValue(rst.getString("RoleU"));
            index ++ ;
        }
        FileOutputStream fileOut = new FileOutputStream("user.xlsx");
        wb.write(fileOut);
        fileOut.close();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("information dialog");
        alert.setContentText("Annonce Details Exported in Excel sheet.");
        alert.showAndWait();
    }
    
    
    } 
                                                                                                                                                                                                                                                                     