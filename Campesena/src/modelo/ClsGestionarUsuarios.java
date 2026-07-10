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
public class ClsGestionarUsuarios {
//Declaracion de variables

    private String identificacion;
    private int id_rol;
    private String tipo_identificacion;
    private String nombre;
    private String direccion;
    private String telefono;
    private String password;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet DatosRoles;
    public ResultSet DatosTipoRol;
    public ResultSet DatosBuscar;
    public ResultSet DatosTabla;
//Encapsulacion

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getTipo_identificacion() {
        return tipo_identificacion;
    }

    public void setTipo_identificacion(String tipo_identificacion) {
        this.tipo_identificacion = tipo_identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//Mrtodos
    public void LlenarRoles() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from rol");
            objClsConexion.sql.executeQuery();
            DatosRoles = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar los roles: " + e);
        }
    }

    public void LlenarTabla() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select usu.tipo_identificacion, usu.identificacion, usu.nombre, usu.telefono,rol.tipo\n"
                    + "from usuario usu, rol where  rol.id = usu.id_rol;");
            objClsConexion.sql.executeQuery();
            DatosTabla = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al llenar la tabla: " + e);
        }
    }

    public void CrearUsuario() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into usuario values(?,?,?,?,?,?,?)");
            objClsConexion.sql.setString(1, getIdentificacion());
            objClsConexion.sql.setInt(2, getId_rol());
            objClsConexion.sql.setString(3, getTipo_identificacion());
            objClsConexion.sql.setString(4, getNombre());
            objClsConexion.sql.setString(5, getDireccion());
            objClsConexion.sql.setString(6, getTelefono());
            objClsConexion.sql.setString(7, getPassword());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos del usuario guardados con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el usuario: " + e);
        }
    }

    public void buscar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * from usuario where identificacion=?");
            objClsConexion.sql.setString(1, getIdentificacion());
            objClsConexion.sql.executeQuery();
            DatosBuscar = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar el usuario: " + e);
        }
    }

    public void editar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("update usuario set  id_rol=?, tipo_identificacion=?, nombre=?, direccion=?, telefono=?, password=? where identificacion=?");
            objClsConexion.sql.setInt(1, getId_rol());
            objClsConexion.sql.setString(2, getTipo_identificacion());
            objClsConexion.sql.setString(3, getNombre());
            objClsConexion.sql.setString(4, getDireccion());
            objClsConexion.sql.setString(5, getTelefono());
            objClsConexion.sql.setString(6, getPassword());
            objClsConexion.sql.setString(7, getIdentificacion());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro editado con éxito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al editar: ");
        }

    }

    public void eliminar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("delete from usuario where identificacion=?");
            objClsConexion.sql.setString(1, getIdentificacion());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos del usuario eliminados con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el usuario: " + e);
        }
    }

}
