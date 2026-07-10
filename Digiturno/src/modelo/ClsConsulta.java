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
public class ClsConsulta {
//Declaracion de variables

    private String docVis;
    private String depIng;
    private String fecIng;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet DatosVisitante;
    public ResultSet DatosDependencia;
    public ResultSet DatosFecha;
//Encapsulacion

    public String getDocVis() {
        return docVis;
    }

    public void setDocVis(String docVis) {
        this.docVis = docVis;
    }

    public String getDepIng() {
        return depIng;
    }

    public void setDepIng(String depIng) {
        this.depIng = depIng;
    }

    public String getFecIng() {
        return fecIng;
    }

    public void setFecIng(String fecIng) {
        this.fecIng = fecIng;
    }



//Metodos
    public void ConsultaVisitante() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select ingreso.fecIng, ingreso.depIng, ingreso.motIng \n"
                    + "from visitante, ingreso \n"
                    + "where visitante.docVis=? \n"
                    + "and visitante.docVis = ingreso.docVisIng;");
            objClsConexion.sql.setString(1, getDocVis());
            objClsConexion.sql.executeQuery();
            DatosVisitante = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar por visitante: " + e);
        }

    }

    public void ConsultaDependencia() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select ingreso.fecIng,visitante.DocVis, visitante.nomVis, visitante.apeVis, ingreso.motIng\n"
                    + "from visitante, ingreso \n"
                    + "where ingreso.depIng=? \n"
                    + "and visitante.docVis = ingreso.docVisIng;");
            objClsConexion.sql.setString(1, getDepIng());
            objClsConexion.sql.executeQuery();
            DatosDependencia = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar por dependencia: " + e);
        }

    }

    public void ConsultaFecha() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select visitante.DocVis, visitante.nomVis, visitante.apeVis, ingreso.motIng, ingreso.depIng\n"
                    + "from visitante, ingreso \n"
                    + "where date(ingreso.fecIng)=? \n"
                    + "and visitante.docVis = ingreso.docVisIng");
            objClsConexion.sql.setObject(1, getFecIng());
            objClsConexion.sql.executeQuery();
            DatosFecha = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar por fecha: " + e);
        }

    }
}
