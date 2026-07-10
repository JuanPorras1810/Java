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
public class ClsProfesional {

//Declaracion de variables
    private String docProf;
    private String nomProf;
    private String apeProf;
    private String emaProf;
    private String TarProf;
    private String RolProf;
    private String telProf;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet datosProfesional;

//Encapsulacion
    public String getDocProf() {
        return docProf;
    }

    public void setDocProf(String docProf) {
        this.docProf = docProf;
    }

    public String getNomProf() {
        return nomProf;
    }

    public void setNomProf(String nomProf) {
        this.nomProf = nomProf;
    }

    public String getApeProf() {
        return apeProf;
    }

    public void setApeProf(String apeProf) {
        this.apeProf = apeProf;
    }

    public String getEmaProf() {
        return emaProf;
    }

    public void setEmaProf(String emaProf) {
        this.emaProf = emaProf;
    }

    public String getTarProf() {
        return TarProf;
    }

    public void setTarProf(String TarProf) {
        this.TarProf = TarProf;
    }

    public String getRolProf() {
        return RolProf;
    }

    public void setRolProf(String RolProf) {
        this.RolProf = RolProf;
    }

    public String getTelProf() {
        return telProf;
    }

    public void setTelProf(String telProf) {
        this.telProf = telProf;
    }

//Metodos
    public void guardar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into profesional values(?,?,?,?,?,?,?)");
            objClsConexion.sql.setString(1, getDocProf());
            objClsConexion.sql.setString(2, getNomProf());
            objClsConexion.sql.setString(3, getApeProf());
            objClsConexion.sql.setString(4, getEmaProf());
            objClsConexion.sql.setString(5, getTarProf());
            objClsConexion.sql.setString(6, getRolProf());
            objClsConexion.sql.setString(7, getTelProf());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos profesional guardados con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar en profesional: " + e);
        }

    }

    public void buscar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from profesional where docProf=?");
            objClsConexion.sql.setString(1, getDocProf());
            objClsConexion.sql.executeQuery();
            datosProfesional = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar profesional: " + e);
        }
    }

    public void actualizar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("update profesional set nomProf=?,apeProf=?,emaProf=?,TarProf=?,rolProf=?,telProf=? where docProf=?");
            objClsConexion.sql.setString(1, getNomProf());
            objClsConexion.sql.setString(2, getApeProf());
            objClsConexion.sql.setString(3, getEmaProf());
            objClsConexion.sql.setString(4, getTarProf());
            objClsConexion.sql.setString(5, getRolProf());
            objClsConexion.sql.setString(6, getTelProf());
            objClsConexion.sql.setString(7, getDocProf());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro actualizado con éxito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: ");
        }

    }

}
