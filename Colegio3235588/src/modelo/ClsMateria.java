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
public class ClsMateria {
//Declaracion de variables

    private int codMat;
    private String nomMat;
    private String graMat;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet datosMateria;
    public ResultSet datosConsecutivo;

//Encapsulacion 
    public int getCodMat() {
        return codMat;
    }

    public void setCodMat(int codMat) {
        this.codMat = codMat;
    }

    public String getNomMat() {
        return nomMat;
    }

    public void setNomMat(String nomMat) {
        this.nomMat = nomMat;
    }

    public String getGraMat() {
        return graMat;
    }

    public void setGraMat(String graMat) {
        this.graMat = graMat;
    }

//Metodos
    public void guardar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into MATERIA(nomMat,graMat) values(?,?)");
            objClsConexion.sql.setString(1, getNomMat());
            objClsConexion.sql.setString(2, getGraMat());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos de la materia guardados con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar en materia" + e);
        }
    }

    public void buscar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("SELECT * FROM MATERIA WHERE codMat=?");
            objClsConexion.sql.setInt(1, getCodMat());
            objClsConexion.sql.executeQuery();
            datosMateria = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar materia" + e);
        }

    }

    public void actualizar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("UPDATE MATERIA SET nomMat=?,graMat=? WHERE codMat=?");
            objClsConexion.sql.setString(1, getNomMat());
            objClsConexion.sql.setString(2, getGraMat());
            objClsConexion.sql.setInt(3, getCodMat());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro actualizado con éxito");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar:" + e);
        }

    }

    public void consecutivo() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select count(*)+1 from MATERIA;");
            objClsConexion.sql.executeQuery();
            datosConsecutivo = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al generar el consecutivo"+ e);
        }
    }
}
