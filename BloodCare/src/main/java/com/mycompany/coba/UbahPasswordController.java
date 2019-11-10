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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author root
 */
public class UbahPasswordController implements Initializable {

    Alert a = new Alert(AlertType.NONE);
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
    private void btnOKOnAction(ActionEvent event) throws IOException {
        String passwordLama = txtPasswordLama.getText();
        String passwordBaru = txtPasswordBaru.getText();
        String konfirmasiPasswordBaru = txtKonfirmasiPasswordBaru.getText();
        if (!passwordLama.isEmpty() && !passwordBaru.isEmpty() && !konfirmasiPasswordBaru.isEmpty()) {
            if (passwordLama.equals(DBUtil.password)) {
                if (passwordBaru.equals(konfirmasiPasswordBaru)) {
                    try {
                        String sql = "UPDATE user SET password='" + passwordBaru + "' WHERE username='" + DBUtil.username + "'";
                        Connection con = DBUtil.connect();
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(sql);

                        Alert alert = new Alert(AlertType.INFORMATION, "Password berhasil dirubah!", ButtonType.YES);
                        alert.showAndWait();

                        Parent root = FXMLLoader.load(getClass().getResource("/fxml/profil_user.fxml"));
                        Scene scene = new Scene(root);
                        scene.getStylesheets().add("/styles/Styles.css");
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(scene);
                        window.show();
                    } catch (SQLException e) {

                    }

                } else {
                    Alert alert_salah1 = new Alert(AlertType.WARNING, "Password dan konfirmasi password tidak cocok!", ButtonType.YES);
                    alert_salah1.showAndWait();

                }
            } else {
                Alert alert_salah2 = new Alert(AlertType.WARNING, "Password lama salah!", ButtonType.YES);
                alert_salah2.showAndWait();

            }
        } else {
            Alert alert_salah3 = new Alert(AlertType.WARNING, "Semua field harus terisi!", ButtonType.YES);
            alert_salah3.showAndWait();

        }
    }

}
