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
public class ClsIndicadoresGestion {
//Declaracion de variables

    ClsConexion objClsConexion = new ClsConexion();
    public ResultSet DatosTabla;
//Metodos

    public void LlenarTabla() {
        try {
            objClsConexion.conectar();
            objClsConexion.sql = objClsConexion.con.prepareStatement("select inte.fecint,usu.nomusu as asesor,\n"
                    + "    count(*) as total,\n"
                    + "    sum(inte.ciecasint = 'Cerrado') as cerrados,\n"
                    + "    sum(inte.ciecasint = 'Abierto') as abiertos, \n"
                    + "    sum(inte.ciecasint = 'Escalado') as escalados,\n"
                    + "    time_format(sec_to_time(sum(time_to_sec(inte.tieproint))), '%H:%i:%s') as tiempo_total,\n"
                    + "    time_format(sec_to_time(sum(time_to_sec(inte.tieproint)) / count(*)), '%H:%i:%s') as promedio_tiempo\n"
                    + "from interaccion inte, basedatosasesor ase, usuario usu\n"
                    + "where ase.conase = inte.conaseint\n"
                    + "and usu.idusu = ase.idusuase\n"
                    + "group by inte.fecint, usu.nomusu\n"
                    + "order by inte.fecint desc, usu.nomusu");
            objClsConexion.sql.executeQuery();
            DatosTabla = objClsConexion.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al llenar la tabla: " + e);
        }
    }

}
