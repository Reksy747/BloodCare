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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
    private Connection conn = null;

    @FXML
    private void loginButton(ActionEvent event) throws SQLException, IOException {

        String username = user.getText();
        String password = pass.getText();
//        db_connect con = new db_connect();
//       con.connect(username,password);
        String query = "SELECT * FROM user WHERE username = '" + username + "' and password='" + password + "'";
        Connection con=DBUtil.connect();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            DBUtil.username = rs.getString("username");
            DBUtil.nama = rs.getString("nama");
            DBUtil.password = rs.getString("password");
            DBUtil.tanggalLahir = rs.getString("tanggal");
            DBUtil.jenisKelamin = rs.getString("gender");

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Dasboard.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            //scene.getStylesheets().add("/styles/Style.css");
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } else {
            if (username.equals("") && (password.equals(""))) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Masukan username dan password terlebih dahulu! ", ButtonType.YES);
                alert.showAndWait();

            } else if (username.equals("")) {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "Masukan username terlebih dahulu! ", ButtonType.YES);
                alert1.showAndWait();

            } else if (password.equals("")) {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "Masukan password terlebih dahulu! ", ButtonType.YES);
                alert2.showAndWait();

            } else {
                Alert alert3 = new Alert(Alert.AlertType.WARNING, "username atau password salah!", ButtonType.YES);
                alert3.showAndWait();

                System.out.println("tidak ada");
            }
        }
        con.close();
    }

    @FXML
    private void Daftar_baru(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/signup.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/signup.css");
        // scene.getStylesheets().add("/styles/Style.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
