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
public class ClsCaso {

//Declaracion de variables
    private int codCas;
    private int codIntCas;
    private String ComIntCas;
    private String fecIniCas;
    private String fecCieCas;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet DatosBuscar;
    public ResultSet DatosConsecutivo;
    public ResultSet DatosInteraccion;
    public ResultSet DatosFecha;

//Encapsulacion
    public int getCodCas() {
        return codCas;
    }

    public void setCodCas(int codCas) {
        this.codCas = codCas;
    }

    public int getCodIntCas() {
        return codIntCas;
    }

    public void setCodIntCas(int codIntCas) {
        this.codIntCas = codIntCas;
    }

    public String getComIntCas() {
        return ComIntCas;
    }

    public void setComIntCas(String ComIntCas) {
        this.ComIntCas = ComIntCas;
    }

    public String getFecIniCas() {
        return fecIniCas;
    }

    public void setFecIniCas(String fecIniCas) {
        this.fecIniCas = fecIniCas;
    }

    public String getFecCieCas() {
        return fecCieCas;
    }

    public void setFecCieCas(String fecCieCas) {
        this.fecCieCas = fecCieCas;
    }

//Metodos 
    public void LlenarInteraccion() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from interaccion");
            objClsConexion.sql.executeQuery();
            DatosInteraccion = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar las interacciones: " + e);
        }
    }

    public void BuscarFecha() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select fecInt from interaccion where codInt=?");
            objClsConexion.sql.setInt(1, getCodIntCas());
            objClsConexion.sql.executeQuery();
            DatosFecha = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar la fecha inicio del caso: " + e);
        }
    }

    public void guardar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into caso(codIntCas,comIntCas,fecIniCas,fecCieCas) values(?,?,?,?)");
            objClsConexion.sql.setInt(1, getCodIntCas());
            objClsConexion.sql.setString(2, getComIntCas());
            objClsConexion.sql.setString(3, getFecIniCas());
            objClsConexion.sql.setString(4, getFecCieCas());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Caso guardada con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el caso: " + e);
        }

    }


    public void consecutivo() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select count(*)+1 from caso");
            objClsConexion.sql.executeQuery();
            DatosConsecutivo = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al generar el consecutivo: " + e);
        }
    }

}
