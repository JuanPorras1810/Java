/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author Juan Porras
 */
public class ClsConexion {
//Declaracion de variables

    public PreparedStatement sql;
    public Connection con;

//Metodo
    public void conectar() {
        String db = "jdbc:mysql://localhost:3306/Citas";
        String usuario = "root";
        String password = "1234";
        try {
            String controlador = "com.mysql.cj.jdbc.Driver";
            Class.forName(controlador);
            con = DriverManager.getConnection(db, usuario, password);
            JOptionPane.showMessageDialog(null, "Conexion exitosa");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar: " + e);
        }

    }

}
