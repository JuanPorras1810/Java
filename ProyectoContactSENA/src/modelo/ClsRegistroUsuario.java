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
public class ClsRegistroUsuario {
//Declaracion de variables

    private int codRegUsu;
    private String idUsuRegUsu;
    private String conRegUsu;
    private String fecHoraIniRegUsu;
    private String fecHoraCieRegUsu;
    private String tieTotRegUsu;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet DatosIniciarSesion;
    public ResultSet DatosConsecutivo;
//Encapsulacion

    public int getCodRegUsu() {
        return codRegUsu;
    }

    public void setCodRegUsu(int codRegUsu) {
        this.codRegUsu = codRegUsu;
    }

    public String getIdUsuRegUsu() {
        return idUsuRegUsu;
    }

    public void setIdUsuRegUsu(String idUsuRegUsu) {
        this.idUsuRegUsu = idUsuRegUsu;
    }

    public String getConRegUsu() {
        return conRegUsu;
    }

    public void setConRegUsu(String conRegUsu) {
        this.conRegUsu = conRegUsu;
    }

    public String getFecHoraIniRegUsu() {
        return fecHoraIniRegUsu;
    }

    public void setFecHoraIniRegUsu(String fecHoraIniRegUsu) {
        this.fecHoraIniRegUsu = fecHoraIniRegUsu;
    }

    public String getFecHoraCieRegUsu() {
        return fecHoraCieRegUsu;
    }

    public void setFecHoraCieRegUsu(String fecHoraCieRegUsu) {
        this.fecHoraCieRegUsu = fecHoraCieRegUsu;
    }

    public String getTieTotRegUsu() {
        return tieTotRegUsu;
    }

    public void setTieTotRegUsu(String tieTotRegUsu) {
        this.tieTotRegUsu = tieTotRegUsu;
    }

//Metodos
    public void consecutivo() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select count(*)+1 from registroUsuario");
            objClsConexion.sql.executeQuery();
            DatosConsecutivo = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al generar el consecutivo: " + e);
        }
    }

    public void IniciarSesion() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select * \n"
                    + "from usuario\n"
                    + "where idUsu=? and conUsu=?");
            objClsConexion.sql.setString(1, getIdUsuRegUsu());
            objClsConexion.sql.setString(2, getConRegUsu());
            objClsConexion.sql.executeQuery();
            DatosIniciarSesion = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar datos del usuario: " + e);
        }
    }

    public void GuardarInicio() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into registroUsuario(idUsuRegUsu,fecHoraIniRegUsu) values(?,?)");
            objClsConexion.sql.setString(1, getIdUsuRegUsu());
            objClsConexion.sql.setString(2, getFecHoraIniRegUsu());
            objClsConexion.sql.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar datos de inicio: " + e);
        }

    }
    public void CerrarSesion() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("update registroUsuario\n"
                    + "set fecHoraCieRegUsu =?, tieTotRegUsu = TIMEDIFF(?, fecHoraIniRegUsu)\n"
                    + "where idUsuRegUsu =?\n"
                    + "order by codRegUsu desc\n"
                    + "limit 1");
            objClsConexion.sql.setString(1, getFecHoraCieRegUsu());
            objClsConexion.sql.setString(2, getFecHoraCieRegUsu());
            objClsConexion.sql.setString(3, getIdUsuRegUsu());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sesión cerrada correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar sesión: " + e);
        }
    }

}
