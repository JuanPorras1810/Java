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
public class ClsCliente {
//Declaracion de variables

    private int conCli;
    private int codCamCli;
    private String idCli;
    private String tipDoCli;
    private String nomCli;
    private String emaCli;
    private String dirCli;
    private String barCli;
    private String munCli;
    private String telCli;
    private String telAltCli;
    private String obsCli;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet DatosBuscar;
    public ResultSet DatosCampana;

//Encapsulacion
    public int getConCli() {
        return conCli;
    }

    public void setConCli(int conCli) {
        this.conCli = conCli;
    }

    public int getCodCamCli() {
        return codCamCli;
    }

    public void setCodCamCli(int codCamCli) {
        this.codCamCli = codCamCli;
    }

    public String getIdCli() {
        return idCli;
    }

    public void setIdCli(String idCli) {
        this.idCli = idCli;
    }

    public String getTipDoCli() {
        return tipDoCli;
    }

    public void setTipDoCli(String tipDoCli) {
        this.tipDoCli = tipDoCli;
    }

    public String getNomCli() {
        return nomCli;
    }

    public void setNomCli(String nomCli) {
        this.nomCli = nomCli;
    }

    public String getEmaCli() {
        return emaCli;
    }

    public void setEmaCli(String emaCli) {
        this.emaCli = emaCli;
    }

    public String getDirCli() {
        return dirCli;
    }

    public void setDirCli(String dirCli) {
        this.dirCli = dirCli;
    }

    public String getBarCli() {
        return barCli;
    }

    public void setBarCli(String barCli) {
        this.barCli = barCli;
    }

    public String getMunCli() {
        return munCli;
    }

    public void setMunCli(String munCli) {
        this.munCli = munCli;
    }

    public String getTelCli() {
        return telCli;
    }

    public void setTelCli(String telCli) {
        this.telCli = telCli;
    }

    public String getTelAltCli() {
        return telAltCli;
    }

    public void setTelAltCli(String telAltCli) {
        this.telAltCli = telAltCli;
    }

    public String getObsCli() {
        return obsCli;
    }

    public void setObsCli(String obsCli) {
        this.obsCli = obsCli;
    }

//Metodos
    public void LlenarCampana() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from campana");
            objClsConexion.sql.executeQuery();
            DatosCampana = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar las campañas: " + e);
        }
    }

    public void registrar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into baseDatosCliente(codCamCli,idCli,tipDoCli,nomCli,emaCli,dirCli,barCli,munCli,telCli,telAltCli,obsCli) values(?,?,?,?,?,?,?,?,?,?,?)");
            objClsConexion.sql.setInt(1, getCodCamCli());
            objClsConexion.sql.setString(2, getIdCli());
            objClsConexion.sql.setString(3, getTipDoCli());
            objClsConexion.sql.setString(4, getNomCli());
            objClsConexion.sql.setString(5, getEmaCli());
            objClsConexion.sql.setString(6, getDirCli());
            objClsConexion.sql.setString(7, getBarCli());
            objClsConexion.sql.setString(8, getMunCli());
            objClsConexion.sql.setString(9, getTelCli());
            objClsConexion.sql.setString(10, getTelAltCli());
            objClsConexion.sql.setString(11, getObsCli());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente registrado con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el usuario: " + e);
        }

    }

    public void buscar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from  baseDatosCliente where conCli=?");
            objClsConexion.sql.setInt(1, getConCli());
            objClsConexion.sql.executeQuery();
            DatosBuscar = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar el cliente: " + e);
        }
    }

    public void actualizar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("update  baseDatosCliente set codCamCli=?,idCli=?,tipDoCli=?,nomCli=?,emaCli=?,dirCli=?,barCli=?,munCli=?,telCli=?,telAltCli=?,obsCli=? where conCli=?");
            objClsConexion.sql.setInt(1, getCodCamCli());
            objClsConexion.sql.setString(2, getIdCli());
            objClsConexion.sql.setString(3, getTipDoCli());
            objClsConexion.sql.setString(4, getNomCli());
            objClsConexion.sql.setString(5, getEmaCli());
            objClsConexion.sql.setString(6, getDirCli());
            objClsConexion.sql.setString(7, getBarCli());
            objClsConexion.sql.setString(8, getMunCli());
            objClsConexion.sql.setString(9, getTelCli());
            objClsConexion.sql.setString(10, getTelAltCli());
            objClsConexion.sql.setString(11, getObsCli());
            objClsConexion.sql.setInt(12, getConCli());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro actualizado con éxito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + e);
        }

    }

}
