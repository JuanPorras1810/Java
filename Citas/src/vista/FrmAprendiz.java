/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.ClsAprendiz;

/**
 *
 * @author sena cset
 */
public class FrmAprendiz extends javax.swing.JFrame {

    /**
     * Creates new form FrmProfesional
     */
    public FrmAprendiz() {
        initComponents();
    }
//Declaracion de variables 
    ClsAprendiz objClsAprendiz = new ClsAprendiz();

//Metodos
    public void capturar() {
        objClsAprendiz.setDocApre(TxtDocApre.getText());
        objClsAprendiz.setCodGrupoApre(CboCodGrupoApre.getSelectedItem().toString());
        objClsAprendiz.setNomApre(TxtNomApre.getText());
        objClsAprendiz.setApeApre(TxtApeApre.getText());
        objClsAprendiz.setEmaApre(TxtEmaApre.getText());
        objClsAprendiz.setTelApre(TxtTelApre.getText());
        objClsAprendiz.setEpsApre(CboEpsApre.getSelectedItem().toString());
        objClsAprendiz.setConEmerApre(TxtConEmerApre.getText());
        objClsAprendiz.setTelConEmerApre(TxtTelConEmerApre.getText());
    }

    public void limpiar() {
        TxtDocApre.setText(null);
        CboCodGrupoApre.setSelectedIndex(0);
        TxtNomApre.setText(null);
        TxtApeApre.setText(null);
        TxtEmaApre.setText(null);
        TxtTelApre.setText(null);
        CboEpsApre.setSelectedIndex(0);
        TxtConEmerApre.setText(null);
        TxtTelConEmerApre.setText(null);
    }

    public void bloquear() {
        this.limpiar();
        TxtDocApre.setEnabled(false);
        CboCodGrupoApre.setEnabled(false);
        TxtNomApre.setEnabled(false);
        TxtApeApre.setEnabled(false);
        TxtEmaApre.setEnabled(false);
        TxtTelApre.setEnabled(false);
        CboEpsApre.setEnabled(false);
        TxtConEmerApre.setEnabled(false);
        TxtTelConEmerApre.setEnabled(false);
        BtnGua.setEnabled(false);
        BtnAct.setEnabled(false);
    }

    public void desbloquear() {
        TxtDocApre.setEnabled(true);
        CboCodGrupoApre.setEnabled(true);
        TxtNomApre.setEnabled(true);
        TxtApeApre.setEnabled(true);
        TxtEmaApre.setEnabled(true);
        TxtTelApre.setEnabled(true);
        CboEpsApre.setEnabled(true);
        TxtConEmerApre.setEnabled(true);
        TxtTelConEmerApre.setEnabled(true);
        BtnGua.setEnabled(true);
    }

    public boolean validar() {
        if (TxtDocApre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el documento");
            TxtDocApre.grabFocus();
        } else if (TxtDocApre.getText().length() > 11) {
            JOptionPane.showMessageDialog(null, "Error, el documento solo permite maximo 11 caracteres");
            TxtDocApre.grabFocus();
        } else if (CboCodGrupoApre.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el grupo");
            CboCodGrupoApre.grabFocus();
        } else if (TxtNomApre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el nombre");
            TxtNomApre.grabFocus();
        } else if (TxtNomApre.getText().length() > 30) {
            JOptionPane.showMessageDialog(null, "Error, el nombre solo permite maximo 30 caracteres");
            TxtNomApre.grabFocus();
        } else if (TxtApeApre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el apellido");
            TxtApeApre.grabFocus();
        } else if (TxtApeApre.getText().length() > 30) {
            JOptionPane.showMessageDialog(null, "Error, el apellido solo permite maximo 30 caracteres");
            TxtApeApre.grabFocus();
        } else if (TxtEmaApre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el email");
            TxtEmaApre.grabFocus();
        } else if (TxtEmaApre.getText().length() > 100) {
            JOptionPane.showMessageDialog(null, "Error, el email solo permite maximo 100 caracteres");
            TxtEmaApre.grabFocus();
        } else if (TxtTelApre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el telefono");
            TxtTelApre.grabFocus();
        } else if (TxtTelApre.getText().length() > 11) {
            JOptionPane.showMessageDialog(null, "Error, el telefono solo permite maximo 11 caracteres");
            TxtTelApre.grabFocus();
        } else if (CboEpsApre.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar la EPS");
            CboEpsApre.grabFocus();
        } else if (TxtConEmerApre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el contacto de emergencia");
            TxtConEmerApre.grabFocus();
        } else if (TxtConEmerApre.getText().length() > 100) {
            JOptionPane.showMessageDialog(null, "Error, el contacto de emergencia solo permite maximo 100 caracteres");
            TxtConEmerApre.grabFocus();
        } else if (TxtTelConEmerApre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el telefono de emergencia");
            TxtTelConEmerApre.grabFocus();
        } else if (TxtTelConEmerApre.getText().length() > 11) {
            JOptionPane.showMessageDialog(null, "Error, el telefono de emergencia solo permite maximo 11 caracteres");
            TxtTelConEmerApre.grabFocus();
        } else {
            return true;
        }
        return false;
    }

    public void LlenarComboGrupo() {
        CboCodGrupoApre.removeAllItems();
        CboCodGrupoApre.addItem("Seleccione...");
        objClsAprendiz.LlenarComboGrupo();
        try {
            while (objClsAprendiz.datosGrupo.next() == true) {
                CboCodGrupoApre.addItem(objClsAprendiz.datosGrupo.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar el codigo del grupo" + e);
        }
    }

    public void nuevo() {
        this.bloquear();
        this.desbloquear();
        this.LlenarComboGrupo();
        TxtDocApre.grabFocus();
    }

    public void guardar() {
        if (validar() == true) {
            this.capturar();
            objClsAprendiz.guardar();
            this.bloquear();
        }
    }

    public void buscar() {
        try {
            String bus = JOptionPane.showInputDialog("Digite el documento del aprendiz que desea buscar");
            objClsAprendiz.setDocApre(bus);
            objClsAprendiz.buscar();
            if (objClsAprendiz.datosAprendiz.next() == true) {
                TxtDocApre.setText(objClsAprendiz.datosAprendiz.getString(1));
                CboCodGrupoApre.setSelectedItem(objClsAprendiz.datosAprendiz.getString(2));
                TxtNomApre.setText(objClsAprendiz.datosAprendiz.getString(3));
                TxtApeApre.setText(objClsAprendiz.datosAprendiz.getString(4));
                TxtEmaApre.setText(objClsAprendiz.datosAprendiz.getString(5));
                TxtTelApre.setText(objClsAprendiz.datosAprendiz.getString(6));
                CboEpsApre.setSelectedItem(objClsAprendiz.datosAprendiz.getString(7));
                TxtConEmerApre.setText(objClsAprendiz.datosAprendiz.getString(8));
                TxtTelConEmerApre.setText(objClsAprendiz.datosAprendiz.getString(9));
                this.desbloquear();
                TxtDocApre.setEnabled(false);
                BtnAct.setEnabled(true);
                BtnGua.setEnabled(false);

            } else {
                JOptionPane.showMessageDialog(null, "El aprendiz no existe");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al visualizar los datos del aprendiz: " + e);

        }
    }

    public void actualizar() {
        try {
            if (validar() == true) {
                this.capturar();
                objClsAprendiz.actualizar();
                this.bloquear();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fallas Tecnicas al actualizar: " + e);
        }
    }

    public void regresar() {
        FrmIdex objFrmIdex = new FrmIdex();
        objFrmIdex.setVisible(true);
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
        TxtDocApre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TxtApeApre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TxtEmaApre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TxtTelApre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        BtnNue = new javax.swing.JButton();
        BtnGua = new javax.swing.JButton();
        BtnBus = new javax.swing.JButton();
        BtnAct = new javax.swing.JButton();
        BtnReg = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        CboCodGrupoApre = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        CboEpsApre = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        TxtConEmerApre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TxtTelConEmerApre = new javax.swing.JTextField();
        TxtNomApre = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aprendiz");
        setResizable(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Documento");

        TxtDocApre.setEnabled(false);

        jLabel2.setText("Nombre");

        jLabel3.setText("Apellido");

        TxtApeApre.setEnabled(false);

        jLabel4.setText("Email");

        TxtEmaApre.setEnabled(false);

        jLabel5.setText("Telefono");

        TxtTelApre.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Aprendiz");

        BtnNue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar-archivo.png"))); // NOI18N
        BtnNue.setToolTipText("Crea un nuevo formulario editable");
        BtnNue.setBorderPainted(false);
        BtnNue.setContentAreaFilled(false);
        BtnNue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNueActionPerformed(evt);
            }
        });

        BtnGua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sav.png"))); // NOI18N
        BtnGua.setToolTipText("Guarda la informacion");
        BtnGua.setBorderPainted(false);
        BtnGua.setContentAreaFilled(false);
        BtnGua.setEnabled(false);
        BtnGua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuaActionPerformed(evt);
            }
        });

        BtnBus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        BtnBus.setToolTipText("Busca la informacion guardada");
        BtnBus.setBorderPainted(false);
        BtnBus.setContentAreaFilled(false);
        BtnBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBusActionPerformed(evt);
            }
        });

        BtnAct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/archivo.png"))); // NOI18N
        BtnAct.setToolTipText("Actualiza datos guardados ");
        BtnAct.setBorderPainted(false);
        BtnAct.setContentAreaFilled(false);
        BtnAct.setEnabled(false);
        BtnAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActActionPerformed(evt);
            }
        });

        BtnReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/deshacer.png"))); // NOI18N
        BtnReg.setToolTipText("Regresa al menu principal ");
        BtnReg.setBorderPainted(false);
        BtnReg.setContentAreaFilled(false);
        BtnReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegActionPerformed(evt);
            }
        });

        jLabel9.setText("Grupo");

        CboCodGrupoApre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..." }));
        CboCodGrupoApre.setEnabled(false);

        jLabel10.setText("EPS");

        CboEpsApre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Coosalud", "Famisanar", "Nueva EPS", "Salud Total", "Sura", "Sanitas", "Otra", "Ninguna" }));
        CboEpsApre.setEnabled(false);

        jLabel6.setText("Contacto de emergencia");

        TxtConEmerApre.setEnabled(false);

        jLabel7.setText("Telefono ");

        TxtTelConEmerApre.setEnabled(false);

        TxtNomApre.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtTelConEmerApre, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CboEpsApre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CboCodGrupoApre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtDocApre, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TxtApeApre, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(TxtNomApre)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtEmaApre, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtTelApre, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtConEmerApre, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(109, 109, 109))
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(BtnNue, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnGua, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnBus, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnAct, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnReg, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TxtDocApre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(CboCodGrupoApre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxtNomApre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TxtApeApre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TxtEmaApre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TxtTelApre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(CboEpsApre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TxtConEmerApre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TxtTelConEmerApre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnNue)
                    .addComponent(BtnGua)
                    .addComponent(BtnBus)
                    .addComponent(BtnAct)
                    .addComponent(BtnReg))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(FrmAprendiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAprendiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAprendiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAprendiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAprendiz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAct;
    private javax.swing.JButton BtnBus;
    private javax.swing.JButton BtnGua;
    private javax.swing.JButton BtnNue;
    private javax.swing.JButton BtnReg;
    private javax.swing.JComboBox<String> CboCodGrupoApre;
    private javax.swing.JComboBox<String> CboEpsApre;
    private javax.swing.JTextField TxtApeApre;
    private javax.swing.JTextField TxtConEmerApre;
    private javax.swing.JTextField TxtDocApre;
    private javax.swing.JTextField TxtEmaApre;
    private javax.swing.JTextField TxtNomApre;
    private javax.swing.JTextField TxtTelApre;
    private javax.swing.JTextField TxtTelConEmerApre;
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
    // End of variables declaration//GEN-END:variables
}
