/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.coba;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class
 *
 * @author root
 */
public class Profil_userController implements Initializable {
    
    @FXML
    private Label lblUsername;
    
    @FXML
    private Label lblNama;
    
    @FXML
    private Label lblTanggalLahir;
    
    @FXML
    private Label lblJenisKelamin;
    
    @FXML
    private TextField txtUsername;
    
    @FXML
    private TextField txtNama;
    
    @FXML
    private Button btnUpdate;
    
    @FXML
    private void kembali (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Dasboard.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void btnUpdateOnAction(ActionEvent event){
        String username=txtUsername.getText();
        String nama=txtNama.getText();
        if(!username.isEmpty() && !nama.isEmpty()){
            String sql="UPDATE user SET username='"+username+"', nama='"+nama+"' WHERE username='"+DBUtil.username+"'";
            try{
                Connection con=DBUtil.connect();
                Statement stmt=con.createStatement();
                stmt.executeUpdate(sql);
                DBUtil.username=username;
                DBUtil.nama=nama;
                lblUsername.setText(username);
                lblNama.setText(nama);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Username dan nama berhasil terupdate!", ButtonType.YES);
                        alert.showAndWait();
                
            }
            catch(SQLException e){
                showMessageDialog(null,e.getMessage());
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Username dan nama harus terisi!", ButtonType.YES);
                        alert.showAndWait();
            
        }
    }
    
    @FXML
    private void btnUbahPasswordOnAction(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/UbahPassword.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    private void keluar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblUsername.setText(DBUtil.username);
        lblNama.setText(DBUtil.nama);
        lblTanggalLahir.setText(DBUtil.tanggalLahir);
        lblJenisKelamin.setText(DBUtil.jenisKelamin);
        txtUsername.setText(DBUtil.username);
        txtNama.setText(DBUtil.nama);
    }
    
}
