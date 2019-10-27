/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.coba;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author root
 */

public class SceneController implements Initializable {
    
    @FXML
    private TextField user;
    
    @FXML
    private PasswordField pass;
    
    @FXML
    private TextArea Status;
    
 /*   @FXML
    private TextArea isi;
    */
     private  Connection conn = null;
   
    @FXML
    private void loginButton(ActionEvent event) throws SQLException, IOException{
        System.out.println("masuk");
            
        String username = user.getText();
        String password = pass.getText();
//        db_connect con = new db_connect();
//       con.connect(username,password);
String url = "jdbc:sqlite:databaase.db";
 conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            System.out.println(username);
            System.out.println(password);
            String query= "SELECT * FROM user WHERE username = '"+username+"' and password='"+password+"'";
            System.out.println(query);

            ResultSet rs = stmt.executeQuery(query);
            System.out.println("3");
            if(rs.next()) {
            
                System.out.println("ada");
      
        Parent root=FXMLLoader.load(getClass().getResource("/fxml/re.fxml"));
      
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Style.css");
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
         }else{
                if(username.equals("")&&(password.equals(""))){
                    JOptionPane.showMessageDialog(null,"Masukan username dan password terlebih dahulu! ");
                }
               else if(username.equals("")){
                    JOptionPane.showMessageDialog(null,"Masukan username terlebih dahulu! ");
                }
                else if(password.equals("")){
             JOptionPane.showMessageDialog(null,"Masukan password terlebih dahulu! ");
         }
                        else{
                 JOptionPane.showMessageDialog(null,"username atau password salah!");
                System.out.println("tidak ada");
            }
        }
    }


      
    

    @FXML
    private void Daftar_baru(ActionEvent event) throws IOException{
        Parent root=FXMLLoader.load(getClass().getResource("/fxml/signup.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Style.css");
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    private void ifelse(boolean equals) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    }
