/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.coba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author root
 */
public class db_connect {
   private  Connection conn = null;
    
    public void connect(String username, String password) {
       
        try {
            // db parameters
            String url = "jdbc:sqlite:databaase.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            System.out.println(username);
            System.out.println(password);
            String query= "SELECT * FROM user WHERE username = '"+username+"' and password='"+password+"'";
            System.out.println(query);

            ResultSet rs = stmt.executeQuery(query);
            System.out.println("3");
            if(rs.next()) {
                System.out.println("ada");
            }else{
                System.out.println("tidak ada");
            }
          
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public void userQuery(String username, String password) throws SQLException{
//        connect();
        
    }

    Connection connect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}