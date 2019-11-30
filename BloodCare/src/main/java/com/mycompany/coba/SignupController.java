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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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

    @FXML
    private TextField nama_daftar;

    /*   @FXML
    private TextArea isi;
     */
    private Connection conn = null;

    @FXML
    private void Daftar_baru(ActionEvent event) throws SQLException, IOException {
        System.out.println("masuk");

        String username_daftar = user_daftar.getText();
        String name_daftar = nama_daftar.getText();
        String password_daftar = pass_daftar.getText();
        String date = this.tanggal_daftar.getValue().toString();
        String gender;
        if (laki.isSelected()) {
            gender = "Laki-laki";
        } else {
            gender = "Perempuan";
        }
//        String tanggallahir_daftar = tanggal_daftar.getText();
//        db_connect con = new db_connect();
//       con.connect(username,password);
        Connection con=DBUtil.connect();
        Statement stmt = con.createStatement();
        System.out.println(username_daftar);
        System.out.println(password_daftar);
        String query = "INSERT INTO user VALUES ('" + username_daftar + "','" + name_daftar + "','" + password_daftar + "','" + date + "','" + gender + "')";
        System.out.println(query);

        int rs = stmt.executeUpdate(query);

        System.out.println("ok");

        if (rs == 1) {

            System.out.println("data masuk");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Data Telah terupdate! ", ButtonType.YES);
            alert.showAndWait();

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            // scene.getStylesheets().add("/styles/Style.css");
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } else {
            if (username_daftar.equals("") && (password_daftar.equals(""))) {
                Alert alert_up = new Alert(Alert.AlertType.INFORMATION, "Masukan username dan password terlebih dahulu! ", ButtonType.YES);
                alert_up.showAndWait();

            } else if (username_daftar.equals("")) {
                Alert alert_u = new Alert(Alert.AlertType.INFORMATION, "Masukan username terlebih dahulu! ", ButtonType.YES);
                alert_u.showAndWait();

            } else if (password_daftar.equals("")) {
                Alert alert_p = new Alert(Alert.AlertType.INFORMATION, "Masukan password terlebih dahulu! ", ButtonType.YES);
                alert_p.showAndWait();

            } else {
                Alert alert_unk = new Alert(Alert.AlertType.INFORMATION, "username atau password salah!", ButtonType.YES);
                alert_unk.showAndWait();

                System.out.println("tidak ada");
            }
        }
    }

    @FXML
    private void kembali_index(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        //scene.getStylesheets().add("/styles/Style.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
