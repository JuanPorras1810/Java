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
public class ClsConsultas {
//Declaracion de variables

    private String diagnostico;
    private int codigo;
    private String fecha_inicio;
    private String fecha_fin;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet DatosAnimal;
    public ResultSet DatosTablaUsuario;
    public ResultSet DatosTablaProcedimiento;
    public ResultSet DatosTablaAnimal;
    public ResultSet DatosTablaFechaInicioFin;
    public ResultSet DatosTablaFechaInicio;
    public ResultSet DatosTablaFechaFin;
//Encapsulacion

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

//Metodos
    public void LlenarAnimal() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from animal");
            objClsConexion.sql.executeQuery();
            DatosAnimal = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar los animales: " + e);
        }
    }

    public void LlenarTablaProcedimiento() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select ani.codigo, ani.nombre, his.fecha_visita, usu.nombre, his.observaciones  \n"
                    + "from usuario usu, animal ani, historial_clinico his \n"
                    + "where his.diagnostico=?\n"
                    + "and ani.codigo = his.codigo_animal\n"
                    + "and usu.identificacion = his.identificacion_usuario\n"
                    + "order by ani.codigo asc");
            objClsConexion.sql.setString(1, getDiagnostico());
            objClsConexion.sql.executeQuery();
            DatosTablaProcedimiento = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al llenar la tabla: " + e);
        }
    }

    public void LlenarTablaAnimal() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select his.diagnostico, his.observaciones, his.fecha_visita, usu.nombre\n"
                    + "from usuario usu, animal ani, historial_clinico his \n"
                    + "where ani.codigo=? \n"
                    + "and ani.codigo = his.codigo_animal\n"
                    + "and usu.identificacion = his.identificacion_usuario");
            objClsConexion.sql.setInt(1, getCodigo());
            objClsConexion.sql.executeQuery();
            DatosTablaAnimal = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al llenar la tabla: " + e);
        }
    }

    public void LlenarTablaUsuario() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select usu.identificacion, usu.nombre, count(*) as total\n"
                    + "from usuario usu, historial_clinico his\n"
                    + "where usu.identificacion = his.identificacion_usuario\n"
                    + "group by usu.identificacion, usu.nombre\n"
                    + "order by total desc");
            objClsConexion.sql.executeQuery();
            DatosTablaUsuario = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al llenar la tabla: " + e);
        }
    }

    public void LlenarTablaFechaInicioFin() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select ani.codigo, ani.nombre, his.diagnostico, his.fecha_visita, usu.nombre, his.observaciones\n"
                    + "from usuario usu, animal ani, historial_clinico his \n"
                    + "where ani.codigo = his.codigo_animal\n"
                    + "and usu.identificacion = his.identificacion_usuario\n"
                    + "and his.fecha_visita between ? and ?");
            objClsConexion.sql.setString(1, getFecha_inicio());
            objClsConexion.sql.setString(2, getFecha_fin());
            objClsConexion.sql.executeQuery();
            DatosTablaFechaInicioFin = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al llenar la tabla: " + e);
        }
    }

    public void LlenarTablaFechaInicio() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select ani.codigo, ani.nombre, his.diagnostico, his.fecha_visita, usu.nombre, his.observaciones\n"
                    + "from usuario usu, animal ani, historial_clinico his \n"
                    + "where ani.codigo = his.codigo_animal\n"
                    + "and usu.identificacion = his.identificacion_usuario\n"
                    + "and his.fecha_visita >=?");
            objClsConexion.sql.setString(1, getFecha_inicio());
            objClsConexion.sql.executeQuery();
            DatosTablaFechaInicio = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al llenar la tabla: " + e);
        }
    }

    public void LlenarTablaFechaFin() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select ani.codigo, ani.nombre, his.diagnostico, his.fecha_visita, usu.nombre, his.observaciones\n"
                    + "from usuario usu, animal ani, historial_clinico his \n"
                    + "where ani.codigo = his.codigo_animal\n"
                    + "and usu.identificacion = his.identificacion_usuario\n"
                    + "and his.fecha_visita <=?");
            objClsConexion.sql.setString(1, getFecha_fin());
            objClsConexion.sql.executeQuery();
            DatosTablaFechaFin = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al llenar la tabla: " + e);
        }
    }

}
