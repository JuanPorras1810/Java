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
public class ClsUsuario {
//Declaracion de variables

    private String idUsu;
    private String tipDocUsu;
    private String nomUsu;
    private String emaUsu;
    private String dirUsu;
    private String telUsu;
    private String telAltUsu;
    private String rolUsu;
    private String conUsu;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet DatosBuscar;
//Encapsulacion

    public String getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(String idUsu) {
        this.idUsu = idUsu;
    }

    public String getTipDocUsu() {
        return tipDocUsu;
    }

    public void setTipDocUsu(String tipDocUsu) {
        this.tipDocUsu = tipDocUsu;
    }

    public String getNomUsu() {
        return nomUsu;
    }

    public void setNomUsu(String nomUsu) {
        this.nomUsu = nomUsu;
    }

    public String getEmaUsu() {
        return emaUsu;
    }

    public void setEmaUsu(String emaUsu) {
        this.emaUsu = emaUsu;
    }

    public String getDirUsu() {
        return dirUsu;
    }

    public void setDirUsu(String dirUsu) {
        this.dirUsu = dirUsu;
    }

    public String getTelUsu() {
        return telUsu;
    }

    public void setTelUsu(String telUsu) {
        this.telUsu = telUsu;
    }

    public String getTelAltUsu() {
        return telAltUsu;
    }

    public void setTelAltUsu(String telAltUsu) {
        this.telAltUsu = telAltUsu;
    }

    public String getRolUsu() {
        return rolUsu;
    }

    public void setRolUsu(String rolUsu) {
        this.rolUsu = rolUsu;
    }

    public String getConUsu() {
        return conUsu;
    }

    public void setConUsu(String conUsu) {
        this.conUsu = conUsu;
    }

//Metodos
    public void registrar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into usuario values(?,?,?,?,?,?,?,?,?)");
            objClsConexion.sql.setString(1, getIdUsu());
            objClsConexion.sql.setString(2, getTipDocUsu());
            objClsConexion.sql.setString(3, getNomUsu());
            objClsConexion.sql.setString(4, getEmaUsu());
            objClsConexion.sql.setString(5, getDirUsu());
            objClsConexion.sql.setString(6, getTelUsu());
            objClsConexion.sql.setString(7, getTelAltUsu());
            objClsConexion.sql.setString(8, getRolUsu());
            objClsConexion.sql.setString(9, getConUsu());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario registrados con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el usuario: " + e);
        }

    }

    public void buscar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from usuario where idUsu=?");
            objClsConexion.sql.setString(1, getIdUsu());
            objClsConexion.sql.executeQuery();
            DatosBuscar = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar el usuario: " + e);
        }
    }

    public void actualizar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("update usuario set tipDocUsu=?,nomUsu=?,emaUsu=?,dirUsu=?,telUsu=?,telAltUsu=?,rolUsu=?,conUsu=? where idUsu=?");
            objClsConexion.sql.setString(1, getTipDocUsu());
            objClsConexion.sql.setString(2, getNomUsu());
            objClsConexion.sql.setString(3, getEmaUsu());
            objClsConexion.sql.setString(4, getDirUsu());
            objClsConexion.sql.setString(5, getTelUsu());
            objClsConexion.sql.setString(6, getTelAltUsu());
            objClsConexion.sql.setString(7, getRolUsu());
            objClsConexion.sql.setString(8, getConUsu());
            objClsConexion.sql.setString(9, getIdUsu());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro actualizado con éxito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: ");
        }

    }

}
