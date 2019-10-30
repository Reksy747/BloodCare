/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.coba;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author root
 */
public class SignupController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
     @FXML
    private TextField user_daftar;
    
    @FXML
    private PasswordField pass_daftar;
    
    @FXML
    private TextArea Status;
    
    @FXML
    private DatePicker tanggal_daftar;
    @FXML
    private RadioButton laki;
    
    @FXML
    private RadioButton perempuan;
    
 /*   @FXML
    private TextArea isi;
    */
     private  Connection conn = null;
   
     @FXML
    private void Daftar_baru(ActionEvent event) throws SQLException, IOException{
        System.out.println("masuk");
            
        String username_daftar = user_daftar.getText();
        String password_daftar = pass_daftar.getText();
        String date = this.tanggal_daftar.getValue().toString();
        String gender;
        if(laki.isSelected()){
            gender = "Laki-laki";
        }else{
            gender = "Perempuan";
        }
//        String tanggallahir_daftar = tanggal_daftar.getText();
//        db_connect con = new db_connect();
//       con.connect(username,password);
String url = "jdbc:sqlite:databaase.db";
 conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            System.out.println(username_daftar);
            System.out.println(password_daftar);
            String query= "INSERT INTO user VALUES ('"+username_daftar+"','"+password_daftar+"','"+date+"','"+gender+"')";
            System.out.println(query);

            int rs = stmt.executeUpdate(query);
            
            System.out.println("ok");
            
            if(rs==1) {
            
                System.out.println("data masuk");
        
              JOptionPane.showMessageDialog(null,"Data Telah terupdate! ");
        
              Parent root=FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
              Scene scene = new Scene(root);
              scene.getStylesheets().add("/styles/Styles.css");
       // scene.getStylesheets().add("/styles/Style.css");
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
         }else{
                if(username_daftar.equals("")&&(password_daftar.equals(""))){
                    JOptionPane.showMessageDialog(null,"Masukan username dan password terlebih dahulu! ");
                }
               else if(username_daftar.equals("")){
                    JOptionPane.showMessageDialog(null,"Masukan username terlebih dahulu! ");
                }
                else if(password_daftar.equals("")){
             JOptionPane.showMessageDialog(null,"Masukan password terlebih dahulu! ");
         }
                        else{
                 JOptionPane.showMessageDialog(null,"username atau password salah!");
                System.out.println("tidak ada");
            }
        }
    }

    
       @FXML
    private void kembali_index(ActionEvent event) throws IOException{
        Parent root=FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        //scene.getStylesheets().add("/styles/Style.css");
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
