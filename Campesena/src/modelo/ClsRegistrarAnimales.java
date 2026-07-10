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
public class ClsRegistrarAnimales {
//Declaracion de variables

    private int codigo;
    private String categoria;
    private String raza;
    private String color;
    private float tamaño;
    private String genero;
    private String nombre;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet DatosConsecutivo;

//Encapsulacion
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getTamaño() {
        return tamaño;
    }

    public void setTamaño(float tamaño) {
        this.tamaño = tamaño;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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
            objClsConexion.sql = objClsConexion.con.prepareStatement("select count(*)+1 from animal");
            objClsConexion.sql.executeQuery();
            DatosConsecutivo = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al generar el consecutivo: " + e);
        }
    }

    public void guardar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into animal(categoria,raza,color,tamaño,genero,nombre) values(?,?,?,?,?,?)");
            objClsConexion.sql.setString(1, getCategoria());
            objClsConexion.sql.setString(2, getRaza());
            objClsConexion.sql.setString(3, getColor());
            objClsConexion.sql.setFloat(4, getTamaño());
            objClsConexion.sql.setString(5, getGenero());
            objClsConexion.sql.setString(6, getNombre());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos del animal guardados con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el animal: " + e);
        }
    }

}
