/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.ClsGestion;

/**
 *
 * @author Juan Porras
 */
public class FrmGestion extends javax.swing.JFrame {

    /**
     * Creates new form FrmGestion
     */
    public FrmGestion() {
        initComponents();
    }
//Declaracion de variables
    ClsGestion objClsGestion = new ClsGestion();

//Metodos
    public void capturar() {
        objClsGestion.setIdPer(Integer.parseInt(TxtIdPer.getText()));
        objClsGestion.setIdMarPer(Integer.parseInt(CboIdMarPer.getSelectedItem().toString().split(". ")[0]));
        objClsGestion.setNomModPer(TxtNomModPer.getText());
        objClsGestion.setTipPer(CboTipPer.getSelectedItem().toString());
        objClsGestion.setPrePer(Double.parseDouble(TxtPrePer.getText()));
        objClsGestion.setFecRegPer(TxtFecRegPer.getText());

    }

    public void limpiar() {
        TxtIdPer.setText(null);
        CboIdMarPer.setSelectedIndex(0);
        TxtNomModPer.setText(null);
        CboTipPer.setSelectedIndex(0);
        TxtPrePer.setText(null);
        TxtFecRegPer.setText(null);
    }

    public void bloquear() {
        this.limpiar();
        TxtIdPer.setEnabled(false);
        CboIdMarPer.setEnabled(false);
        TxtNomModPer.setEnabled(false);
        CboTipPer.setEnabled(false);
        TxtPrePer.setEnabled(false);
        TxtFecRegPer.setEnabled(false);
        BtnGua.setEnabled(false);
        BtnAct.setEnabled(false);
        BtnMod.setEnabled(false);
    }

    public void desbloquear() {
        TxtIdPer.setEnabled(true);
        CboIdMarPer.setEnabled(true);
        TxtNomModPer.setEnabled(true);
        CboTipPer.setEnabled(true);
        TxtPrePer.setEnabled(true);
        TxtFecRegPer.setEnabled(true);
        BtnGua.setEnabled(true);
    }

    public void modificar() {
        CboIdMarPer.setEnabled(true);
        TxtNomModPer.setEnabled(true);
        CboTipPer.setEnabled(true);
        TxtPrePer.setEnabled(true);
        TxtFecRegPer.setEnabled(true);
        BtnGua.setEnabled(false);
        BtnAct.setEnabled(true);

    }

    public boolean validar() {
        if (TxtNomModPer.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el nombre del periferico");
            TxtNomModPer.grabFocus();
        } else if (TxtNomModPer.getText().length() > 100) {
            JOptionPane.showMessageDialog(null, "Error, el nombre del periferico solo permite un maximo de 100 caracteres");
            TxtNomModPer.grabFocus();
        } else if (CboTipPer.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el tipo");
            CboTipPer.grabFocus();
        } else if (CboIdMarPer.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar la marca");
            CboIdMarPer.grabFocus();
        } else if (TxtPrePer.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el precio");
            TxtPrePer.grabFocus();
        } else {
            return true;
        }
        return false;

    }

    public void consecutivo() {
        objClsGestion.consecutivo();
        try {
            if (objClsGestion.DatosConsecutivo.next()) {
                TxtIdPer.setText(objClsGestion.DatosConsecutivo.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se puede generar el ID del producto: " + e);

        }
    }

    public void fecha() {
        Date FechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String CadenaFecha = formato.format(FechaActual);
        TxtFecRegPer.setText("" + CadenaFecha);
    }

    public void LlenarMarca() {
        try {
            CboIdMarPer.removeAllItems();
            CboIdMarPer.addItem("Seleccione...");
            objClsGestion.LlenarMarca();
            while (objClsGestion.DatosMarca.next() == true) {
                String id = objClsGestion.DatosMarca.getString(1);
                String nombre = objClsGestion.DatosMarca.getString(2);
                CboIdMarPer.addItem(id + ". " + nombre);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar las marcas:  " + e);
        }
    }

    public void nuevo() {
        this.bloquear();
        this.desbloquear();
        this.fecha();
        this.consecutivo();
        this.LlenarMarca();
    }

    public void guardar() {
        if (validar() == true) {
            this.capturar();
            objClsGestion.guardar();
            this.bloquear();
        }
    }

    public void buscar() {
        try {
            String bus = JOptionPane.showInputDialog("Digite el ID del producto que desea buscar");
            objClsGestion.setIdPer(Integer.parseInt(bus));
            objClsGestion.buscar();
            if (objClsGestion.DatosBuscar.next() == true) {
                this.LlenarMarca();
                TxtIdPer.setText(objClsGestion.DatosBuscar.getString(1));
                int IdMar = objClsGestion.DatosBuscar.getInt(2);
                CboIdMarPer.setSelectedIndex(IdMar);
                TxtNomModPer.setText(objClsGestion.DatosBuscar.getString(3));
                CboTipPer.setSelectedItem(objClsGestion.DatosBuscar.getString(4));
                TxtPrePer.setText(objClsGestion.DatosBuscar.getString(5));
                TxtFecRegPer.setText(objClsGestion.DatosBuscar.getString(6));
                BtnGua.setEnabled(false);
                BtnMod.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "El producto no existe");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al visualizar los datos del producto: " + e);
        }

    }

    public void actualizar() {
        try {
            if (validar() == true) {
                this.capturar();
                objClsGestion.actualizar();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TxtIdPer = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TxtNomModPer = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TxtFecRegPer = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        CboIdMarPer = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        TxtPrePer = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        CboTipPer = new javax.swing.JComboBox<>();
        BtnNue = new javax.swing.JButton();
        BtnGua = new javax.swing.JButton();
        BtnBus = new javax.swing.JButton();
        BtnAct = new javax.swing.JButton();
        BtnReg = new javax.swing.JButton();
        BtnMod = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("ULTRAGAMING");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 204, 255));
        jLabel2.setText("S.A");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Modulo de gestion");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setText("ID producto ");

        TxtIdPer.setEditable(false);
        TxtIdPer.setEnabled(false);

        jLabel5.setText("Nombre del periferico");

        TxtNomModPer.setEnabled(false);

        jLabel6.setText("Fecha (Sistema)");

        TxtFecRegPer.setEditable(false);
        TxtFecRegPer.setEnabled(false);

        jLabel7.setText("Marca");

        CboIdMarPer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..." }));
        CboIdMarPer.setEnabled(false);

        jLabel8.setText("Precio unitario ($)");

        TxtPrePer.setEnabled(false);

        jLabel9.setText("Tipo");

        CboTipPer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Mouse", "Teclado", "Headset" }));
        CboTipPer.setEnabled(false);

        BtnNue.setText("Nuevo");
        BtnNue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNueActionPerformed(evt);
            }
        });

        BtnGua.setText("Guardar");
        BtnGua.setEnabled(false);
        BtnGua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuaActionPerformed(evt);
            }
        });

        BtnBus.setText("Buscar");
        BtnBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBusActionPerformed(evt);
            }
        });

        BtnAct.setText("Actualizar");
        BtnAct.setEnabled(false);
        BtnAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActActionPerformed(evt);
            }
        });

        BtnReg.setText("Regresar");
        BtnReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegActionPerformed(evt);
            }
        });

        BtnMod.setText("Modificar");
        BtnMod.setEnabled(false);
        BtnMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnNue)
                .addGap(6, 6, 6)
                .addComponent(BtnGua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnBus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnMod)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnAct)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnReg)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtIdPer, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtNomModPer, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(CboTipPer, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(CboIdMarPer, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8)
                            .addComponent(TxtPrePer, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(TxtFecRegPer, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(42, 42, 42))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtIdPer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNomModPer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CboTipPer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CboIdMarPer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtPrePer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtFecRegPer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnNue)
                    .addComponent(BtnGua)
                    .addComponent(BtnBus)
                    .addComponent(BtnReg)
                    .addComponent(BtnAct)
                    .addComponent(BtnMod))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 43, Short.MAX_VALUE))
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

    private void BtnModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModActionPerformed
        this.modificar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnModActionPerformed

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
            java.util.logging.Logger.getLogger(FrmGestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmGestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmGestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmGestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmGestion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAct;
    private javax.swing.JButton BtnBus;
    private javax.swing.JButton BtnGua;
    private javax.swing.JButton BtnMod;
    private javax.swing.JButton BtnNue;
    private javax.swing.JButton BtnReg;
    private javax.swing.JComboBox<String> CboIdMarPer;
    private javax.swing.JComboBox<String> CboTipPer;
    private javax.swing.JTextField TxtFecRegPer;
    private javax.swing.JTextField TxtIdPer;
    private javax.swing.JTextField TxtNomModPer;
    private javax.swing.JTextField TxtPrePer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
