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
public class ClsGrupo {
//Declaracion de variables

    private String codGrupo;
    private String nomGrupo;
    private String fechIniGrupo;
    private String fechFinGrupo;
    private String ambGrupo;
    private String insLidGrupo;
    private String jornGrupo;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet datosGrupo;

//encapsulacion
    public String getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(String codGrupo) {
        this.codGrupo = codGrupo;
    }

    public String getNomGrupo() {
        return nomGrupo;
    }

    public void setNomGrupo(String nomGrupo) {
        this.nomGrupo = nomGrupo;
    }

    public String getFechIniGrupo() {
        return fechIniGrupo;
    }

    public void setFechIniGrupo(String fechIniGrupo) {
        this.fechIniGrupo = fechIniGrupo;
    }

    public String getFechFinGrupo() {
        return fechFinGrupo;
    }

    public void setFechFinGrupo(String fechFinGrupo) {
        this.fechFinGrupo = fechFinGrupo;
    }

    public String getAmbGrupo() {
        return ambGrupo;
    }

    public void setAmbGrupo(String ambGrupo) {
        this.ambGrupo = ambGrupo;
    }

    public String getInsLidGrupo() {
        return insLidGrupo;
    }

    public void setInsLidGrupo(String insLidGrupo) {
        this.insLidGrupo = insLidGrupo;
    }

    public String getJornGrupo() {
        return jornGrupo;
    }

    public void setJornGrupo(String jornGrupo) {
        this.jornGrupo = jornGrupo;
    }

//Metodos
    public void guardar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into grupo values(?,?,?,?,?,?,?)");
            objClsConexion.sql.setString(1, getCodGrupo());
            objClsConexion.sql.setString(2, getNomGrupo());
            objClsConexion.sql.setString(3, getFechIniGrupo());
            objClsConexion.sql.setString(4, getFechFinGrupo());
            objClsConexion.sql.setString(5, getAmbGrupo());
            objClsConexion.sql.setString(6, getInsLidGrupo());
            objClsConexion.sql.setString(7, getJornGrupo());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos grupo guardados con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar en grupo: " + e);
        }
    }

    public void buscar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from grupo where codGrupo=?");
            objClsConexion.sql.setString(1, getCodGrupo());
            objClsConexion.sql.executeQuery();
            datosGrupo = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar grupo: " + e);
        }
    }

    public void actualizar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("update grupo set nomGrupo=?,fechIniGrupo=?,fechFinGrupo=?,ambGrupo=?,insLidGrupo=?,jornGrupo=? where codGrupo=?");
            objClsConexion.sql.setString(1, getNomGrupo());
            objClsConexion.sql.setString(2, getFechIniGrupo());
            objClsConexion.sql.setString(3, getFechFinGrupo());
            objClsConexion.sql.setString(4, getAmbGrupo());
            objClsConexion.sql.setString(5, getInsLidGrupo());
            objClsConexion.sql.setString(6, getJornGrupo());
            objClsConexion.sql.setString(7, getCodGrupo());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro actualizado con éxito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + e);
        }

    }

}
