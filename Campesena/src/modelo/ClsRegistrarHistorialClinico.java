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
public class ClsRegistrarHistorialClinico {
//Declaracion de variables

    private String codigo;
    private int codigo_animal;
    private String identificacion_usuario;
    private String diagnostico;
    private String tratamiento;
    private String medicamentos;
    private String fecha_visita;
    private String observaciones;
    private String nombre;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet DatosAnimal;
    public ResultSet DatosUsuario;
    public ResultSet DatosBuscarUsuario;
    public ResultSet DatosConsecutivo;

//Encapsulacion
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCodigo_animal() {
        return codigo_animal;
    }

    public void setCodigo_animal(int codigo_animal) {
        this.codigo_animal = codigo_animal;
    }

    public String getIdentificacion_usuario() {
        return identificacion_usuario;
    }

    public void setIdentificacion_usuario(String identificacion_usuario) {
        this.identificacion_usuario = identificacion_usuario;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getFecha_visita() {
        return fecha_visita;
    }

    public void setFecha_visita(String fecha_visita) {
        this.fecha_visita = fecha_visita;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

//Metodos
    public void consecutivo() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select count(*)+1 from historial_clinico");
            objClsConexion.sql.executeQuery();
            DatosConsecutivo = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al generar el consecutivo: " + e);
        }
    }

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

    public void LlenarUsuario() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from usuario");
            objClsConexion.sql.executeQuery();
            DatosUsuario = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar los usuarios: " + e);
        }
    }

    public void BuscarUsuario() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select identificacion from usuario where nombre=?");
            objClsConexion.sql.setString(1, getNombre());
            objClsConexion.sql.executeQuery();
            DatosBuscarUsuario = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar los usuarios: " + e);
        }
    }

    public void guardar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into historial_clinico values(?,?,?,?,?,?,?,?)");
            objClsConexion.sql.setString(1, getCodigo());
            objClsConexion.sql.setInt(2, getCodigo_animal());
            objClsConexion.sql.setString(3, getIdentificacion_usuario());
            objClsConexion.sql.setString(4, getDiagnostico());
            objClsConexion.sql.setString(5, getTratamiento());
            objClsConexion.sql.setString(6, getMedicamentos());
            objClsConexion.sql.setString(7, getFecha_visita());
            objClsConexion.sql.setString(8, getObservaciones());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos de la historia clinica guardados con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la historia clinica: " + e);
        }
    }

}
