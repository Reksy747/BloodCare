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
public class EditTekananDarahController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Spinner<Integer> spnSistol;

    @FXML
    private Spinner<Integer> spnDiastol;

    @FXML
    private Spinner<Integer> spnPulse;

    @FXML
    private DatePicker dpTanggal;

    @FXML
    private Button btnOK;

    private Integer idData;

    @FXML
    private void btnOKOnClick(ActionEvent event) throws IOException {
        String sistol = spnSistol.getEditor().getText();
        String diastol = spnDiastol.getEditor().getText();
        String pulse = spnPulse.getEditor().getText();
        String tanggal = dpTanggal.getValue().toString();
        String sql = "UPDATE tekanan_darah SET sistol=" + sistol + ",diastol=" + diastol + ",pulse=" + pulse + ",tanggal='" + tanggal + "' WHERE id=" + idData;
        try {
            Connection con = DBUtil.connect();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/TekananDarah.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (SQLException e) {
            showMessageDialog(null, e.getMessage());
        }
    }

    public void initData(Integer idTerpilih, String tanggalTerpilih, int sistolTerpilih, int diastolTerpilih, int pulseTerpilih) {
        SpinnerValueFactory<Integer> svfSistol = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 800, 0);
        SpinnerValueFactory<Integer> svfDiastol = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 800, 0);
        SpinnerValueFactory<Integer> svfPulse = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 800, 0);
        spnSistol.setValueFactory(svfSistol);
        spnDiastol.setValueFactory(svfDiastol);
        spnPulse.setValueFactory(svfPulse);
        this.idData = idTerpilih;
        spnSistol.getValueFactory().setValue(sistolTerpilih);
        spnDiastol.getValueFactory().setValue(diastolTerpilih);
        spnPulse.getValueFactory().setValue(pulseTerpilih);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate tanggalTerpilihLocalDate = LocalDate.parse(tanggalTerpilih, dtf);
        dpTanggal.setValue(tanggalTerpilihLocalDate);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void initData(Integer idTerpilih, String tanggalTerpilih, Integer miligramTerpilih, Integer milimolTerpilih) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
