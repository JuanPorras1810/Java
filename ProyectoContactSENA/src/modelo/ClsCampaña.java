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
public class ClsCampaña {
//Declaracion de variables

    private int codCam;
    private String nomCam;
    private String fecIniCam;
    private String fecFinCam;
    private String proCam;

    private int codTip;
    private int codCamTip;
    private String nomTip;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet DatosBuscar;
    public ResultSet DatosConsecutivo;
    public ResultSet DatosTabla;
    public ResultSet DatosConsecutivoTipificaciones;
    public ResultSet DatosCampana;

//Encapsulacion 
    public int getCodCam() {
        return codCam;
    }

    public void setCodCam(int codCam) {
        this.codCam = codCam;
    }

    public String getNomCam() {
        return nomCam;
    }

    public void setNomCam(String nomCam) {
        this.nomCam = nomCam;
    }

    public String getFecIniCam() {
        return fecIniCam;
    }

    public void setFecIniCam(String fecIniCam) {
        this.fecIniCam = fecIniCam;
    }

    public String getFecFinCam() {
        return fecFinCam;
    }

    public void setFecFinCam(String fecFinCam) {
        this.fecFinCam = fecFinCam;
    }

    public String getProCam() {
        return proCam;
    }

    public void setProCam(String proCam) {
        this.proCam = proCam;
    }

    public int getCodTip() {
        return codTip;
    }

    public void setCodTip(int codTip) {
        this.codTip = codTip;
    }

    public int getCodCamTip() {
        return codCamTip;
    }

    public void setCodCamTip(int codCamTip) {
        this.codCamTip = codCamTip;
    }

    public String getNomTip() {
        return nomTip;
    }

    public void setNomTip(String nomTip) {
        this.nomTip = nomTip;
    }

//Metodos 
    public void guardar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into campana(nomCam,fecIniCam,fecFinCam,proCam) values(?,?,?,?)");
            objClsConexion.sql.setString(1, getNomCam());
            objClsConexion.sql.setString(2, getFecIniCam());
            objClsConexion.sql.setString(3, getFecFinCam());
            objClsConexion.sql.setString(4, getProCam());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Campaña guardada con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la campaña: " + e);
        }

    }

    public void buscar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from campana where codCam=?");
            objClsConexion.sql.setInt(1, getCodCam());
            objClsConexion.sql.executeQuery();
            DatosBuscar = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar la campaña: " + e);
        }
    }

    public void actualizar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("update campana set nomCam=?,fecIniCam=?,fecFinCam=?,proCam=? where codCam=?");
            objClsConexion.sql.setString(1, getNomCam());
            objClsConexion.sql.setString(2, getFecIniCam());
            objClsConexion.sql.setString(3, getFecFinCam());
            objClsConexion.sql.setString(4, getProCam());
            objClsConexion.sql.setInt(5, getCodCam());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro actualizado con éxito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: ");
        }

    }

    public void consecutivo() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select count(*)+1 from campana");
            objClsConexion.sql.executeQuery();
            DatosConsecutivo = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al generar el consecutivo: " + e);
        }
    }

    public void GuardarTipificacion() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into tipificaciones(codCamTip,nomTip) values(?,?)");
            objClsConexion.sql.setInt(1, getCodCamTip());
            objClsConexion.sql.setString(2, getNomTip());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Tipificacion guardada con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la tipificacion: " + e);
        }
    }

    public void ActualizarTipificacion() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("update tipificaciones set codCamTip=?,nomTip=? where codTip=?");
            objClsConexion.sql.setInt(1, getCodCamTip());
            objClsConexion.sql.setString(2, getNomTip());
            objClsConexion.sql.setInt(3, getCodTip());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro actualizado con éxito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: ");
        }

    }

    public void LlenarTabla() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from tipificaciones");
            objClsConexion.sql.executeQuery();
            DatosTabla = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al llenar la tabla: " + e);
        }

    }

    public void ConsecutivoTipificaciones() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select count(*)+1 from tipificaciones");
            objClsConexion.sql.executeQuery();
            DatosConsecutivoTipificaciones = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al generar el consecutivo tipificaciones: " + e);
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

}
