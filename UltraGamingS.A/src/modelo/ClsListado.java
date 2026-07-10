/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.ClsConexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Juan Porras
 */
public class ClsListado {
//Declaracion de variables

    private String fecRegPer;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet DatosTabla;
    public ResultSet DatosTablaBuscar;
//Encapsulacion

    public String getFecRegPer() {
        return fecRegPer;
    }

    public void setFecRegPer(String fecRegPer) {
        this.fecRegPer = fecRegPer;
    }

//Metodos
    public void LlenarTabla() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select per.idPer, per.nomModPer, mar.nomMar, per.prePer, per.fecRegPer\n"
                    + "from periferico per, marca mar\n"
                    + "where mar.idMar = per.idMarPer");
            objClsConexion.sql.executeQuery();
            DatosTabla = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al llenar la tabla: " + e);
        }

    }

    public void LlenarTablaBuscar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select per.idPer, per.nomModPer, mar.nomMar, per.prePer, per.fecRegPer\n"
                    + "from periferico per, marca mar\n"
                    + "where per.fecRegPer=?\n"
                    + "and mar.idMar = per.idMarPer");
            objClsConexion.sql.setString(1, getFecRegPer());
            objClsConexion.sql.executeQuery();
            DatosTablaBuscar = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al llenar la tabla al buscar con fecha: " + e);
        }

    }

}
