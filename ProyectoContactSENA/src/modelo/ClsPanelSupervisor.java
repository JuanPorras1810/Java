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
public class ClsPanelSupervisor {

//Declaracion de variables
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet DatosTablaAsesor;
    public ResultSet DatosTablaActividad;
    public ResultSet DatosTablaCaso;
//Metodos

    public void LlenarTablaAsesor() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select idUsu, nomUsu, 'Conectado' AS Estado\n"
                    + "from usuario\n"
                    + "where rolUsu = 'Agente'\n"
                    + "and idUsu IN (select reg.idUsuRegUsu\n"
                    + "from registroUsuario reg\n"
                    + "where reg.codRegUsu = (select MAX(reg2.codRegUsu)from registroUsuario reg2 where reg2.idUsuRegUsu = reg.idUsuRegUsu)\n"
                    + "and reg.fecHoraCieRegUsu IS NULL)");
            objClsConexion.sql.executeQuery();
            DatosTablaAsesor = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al llenar la tabla: " + e);
        }
    }

    public void LlenarTablaActividad() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select inte.codInt, usu.nomUsu, cli.nomCli, inte.motInt, inte.obsInt\n"
                    + "from interaccion inte, baseDatosAsesor ase, usuario usu, baseDatosCliente cli\n"
                    + "where ase.conAse = inte.conAseInt \n"
                    + "and cli.conCli = inte.conCliInt\n"
                    + "and usu.idUsu = ase.idUsuAse\n"
                    + "order by inte.codInt desc");
            objClsConexion.sql.executeQuery();
            DatosTablaActividad = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al llenar la tabla: " + e);
        }
    }

    public void LlenarTablaCaso() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select codCas, codIntCas, comIntCas\n"
                    + "from caso \n"
                    + "where fecCieCas IS NULL");
            objClsConexion.sql.executeQuery();
            DatosTablaCaso = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al llenar la tabla: " + e);
        }
    }

}
