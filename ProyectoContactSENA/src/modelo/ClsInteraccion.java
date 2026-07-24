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
public class ClsInteraccion {
//Declaracion de variables 

    private int codInt;
    private int conAseInt;
    private int conCliInt;
    private int codTipInt;
    private String tipConInt;
    private String motInt;
    private String fecInt;
    private String horIniInt;
    private String horFinInt;
    private String tieProInt;
    private String obsInt;
    private String cieCasInt;
    private String idUsu;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet DatosBuscar;
    public ResultSet DatosConsecutivo;
    public ResultSet DatosCliente;
    public ResultSet DatosAsesor;
    public ResultSet DatosTipificaciones;
    public ResultSet DatosMotivo;
    public ResultSet DatosPdf;
//Encapsulacion

    public int getCodInt() {
        return codInt;
    }

    public void setCodInt(int codInt) {
        this.codInt = codInt;
    }

    public int getConAseInt() {
        return conAseInt;
    }

    public void setConAseInt(int conAseInt) {
        this.conAseInt = conAseInt;
    }

    public int getConCliInt() {
        return conCliInt;
    }

    public void setConCliInt(int conCliInt) {
        this.conCliInt = conCliInt;
    }

    public int getCodTipInt() {
        return codTipInt;
    }

    public void setCodTipInt(int codTipInt) {
        this.codTipInt = codTipInt;
    }

    public String getTipConInt() {
        return tipConInt;
    }

    public void setTipConInt(String tipConInt) {
        this.tipConInt = tipConInt;
    }

    public String getMotInt() {
        return motInt;
    }

    public void setMotInt(String motInt) {
        this.motInt = motInt;
    }

    public String getFecInt() {
        return fecInt;
    }

    public void setFecInt(String fecInt) {
        this.fecInt = fecInt;
    }

    public String getHorIniInt() {
        return horIniInt;
    }

    public void setHorIniInt(String horIniInt) {
        this.horIniInt = horIniInt;
    }

    public String getHorFinInt() {
        return horFinInt;
    }

    public void setHorFinInt(String horFinInt) {
        this.horFinInt = horFinInt;
    }

    public String getTieProInt() {
        return tieProInt;
    }

    public void setTieProInt(String tieProInt) {
        this.tieProInt = tieProInt;
    }

    public String getObsInt() {
        return obsInt;
    }

    public void setObsInt(String obsInt) {
        this.obsInt = obsInt;
    }

    public String getCieCasInt() {
        return cieCasInt;
    }

    public void setCieCasInt(String cieCasInt) {
        this.cieCasInt = cieCasInt;
    }

    public String getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(String idUsu) {
        this.idUsu = idUsu;
    }

    //Metodos
    public void LlenarCliente() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select conCli, idCli, nomCli, emaCli, telCli, telAltCli \n"
                    + "from baseDatosCliente \n"
                    + "where conCli IN (select conCliAsi from asignacionLlamada where conAseAsi IN (select conAse from baseDatosAsesor where idUsuAse =?) and conAteAsi IS NULL) \n"
                    + "ORDER BY (select fecFinCam from campana where codCam = codCamCli) ASC \n"
                    + "LIMIT 1");
            objClsConexion.sql.setString(1, getIdUsu());
            objClsConexion.sql.executeQuery();
            DatosCliente = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar la informacion del cliente: " + e);
        }
    }

    public void BuscarAsesor() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select conAseAsi  \n"
                    + "from asignacionLlamada \n"
                    + "where conCliAsi =?\n"
                    + "and conAteAsi IS NULL \n"
                    + "LIMIT 1");
            objClsConexion.sql.setInt(1, getConCliInt());
            objClsConexion.sql.executeQuery();
            DatosAsesor = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar la informacion del asesor: " + e);
        }
    }

    public void LlenarMotivo() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select cam.nomCam\n"
                    + "from baseDatosCliente cli, campana cam\n"
                    + "where cli.conCli =? \n"
                    + "and cam.codCam = cli.codCamCli");
            objClsConexion.sql.setInt(1, getConCliInt());
            objClsConexion.sql.executeQuery();
            DatosMotivo = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar motivo interaccion: " + e);
        }
    }

    public void LlenarTipificaciones() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select tip.codTip, tip.nomTip, cam.nomCam\n"
                    + "from tipificaciones tip, baseDatosAsesor ase, campana cam\n"
                    + "where ase.conAse =?\n"
                    + "and ase.codCamAse = cam.codCam\n"
                    + "and cam.codCam = tip.codCamTip");
            objClsConexion.sql.setInt(1, getConAseInt());
            objClsConexion.sql.executeQuery();
            DatosTipificaciones = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al buscar las tipificaciones: " + e);
        }
    }

    public void ObtenerRutaPdf() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select cam.proCam \n"
                    + "from baseDatosAsesor ase, campana cam \n"
                    + "where ase.conAse = ? \n"
                    + "and ase.codCamAse = cam.codCam");
            objClsConexion.sql.setInt(1, getConAseInt());
            objClsConexion.sql.executeQuery();
            DatosPdf = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al obtener el PDF: " + e);
        }
    }

    public void ActualizarAsignacionLlamada() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("UPDATE asignacionLlamada\n"
                    + "SET conAteAsi = 1 \n"
                    + "WHERE conAseAsi = ? AND conCliAsi = ?");
            objClsConexion.sql.setInt(1, getConAseInt());
            objClsConexion.sql.setInt(2, getConCliInt());
            objClsConexion.sql.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al actualizar la aignacion de clientes: " + e);
        }
    }

    public void guardar() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("insert into interaccion(conAseInt,conCliInt,codTipInt,tipConInt,motInt,fecInt,horIniInt,horFinInt,obsInt,cieCasInt,tieProInt)\n"
                    + "values(?,?,?,?,?,?,?,?,?,?,TIMEDIFF(?,?))");
            objClsConexion.sql.setInt(1, getConAseInt());
            objClsConexion.sql.setInt(2, getConCliInt());
            objClsConexion.sql.setInt(3, getCodTipInt());
            objClsConexion.sql.setString(4, getTipConInt());
            objClsConexion.sql.setString(5, getMotInt());
            objClsConexion.sql.setString(6, getFecInt());
            objClsConexion.sql.setString(7, getHorIniInt());
            objClsConexion.sql.setString(8, getHorFinInt());
            objClsConexion.sql.setString(9, getObsInt());
            objClsConexion.sql.setString(10, getCieCasInt());
            objClsConexion.sql.setString(11, getHorFinInt());
            objClsConexion.sql.setString(12, getHorIniInt());
            objClsConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Interaccion guardada con exito!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la interaccion: " + e);
        }

    }

    public void consecutivo() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select count(*)+1 from interaccion");
            objClsConexion.sql.executeQuery();
            DatosConsecutivo = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al generar el consecutivo: " + e);
        }
    }

}
