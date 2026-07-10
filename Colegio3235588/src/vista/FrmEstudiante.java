/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.ClsEstudiante;

/**
 *
 * @author Juan Porras
 */
public class FrmEstudiante extends javax.swing.JFrame {

    /**
     * Creates new form FrmEstudiante
     */
    public FrmEstudiante() {
        initComponents();
    }
//Declaracion de variables
    ClsEstudiante objClsEstudiante = new ClsEstudiante();

//Metodos
    public void capturar() {
        objClsEstudiante.setDocEst(TxtDocEst.getText());
        objClsEstudiante.setNomEst(TxtNomEst.getText());
        objClsEstudiante.setApeEst(TxtApeEst.getText());
        objClsEstudiante.setDirEst(TxtDirEst.getText());
        objClsEstudiante.setTelEst(TxtTelEst.getText());
    }

    public void limpiar() {
        TxtDocEst.setText(null);
        TxtNomEst.setText(null);
        TxtApeEst.setText(null);
        TxtDirEst.setText(null);
        TxtTelEst.setText(null);
    }

    public void bloquear() {
        this.limpiar();
        TxtDocEst.setEnabled(false);
        TxtNomEst.setEnabled(false);
        TxtApeEst.setEnabled(false);
        TxtDirEst.setEnabled(false);
        TxtTelEst.setEnabled(false);
        BtnGua.setEnabled(false);
        BtnAct.setEnabled(false);
    }

    public void desbloquear() {
        TxtDocEst.setEnabled(true);
        TxtNomEst.setEnabled(true);
        TxtApeEst.setEnabled(true);
        TxtDirEst.setEnabled(true);
        TxtTelEst.setEnabled(true);
        BtnGua.setEnabled(true);
    }

    public boolean validar() {
        if (TxtDocEst.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el documento");
            TxtDocEst.grabFocus();
        } else if (TxtDocEst.getText().length() > 11) {
            JOptionPane.showMessageDialog(null, "Error, el documento solo permite un maximo de 11 caracteres");
            TxtDocEst.grabFocus();
        } else if (TxtNomEst.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el nombre");
            TxtNomEst.grabFocus();
        } else if (TxtNomEst.getText().length() > 30) {
            JOptionPane.showMessageDialog(null, "Error, el nombre solo permite un maximo de 30 caracteres");
            TxtNomEst.grabFocus();
        } else if (TxtApeEst.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el apellido");
            TxtApeEst.grabFocus();
        } else if (TxtApeEst.getText().length() > 30) {
            JOptionPane.showMessageDialog(null, "Error, el apellido solo permite un maximo de 30 caracteres");
            TxtApeEst.grabFocus();
        } else if (TxtDirEst.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar la direccion");
            TxtDirEst.grabFocus();
        } else if (TxtDirEst.getText().length() > 60) {
            JOptionPane.showMessageDialog(null, "Error, la direccion solo permite un maximo de 60 caracteres");
            TxtDirEst.grabFocus();
        } else if (TxtTelEst.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el numero de telefono");
            TxtTelEst.grabFocus();
        } else if (TxtTelEst.getText().length() > 11) {
            JOptionPane.showMessageDialog(null, "Error, el numero de telefono solo permite un maximo de 11 caracteres");
            TxtTelEst.grabFocus();
        } else {
            return true;
        }
        return false;
    }

    public void guardar() {
        if (validar() == true) {
            this.capturar();
            objClsEstudiante.guardar();
            this.bloquear();

        }

    }

    public void buscar() {
        try {
            String bus = JOptionPane.showInputDialog("Digite el documento que desea buscar");
            objClsEstudiante.setDocEst(bus);
            objClsEstudiante.buscar();
            if (objClsEstudiante.datosEstudiante.next() == true) {
                TxtDocEst.setText(objClsEstudiante.datosEstudiante.getString(1));
                TxtNomEst.setText(objClsEstudiante.datosEstudiante.getString(2));
                TxtApeEst.setText(objClsEstudiante.datosEstudiante.getString(3));
                TxtDirEst.setText(objClsEstudiante.datosEstudiante.getString(4));
                TxtTelEst.setText(objClsEstudiante.datosEstudiante.getString(5));
                this.desbloquear();
                TxtDocEst.setEnabled(false);
                BtnGua.setEnabled(false);
                BtnAct.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "El estudiante no existe");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al visualizar los datos del estudiante" + e);
        }

    }

    public void actualizar() {
        try {
            this.capturar();
            objClsEstudiante.actualizar();
            this.bloquear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fallas Tecnicas" + e);
        }
    }

    public void nuevo() {
        this.bloquear();
        this.desbloquear();
        TxtDocEst.grabFocus();
        this.limpiar();
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
        TxtDocEst = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TxtNomEst = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TxtApeEst = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TxtDirEst = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TxtTelEst = new javax.swing.JTextField();
        BtnNue = new javax.swing.JButton();
        BtnGua = new javax.swing.JButton();
        BtnBus = new javax.swing.JButton();
        BtnMod = new javax.swing.JButton();
        BtnAct = new javax.swing.JButton();
        BtnImp = new javax.swing.JButton();
        BtnReg = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Estudiante");
        setResizable(false);

        jLabel1.setText("Documento");

        TxtDocEst.setEnabled(false);
        TxtDocEst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtDocEstActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nombre");

        TxtNomEst.setEnabled(false);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Apellido");

        TxtApeEst.setEnabled(false);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Direccion");

        TxtDirEst.setEnabled(false);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Telefono");

        TxtTelEst.setEnabled(false);

        BtnNue.setText("Nuevo");
        BtnNue.setToolTipText("Limpia las cajas de texto del formulario");
        BtnNue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNueActionPerformed(evt);
            }
        });

        BtnGua.setText("Guardar");
        BtnGua.setToolTipText("Guarda la informacion en la base de datos");
        BtnGua.setEnabled(false);
        BtnGua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuaActionPerformed(evt);
            }
        });

        BtnBus.setText("Buscar");
        BtnBus.setToolTipText("Busca la informacion en la base de datos");
        BtnBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBusActionPerformed(evt);
            }
        });

        BtnMod.setText("Modificar");
        BtnMod.setToolTipText("Modifica datos ya guardados");
        BtnMod.setEnabled(false);

        BtnAct.setText("Actualizar");
        BtnAct.setToolTipText("Actualiza los datos que ya estan guardados");
        BtnAct.setEnabled(false);
        BtnAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActActionPerformed(evt);
            }
        });

        BtnImp.setText("Imprimir");
        BtnImp.setEnabled(false);

        BtnReg.setText("Regresar");
        BtnReg.setToolTipText("Regresa al menu principal ");
        BtnReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("Estudiantes");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnNue)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnGua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnBus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnMod)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TxtNomEst)
                            .addComponent(TxtDocEst)
                            .addComponent(TxtApeEst)
                            .addComponent(TxtDirEst)
                            .addComponent(TxtTelEst, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnAct)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnImp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnReg))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(103, 103, 103)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TxtDocEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxtNomEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(TxtApeEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtDirEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(42, 42, 42)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TxtTelEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnNue)
                    .addComponent(BtnGua)
                    .addComponent(BtnBus)
                    .addComponent(BtnMod)
                    .addComponent(BtnAct)
                    .addComponent(BtnImp)
                    .addComponent(BtnReg))
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtDocEstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtDocEstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtDocEstActionPerformed

    private void BtnRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegActionPerformed
        this.regresar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRegActionPerformed

    private void BtnNueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNueActionPerformed
        this.nuevo();        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(FrmEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmEstudiante().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAct;
    private javax.swing.JButton BtnBus;
    private javax.swing.JButton BtnGua;
    private javax.swing.JButton BtnImp;
    private javax.swing.JButton BtnMod;
    private javax.swing.JButton BtnNue;
    private javax.swing.JButton BtnReg;
    private javax.swing.JTextField TxtApeEst;
    private javax.swing.JTextField TxtDirEst;
    private javax.swing.JTextField TxtDocEst;
    private javax.swing.JTextField TxtNomEst;
    private javax.swing.JTextField TxtTelEst;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
