/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import modelo.ClsGrupo;

/**
 *
 * @author Juan Porras
 */
public class FrmGrupo extends javax.swing.JFrame {

    /**
     * Creates new form FrmProfesional
     */
    public FrmGrupo() {
        initComponents();
    }

//Declaracion de variables
    ClsGrupo objClsGrupo = new ClsGrupo();

//Metodos
    public void capturar() {
        objClsGrupo.setCodGrupo(TxtCodGrupo.getText());
        objClsGrupo.setNomGrupo(TxtNomGrupo.getText());
        objClsGrupo.setAmbGrupo(TxtAmbGrupo.getText());
        String FecIni = new SimpleDateFormat("yyyy-MM-dd").format(TxtFechIniGrupo.getDate());
        objClsGrupo.setFechIniGrupo(FecIni);
        String FecFin = new SimpleDateFormat("yyyy-MM-dd").format(TxtFechFinGrupo.getDate());
        objClsGrupo.setFechFinGrupo(FecFin);
        objClsGrupo.setInsLidGrupo(TxtInsLidGrupo.getText());
        objClsGrupo.setJornGrupo(CboJornGrupo.getSelectedItem().toString());

    }

    public void limpiar() {
        TxtCodGrupo.setText(null);
        TxtNomGrupo.setText(null);
        TxtFechIniGrupo.setDate(null);
        TxtFechFinGrupo.setDate(null);
        TxtAmbGrupo.setText(null);
        TxtInsLidGrupo.setText(null);
        CboJornGrupo.setSelectedIndex(0);
    }

    public void bloquear() {
        this.limpiar();
        TxtCodGrupo.setEnabled(false);
        TxtNomGrupo.setEnabled(false);
        TxtFechIniGrupo.setEnabled(false);
        TxtFechFinGrupo.setEnabled(false);
        TxtAmbGrupo.setEnabled(false);
        TxtInsLidGrupo.setEnabled(false);
        CboJornGrupo.setEnabled(false);
        BtnGua.setEnabled(false);
        BtnAct.setEnabled(false);
    }

    public void desbloquear() {
        TxtCodGrupo.setEnabled(true);
        TxtNomGrupo.setEnabled(true);
        TxtFechIniGrupo.setEnabled(true);
        TxtFechFinGrupo.setEnabled(true);
        TxtAmbGrupo.setEnabled(true);
        TxtInsLidGrupo.setEnabled(true);
        CboJornGrupo.setEnabled(true);
        BtnGua.setEnabled(true);
    }

    public boolean validar() {
        if (TxtCodGrupo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el codigo del grupo");
            TxtCodGrupo.grabFocus();
        } else if (TxtCodGrupo.getText().length() > 10) {
            JOptionPane.showMessageDialog(null, "Error, el codigo solo permite un maximo de 10 caracteres");
            TxtCodGrupo.grabFocus();
        } else if (TxtNomGrupo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el nombre del grupo");
            TxtNomGrupo.grabFocus();
        } else if (TxtNomGrupo.getText().length() > 300) {
            JOptionPane.showMessageDialog(null, "Error, el nombre solo permite un maximo de 300 caracteres");
            TxtNomGrupo.grabFocus();
        } else if (TxtFechIniGrupo.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Debe digitar o seleccionar la fecha inicio");
            TxtFechIniGrupo.grabFocus();
        } else if (TxtFechFinGrupo.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Debe digitar o seleccionar la fecha fin");
            TxtFechFinGrupo.grabFocus();
        } else if (TxtAmbGrupo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el ambiente");
            TxtAmbGrupo.grabFocus();
        } else if (TxtAmbGrupo.getText().length() > 10) {
            JOptionPane.showMessageDialog(null, "Error, el ambiente solo permite un maximo de 10 caracteres");
            TxtAmbGrupo.grabFocus();
        } else if (TxtInsLidGrupo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el instructor lider");
            TxtInsLidGrupo.grabFocus();
        } else if (TxtInsLidGrupo.getText().length() > 200) {
            JOptionPane.showMessageDialog(null, "Error, el instructor solo permite un maximo de 200 caracteres");
            TxtInsLidGrupo.grabFocus();
        } else if (CboJornGrupo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar la jornada");
            CboJornGrupo.grabFocus();
        } else {
            return true;
        }
        return false;
    }

    public void nuevo() {
        this.bloquear();
        this.desbloquear();
        TxtCodGrupo.grabFocus();
    }

    public void guardar() {
        if (validar() == true) {
            this.capturar();
            objClsGrupo.guardar();
            this.bloquear();
        }
    }

    public void buscar() {
        try {
            String bus = JOptionPane.showInputDialog("Digite el codigo del grupo que desea buscar");
            objClsGrupo.setCodGrupo(bus);
            objClsGrupo.buscar();
            if (objClsGrupo.datosGrupo.next() == true) {
                TxtCodGrupo.setText(objClsGrupo.datosGrupo.getString(1));
                TxtNomGrupo.setText(objClsGrupo.datosGrupo.getString(2));
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    TxtFechIniGrupo.setDate(formato.parse(objClsGrupo.datosGrupo.getString(3)));
                    TxtFechFinGrupo.setDate(formato.parse(objClsGrupo.datosGrupo.getString(4)));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al traer fechas: " + e);
                }
                TxtAmbGrupo.setText(objClsGrupo.datosGrupo.getString(5));
                TxtInsLidGrupo.setText(objClsGrupo.datosGrupo.getString(6));
                CboJornGrupo.setSelectedItem(objClsGrupo.datosGrupo.getString(7));
                this.desbloquear();
                TxtCodGrupo.setEnabled(false);
                BtnAct.setEnabled(true);
                BtnGua.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "El grupo no existe");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al visualizar los datos del grupo: " + e);
        }

    }

    public void actualizar() {
        try {
            if (validar() == true) {
                this.capturar();
                objClsGrupo.actualizar();
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
        TxtCodGrupo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TxtAmbGrupo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TxtInsLidGrupo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        BtnNue = new javax.swing.JButton();
        BtnGua = new javax.swing.JButton();
        BtnBus = new javax.swing.JButton();
        BtnAct = new javax.swing.JButton();
        BtnReg = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        CboJornGrupo = new javax.swing.JComboBox<>();
        TxtNomGrupo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TxtFechFinGrupo = new com.toedter.calendar.JDateChooser();
        TxtFechIniGrupo = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Grupo");
        setResizable(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Codigo");

        TxtCodGrupo.setEnabled(false);

        jLabel2.setText("Nombre");

        jLabel3.setText("Fecha inicio");

        jLabel5.setText("Ambiente");

        TxtAmbGrupo.setEnabled(false);

        jLabel7.setText("Instructor lider");

        TxtInsLidGrupo.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Grupo");

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

        jLabel4.setText("Jornada");

        CboJornGrupo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Mañana", "Tarde", "Noche", "Combinada" }));
        CboJornGrupo.setEnabled(false);

        TxtNomGrupo.setEnabled(false);

        jLabel6.setText("Fecha fin");

        TxtFechFinGrupo.setDateFormatString("yyyy-MM-dd");
        TxtFechFinGrupo.setEnabled(false);

        TxtFechIniGrupo.setDateFormatString("yyyy-MM-dd");
        TxtFechIniGrupo.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CboJornGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtCodGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtInsLidGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(TxtNomGrupo))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(TxtAmbGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(TxtFechFinGrupo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                        .addComponent(TxtFechIniGrupo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(138, 147, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnNue, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnGua, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnBus, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnAct, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnReg, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TxtCodGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxtNomGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtFechIniGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtFechFinGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TxtAmbGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TxtInsLidGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CboJornGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnNue)
                    .addComponent(BtnGua)
                    .addComponent(BtnBus)
                    .addComponent(BtnAct)
                    .addComponent(BtnReg))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(FrmGrupo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmGrupo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmGrupo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmGrupo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmGrupo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAct;
    private javax.swing.JButton BtnBus;
    private javax.swing.JButton BtnGua;
    private javax.swing.JButton BtnNue;
    private javax.swing.JButton BtnReg;
    private javax.swing.JComboBox<String> CboJornGrupo;
    private javax.swing.JTextField TxtAmbGrupo;
    private javax.swing.JTextField TxtCodGrupo;
    private com.toedter.calendar.JDateChooser TxtFechFinGrupo;
    private com.toedter.calendar.JDateChooser TxtFechIniGrupo;
    private javax.swing.JTextField TxtInsLidGrupo;
    private javax.swing.JTextField TxtNomGrupo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables
}
