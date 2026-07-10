/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ClsConsulta;

/**
 *
 * @author Juan Porras
 */
public class FrmConsultar extends javax.swing.JFrame {

    /**
     * Creates new form FrmConsultar
     */
    public FrmConsultar() {
        initComponents();
    }
//Declaracion de variables
    ClsConsulta objClsConsulta = new ClsConsulta();

//Contenido tabla
    DefaultTableModel tabladatos;

//Metodos
    public void CrearTablaVisitante() {
        Object modelodata[][] = new Object[0][0];
        Object modelotitulos[] = {"Fecha", "Dependencia", "Motivo"};
        tabladatos = new DefaultTableModel();
        tabladatos = new DefaultTableModel(modelodata, modelotitulos);
        this.TblVis.setModel(this.tabladatos);
    }

    public void BorrarTablaVisitante() {
        while (0 < this.TblVis.getRowCount()) {
            TblVis.setModel(new DefaultTableModel());
            this.CrearTablaVisitante();
        }
    }

    public void LLenarTablaVisitante() {
        if (TxtDocVis.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el documento del visitante");
            BtgTipCon.clearSelection();
            TxtDocVis.grabFocus();
        } else {
            try {
                this.BorrarTablaVisitante();
                objClsConsulta.setDocVis(TxtDocVis.getText());
                objClsConsulta.ConsultaVisitante();
                while (objClsConsulta.DatosVisitante.next() == true) {
                    String fecha = objClsConsulta.DatosVisitante.getString(1);
                    String dependencia = objClsConsulta.DatosVisitante.getString(2);
                    String motivo = objClsConsulta.DatosVisitante.getString(3);
                    Object fila[] = {fecha, dependencia, motivo};
                    tabladatos.addRow(fila);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al llenar la tabla con la consulta visitante: " + e);
            }
        }
    }

    public void CrearTablaDependencia() {
        Object modelodata[][] = new Object[0][0];
        Object modelotitulos[] = {"Fecha", "Documento", "Nombre", "Apellido", "Motivo"};
        tabladatos = new DefaultTableModel();
        tabladatos = new DefaultTableModel(modelodata, modelotitulos);
        this.TblDep.setModel(this.tabladatos);
    }

    public void BorrarTablaDependencia() {
        while (0 < this.TblDep.getRowCount()) {
            TblDep.setModel(new DefaultTableModel());
            this.CrearTablaDependencia();

        }

    }

    public void LlenarTablaDependencia() {
        if (CboDep.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe elegir la dependencia");
            BtgTipCon.clearSelection();
            CboDep.grabFocus();
        } else {
            try {
                this.BorrarTablaDependencia();
                objClsConsulta.setDepIng(CboDep.getSelectedItem().toString());
                objClsConsulta.ConsultaDependencia();
                while (objClsConsulta.DatosDependencia.next() == true) {
                    String fecha = objClsConsulta.DatosDependencia.getString(1);
                    String documento = objClsConsulta.DatosDependencia.getString(2);
                    String nombre = objClsConsulta.DatosDependencia.getString(3);
                    String apellido = objClsConsulta.DatosDependencia.getString(4);
                    String motivo = objClsConsulta.DatosDependencia.getString(5);
                    Object fila[] = {fecha, documento, nombre, apellido, motivo};
                    tabladatos.addRow(fila);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al llenar la tabla con la consulta dependencia: " + e);
            }
        }

    }

    public void CrearTablaFecha() {
        Object modelodata[][] = new Object[0][0];
        Object modelotitulos[] = {"Documento", "Nombre", "Apellido", "Motivo", "Dependencia"};
        tabladatos = new DefaultTableModel();
        tabladatos = new DefaultTableModel(modelodata, modelotitulos);
        this.TblFecha.setModel(this.tabladatos);
    }

    public void BorrarTablaFecha() {
        while (0 < this.TblFecha.getRowCount()) {
            TblFecha.setModel(new DefaultTableModel());
            this.CrearTablaFecha();

        }

    }

    public void LlenarTablaFecha() {
        if (TxtFec.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Debe digitar o elegir la fecha");
            BtgTipCon.clearSelection();
            TxtFec.grabFocus();
        } else {
            try {
                this.BorrarTablaFecha();
                String dato = new SimpleDateFormat("yyyy-MM-dd").format(TxtFec.getDate());
                objClsConsulta.setFecIng(dato);
                objClsConsulta.ConsultaFecha();
                while (objClsConsulta.DatosFecha.next() == true) {
                    String documento = objClsConsulta.DatosFecha.getString(1);
                    String nombre = objClsConsulta.DatosFecha.getString(2);
                    String apellido = objClsConsulta.DatosFecha.getString(3);
                    String motivo = objClsConsulta.DatosFecha.getString(4);
                    String dependencia = objClsConsulta.DatosFecha.getString(5);
                    Object fila[] = {documento, nombre, apellido, motivo, dependencia};
                    tabladatos.addRow(fila);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al llenar la tabla con la consulta fecha: " + e);
            }
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

        BtgTipCon = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        RbtVis = new javax.swing.JRadioButton();
        RbtDep = new javax.swing.JRadioButton();
        RbtFec = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        TxtDocVis = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblVis = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        CboDep = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblDep = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        TxtFec = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        TblFecha = new javax.swing.JTable();
        BtnReg = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consultar");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Consultar");

        BtgTipCon.add(RbtVis);
        RbtVis.setText("Visitante");
        RbtVis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbtVisActionPerformed(evt);
            }
        });

        BtgTipCon.add(RbtDep);
        RbtDep.setText("Dependencia");
        RbtDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbtDepActionPerformed(evt);
            }
        });

        BtgTipCon.add(RbtFec);
        RbtFec.setText("Fecha");
        RbtFec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbtFecActionPerformed(evt);
            }
        });

        jLabel3.setText("Documento");

        TblVis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Fecha", "Dependencia", "Motivo"
            }
        ));
        TblVis.setEnabled(false);
        jScrollPane1.setViewportView(TblVis);

        jLabel4.setText("Dependencia");

        CboDep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Bienestar", "Biblioteca", "Subdireccion", "Cordinacion", "Gimnasio", "Ambiente de formacion", "Emprendimiento", "Sistemas", "Auditorio" }));

        TblDep.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Fecha", "Documento", "Nombre", "Apellido", "Motivo"
            }
        ));
        TblDep.setEnabled(false);
        jScrollPane2.setViewportView(TblDep);

        jLabel2.setText("Fecha");

        TxtFec.setDateFormatString("yyyy-MM-dd");

        TblFecha.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Documento", "Nombre", "Apellido", "Motivo", "Dependencia"
            }
        ));
        TblFecha.setEnabled(false);
        jScrollPane3.setViewportView(TblFecha);

        BtnReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/deshacer.png"))); // NOI18N
        BtnReg.setToolTipText("Regresar");
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
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtDocVis, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(RbtVis)
                                .addGap(18, 18, 18)
                                .addComponent(RbtDep)
                                .addGap(18, 18, 18)
                                .addComponent(RbtFec))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CboDep, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtFec, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BtnReg)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RbtVis)
                    .addComponent(RbtFec)
                    .addComponent(RbtDep))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TxtDocVis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CboDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(TxtFec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnReg)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RbtVisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbtVisActionPerformed
        this.LLenarTablaVisitante();        // TODO add your handling code here:
    }//GEN-LAST:event_RbtVisActionPerformed

    private void RbtDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbtDepActionPerformed
        this.LlenarTablaDependencia();        // TODO add your handling code here:
    }//GEN-LAST:event_RbtDepActionPerformed

    private void RbtFecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbtFecActionPerformed
        this.LlenarTablaFecha();        // TODO add your handling code here:
    }//GEN-LAST:event_RbtFecActionPerformed

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
            java.util.logging.Logger.getLogger(FrmConsultar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmConsultar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmConsultar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmConsultar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmConsultar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BtgTipCon;
    private javax.swing.JButton BtnReg;
    private javax.swing.JComboBox<String> CboDep;
    private javax.swing.JRadioButton RbtDep;
    private javax.swing.JRadioButton RbtFec;
    private javax.swing.JRadioButton RbtVis;
    private javax.swing.JTable TblDep;
    private javax.swing.JTable TblFecha;
    private javax.swing.JTable TblVis;
    private javax.swing.JTextField TxtDocVis;
    private com.toedter.calendar.JDateChooser TxtFec;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
