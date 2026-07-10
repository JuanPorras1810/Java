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
public class ClsIngreso {
//Declaracion de variables

    private Integer codIng;
    private String docVisIng;
    private String fecIng;
    private String depIng;
    private String motIng;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet datos;
    public ResultSet DatosConsecutivo;

//Encapsulacion
    public Integer getCodIng() {
        return codIng;
    }

    public void setCodIng(Integer codIng) {
        this.codIng = codIng;
    }

    public String getDocVisIng() {
        return docVisIng;
    }

    public void setDocVisIng(String docVisIng) {
        this.docVisIng = docVisIng;
    }

    public String getFecIng() {
        return fecIng;
    }

    public void setFecIng(String fecIng) {
        this.fecIng = fecIng;
    }

    public String getDepIng() {
        return depIng;
    }

    public void setDepIng(String depIng) {
        this.depIng = depIng;
    }

    public String getMotIng() {
        return motIng;
    }

    public void setMotIng(String motIng) {
        this.motIng = motIng;
    }
//Metodos

    public void guardar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into ingreso(docVisIng,fecIng,depIng,motIng) values(?,?,?,?)");
            objClsConexion.sql.setString(1, getDocVisIng());
            objClsConexion.sql.setString(2, getFecIng());
            objClsConexion.sql.setString(3, getDepIng());
            objClsConexion.sql.setString(4, getMotIng());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos del ingreso guardados con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar en ingreso: " + e);
        }

    }

    public void buscar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from visitante where docVis=?");
            objClsConexion.sql.setString(1, getDocVisIng());
            objClsConexion.sql.executeQuery();
            datos = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar el nombre y apellido del visitante: " + e);
        }
    }

    public void consecutivo() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select count(*)+1 from ingreso");
            objClsConexion.sql.executeQuery();
            DatosConsecutivo = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al generar el codigo: " + e);
        }

    }

}
