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
public class ClsAgendamiento {
//Declaracion de variables

    private int codAgen;
    private String docApreAgen;
    private String docProfAgen;
    private String fechaAgen;
    private String horaAgen;
    private String motAgen;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet datosAprendiz;
    public ResultSet datosProfesional;
    public ResultSet datosAgendamiento;
    public ResultSet datosConsecutivo;
//Encapsulacion

    public int getCodAgen() {
        return codAgen;
    }

    public void setCodAgen(int codAgen) {
        this.codAgen = codAgen;
    }

    public String getDocApreAgen() {
        return docApreAgen;
    }

    public void setDocApreAgen(String docApreAgen) {
        this.docApreAgen = docApreAgen;
    }

    public String getDocProfAgen() {
        return docProfAgen;
    }

    public void setDocProfAgen(String docProfAgen) {
        this.docProfAgen = docProfAgen;
    }

    public String getFechaAgen() {
        return fechaAgen;
    }

    public void setFechaAgen(String fechaAgen) {
        this.fechaAgen = fechaAgen;
    }

    public String getHoraAgen() {
        return horaAgen;
    }

    public void setHoraAgen(String horaAgen) {
        this.horaAgen = horaAgen;
    }

    public String getMotAgen() {
        return motAgen;
    }

    public void setMotAgen(String motAgen) {
        this.motAgen = motAgen;
    }

//Metodos
    public void ConsecutivoAgendamiento() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select count(*)+1 from agendamiento;");
            objClsConexion.sql.executeQuery();
            datosConsecutivo = objClsConexion.sql.getResultSet();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al generar el consecutivo: " + e);
        }
    }

    public void BuscarAprendiz() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from aprendiz where docApre=?");
            objClsConexion.sql.setString(1, getDocApreAgen());
            objClsConexion.sql.executeQuery();
            datosAprendiz = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar aprendiz: " + e);
        }
    }

    public void BuscarProfesional() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from profesional where docProf=?");
            objClsConexion.sql.setString(1, getDocProfAgen());
            objClsConexion.sql.executeQuery();
            datosProfesional = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar el profesional: " + e);
        }
    }

    public void guardar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into agendamiento(docApreAgen, docProfAgen, fechaAgen, horaAgen, motAgen)  values(?,?,?,?,?)");
            objClsConexion.sql.setString(1, getDocApreAgen());
            objClsConexion.sql.setString(2, getDocProfAgen());
            objClsConexion.sql.setString(3, getFechaAgen());
            objClsConexion.sql.setString(4, getHoraAgen());
            objClsConexion.sql.setString(5, getMotAgen());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos agendamiento guardados con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar en agendamiento: " + e);
        }

    }

    public void Buscar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from agendamiento where codAgen=?");
            objClsConexion.sql.setInt(1, getCodAgen());
            objClsConexion.sql.executeQuery();
            datosAgendamiento = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar el agendamiento: " + e);
        }
    }

    public void actualizar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("update agendamiento set docApreAgen=?,docProfAgen=?,fechaAgen=?,horaAgen=?,motAgen=? where codAgen=?");
            objClsConexion.sql.setString(1, getDocApreAgen());
            objClsConexion.sql.setString(2, getDocProfAgen());
            objClsConexion.sql.setString(3, getFechaAgen());
            objClsConexion.sql.setString(4, getHoraAgen());
            objClsConexion.sql.setString(5, getMotAgen());
            objClsConexion.sql.setInt(6, getCodAgen());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro actualizado con éxito");
        } catch (SQLException e) {
 JOptionPane.showMessageDialog(null, "Error al actualizar: ");
        }
    }

}
