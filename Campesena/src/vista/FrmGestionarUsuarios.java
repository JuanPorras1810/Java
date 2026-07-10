/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ClsGestionarUsuarios;

/**
 *
 * @author sena cset
 */
public class FrmGestionarUsuarios extends javax.swing.JFrame {

    /**
     * Creates new form FrmGestionarUsuarios
     */
    public FrmGestionarUsuarios() {
        initComponents();
        this.LlenarRoles();
        this.LlenarTabla();

    }
//Declaracion de variables
    ClsGestionarUsuarios objClsGestionarUsuarios = new ClsGestionarUsuarios();

//Contenido tabla
    DefaultTableModel tabladatos;

//Metodos 
    public void capturar() {
        objClsGestionarUsuarios.setIdentificacion(Txt_identificacion.getText());
        objClsGestionarUsuarios.setId_rol(Integer.parseInt(Cbo_id_rol.getSelectedItem().toString().split(". ")[0]));
        objClsGestionarUsuarios.setTipo_identificacion(Cbo_tipo_identificacion.getSelectedItem().toString());
        objClsGestionarUsuarios.setNombre(Txt_nombre.getText() + " " + "|" + " " + Txt_Apellidos.getText());
        objClsGestionarUsuarios.setDireccion(Txt_Direccion.getText());
        objClsGestionarUsuarios.setTelefono(Txt_telefono.getText());
        objClsGestionarUsuarios.setPassword(Txt_password.getText());
    }

    public void limpiar() {
        Txt_identificacion.setText(null);
        Cbo_id_rol.setSelectedIndex(0);
        Cbo_tipo_identificacion.setSelectedIndex(0);
        Txt_nombre.setText(null);
        Txt_Apellidos.setText(null);
        Txt_Direccion.setText(null);
        Txt_telefono.setText(null);
        Txt_password.setText(null);
    }

    public void LlenarRoles() {
        try {
            Cbo_id_rol.removeAllItems();
            Cbo_id_rol.addItem("Seleccione...");
            objClsGestionarUsuarios.LlenarRoles();
            while (objClsGestionarUsuarios.DatosRoles.next() == true) {
                int id = objClsGestionarUsuarios.DatosRoles.getInt(1);
                String tipo = objClsGestionarUsuarios.DatosRoles.getString(2);
                Cbo_id_rol.addItem(id + ". " + tipo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar los roles:  " + e);
        }
    }

    public void CrearTabla() {
        Object modelodata[][] = new Object[0][0];
        Object modelotitulos[] = {"Tipo", "Identificacion", "Nombres", "Apellidos", "Telefono", "Rol"};
        tabladatos = new DefaultTableModel();
        tabladatos = new DefaultTableModel(modelodata, modelotitulos);
        this.TblUsuarios.setModel(this.tabladatos);
    }

    public void BorrarTabla() {
        while (0 < this.TblUsuarios.getRowCount()) {
            TblUsuarios.setModel(new DefaultTableModel());
            this.CrearTabla();
        }
    }

    public void LlenarTabla() {
        try {
            this.BorrarTabla();
            objClsGestionarUsuarios.LlenarTabla();
            while (objClsGestionarUsuarios.DatosTabla.next() == true) {
                String tipo = objClsGestionarUsuarios.DatosTabla.getString(1);
                String identificacion = objClsGestionarUsuarios.DatosTabla.getString(2);
                String nombreCompleto = objClsGestionarUsuarios.DatosTabla.getString(3);
                String[] partes = nombreCompleto.split("\\|");
                String nombres = "";
                String apellidos = "";
                if (partes.length >= 2) {
                    nombres = partes[0];
                    apellidos = partes[1];
                }
                String telefono = objClsGestionarUsuarios.DatosTabla.getString(4);
                String rol = objClsGestionarUsuarios.DatosTabla.getString(5);
                Object fila[] = {tipo, identificacion, nombres, apellidos, telefono, rol};
                tabladatos.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla: " + e);
        }
    }

    public void CrearUsuario() {
        this.capturar();
        objClsGestionarUsuarios.CrearUsuario();
        this.LlenarTabla();
        this.limpiar();
    }

    public void eliminar() {
        int confirmacion = JOptionPane.showConfirmDialog(null, "Desea Eliminar los datos?", "Sistema", 0, 1);
        if (confirmacion == 0) {
            objClsGestionarUsuarios.eliminar();
            this.limpiar();
            this.LlenarTabla();
        }
    }

    public void editar() {
        try {
            this.capturar();
            objClsGestionarUsuarios.editar();
            this.limpiar();
            this.LlenarTabla();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fallas Tecnicas al editar: " + e);
        }
    }

    public void regresar() {
        FrmMenu objFrmMenu = new FrmMenu();
        objFrmMenu.setVisible(true);
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Cbo_tipo_identificacion = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Txt_identificacion = new javax.swing.JTextField();
        Txt_nombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Cbo_id_rol = new javax.swing.JComboBox<>();
        Txt_telefono = new javax.swing.JTextField();
        Txt_Direccion = new javax.swing.JTextField();
        Btn_crear_usuario = new javax.swing.JButton();
        Btn_editar_usuario = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        Txt_Apellidos = new javax.swing.JTextField();
        Txt_password = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblUsuarios = new javax.swing.JTable();
        Btn_eliminar = new javax.swing.JButton();
        Btn_regresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion de usuarios");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel1.setText("Tipo de Identificacion");

        Cbo_tipo_identificacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Cedula de Ciudadanía", "Tarjeta de Identidad" }));

        jLabel2.setText("Numero de Identificacion");

        jLabel3.setText("Nombres");

        jLabel5.setText("Contraseña");

        jLabel6.setText("Telefono");

        jLabel7.setText("Rol");

        jLabel8.setText("Direccion");

        Cbo_id_rol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", " " }));

        Btn_crear_usuario.setBackground(new java.awt.Color(153, 153, 153));
        Btn_crear_usuario.setText("Crear Usuario");
        Btn_crear_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_crear_usuarioActionPerformed(evt);
            }
        });

        Btn_editar_usuario.setBackground(new java.awt.Color(153, 153, 153));
        Btn_editar_usuario.setText("Editar Usuario");
        Btn_editar_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_editar_usuarioActionPerformed(evt);
            }
        });

        jLabel4.setText("Apellidos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Cbo_tipo_identificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Txt_Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Txt_identificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Cbo_id_rol, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Btn_crear_usuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Btn_editar_usuario))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Txt_Apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Cbo_tipo_identificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Txt_identificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Txt_Apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(Txt_Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(Cbo_id_rol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Btn_crear_usuario)
                    .addComponent(Btn_editar_usuario))
                .addGap(31, 31, 31))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        TblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Tipo", "Identificacion", "Nombres", "Apellidos", "Telefono", "Rol"
            }
        ));
        TblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblUsuarios);

        Btn_eliminar.setBackground(new java.awt.Color(153, 153, 153));
        Btn_eliminar.setText("Eliminar");
        Btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_eliminarActionPerformed(evt);
            }
        });

        Btn_regresar.setBackground(new java.awt.Color(153, 153, 153));
        Btn_regresar.setText("Regresar");
        Btn_regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_regresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Btn_regresar)
                        .addGap(18, 18, 18)
                        .addComponent(Btn_eliminar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_eliminar)
                    .addComponent(Btn_regresar))
                .addContainerGap(228, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_crear_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_crear_usuarioActionPerformed
        this.CrearUsuario();        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_crear_usuarioActionPerformed

    private void Btn_editar_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_editar_usuarioActionPerformed
        this.editar();        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_editar_usuarioActionPerformed

    private void TblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblUsuariosMouseClicked
        int linea = TblUsuarios.getSelectedRow();
        try {
            objClsGestionarUsuarios.setIdentificacion(TblUsuarios.getValueAt(linea, 1).toString());
            objClsGestionarUsuarios.buscar();
            if (objClsGestionarUsuarios.DatosBuscar.next() == true) {
                Txt_identificacion.setText(objClsGestionarUsuarios.DatosBuscar.getString(1));
                int idRol = objClsGestionarUsuarios.DatosBuscar.getInt(2);
                Cbo_id_rol.setSelectedIndex(idRol);
                Cbo_tipo_identificacion.setSelectedItem(objClsGestionarUsuarios.DatosBuscar.getString(3));
                String nombreCompleto = objClsGestionarUsuarios.DatosBuscar.getString(4);
                String[] partes = nombreCompleto.split("\\|");
                String nombres = "";
                String apellidos = "";
                if (partes.length >= 2) {
                    nombres = partes[0];
                    apellidos = partes[1];
                }
                Txt_nombre.setText(nombres);
                Txt_Apellidos.setText(apellidos);
                Txt_Direccion.setText(objClsGestionarUsuarios.DatosBuscar.getString(5));
                Txt_telefono.setText(objClsGestionarUsuarios.DatosBuscar.getString(6));
                Txt_password.setText(objClsGestionarUsuarios.DatosBuscar.getString(7));
            } else {
                JOptionPane.showMessageDialog(null, "El usuario no existe");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al visualizar los datos del usuario: " + e);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_TblUsuariosMouseClicked

    private void Btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_eliminarActionPerformed
        this.eliminar();        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_eliminarActionPerformed

    private void Btn_regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_regresarActionPerformed
        this.regresar();        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_regresarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmGestionarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmGestionarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmGestionarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmGestionarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmGestionarUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_crear_usuario;
    private javax.swing.JButton Btn_editar_usuario;
    private javax.swing.JButton Btn_eliminar;
    private javax.swing.JButton Btn_regresar;
    private javax.swing.JComboBox<String> Cbo_id_rol;
    private javax.swing.JComboBox<String> Cbo_tipo_identificacion;
    private javax.swing.JTable TblUsuarios;
    private javax.swing.JTextField Txt_Apellidos;
    private javax.swing.JTextField Txt_Direccion;
    private javax.swing.JTextField Txt_identificacion;
    private javax.swing.JTextField Txt_nombre;
    private javax.swing.JPasswordField Txt_password;
    private javax.swing.JTextField Txt_telefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
