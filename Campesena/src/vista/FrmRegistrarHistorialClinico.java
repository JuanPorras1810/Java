/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.ClsRegistrarHistorialClinico;

/**
 *
 * @author Juan Porras
 */
public class FrmRegistrarHistorialClinico extends javax.swing.JFrame {

    /**
     * Creates new form FrmRegistrarAnimales
     */
    public FrmRegistrarHistorialClinico() {
        initComponents();
        this.fecha();
        this.consecutivo();
        this.LlenarAnimal();
        this.LlenarUsuario();
    }
//Declaracion de variables 
    ClsRegistrarHistorialClinico objClsRegistrarHistorialClinico = new ClsRegistrarHistorialClinico();

//Metodos
    public void capturar() {
        objClsRegistrarHistorialClinico.setCodigo(Txt_codigo.getText());
        objClsRegistrarHistorialClinico.setCodigo_animal(Integer.parseInt(Cbo_codigo_animal.getSelectedItem().toString().split(". ")[0]));
        objClsRegistrarHistorialClinico.setIdentificacion_usuario(Txt_identificacion_usuario.getText());
        objClsRegistrarHistorialClinico.setDiagnostico(Cbo_diagnostico.getSelectedItem().toString());
        objClsRegistrarHistorialClinico.setTratamiento(Txt_tratamiento.getText());
        objClsRegistrarHistorialClinico.setMedicamentos(Txt_medicamentos.getText());
        objClsRegistrarHistorialClinico.setFecha_visita(Txt_fecha_visita.getText());
        objClsRegistrarHistorialClinico.setObservaciones(Txt_observaciones.getText());
    }

    public void limpiar() {
        Txt_codigo.setText(null);
        Cbo_codigo_animal.setSelectedIndex(0);
        Cbo_nombre_usuario.setSelectedIndex(0);
        Cbo_diagnostico.setSelectedIndex(0);
        Txt_tratamiento.setText(null);
        Txt_medicamentos.setText(null);
        //Txt_fecha_visita.setText(null);
        Txt_observaciones.setText(null);
    }

    public void consecutivo() {
        objClsRegistrarHistorialClinico.consecutivo();
        try {
            if (objClsRegistrarHistorialClinico.DatosConsecutivo.next()) {
                Txt_codigo.setText(objClsRegistrarHistorialClinico.DatosConsecutivo.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se puede generar el codigo: " + e);
        }
    }

    public void LlenarAnimal() {
        try {
            Cbo_codigo_animal.removeAllItems();
            Cbo_codigo_animal.addItem("Seleccione...");
            objClsRegistrarHistorialClinico.LlenarAnimal();
            while (objClsRegistrarHistorialClinico.DatosAnimal.next() == true) {
                int codigo = objClsRegistrarHistorialClinico.DatosAnimal.getInt(1);
                String nombre = objClsRegistrarHistorialClinico.DatosAnimal.getString(7);
                Cbo_codigo_animal.addItem(codigo + ". " + nombre);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar los animales:  " + e);
        }
    }

    public void LlenarUsuario() {
        try {
            Cbo_nombre_usuario.removeAllItems();
            Cbo_nombre_usuario.addItem("Seleccione...");
            objClsRegistrarHistorialClinico.LlenarUsuario();
            while (objClsRegistrarHistorialClinico.DatosUsuario.next() == true) {
                Cbo_nombre_usuario.addItem(objClsRegistrarHistorialClinico.DatosUsuario.getString(4));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar los usuarios:  " + e);
        }
    }

    public void BuscarUsuario() {
        try {
            objClsRegistrarHistorialClinico.setNombre(Cbo_nombre_usuario.getSelectedItem().toString());
            objClsRegistrarHistorialClinico.BuscarUsuario();
            if (objClsRegistrarHistorialClinico.DatosBuscarUsuario.next() == true) {
                Txt_identificacion_usuario.setText(objClsRegistrarHistorialClinico.DatosBuscarUsuario.getString(1));
            } else {
                JOptionPane.showMessageDialog(null, "El usuario no existe");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al visualizar la identificacion del usuario: " + e);
        }
    }

    public void fecha() {
        Date FechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String CadenaFecha = formato.format(FechaActual);
        Txt_fecha_visita.setText("" + CadenaFecha);
    }

    public void guardar() {
        this.capturar();
        objClsRegistrarHistorialClinico.guardar();
        this.limpiar();
        this.consecutivo();
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Txt_codigo = new javax.swing.JTextField();
        Cbo_codigo_animal = new javax.swing.JComboBox<>();
        Cbo_nombre_usuario = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        Cbo_diagnostico = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Txt_tratamiento = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Txt_medicamentos = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        Txt_observaciones = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        Txt_fecha_visita = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        Txt_identificacion_usuario = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Historia clinica");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gestionar historia clinica");

        jLabel2.setText("Codigo");

        jLabel3.setText("Animal");

        jLabel4.setText("Usuario");

        jLabel5.setText("Observaciones");

        Txt_codigo.setEditable(false);

        Cbo_codigo_animal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..." }));

        Cbo_nombre_usuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..." }));
        Cbo_nombre_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cbo_nombre_usuarioActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(153, 153, 153));
        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(153, 153, 153));
        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Procedimiento a realizar");

        Cbo_diagnostico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Parto", "Vacunacion", "Control por crecimiento" }));

        jLabel7.setText("Tratamiento");

        Txt_tratamiento.setColumns(20);
        Txt_tratamiento.setRows(5);
        jScrollPane1.setViewportView(Txt_tratamiento);

        jLabel8.setText("Medicamentos");

        Txt_medicamentos.setColumns(20);
        Txt_medicamentos.setRows(5);
        jScrollPane2.setViewportView(Txt_medicamentos);

        Txt_observaciones.setColumns(20);
        Txt_observaciones.setRows(5);
        jScrollPane3.setViewportView(Txt_observaciones);

        jLabel9.setText("Fecha");

        Txt_fecha_visita.setEditable(false);

        jLabel10.setText("Identificacion");

        Txt_identificacion_usuario.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Txt_identificacion_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addGap(18, 18, 18)
                            .addComponent(jButton2))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel7)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(Cbo_diagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel5)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(Cbo_nombre_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Txt_fecha_visita))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Cbo_codigo_animal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(Txt_fecha_visita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Cbo_codigo_animal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Cbo_diagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Cbo_nombre_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(Txt_identificacion_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.guardar();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void Cbo_nombre_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cbo_nombre_usuarioActionPerformed
        if (Cbo_nombre_usuario.getSelectedIndex() > 0) {
            objClsRegistrarHistorialClinico.setNombre(Cbo_nombre_usuario.getSelectedItem().toString());
            this.BuscarUsuario();
        } else {
            Txt_identificacion_usuario.setText(null);
        }      // TODO add your handling code here:
    }//GEN-LAST:event_Cbo_nombre_usuarioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.regresar();// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmRegistrarHistorialClinico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRegistrarHistorialClinico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRegistrarHistorialClinico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRegistrarHistorialClinico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmRegistrarHistorialClinico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Cbo_codigo_animal;
    private javax.swing.JComboBox<String> Cbo_diagnostico;
    private javax.swing.JComboBox<String> Cbo_nombre_usuario;
    private javax.swing.JTextField Txt_codigo;
    private javax.swing.JTextField Txt_fecha_visita;
    private javax.swing.JTextField Txt_identificacion_usuario;
    private javax.swing.JTextArea Txt_medicamentos;
    private javax.swing.JTextArea Txt_observaciones;
    private javax.swing.JTextArea Txt_tratamiento;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
