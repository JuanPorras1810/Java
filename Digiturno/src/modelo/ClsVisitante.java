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
public class ClsVisitante {
//Declaracion de variables

    private String docVis;
    private String nomVis;
    private String apeVis;
    private String telVis;
    private String emaVis;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet DatosVisitante;
//Encapsulacion

    public String getDocVis() {
        return docVis;
    }

    public void setDocVis(String docVis) {
        this.docVis = docVis;
    }

    public String getNomVis() {
        return nomVis;
    }

    public void setNomVis(String nomVis) {
        this.nomVis = nomVis;
    }

    public String getApeVis() {
        return apeVis;
    }

    public void setApeVis(String apeVis) {
        this.apeVis = apeVis;
    }

    public String getTelVis() {
        return telVis;
    }

    public void setTelVis(String telVis) {
        this.telVis = telVis;
    }

    public String getEmaVis() {
        return emaVis;
    }

    public void setEmaVis(String emaVis) {
        this.emaVis = emaVis;
    }

//Metodos
    public void guardar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into visitante values(?,?,?,?,?)");
            objClsConexion.sql.setString(1, getDocVis());
            objClsConexion.sql.setString(2, getNomVis());
            objClsConexion.sql.setString(3, getApeVis());
            objClsConexion.sql.setString(4, getTelVis());
            objClsConexion.sql.setString(5, getEmaVis());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos del visitante guardados con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar en visitante: " + e);
        }
    }

    public void buscar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from visitante where docVis=?");
            objClsConexion.sql.setString(1, getDocVis());
            objClsConexion.sql.executeQuery();
            DatosVisitante = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar visitante: " + e);
        }

    }

    public void actualizar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("update visitante set nomVis=?,apeVis=?,telVis=?,emaVis=? where docVis=?");
            objClsConexion.sql.setString(1, getNomVis());
            objClsConexion.sql.setString(2, getApeVis());
            objClsConexion.sql.setString(3, getTelVis());
            objClsConexion.sql.setString(4, getEmaVis());
            objClsConexion.sql.setString(5, getDocVis());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro actualizado con exito");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar" + e);
        }
    }

}
