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
public class ClsAsignacionLlamada {
//Declaracion de variables

    private int codAsi;
    private int conAseAsi;
    private int conCliAsi;
    private String fecAsi;
    private int codCam;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet DatosBuscar;
    public ResultSet DatosConsecutivo;
    public ResultSet DatosTabla;
    public ResultSet DatosAsesor;
    public ResultSet DatosCliente;
    public ResultSet DatosBuscarAsesor;
    public ResultSet DatosBuscarCliente;
    public ResultSet DatosCampana;

//Encapsulacion
    public int getCodAsi() {
        return codAsi;
    }

    public void setCodAsi(int codAsi) {
        this.codAsi = codAsi;
    }

    public int getConAseAsi() {
        return conAseAsi;
    }

    public void setConAseAsi(int conAseAsi) {
        this.conAseAsi = conAseAsi;
    }

    public int getConCliAsi() {
        return conCliAsi;
    }

    public void setConCliAsi(int conCliAsi) {
        this.conCliAsi = conCliAsi;
    }

    public String getFecAsi() {
        return fecAsi;
    }

    public void setFecAsi(String fecAsi) {
        this.fecAsi = fecAsi;
    }

    public int getCodCam() {
        return codCam;
    }

    public void setCodCam(int codCam) {
        this.codCam = codCam;
    }

//Metodos
    public void LlenarCampana() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select codCam, nomCam from campana");
            objClsConexion.sql.executeQuery();
            DatosCampana = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar campañas: " + e);
        }
    }

    public void LlenarAsesor() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select conAse from baseDatosAsesor where codCamAse=?");
            objClsConexion.sql.setInt(1, getCodCam());
            objClsConexion.sql.executeQuery();
            DatosAsesor = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar asesores: " + e);
        }
    }

    public void BuscarAsesor() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select usu.nomUsu from baseDatosAsesor ase, usuario usu \n"
                    + "where conAse=?\n"
                    + "and usu.idUsu = ase.idUsuAse");
            objClsConexion.sql.setInt(1, getConAseAsi());
            objClsConexion.sql.executeQuery();
            DatosBuscarAsesor = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar el nombre del asesor: " + e);
        }
    }

    public void LlenarCliente() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select conCli from baseDatosCliente where codCamCli=?");
            objClsConexion.sql.setInt(1, getCodCam());
            objClsConexion.sql.executeQuery();
            DatosCliente = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar clientes: " + e);
        }
    }

    public void BuscarCliente() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select nomCli from baseDatosCliente where conCli=? ");
            objClsConexion.sql.setInt(1, getConCliAsi());
            objClsConexion.sql.executeQuery();
            DatosBuscarCliente = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar el nombre del cliente: " + e);
        }
    }

    public boolean ExisteAsignacionGuardar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("SELECT 1 \n"
                    + "from asignacionLlamada \n"
                    + "where conAseAsi = ? \n"
                    + "and conCliAsi = ? \n"
                    + "and fecAsi = ?");
            objClsConexion.sql.setInt(1, getConAseAsi());
            objClsConexion.sql.setInt(2, getConCliAsi());
            objClsConexion.sql.setString(3, getFecAsi());
            ResultSet rs = objClsConexion.sql.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al validar asignacion: " + e);
        }
        return false;
    }

    public boolean existeAsignacionActualizar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select 1 \n"
                    + "from asignacionLlamada \n"
                    + "where conAseAsi = ? \n" 
                    + "and conCliAsi = ? \n" 
                    + "and codAsi <> ?");
            objClsConexion.sql.setInt(1, getConAseAsi());
            objClsConexion.sql.setInt(2, getConCliAsi());
            objClsConexion.sql.setInt(3, getCodAsi());
            ResultSet rs = objClsConexion.sql.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al validar asignacion: " + e);
        }
        return false;
    }

    public void guardar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into asignacionLlamada(conAseAsi,conCliAsi,fecAsi) values(?,?,?)");
            objClsConexion.sql.setInt(1, getConAseAsi());
            objClsConexion.sql.setInt(2, getConCliAsi());
            objClsConexion.sql.setString(3, getFecAsi());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Asignacion de llamada guardada con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la asignacion de llamada: " + e);
        }

    }

    public void buscar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select asi.codAsi, asi.conAseAsi, asi.conCliAsi, asi.fecAsi, ase.codCamAse\n"
                    + "from asignacionLlamada asi, baseDatosAsesor ase\n"
                    + "where asi.conAseAsi = ase.conAse\n"
                    + "and asi.codAsi = ?");
            objClsConexion.sql.setInt(1, getCodAsi());
            objClsConexion.sql.executeQuery();
            DatosBuscar = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar la asignacion de llamada: " + e);
        }
    }

    public void actualizar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("update asignacionLlamada set conAseAsi=?,conCliAsi=?,fecAsi=? where codAsi=?");
            objClsConexion.sql.setInt(1, getConAseAsi());
            objClsConexion.sql.setInt(2, getConCliAsi());
            objClsConexion.sql.setString(3, getFecAsi());
            objClsConexion.sql.setInt(4, getCodAsi());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro actualizado con éxito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + e);
        }

    }

    public void consecutivo() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select count(*)+1 from asignacionLlamada");
            objClsConexion.sql.executeQuery();
            DatosConsecutivo = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al generar el consecutivo: " + e);
        }
    }

    public void LlenarTabla() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select asi.codAsi, CONCAT(ase.conAse, '. ', usu.nomUsu), CONCAT(cli.conCli, '. ', cli.nomCli), asi.fecAsi\n"
                    + "from asignacionLlamada asi, baseDatosAsesor ase, usuario usu, baseDatosCliente cli\n"
                    + "where asi.conAseAsi = ase.conAse\n"
                    + "and ase.idUsuAse = usu.idUsu\n"
                    + "and asi.conCliAsi = cli.conCli\n"
                    + "order by asi.codAsi asc");
            objClsConexion.sql.executeQuery();
            DatosTabla = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al llenar la tabla: " + e);
        }

    }

}
