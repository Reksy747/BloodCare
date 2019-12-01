package com.mycompany.coba;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class
 *
 * @author root
 */
public class EditGulaDarahController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Spinner<Integer> spnMiligram;

    @FXML
    private Spinner<Integer> spnMilimol;

    @FXML
    private DatePicker dpTanggal;

    @FXML
    private Button btnOK;

    private Integer idData;

    @FXML
    private void btnOKOnClick(ActionEvent event) throws IOException {
        String miligram = spnMiligram.getEditor().getText();
        String milimol = spnMilimol.getEditor().getText();
        String tanggal = dpTanggal.getValue().toString();
        String sql = "UPDATE gula_darah SET miligram=" + miligram + ",milimol=" + milimol + ",tanggal='" + tanggal + "' WHERE id=" + idData;
        try {
            Connection con = DBUtil.connect();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/GulaDarah.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (SQLException e) {
            showMessageDialog(null, e.getMessage());
        }
    }

    public void initData(Integer idTerpilih, String tanggalTerpilih, int miligramTerpilih, int milimolTerpilih) {
        SpinnerValueFactory<Integer> svfMiligram = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 800, 0);
        SpinnerValueFactory<Integer> svfMilimol = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 800, 0);
        spnMiligram.setValueFactory(svfMiligram);
        spnMilimol.setValueFactory(svfMilimol);
        this.idData = idTerpilih;
        spnMiligram.getValueFactory().setValue(miligramTerpilih);
        spnMilimol.getValueFactory().setValue(milimolTerpilih);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate tanggalTerpilihLocalDate = LocalDate.parse(tanggalTerpilih, dtf);
        dpTanggal.setValue(tanggalTerpilihLocalDate);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
