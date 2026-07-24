/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ClsAsesor;
import modelo.ClsAsignacionLlamada;

/**
 *
 * @author Juan Porras
 */
public class FrmAsesor extends javax.swing.JFrame {

    /**
     * Creates new form FrmUsuario
     */
    public FrmAsesor() {
        initComponents();
        this.LlenarTabla();
    }
//Declaracion de variables
    ClsAsesor objClsAsesor = new ClsAsesor();

//Contenido tabla
    DefaultTableModel tabladatos;

//Metodos 
    public void capturar() {
        objClsAsesor.setIdUsuAse(CboIdUsuAse.getSelectedItem().toString());
        objClsAsesor.setCodCamAse(Integer.parseInt(CboCodCamAse.getSelectedItem().toString()));
    }

    public void limpiar() {
        TxtConAse.setText(null);
        CboIdUsuAse.setSelectedIndex(0);
        CboCodCamAse.setSelectedIndex(0);
        TxtNomCam.setText(null);
        TxtNomUsu.setText(null);
    }

    public void bloquear() {
        this.limpiar();
        CboIdUsuAse.setEnabled(false);
        CboCodCamAse.setEnabled(false);
        TxtNomCam.setEnabled(false);
        TxtNomUsu.setEnabled(false);
        BtnGua.setEnabled(false);
        BtnAct.setEnabled(false);
    }

    public void desbloquear() {
        CboIdUsuAse.setEnabled(true);
        CboCodCamAse.setEnabled(true);
        TxtNomCam.setEnabled(true);
        TxtNomUsu.setEnabled(true);
        BtnGua.setEnabled(true);
    }

    public void LlenarUsuario() {
        try {
            CboIdUsuAse.removeAllItems();
            CboIdUsuAse.addItem("Seleccione...");
            objClsAsesor.LlenarUsuario();
            while (objClsAsesor.DatosUsuario.next() == true) {
                CboIdUsuAse.addItem(objClsAsesor.DatosUsuario.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar los usuario:  " + e);
        }
    }

    public void LlenarNombreUsuario() {
        try {
            objClsAsesor.BuscarUsuario();
            if (objClsAsesor.DatosBuscarUsuario.next() == true) {
                TxtNomUsu.setText(objClsAsesor.DatosBuscarUsuario.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar el nombre del usuario:  " + e);
        }
    }

    public void LlenarCampana() {
        try {
            CboCodCamAse.removeAllItems();
            CboCodCamAse.addItem("Seleccione...");
            objClsAsesor.LlenarCampana();
            while (objClsAsesor.DatosCampana.next() == true) {
                CboCodCamAse.addItem(objClsAsesor.DatosCampana.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar las campañas:  " + e);
        }
    }

    public void LlenarNombreCampana() {
        try {
            objClsAsesor.BuscarCampana();
            if (objClsAsesor.DatosBuscarCampana.next() == true) {
                TxtNomCam.setText(objClsAsesor.DatosBuscarCampana.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar el nombre de la camapaña:  " + e);
        }
    }

    public boolean validar() {
        if (CboIdUsuAse.getSelectedItem() == "Seleccione...") {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el documento del usuario");
            CboIdUsuAse.grabFocus();
        } else if (CboCodCamAse.getSelectedItem() == "Seleccione...") {
            JOptionPane.showMessageDialog(null, "Debe seleccionar la campaña");
            CboCodCamAse.grabFocus();
        } else {
            return true;
        }
        return false;
    }

    public void nuevo() {
        this.bloquear();
        this.desbloquear();
        this.LlenarUsuario();
        this.LlenarCampana();
        CboIdUsuAse.grabFocus();
    }

    public void CrearTabla() {
        Object modelodata[][] = new Object[0][0];
        Object modelotitulos[] = {"Consecutivo", "Usuario", "Campaña"};
        tabladatos = new DefaultTableModel();
        tabladatos = new DefaultTableModel(modelodata, modelotitulos);
        this.TblAse.setModel(this.tabladatos);
    }

    public void BorrarTabla() {
        while (0 < this.TblAse.getRowCount()) {
            TblAse.setModel(new DefaultTableModel());
            this.CrearTabla();
        }
    }

    public void LlenarTabla() {
        try {
            this.BorrarTabla();
            objClsAsesor.LlenarTabla();
            while (objClsAsesor.DatosTabla.next() == true) {
                String consecutivo = objClsAsesor.DatosTabla.getString(1);
                String usuario = objClsAsesor.DatosTabla.getString(2);
                String campana = objClsAsesor.DatosTabla.getString(3);
                Object fila[] = {consecutivo, usuario, campana};
                tabladatos.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla: " + e);
        }
    }

    public void guardar() {
        if (validar() == true) {
            this.capturar();
            if (objClsAsesor.ValidarGuardar()) {
                JOptionPane.showMessageDialog(null, "Este usuario ya esta asignado a esta campaña");
                return;
            }
            objClsAsesor.guardar();
            this.LlenarTabla();
            this.bloquear();
        }
    }

    public void buscar() {
        try {
            String bus = JOptionPane.showInputDialog("Digite el codigo del asesor que desea buscar");
            objClsAsesor.setConAse(Integer.parseInt(bus));
            objClsAsesor.buscar();
            if (objClsAsesor.DatosBuscar.next() == true) {
                this.desbloquear();
                this.LlenarUsuario();
                this.LlenarCampana();
                TxtConAse.setText(objClsAsesor.DatosBuscar.getString(1));
                CboIdUsuAse.setSelectedItem(objClsAsesor.DatosBuscar.getString(2));
                CboCodCamAse.setSelectedItem(objClsAsesor.DatosBuscar.getString(3));
                BtnAct.setEnabled(true);
                BtnGua.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "El asesor no existe");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al visualizar los datos del asesor: " + e);
        }
    }

    public void actualizar() {
        try {
            if (validar() == true) {
                this.capturar();
                if (objClsAsesor.ValidarActualizar()) {
                    JOptionPane.showMessageDialog(null, "Este usuario ya esta asignado a esta campaña");
                    return;
                }

                objClsAsesor.actualizar();
                this.LlenarTabla();
                this.bloquear();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fallas Tecnicas al actualizar: " + e);
        }
    }

    public void regresar() {
        FrmIndex objFrmIdex = new FrmIndex();
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

        jPanel2 = new javax.swing.JPanel();
        TxtConAse = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        BtnGua = new javax.swing.JButton();
        BtnNue = new javax.swing.JButton();
        BtnBus = new javax.swing.JButton();
        BtnAct = new javax.swing.JButton();
        BtnRegr = new javax.swing.JButton();
        CboCodCamAse = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblAse = new javax.swing.JTable();
        CboIdUsuAse = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        TxtNomUsu = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtNomCam = new javax.swing.JTextArea();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        TxtConAse.setEditable(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Asesor");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 51, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Asesor");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nom.png"))); // NOI18N
        jLabel3.setText("Usuario");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cam.png"))); // NOI18N
        jLabel4.setText("Campaña");

        BtnGua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sav.png"))); // NOI18N
        BtnGua.setToolTipText("Guardar");
        BtnGua.setContentAreaFilled(false);
        BtnGua.setDefaultCapable(false);
        BtnGua.setEnabled(false);
        BtnGua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuaActionPerformed(evt);
            }
        });

        BtnNue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nue.png"))); // NOI18N
        BtnNue.setToolTipText("Nuevo");
        BtnNue.setBorderPainted(false);
        BtnNue.setContentAreaFilled(false);
        BtnNue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNueActionPerformed(evt);
            }
        });

        BtnBus.setBackground(new java.awt.Color(0, 51, 255));
        BtnBus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BtnBus.setForeground(new java.awt.Color(255, 255, 255));
        BtnBus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bus.png"))); // NOI18N
        BtnBus.setToolTipText("Buscar");
        BtnBus.setBorderPainted(false);
        BtnBus.setDefaultCapable(false);
        BtnBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBusActionPerformed(evt);
            }
        });

        BtnAct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/act.png"))); // NOI18N
        BtnAct.setToolTipText("Actualizar");
        BtnAct.setBorderPainted(false);
        BtnAct.setContentAreaFilled(false);
        BtnAct.setEnabled(false);
        BtnAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActActionPerformed(evt);
            }
        });

        BtnRegr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reg.png"))); // NOI18N
        BtnRegr.setText("Regresar");
        BtnRegr.setToolTipText("Regresa al menu principal");
        BtnRegr.setContentAreaFilled(false);
        BtnRegr.setDefaultCapable(false);
        BtnRegr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegrActionPerformed(evt);
            }
        });

        CboCodCamAse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..." }));
        CboCodCamAse.setEnabled(false);
        CboCodCamAse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboCodCamAseActionPerformed(evt);
            }
        });

        TblAse.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Consecutivo", "Usuario", "Campaña"
            }
        ));
        TblAse.setEnabled(false);
        jScrollPane2.setViewportView(TblAse);

        CboIdUsuAse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..." }));
        CboIdUsuAse.setEnabled(false);
        CboIdUsuAse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboIdUsuAseActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 51, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        TxtNomUsu.setEditable(false);
        TxtNomUsu.setEnabled(false);
        TxtNomUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNomUsuActionPerformed(evt);
            }
        });

        TxtNomCam.setEditable(false);
        TxtNomCam.setColumns(20);
        TxtNomCam.setRows(5);
        TxtNomCam.setEnabled(false);
        jScrollPane1.setViewportView(TxtNomCam);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnRegr))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(BtnBus)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CboCodCamAse, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CboIdUsuAse, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TxtNomUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(73, 73, 73))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
            .addGroup(layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(BtnNue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnGua, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnAct)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnBus, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(CboIdUsuAse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNomUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(CboCodCamAse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(BtnGua, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(BtnNue, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(BtnAct))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(BtnRegr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnNueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNueActionPerformed
        this.nuevo();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnNueActionPerformed

    private void BtnGuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuaActionPerformed
        this.guardar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGuaActionPerformed

    private void BtnActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActActionPerformed
        this.actualizar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnActActionPerformed

    private void BtnBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBusActionPerformed
        this.buscar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBusActionPerformed

    private void BtnRegrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegrActionPerformed
        this.regresar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRegrActionPerformed

    private void TxtNomUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNomUsuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNomUsuActionPerformed

    private void CboIdUsuAseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboIdUsuAseActionPerformed
        if (CboIdUsuAse.getSelectedIndex() > 0) {
            objClsAsesor.setIdUsuAse(CboIdUsuAse.getSelectedItem().toString());
            this.LlenarNombreUsuario();
        } else {
            TxtNomUsu.setText(null);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_CboIdUsuAseActionPerformed

    private void CboCodCamAseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboCodCamAseActionPerformed
        if (CboCodCamAse.getSelectedIndex() > 0) {
            objClsAsesor.setCodCamAse(Integer.parseInt(CboCodCamAse.getSelectedItem().toString()));
            this.LlenarNombreCampana();
        } else {
            TxtNomUsu.setText(null);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_CboCodCamAseActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAsesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAsesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAsesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAsesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAsesor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAct;
    private javax.swing.JButton BtnBus;
    private javax.swing.JButton BtnGua;
    private javax.swing.JButton BtnNue;
    private javax.swing.JButton BtnRegr;
    private javax.swing.JComboBox<String> CboCodCamAse;
    private javax.swing.JComboBox<String> CboIdUsuAse;
    private javax.swing.JTable TblAse;
    private javax.swing.JTextField TxtConAse;
    private javax.swing.JTextArea TxtNomCam;
    private javax.swing.JTextField TxtNomUsu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
