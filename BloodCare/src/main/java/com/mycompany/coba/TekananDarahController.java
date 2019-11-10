/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.coba;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.text.Font;
import static javax.swing.JOptionPane.showMessageDialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author root
 */
public class TekananDarahController implements Initializable {

    @FXML
    private Spinner<Integer> spnSistol;

    @FXML
    private Spinner<Integer> spnDiastol;

    @FXML
    private Spinner<Integer> spnPulse;

    @FXML
    private Button btnOK;

    @FXML
    private TableView tableTekananDarah;

    @FXML
    private TableColumn<TekananDarah, Integer> colId;

    @FXML
    private TableColumn<TekananDarah, String> colTanggal;

    @FXML
    private TableColumn<TekananDarah, Integer> colSistol;

    @FXML
    private TableColumn<TekananDarah, Integer> colDiastol;

    @FXML
    private TableColumn<TekananDarah, Integer> colPulse;

    /**
     * Initializes the controller class.
     */
    private void loadData() {
        try {
            ObservableList<TekananDarah> dataTekananDarah = FXCollections.observableArrayList();
            String sql = "SELECT * FROM tekanan_darah";
            Connection con = DBUtil.connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String tanggal = rs.getString("tanggal");
                int sistol = rs.getInt("sistol");
                int diastol = rs.getInt("diastol");
                int pulse = rs.getInt("pulse");
                dataTekananDarah.add(new TekananDarah(id, tanggal, sistol, diastol, pulse));
            }
            tableTekananDarah.setItems(dataTekananDarah);
            con.close();
        } catch (SQLException e) {
            showMessageDialog(null, e.getMessage());
        }
    }

    @FXML
    private void btnOKOnClick(ActionEvent event) {
        String sistol = spnSistol.getEditor().getText();
        String diastol = spnDiastol.getEditor().getText();
        String pulse = spnPulse.getEditor().getText();
        Date today = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String tglHariIni = dateFormatter.format(today);
        String sql = "INSERT INTO tekanan_darah(sistol,diastol,pulse,tanggal) VALUES(" + sistol + "," + diastol + "," + pulse + ",'" + tglHariIni + "')";
        try {
            Connection con = DBUtil.connect();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
            loadData();
        } catch (SQLException e) {
            showMessageDialog(null, e.getMessage());
        }
    }

    @FXML
    private void btnHapusOnClick(ActionEvent event) {
//        int dialogResult=JOptionPane.showOptionDialog(null,"Anda yakin akan menghapus data ini?","Konfirmasi Hapus Data Tekanan Darah",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
//        if(dialogResult==JOptionPane.YES_OPTION){
        Alert alert = new Alert(AlertType.CONFIRMATION, "Anda yakin akan menghapus data ini?", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {

            int selectedRowIdx = tableTekananDarah.getSelectionModel().getSelectedIndex();
            TableColumn colId = (TableColumn) tableTekananDarah.getColumns().get(0);
            Integer id = (Integer) colId.getCellObservableValue(selectedRowIdx).getValue();
            String sql = "DELETE FROM tekanan_darah WHERE id=" + Integer.toString(id);
            try {
                Connection con = DBUtil.connect();
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();
                loadData();
            } catch (SQLException e) {
                showMessageDialog(null, e.getMessage());
            }
        }
    }

    @FXML
    private void re(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/re.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void profil(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/profil_user.fxml"));
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

    @FXML
    private void btnCekTekananDarahOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/TekananDarah.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void btnCekGulaDarahOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/GulaDarah.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
      @FXML
    private void home(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Dasboard.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory<Integer> svfSistol = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 800, 0);
        SpinnerValueFactory<Integer> svfDiastol = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 800, 0);
        SpinnerValueFactory<Integer> svfPulse = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 800, 0);
        spnSistol.setValueFactory(svfSistol);
        spnDiastol.setValueFactory(svfDiastol);
        spnPulse.setValueFactory(svfPulse);
        colId.setCellValueFactory(new PropertyValueFactory<TekananDarah, Integer>("id"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<TekananDarah, String>("tanggal"));
        colSistol.setCellValueFactory(new PropertyValueFactory<TekananDarah, Integer>("sistol"));
        colDiastol.setCellValueFactory(new PropertyValueFactory<TekananDarah, Integer>("diastol"));
        colPulse.setCellValueFactory(new PropertyValueFactory<TekananDarah, Integer>("pulse"));
        loadData();
    }

}
