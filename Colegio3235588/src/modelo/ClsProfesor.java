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
public class ClsProfesor {
//Declaracion de variables

    private String docPro;
    private String nomPro;
    private String apePro;
    private String dirPro;
    private String telPro;
    private String emaPro;
    private String titPro;
    private String nomMat;
    private Integer codMat;
    private String graMat;
    private String conMatxPro;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet datosProfesor;
    public ResultSet datosMateria;
    public ResultSet datosGrado;
    public ResultSet datosTabla;
    public ResultSet datosMateriaXprofesor;

//Encapsulacion
    public String getDocPro() {
        return docPro;
    }

    public void setDocPro(String docPro) {
        this.docPro = docPro;
    }

    public String getNomPro() {
        return nomPro;
    }

    public void setNomPro(String nomPro) {
        this.nomPro = nomPro;
    }

    public String getApePro() {
        return apePro;
    }

    public void setApePro(String apePro) {
        this.apePro = apePro;
    }

    public String getDirPro() {
        return dirPro;
    }

    public void setDirPro(String dirPro) {
        this.dirPro = dirPro;
    }

    public String getTelPro() {
        return telPro;
    }

    public void setTelPro(String telPro) {
        this.telPro = telPro;
    }

    public String getEmaPro() {
        return emaPro;
    }

    public void setEmaPro(String emaPro) {
        this.emaPro = emaPro;
    }

    public String getTitPro() {
        return titPro;
    }

    public void setTitPro(String titPro) {
        this.titPro = titPro;
    }

    public String getNomMat() {
        return nomMat;
    }

    public void setNomMat(String nomMat) {
        this.nomMat = nomMat;
    }

    public Integer getCodMat() {
        return codMat;
    }

    public void setCodMat(Integer codMat) {
        this.codMat = codMat;
    }

    public String getGraMat() {
        return graMat;
    }

    public void setGraMat(String graMat) {
        this.graMat = graMat;
    }

    public String getConMatxPro() {
        return conMatxPro;
    }

    public void setConMatxPro(String conMatxPro) {
        this.conMatxPro = conMatxPro;
    }

//Metodos 
    public void guardar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into PROFESOR values(?,?,?,?,?,?,?)");
            objClsConexion.sql.setString(1, getDocPro());
            objClsConexion.sql.setString(2, getNomPro());
            objClsConexion.sql.setString(3, getApePro());
            objClsConexion.sql.setString(4, getDirPro());
            objClsConexion.sql.setString(5, getTelPro());
            objClsConexion.sql.setString(6, getEmaPro());
            objClsConexion.sql.setString(7, getTitPro());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos del profesor guardados con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar en profesor: " + e);
        }

    }

    public void buscar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from PROFESOR where docPro=?");
            objClsConexion.sql.setString(1, getDocPro());
            objClsConexion.sql.executeQuery();
            datosProfesor = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar profesor: " + e);
        }
    }

    public void BuscarMateria() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select MATERIAXPROFESOR.conMatxPro, MATERIAXPROFESOR.codMatMatxPro, MATERIAXPROFESOR.docProfMatxPro, MATERIAXPROFESOR.graMatxPro, MATERIA.nomMat\n"
                    + "from MATERIAXPROFESOR, MATERIA\n"
                    + "where MATERIAXPROFESOR.conMatxPro=?\n"
                    + "and MATERIA.codMat = MATERIAXPROFESOR.codMatMatxPro;");
            objClsConexion.sql.setString(1, getConMatxPro());
            objClsConexion.sql.executeQuery();
            datosMateriaXprofesor = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar materia del profesor: " + e);
        }
    }

    public void actualizar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("UPDATE PROFESOR SET nomPro=?,apePro=?,dirPro=?,telPro=?,emaPro=?,titPro=? where docPro=?");
            objClsConexion.sql.setString(1, getNomPro());
            objClsConexion.sql.setString(2, getApePro());
            objClsConexion.sql.setString(3, getDirPro());
            objClsConexion.sql.setString(4, getTelPro());
            objClsConexion.sql.setString(5, getEmaPro());
            objClsConexion.sql.setString(6, getTitPro());
            objClsConexion.sql.setString(7, getDocPro());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro actualizado con éxito");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error al actualizar: " + e);
        }

    }

    public void ActualizarMateria() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("UPDATE MATERIAXPROFESOR SET codMatMatxPro=?,docProfMatxPro=?,graMatxPro=? where conMatxPro=?");
            objClsConexion.sql.setInt(1, getCodMat());
            objClsConexion.sql.setString(2, getDocPro());
            objClsConexion.sql.setString(3, getGraMat());
            objClsConexion.sql.setString(4, getConMatxPro());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro actualizado con éxito");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error al actualizar: " + e);
        }

    }

    public void GuardarMateria() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into MATERIAXPROFESOR(codMatMatxPro,docProfMatxPro,graMatxPro) values(?,?,?)");
            objClsConexion.sql.setInt(1, getCodMat());
            objClsConexion.sql.setString(2, getDocPro());
            objClsConexion.sql.setString(3, getGraMat());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos de la materia profesor guardados con exito!!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar en materia profesor: " + e);
        }

    }

    public void LlenarComboNomMat() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from MATERIA");
            objClsConexion.sql.executeQuery();
            datosMateria = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al generar nombre de las materias: " + e);
        }

    }

    public void LlenarGrado() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from MATERIA where nomMat=?");
            objClsConexion.sql.setString(1, getNomMat());
            objClsConexion.sql.executeQuery();
            datosGrado = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar el nombre y apellido estudiante" + e);
        }

    }

    public void LlenarTabla() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("SELECT MATERIA.nomMat, MATERIA.graMat  \n"
                    + "FROM MATERIA, MATERIAXPROFESOR \n"
                    + "WHERE MATERIAXPROFESOR.docProfMatxPro=?\n"
                    + "AND MATERIA.codMat = MATERIAXPROFESOR.codMatMatxPro ;");
            objClsConexion.sql.setString(1, getDocPro());
            objClsConexion.sql.executeQuery();
            datosTabla = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al llenar la tabla" + e);
        }

    }

}
