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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author root
 */
public class DasboardController implements Initializable {
    
    @FXML
    private LineChart grafikTekananDarah;
    
    @FXML
    private LineChart grafikGulaDarah;
    
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
    private void re(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/re.fxml"));
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

    /**
     * Initializes the controller class.
     */
    private void loadGrafikTekananDarah(){
        XYChart.Series<String,Integer> sistolChartSeries=new XYChart.Series<>();
        XYChart.Series<String,Integer> diastolChartSeries=new XYChart.Series<>();
        XYChart.Series<String,Integer> pulseChartSeries=new XYChart.Series<>();
        sistolChartSeries.setName("Sistol");
        diastolChartSeries.setName("Diastol");
        pulseChartSeries.setName("Pulse");
        try{
            String sql="SELECT tanggal,sistol,diastol,pulse FROM tekanan_darah WHERE username='"+DBUtil.username+"' ORDER BY tanggal";
            Connection con = DBUtil.connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String tanggal = rs.getString("tanggal");
                int sistol = rs.getInt("sistol");
                int diastol = rs.getInt("diastol");
                int pulse = rs.getInt("pulse");
                sistolChartSeries.getData().add(new XYChart.Data<>(tanggal,sistol));
                diastolChartSeries.getData().add(new XYChart.Data<>(tanggal,diastol));
                pulseChartSeries.getData().add(new XYChart.Data<>(tanggal,pulse));
            }
            con.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        grafikTekananDarah.getData().clear();
        grafikTekananDarah.getData().add(sistolChartSeries);
        grafikTekananDarah.getData().add(diastolChartSeries);
        grafikTekananDarah.getData().add(pulseChartSeries);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadGrafikTekananDarah();
    }

}
