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
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author root
 */
public class GulaDarahController implements Initializable {

    @FXML
    private Spinner<Integer> spnMiligram;

    @FXML
    private Spinner<Integer> spnMilimol;

    @FXML
    private Button btnOK;

    @FXML
    private TableView tableGulaDarah;
    
    @FXML
    private TableColumn<GulaDarah, Integer> colId;

    @FXML
    private TableColumn<GulaDarah, String> colTanggal;

    @FXML
    private TableColumn<GulaDarah, Integer> colMiligram;

    @FXML
    private TableColumn<GulaDarah, Integer> colMilimol;

    /**
     * Initializes the controller class.
     */
    private void loadData() {
        try {
            ObservableList<GulaDarah> dataGulaDarah = FXCollections.observableArrayList();
            String sql = "SELECT * FROM gula_darah WHERE username='"+DBUtil.username+"'";
            Connection con = DBUtil.connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String tanggal = rs.getString("tanggal");
                int miligram = rs.getInt("miligram");
                int milimol = rs.getInt("milimol");
                dataGulaDarah.add(new GulaDarah(id, tanggal, miligram, milimol));
            }
            tableGulaDarah.setItems(dataGulaDarah);
            con.close();
        } catch (SQLException e) {
            showMessageDialog(null, e.getMessage());
        }
    }

    @FXML
    private void btnOKOnClick(ActionEvent event) {
        String miligram = spnMiligram.getEditor().getText();
        String milimol = spnMilimol.getEditor().getText();
        Date today = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String tglHariIni = dateFormatter.format(today);
        String sql = "INSERT INTO gula_darah(miligram,milimol,tanggal,username) VALUES(" + miligram + "," + milimol + ",'" + tglHariIni + "','"+DBUtil.username+"')";
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
    private void btnEditOnClick(ActionEvent event) throws IOException{
        int selectedRowIdx = tableGulaDarah.getSelectionModel().getSelectedIndex();
        Integer idTerpilih = (Integer) colId.getCellObservableValue(selectedRowIdx).getValue();
        String tanggalTerpilih = (String) colTanggal.getCellObservableValue(selectedRowIdx).getValue();
        Integer miligramTerpilih = (Integer) colMiligram.getCellObservableValue(selectedRowIdx).getValue();
        Integer milimolTerpilih = (Integer) colMilimol.getCellObservableValue(selectedRowIdx).getValue();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/fxml/EditGulaDarah.fxml"));
        Scene scene = new Scene((Parent)root.load());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        EditGulaDarahController editGulaDarahController=root.getController();
        editGulaDarahController.initData(idTerpilih,tanggalTerpilih,miligramTerpilih,milimolTerpilih);
        window.show();
    }

    @FXML
    private void btnHapusOnClick(ActionEvent event) {
//        int dialogResult = JOptionPane.showOptionDialog(null, "Anda yakin akan menghapus data ini?", "Konfirmasi Hapus Data Tekanan Darah", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
//        if (dialogResult == JOptionPane.YES_OPTION) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Anda yakin akan menghapus data ini?", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            int selectedRowIdx = tableGulaDarah.getSelectionModel().getSelectedIndex();
            TableColumn colId = (TableColumn) tableGulaDarah.getColumns().get(0);
            int id = (int) colId.getCellObservableValue(selectedRowIdx).getValue();
            String sql = "DELETE FROM gula_darah WHERE id=" + Integer.toString(id);
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

//    @FXML
//    private void keluar(ActionEvent event) {
//    }
//
//    @FXML
//    private void re(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/re.fxml"));
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add("/styles/Styles.css");
//        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        window.setScene(scene);
//        window.show();
//    }
//
//    @FXML
//    private void profil(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/profil_user.fxml"));
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add("/styles/Styles.css");
//        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        window.setScene(scene);
//        window.show();
//    }
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
    private void btnRekomendasiOnClick(ActionEvent event) throws IOException{
        int selectedRowIdx = tableGulaDarah.getSelectionModel().getSelectedIndex();
        Integer miligramTerpilih = (Integer) colMiligram.getCellObservableValue(selectedRowIdx).getValue();
        Integer milimolTerpilih = (Integer) colMilimol.getCellObservableValue(selectedRowIdx).getValue();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/fxml/rekomendasiTekanan.fxml"));
        Scene scene = new Scene((Parent)root.load());
         scene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        RekomendasiTekananController rekomendasiMakananController=root.getController();
        rekomendasiMakananController.initData(miligramTerpilih,milimolTerpilih);
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
        SpinnerValueFactory<Integer> svfMiligram = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 120, 0);
        SpinnerValueFactory<Integer> svfMilimol = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 8, 0);
        spnMiligram.setValueFactory(svfMiligram);
        spnMilimol.setValueFactory(svfMilimol);
        colId.setCellValueFactory(new PropertyValueFactory<GulaDarah, Integer>("id"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<GulaDarah, String>("tanggal"));
        colMiligram.setCellValueFactory(new PropertyValueFactory<GulaDarah, Integer>("miligram"));
        colMilimol.setCellValueFactory(new PropertyValueFactory<GulaDarah, Integer>("milimol"));
        loadData();
    }

}
