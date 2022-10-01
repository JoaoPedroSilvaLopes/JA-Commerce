/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.jacommerce.dal;

/**
 *
 * @author finkl
 */

// imports
import java.sql.*;

public class ModuloConexao {
    
    public static Connection conector() {
        // conexao iniciar nula
        java.sql.Connection conexao = null;
        
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3307/dbeco";
        String user = "root";
        String password = "";
        
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } 
        catch (Exception e) {
            return null;
        }
    }
}