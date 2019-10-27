/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.coba;

import com.sun.istack.internal.logging.Logger;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author A S U S
 */
public class ReController implements Initializable {

    /**
     * Initializes the controller class.
     */
//    private  Connection conn = null;
//    
//    @FXML
//    private void TekananDarahButton(ActionEvent event) throws SQLException, IOException{
//        System.out.println("masuk");
//    String url = "jdbc:sqlite:databaase.db";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void KadarGulaDarahButton(ActionEvent event) throws IOException {
         javafx.scene.Parent root=FXMLLoader.load(getClass().getResource("/fxml/KadarGulaDarah.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Style.css");
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

  @FXML
   private void TekananDarahButton(ActionEvent event)throws IOException  {    
   javafx.scene.Parent root=FXMLLoader.load(getClass().getResource("/fxml/TekananDarah.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Style.css");
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show(); 
}

}