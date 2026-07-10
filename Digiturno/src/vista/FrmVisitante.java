/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.ClsVisitante;

/**
 *
 * @author Juan Porras
 */
public class FrmVisitante extends javax.swing.JFrame {

    /**
     * Creates new form FrmVisitante
     */
    public FrmVisitante() {
        initComponents();
    }
//Declaracion de variables
    ClsVisitante objClsVisitante = new ClsVisitante();

//Metodos
    public void capturar() {
        objClsVisitante.setDocVis(TxtDocVis.getText());
        objClsVisitante.setNomVis(TxtNomVis.getText());
        objClsVisitante.setApeVis(TxtApeVis.getText());
        objClsVisitante.setTelVis(TxtTelVis.getText());
        objClsVisitante.setEmaVis(TxtEmaVis.getText());

    }

    public void limpiar() {
        TxtDocVis.setText(null);
        TxtNomVis.setText(null);
        TxtApeVis.setText(null);
        TxtTelVis.setText(null);
        TxtEmaVis.setText(null);
    }

    public void bloquear() {
        this.limpiar();
        TxtDocVis.setEnabled(false);
        TxtNomVis.setEnabled(false);
        TxtApeVis.setEnabled(false);
        TxtTelVis.setEnabled(false);
        TxtEmaVis.setEnabled(false);
        BtnGua.setEnabled(false);
        BtnAct.setEnabled(false);
    }

    public void desbloquear() {
        TxtDocVis.setEnabled(true);
        TxtNomVis.setEnabled(true);
        TxtApeVis.setEnabled(true);
        TxtTelVis.setEnabled(true);
        TxtEmaVis.setEnabled(true);
        BtnGua.setEnabled(true);
    }

    public void nuevo() {
        this.bloquear();
        this.desbloquear();
        TxtDocVis.grabFocus();
    }

    public boolean validar() {
        if (TxtDocVis.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el documento");
            TxtDocVis.grabFocus();
        } else if (TxtDocVis.getText().length() > 11) {
            JOptionPane.showMessageDialog(null, "Error el documento solo permite maximo 11 caracteres");
            TxtDocVis.grabFocus();
        } else if (TxtNomVis.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el nombre");
            TxtNomVis.grabFocus();
        } else if (TxtNomVis.getText().length() > 30) {
            JOptionPane.showMessageDialog(null, "Error el nombre solo permite maximo 30 caracteres");
            TxtNomVis.grabFocus();
        } else if (TxtApeVis.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el apellido");
            TxtApeVis.grabFocus();
        } else if (TxtApeVis.getText().length() > 30) {
            JOptionPane.showMessageDialog(null, "Error el apellido solo permite maximo 30 caracteres");
            TxtApeVis.grabFocus();
        } else if (TxtTelVis.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el telefono");
            TxtTelVis.grabFocus();
        } else if (TxtTelVis.getText().length() > 10) {
            JOptionPane.showMessageDialog(null, "Error el telefono solo permite maximo 10 caracteres");
            TxtTelVis.grabFocus();
        } else if (TxtEmaVis.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el email");
            TxtEmaVis.grabFocus();
        } else if (TxtEmaVis.getText().length() > 100) {
            JOptionPane.showMessageDialog(null, "Error el email solo permite maximo 100 caracteres");
            TxtEmaVis.grabFocus();
        } else {
            return true;
        }
        return false;
    }

    public void guardar() {
        if (validar() == true) {
            this.capturar();
            objClsVisitante.guardar();
            this.bloquear();
        }
    }

    public void buscar() {
        try {
            String bus = JOptionPane.showInputDialog("Digite el documento del visitante que desea buscar");
            objClsVisitante.setDocVis(bus);
            objClsVisitante.buscar();
            if (objClsVisitante.DatosVisitante.next() == true) {
                TxtDocVis.setText(objClsVisitante.DatosVisitante.getString(1));
                TxtNomVis.setText(objClsVisitante.DatosVisitante.getString(2));
                TxtApeVis.setText(objClsVisitante.DatosVisitante.getString(3));
                TxtTelVis.setText(objClsVisitante.DatosVisitante.getString(4));
                TxtEmaVis.setText(objClsVisitante.DatosVisitante.getString(5));
                this.desbloquear();
                TxtDocVis.setEnabled(false);
                BtnAct.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "El visitante no existe");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al visualizar los datos del visitante: " + e);
        }
    }

    public void actualizar() {
        try {
            if (validar() == true) {
                this.capturar();
                objClsVisitante.actualizar();
                this.bloquear();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fallas tecnicas al actualizar: " + e);
        }
    }

    public void regresar() {
        FrmIndex objFrmIndex = new FrmIndex();
        objFrmIndex.setVisible(true);
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
        jLabel6 = new javax.swing.JLabel();
        TxtDocVis = new javax.swing.JTextField();
        TxtNomVis = new javax.swing.JTextField();
        TxtApeVis = new javax.swing.JTextField();
        TxtTelVis = new javax.swing.JTextField();
        TxtEmaVis = new javax.swing.JTextField();
        BtnNue = new javax.swing.JButton();
        BtnGua = new javax.swing.JButton();
        BtnBus = new javax.swing.JButton();
        BtnAct = new javax.swing.JButton();
        BtnReg = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Visitante");
        setResizable(false);

        jLabel1.setText("Documento");

        jLabel2.setText("Nombre");

        jLabel3.setText("Apellido");

        jLabel4.setText("Telefono");

        jLabel5.setText("Email");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Visitante");

        TxtDocVis.setEnabled(false);

        TxtNomVis.setEnabled(false);

        TxtApeVis.setEnabled(false);

        TxtTelVis.setEnabled(false);

        TxtEmaVis.setEnabled(false);

        BtnNue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar-archivo.png"))); // NOI18N
        BtnNue.setToolTipText("Nuevo");
        BtnNue.setBorderPainted(false);
        BtnNue.setContentAreaFilled(false);
        BtnNue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNueActionPerformed(evt);
            }
        });

        BtnGua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sav.png"))); // NOI18N
        BtnGua.setToolTipText("Guardar");
        BtnGua.setBorderPainted(false);
        BtnGua.setContentAreaFilled(false);
        BtnGua.setEnabled(false);
        BtnGua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuaActionPerformed(evt);
            }
        });

        BtnBus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        BtnBus.setToolTipText("Buscar");
        BtnBus.setBorderPainted(false);
        BtnBus.setContentAreaFilled(false);
        BtnBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBusActionPerformed(evt);
            }
        });

        BtnAct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/archivo.png"))); // NOI18N
        BtnAct.setToolTipText("Actualizar");
        BtnAct.setBorderPainted(false);
        BtnAct.setContentAreaFilled(false);
        BtnAct.setEnabled(false);
        BtnAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActActionPerformed(evt);
            }
        });

        BtnReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/deshacer.png"))); // NOI18N
        BtnReg.setToolTipText("Regresar al menu");
        BtnReg.setBorderPainted(false);
        BtnReg.setContentAreaFilled(false);
        BtnReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(BtnNue, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnGua, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnBus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnAct, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnReg, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtEmaVis))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtDocVis, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtTelVis))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtApeVis, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtNomVis, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TxtDocVis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxtNomVis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TxtApeVis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TxtTelVis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TxtEmaVis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnGua)
                    .addComponent(BtnBus)
                    .addComponent(BtnAct)
                    .addComponent(BtnReg)
                    .addComponent(BtnNue))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnNueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNueActionPerformed
        this.nuevo(); // TODO add your handling code here:
    }//GEN-LAST:event_BtnNueActionPerformed

    private void BtnGuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuaActionPerformed
        this.guardar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGuaActionPerformed

    private void BtnBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBusActionPerformed
        this.buscar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBusActionPerformed

    private void BtnActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActActionPerformed
        this.actualizar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnActActionPerformed

    private void BtnRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegActionPerformed
        this.regresar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRegActionPerformed

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
            java.util.logging.Logger.getLogger(FrmVisitante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmVisitante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmVisitante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmVisitante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmVisitante().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAct;
    private javax.swing.JButton BtnBus;
    private javax.swing.JButton BtnGua;
    private javax.swing.JButton BtnNue;
    private javax.swing.JButton BtnReg;
    private javax.swing.JTextField TxtApeVis;
    private javax.swing.JTextField TxtDocVis;
    private javax.swing.JTextField TxtEmaVis;
    private javax.swing.JTextField TxtNomVis;
    private javax.swing.JTextField TxtTelVis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
