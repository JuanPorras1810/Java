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
public class ClsAcudiente {
//Declaracion de variables

    private String docAcu;
    private String nomAcu;
    private String apeAcu;
    private String dirAcu;
    private String telAcu;
    private String emaAcu;
    private String docEstAcuxEst;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet datosAcudiente;
    public ResultSet datosEstudiante;
    public ResultSet datosNomApeEst;
    public ResultSet datosTabla;

//encapsulacion
    public String getDocAcu() {
        return docAcu;
    }

    public void setDocAcu(String docAcu) {
        this.docAcu = docAcu;
    }

    public String getNomAcu() {
        return nomAcu;
    }

    public void setNomAcu(String nomAcu) {
        this.nomAcu = nomAcu;
    }

    public String getApeAcu() {
        return apeAcu;
    }

    public void setApeAcu(String apeAcu) {
        this.apeAcu = apeAcu;
    }

    public String getDirAcu() {
        return dirAcu;
    }

    public void setDirAcu(String dirAcu) {
        this.dirAcu = dirAcu;
    }

    public String getTelAcu() {
        return telAcu;
    }

    public void setTelAcu(String telAcu) {
        this.telAcu = telAcu;
    }

    public String getEmaAcu() {
        return emaAcu;
    }

    public void setEmaAcu(String emaAcu) {
        this.emaAcu = emaAcu;
    }

    public String getDocEstAcuxEst() {
        return docEstAcuxEst;
    }

    public void setDocEstAcuxEst(String docEstAcuxEst) {
        this.docEstAcuxEst = docEstAcuxEst;
    }

//Metodos
    public void guardar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into ACUDIENTE values(?,?,?,?,?,?)");
            objClsConexion.sql.setString(1, getDocAcu());
            objClsConexion.sql.setString(2, getNomAcu());
            objClsConexion.sql.setString(3, getApeAcu());
            objClsConexion.sql.setString(4, getDirAcu());
            objClsConexion.sql.setString(5, getTelAcu());
            objClsConexion.sql.setString(6, getEmaAcu());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos del acudiente guardados con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar en acudiente" + e);
        }

    }

    public void AgregarEstudiante() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into ACUDIENTEXESTUDIANTE(docAcuAcuxEst,docEstAcuxEst) values(?,?)");
            objClsConexion.sql.setString(1, getDocAcu());
            objClsConexion.sql.setString(2, getDocEstAcuxEst());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos de estudiante con su acudiente guardados con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar en acudienteXestudiante" + e);
        }

    }

    public void NombreApellidoEstudiante() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("SELECT * FROM ESTUDIANTE WHERE docEst=?");
            objClsConexion.sql.setString(1, getDocEstAcuxEst());
            objClsConexion.sql.executeQuery();
            datosNomApeEst = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar el nombre y apellido estudiante" + e);
        }

    }

    public void buscar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("SELECT * FROM ACUDIENTE WHERE docAcu=?");
            objClsConexion.sql.setString(1, getDocAcu());
            objClsConexion.sql.executeQuery();
            datosAcudiente = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar acudiente" + e);
        }

    }

    public void actualizar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("UPDATE ACUDIENTE SET nomAcu=?,apeAcu=?,dirAcu=?,telAcu=?,emaAcu=? WHERE docAcu=?");
            objClsConexion.sql.setString(1, getNomAcu());
            objClsConexion.sql.setString(2, getApeAcu());
            objClsConexion.sql.setString(3, getDirAcu());
            objClsConexion.sql.setString(4, getTelAcu());
            objClsConexion.sql.setString(5, getEmaAcu());
            objClsConexion.sql.setString(6, getDocAcu());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro actualizado con éxito");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error al actualizar:" + e);
        }

    }

    public void LlenarComboDocEstudiante() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from ESTUDIANTE");
            objClsConexion.sql.executeQuery();
            datosEstudiante = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al generar documentos de los estudiantes" + e);
        }

    }

    public void LlenarTabla() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select ESTUDIANTE.*\n"
                    + "from ESTUDIANTE, ACUDIENTE, ACUDIENTEXESTUDIANTE\n"
                    + "where ACUDIENTE.docAcu=? and ACUDIENTE.docAcu = ACUDIENTEXESTUDIANTE.docAcuAcuxEst and ESTUDIANTE.docEst =ACUDIENTEXESTUDIANTE.docEstAcuxEst;");
            objClsConexion.sql.setString(1, getDocAcu());
            objClsConexion.sql.executeQuery();
            datosTabla = objClsConexion.sql.getResultSet();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error SQL al llenar la tabla" + e);
        }

    }

}
