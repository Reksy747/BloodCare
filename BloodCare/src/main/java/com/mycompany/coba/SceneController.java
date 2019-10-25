/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.coba;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.*;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    @FXML
    private void loginButton(ActionEvent event) throws SQLException{
        System.out.println("masuk");
            
        String username = user.getText();
        String password = pass.getText();
        db_connect con = new db_connect();
       con.connect(username,password);
//        Statement statement;
//        statement = connection.createStatement();
//        System.out.println("konek gan");
      
//        String query= "SELECT * FROM user WHERE username = '"+username+"' and password='"+password+"'";
//        ResultSet rs = statement.executeQuery(query);
//            
//            if(rs.next()){
//                Status.setText(query);
//                Status.setText("sukses gan!");
//            }else{
//                Status.setText("GAGAL!");
//                Status.setText("GAGAL CUK!");
//                        
//            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    }
