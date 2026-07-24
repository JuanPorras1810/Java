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
public class ClsAsesor {
//Declaracion de variables

    private int conAse;
    private String idUsuAse;
    private int codCamAse;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet DatosBuscar;
    public ResultSet DatosUsuario;
    public ResultSet DatosBuscarUsuario;
    public ResultSet DatosCampana;
    public ResultSet DatosBuscarCampana;
    public ResultSet DatosTabla;

//Encapsulacion
    public int getConAse() {
        return conAse;
    }

    public void setConAse(int conAse) {
        this.conAse = conAse;
    }

    public String getIdUsuAse() {
        return idUsuAse;
    }

    public void setIdUsuAse(String idUsuAse) {
        this.idUsuAse = idUsuAse;
    }

    public int getCodCamAse() {
        return codCamAse;
    }

    public void setCodCamAse(int codCamAse) {
        this.codCamAse = codCamAse;
    }

//Metodos
    public void LlenarUsuario() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from usuario where rolUsu='Agente'");
            objClsConexion.sql.executeQuery();
            DatosUsuario = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar los usuarios: " + e);
        }
    }

    public void BuscarUsuario() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select nomUsu from usuario where idUsu=?");
            objClsConexion.sql.setString(1, getIdUsuAse());
            objClsConexion.sql.executeQuery();
            DatosBuscarUsuario = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar nombre del usuario: " + e);
        }
    }

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

    public void BuscarCampana() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select nomCam from campana where codCam=?");
            objClsConexion.sql.setInt(1, getCodCamAse());
            objClsConexion.sql.executeQuery();
            DatosBuscarCampana = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar nombre de la campaña: " + e);
        }
    }

    public void LlenarTabla() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from baseDatosAsesor");
            objClsConexion.sql.executeQuery();
            DatosTabla = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al llenar tabla: " + e);
        }
    }

    public boolean ValidarGuardar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select 1 from baseDatosAsesor where idUsuAse=? and codCamAse=?");
            objClsConexion.sql.setString(1, getIdUsuAse());
            objClsConexion.sql.setInt(2, getCodCamAse());
            ResultSet rs = objClsConexion.sql.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al validar en guardar: " + e);
        }
        return false;
    }

    public boolean ValidarActualizar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select 1 from baseDatosAsesor where idUsuAse=? and codCamAse=? and conAse<>?");
            objClsConexion.sql.setString(1, getIdUsuAse());
            objClsConexion.sql.setInt(2, getCodCamAse());
            objClsConexion.sql.setInt(3, getConAse());
            ResultSet rs = objClsConexion.sql.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al validar actualización: " + e);
        }
        return false;
    }

    public void guardar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into  baseDatosAsesor(idUsuAse,codCamAse) values(?,?)");
            objClsConexion.sql.setString(1, getIdUsuAse());
            objClsConexion.sql.setInt(2, getCodCamAse());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Asesor guardado con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el asesor: " + e);
        }

    }

    public void buscar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from baseDatosAsesor where conAse=?");
            objClsConexion.sql.setInt(1, getConAse());
            objClsConexion.sql.executeQuery();
            DatosBuscar = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar el asesor: " + e);
        }
    }

    public void actualizar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("update  baseDatosAsesor set idUsuAse=?, codCamAse=? where conAse=?");
            objClsConexion.sql.setString(1, getIdUsuAse());
            objClsConexion.sql.setInt(2, getCodCamAse());
            objClsConexion.sql.setInt(3, getConAse());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro actualizado con éxito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: ");
        }

    }

}
