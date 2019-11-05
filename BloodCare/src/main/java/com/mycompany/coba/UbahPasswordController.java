/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.coba;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author root
 */
public class UbahPasswordController implements Initializable{
    
    @FXML
    private TextField txtPasswordLama;
    
    @FXML
    private TextField txtPasswordBaru;
    
    @FXML
    private TextField txtKonfirmasiPasswordBaru;
    
    @FXML
    private Button btnOK;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    private void btnOKOnAction(ActionEvent event){
        String passwordLama=txtPasswordLama.getText();
        String passwordBaru=txtPasswordBaru.getText();
        String konfirmasiPasswordBaru=txtKonfirmasiPasswordBaru.getText();
        if(!passwordLama.isEmpty() && !passwordBaru.isEmpty() && !konfirmasiPasswordBaru.isEmpty()){
            if(passwordLama.equals(DBUtil.password)){
                if(passwordBaru.equals(konfirmasiPasswordBaru)){
                    try{
                        String sql="UPDATE user SET password='"+passwordBaru+"' WHERE username='"+DBUtil.username+"'";
                        Connection con=DBUtil.connect();
                        Statement stmt=con.createStatement();
                        stmt.executeUpdate(sql);
                        showMessageDialog(null,"Password berhasil diubah!");
                    }
                    catch(SQLException e){

                    }

                }
                else{
                    showMessageDialog(null,"Password dan konfirmasi password tidak cocok!");
                }
            }
            else{
                showMessageDialog(null,"Password lama salah!");
            }
        }
        else
            showMessageDialog(null,"Semua field harus terisi!");
    }
    
}
