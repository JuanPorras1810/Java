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
public class ClsAprendiz {
//Declaracion de variables

    private String docApre;
    private String codGrupoApre;
    private String nomApre;
    private String apeApre;
    private String emaApre;
    private String telApre;
    private String epsApre;
    private String conEmerApre;
    private String telConEmerApre;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet datosAprendiz;
    public ResultSet datosGrupo;

//encapsulacion
    public String getDocApre() {
        return docApre;
    }

    public void setDocApre(String docApre) {
        this.docApre = docApre;
    }

    public String getCodGrupoApre() {
        return codGrupoApre;
    }

    public void setCodGrupoApre(String codGrupoApre) {
        this.codGrupoApre = codGrupoApre;
    }

    public String getNomApre() {
        return nomApre;
    }

    public void setNomApre(String nomApre) {
        this.nomApre = nomApre;
    }

    public String getApeApre() {
        return apeApre;
    }

    public void setApeApre(String apeApre) {
        this.apeApre = apeApre;
    }

    public String getEmaApre() {
        return emaApre;
    }

    public void setEmaApre(String emaApre) {
        this.emaApre = emaApre;
    }

    public String getTelApre() {
        return telApre;
    }

    public void setTelApre(String telApre) {
        this.telApre = telApre;
    }

    public String getEpsApre() {
        return epsApre;
    }

    public void setEpsApre(String epsApre) {
        this.epsApre = epsApre;
    }

    public String getConEmerApre() {
        return conEmerApre;
    }

    public void setConEmerApre(String conEmerApre) {
        this.conEmerApre = conEmerApre;
    }

    public String getTelConEmerApre() {
        return telConEmerApre;
    }

    public void setTelConEmerApre(String telConEmerApre) {
        this.telConEmerApre = telConEmerApre;
    }
//Metodos 

    public void guardar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into aprendiz values(?,?,?,?,?,?,?,?,?)");
            objClsConexion.sql.setString(1, getDocApre());
            objClsConexion.sql.setString(2, getCodGrupoApre());
            objClsConexion.sql.setString(3, getNomApre());
            objClsConexion.sql.setString(4, getApeApre());
            objClsConexion.sql.setString(5, getEmaApre());
            objClsConexion.sql.setString(6, getTelApre());
            objClsConexion.sql.setString(7, getEpsApre());
            objClsConexion.sql.setString(8, getConEmerApre());
            objClsConexion.sql.setString(9, getTelConEmerApre());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos del aprendiz guardados con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el aprendiz: " + e);
        }
    }

    public void buscar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from aprendiz where docApre=?");
            objClsConexion.sql.setString(1, getDocApre());
            objClsConexion.sql.executeQuery();
            datosAprendiz = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar aprendiz: " + e);
        }

    }

    public void actualizar() {

        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("update aprendiz set codGrupoApre=?,nomApre=?,apeApre=?,emaApre=?,telApre=?,epsApre=?,conEmerApre=?,telConEmerApre=? where docApre=?");
            objClsConexion.sql.setString(1, getCodGrupoApre());
            objClsConexion.sql.setString(2, getNomApre());
            objClsConexion.sql.setString(3, getApeApre());
            objClsConexion.sql.setString(4, getEmaApre());
            objClsConexion.sql.setString(5, getTelApre());
            objClsConexion.sql.setString(6, getEpsApre());
            objClsConexion.sql.setString(7, getConEmerApre());
            objClsConexion.sql.setString(8, getTelConEmerApre());
            objClsConexion.sql.setString(9, getDocApre());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro actualizado con éxito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + e);
        }
    }

    public void LlenarComboGrupo() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from grupo");
            objClsConexion.sql.executeQuery();
            datosGrupo = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al generar los grupos: " + e);
        }

    }

}
