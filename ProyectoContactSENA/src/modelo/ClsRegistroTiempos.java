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
public class ClsRegistroTiempos {
//Declaracion de variables

    private String idUsu;
    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet DatosTabla;

//Encapsulacion
    public String getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(String idUsu) {
        this.idUsu = idUsu;
    }

//Metodos 
    public void LlenarTabla() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select date(fecHoraIniRegUsu), time(fecHoraIniRegUsu), time(fecHoraCieRegUsu), tieTotRegUsu \n"
                    + "from registroUsuario\n"
                    + "where idUsuRegUsu =?\n"
                    + "order by fecHoraIniRegUsu desc;");
            objClsConexion.sql.setString(1, getIdUsu());
            objClsConexion.sql.executeQuery();
            DatosTabla = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al llenar la tabla: " + e);
        }

    }

}
