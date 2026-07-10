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
public class ClsHistoriaClinica {
//Declaracion de variables

    private int codHisCli;
    private int codAgenHisCli;
    private String docApreHisCli;
    private String descHisCli;
    private String obserHisCli;
    private String medHisCli;
    private String fechHoraHisCli;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet datosConsecutivo;
    public ResultSet datosAprendiz;
    public ResultSet datosAgendamiento;
    public ResultSet datosHisCli;
    public ResultSet datosTabla;

//encapsulacion
    public int getCodHisCli() {
        return codHisCli;
    }

    public void setCodHisCli(int codHisCli) {
        this.codHisCli = codHisCli;
    }

    public int getCodAgenHisCli() {
        return codAgenHisCli;
    }

    public void setCodAgenHisCli(int codAgenHisCli) {
        this.codAgenHisCli = codAgenHisCli;
    }

    public String getDocApreHisCli() {
        return docApreHisCli;
    }

    public void setDocApreHisCli(String docApreHisCli) {
        this.docApreHisCli = docApreHisCli;
    }

    public String getDescHisCli() {
        return descHisCli;
    }

    public void setDescHisCli(String descHisCli) {
        this.descHisCli = descHisCli;
    }

    public String getObserHisCli() {
        return obserHisCli;
    }

    public void setObserHisCli(String obserHisCli) {
        this.obserHisCli = obserHisCli;
    }

    public String getMedHisCli() {
        return medHisCli;
    }

    public void setMedHisCli(String medHisCli) {
        this.medHisCli = medHisCli;
    }

    public String getFechHoraHisCli() {
        return fechHoraHisCli;
    }

    public void setFechHoraHisCli(String fechHoraHisCli) {
        this.fechHoraHisCli = fechHoraHisCli;
    }

//Metodos
    public void ConsecutivoHistoriaClinica() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select count(*)+1 from historiaClinica;");
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
            objClsConexion.sql.setString(1, getDocApreHisCli());
            objClsConexion.sql.executeQuery();
            datosAprendiz = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar aprendiz: " + e);
        }
    }

    public void buscarAgendamiento() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from agendamiento where docApreAgen=?");
            objClsConexion.sql.setString(1, getDocApreHisCli());
            objClsConexion.sql.executeQuery();
            datosAgendamiento = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar agendamiento: " + e);
        }
    }

    public void guardar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into historiaClinica(codAgenHisCli, docApreHisCli, descHisCli, obserHisCli, medHisCli, fechHoraHisCli ) values(?,?,?,?,?,?)");
            objClsConexion.sql.setInt(1, getCodAgenHisCli());
            objClsConexion.sql.setString(2, getDocApreHisCli());
            objClsConexion.sql.setString(3, getDescHisCli());
            objClsConexion.sql.setString(4, getObserHisCli());
            objClsConexion.sql.setString(5, getMedHisCli());
            objClsConexion.sql.setString(6, getFechHoraHisCli());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos de la historia clinica guardados con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar en historia clinica: " + e);
        }
    }

    public void buscar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from historiaClinica where codHisCli=?");
            objClsConexion.sql.setInt(1, getCodHisCli());
            objClsConexion.sql.executeQuery();
            datosHisCli = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar la historia clinica: " + e);
        }
    }

    public void actualizar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("update historiaClinica set codAgenHisCli=?,docApreHisCli=?,descHisCli=?,obserHisCli=?,medHisCli=?,fechHoraHisCli=? where codHisCli=?");
            objClsConexion.sql.setInt(1, getCodAgenHisCli());
            objClsConexion.sql.setString(2, getDocApreHisCli());
            objClsConexion.sql.setString(3, getDescHisCli());
            objClsConexion.sql.setString(4, getObserHisCli());
            objClsConexion.sql.setString(5, getMedHisCli());
            objClsConexion.sql.setString(6, getFechHoraHisCli());
            objClsConexion.sql.setInt(7, getCodHisCli());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro actualizado con éxito");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: ");
        }
    }

    public void LlenarTabla() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select aprendiz.nomApre, aprendiz.apeApre, agendamiento.fechaAgen, agendamiento.horaAgen, historiaClinica.descHisCli, historiaClinica.obserHisCli, historiaClinica.medHisCli  \n"
                    + "from  aprendiz , agendamiento , historiaClinica\n"
                    + "where aprendiz.docApre=?  and aprendiz.docApre = agendamiento.docApreAgen and agendamiento.codAgen = historiaClinica.codAgenHisCli");
            objClsConexion.sql.setString(1, getDocApreHisCli());
            objClsConexion.sql.executeQuery();
            datosTabla = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al llenar la tabla" + e);
        }

    }

}
