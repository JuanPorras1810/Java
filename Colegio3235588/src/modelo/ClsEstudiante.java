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
public class ClsEstudiante {
//Declaracion de variables 

    private String docEst;
    private String nomEst;
    private String apeEst;
    private String dirEst;
    private String telEst;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet datosEstudiante;

//encapsulacion
    public String getDocEst() {
        return docEst;
    }

    public void setDocEst(String docEst) {
        this.docEst = docEst;
    }

    public String getNomEst() {
        return nomEst;
    }

    public void setNomEst(String nomEst) {
        this.nomEst = nomEst;
    }

    public String getApeEst() {
        return apeEst;
    }

    public void setApeEst(String apeEst) {
        this.apeEst = apeEst;
    }

    public String getDirEst() {
        return dirEst;
    }

    public void setDirEst(String dirEst) {
        this.dirEst = dirEst;
    }

    public String getTelEst() {
        return telEst;
    }

    public void setTelEst(String telEst) {
        this.telEst = telEst;
    }
//Metodos

    public void guardar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into ESTUDIANTE values(?,?,?,?,?)");
            objClsConexion.sql.setString(1, getDocEst());
            objClsConexion.sql.setString(2, getNomEst());
            objClsConexion.sql.setString(3, getApeEst());
            objClsConexion.sql.setString(4, getDirEst());
            objClsConexion.sql.setString(5, getTelEst());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos del estudiante guardados con exito!!");
//objClsConexion.cerrar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar en estudiante" + e);
        }

    }

    public void buscar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("SELECT * FROM ESTUDIANTE WHERE docEst=?");
            objClsConexion.sql.setString(1, getDocEst());
            objClsConexion.sql.executeQuery();
            datosEstudiante = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar estudiante" + e);
        }

    }

    public void actualizar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("UPDATE ESTUDIANTE SET nomEst=?,apeEst=?,dirEst=?,telEst=? WHERE docEst=?");
            objClsConexion.sql.setString(1, getNomEst());
            objClsConexion.sql.setString(2, getApeEst());
            objClsConexion.sql.setString(3, getDirEst());
            objClsConexion.sql.setString(4, getTelEst());
            objClsConexion.sql.setString(5, getDocEst());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro actualizado con éxito");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error al actualizar:" + e);
        }
    }

}
