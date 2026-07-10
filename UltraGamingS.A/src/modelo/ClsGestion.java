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
public class ClsGestion {
//Declaracion de variables

    private int idPer;
    private int idMarPer;
    private String nomModPer;
    private String tipPer;
    private double prePer;
    private String fecRegPer;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet DatosBuscar;
    public ResultSet DatosConsecutivo;
    public ResultSet DatosMarca;

//Encapsulacion
    public int getIdPer() {
        return idPer;
    }

    public void setIdPer(int idPer) {
        this.idPer = idPer;
    }

    public int getIdMarPer() {
        return idMarPer;
    }

    public void setIdMarPer(int idMarPer) {
        this.idMarPer = idMarPer;
    }

    public String getNomModPer() {
        return nomModPer;
    }

    public void setNomModPer(String nomModPer) {
        this.nomModPer = nomModPer;
    }

    public String getTipPer() {
        return tipPer;
    }

    public void setTipPer(String tipPer) {
        this.tipPer = tipPer;
    }

    public double getPrePer() {
        return prePer;
    }

    public void setPrePer(double prePer) {
        this.prePer = prePer;
    }

    public String getFecRegPer() {
        return fecRegPer;
    }

    public void setFecRegPer(String fecRegPer) {
        this.fecRegPer = fecRegPer;
    }

//Metodos
    public void guardar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into periferico(idMarPer,nomModPer,tipPer,prePer,fecRegPer) values(?,?,?,?,?)");
            objClsConexion.sql.setInt(1, getIdMarPer());
            objClsConexion.sql.setString(2, getNomModPer());
            objClsConexion.sql.setString(3, getTipPer());
            objClsConexion.sql.setDouble(4, getPrePer());
            objClsConexion.sql.setString(5, getFecRegPer());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "La gestion fue guardada con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al guardar la gestion: " + e);
        }

    }

    public void buscar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from periferico where idPer=? ");
            objClsConexion.sql.setInt(1, getIdPer());
            objClsConexion.sql.executeQuery();
            DatosBuscar = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar la gestion: " + e);
        }

    }

    public void actualizar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("update periferico set idMarPer=?,nomModPer=?,tipPer=?,prePer=?,fecRegPer=? where idPer=?;");
            objClsConexion.sql.setInt(1, getIdMarPer());
            objClsConexion.sql.setString(2, getNomModPer());
            objClsConexion.sql.setString(3, getTipPer());
            objClsConexion.sql.setDouble(4, getPrePer());
            objClsConexion.sql.setString(5, getFecRegPer());
            objClsConexion.sql.setInt(6, getIdPer());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro actualizado con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al actualizar: " + e);
        }

    }

    public void consecutivo() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select count(*)+1 from periferico");
            objClsConexion.sql.executeQuery();
            DatosConsecutivo = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al generar el consecutivo del ID producto: " + e);
        }

    }

    public void LlenarMarca() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from marca");
            objClsConexion.sql.executeQuery();
            DatosMarca = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar las marcas: " + e);
        }

    }

}
