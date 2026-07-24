/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.ClsUsuario;

/**
 *
 * @author juanp
 */
public class FrmUsuario extends javax.swing.JFrame {

    /**
     * Creates new form FrmUsuario
     */
    public FrmUsuario() {
        initComponents();
    }
//Declaracion de variables
    ClsUsuario objClsUsuario = new ClsUsuario();

//Metodos
    public void capturar() {
        objClsUsuario.setIdUsu(TxtIdUsu.getText());
        objClsUsuario.setTipDocUsu(CboTipDocUsu.getSelectedItem().toString());
        objClsUsuario.setNomUsu(TxtNomUsu.getText());
        objClsUsuario.setEmaUsu(TxtEmaUsu.getText());
        objClsUsuario.setDirUsu(TxtDirUsu.getText());
        objClsUsuario.setTelUsu(TxtTelUsu.getText());
        objClsUsuario.setTelAltUsu(TxtTelAltUsu.getText());
        if (RbnRolUsuSup.isSelected()) {
            objClsUsuario.setRolUsu("Supervisor");
        } else if (RbnRolUsuAge.isSelected()) {
            objClsUsuario.setRolUsu("Agente");
        }
        objClsUsuario.setConUsu(TxtConUsu.getText());
    }

    public void limpiar() {
        TxtIdUsu.setText(null);
        CboTipDocUsu.setSelectedIndex(0);
        TxtNomUsu.setText(null);
        TxtEmaUsu.setText(null);
        TxtDirUsu.setText(null);
        TxtTelUsu.setText(null);
        TxtTelAltUsu.setText(null);
        BtgRol.clearSelection();
        TxtConUsu.setText(null);
    }

    public void bloquear() {
        this.limpiar();
        TxtIdUsu.setEnabled(false);
        CboTipDocUsu.setEnabled(false);
        TxtNomUsu.setEnabled(false);
        TxtEmaUsu.setEnabled(false);
        TxtDirUsu.setEnabled(false);
        TxtTelUsu.setEnabled(false);
        TxtTelAltUsu.setEnabled(false);
        TxtConUsu.setEnabled(false);
        RbnRolUsuAge.setEnabled(false);
        RbnRolUsuSup.setEnabled(false);
        BtnReg.setEnabled(false);
        BtnAct.setEnabled(false);
    }

    public void desbloquear() {
        TxtIdUsu.setEnabled(true);
        CboTipDocUsu.setEnabled(true);
        TxtNomUsu.setEnabled(true);
        TxtEmaUsu.setEnabled(true);
        TxtDirUsu.setEnabled(true);
        TxtTelUsu.setEnabled(true);
        TxtTelAltUsu.setEnabled(true);
        TxtConUsu.setEnabled(true);
        RbnRolUsuAge.setEnabled(true);
        RbnRolUsuSup.setEnabled(true);
        BtnReg.setEnabled(true);
    }

    public boolean validar() {
        if (TxtIdUsu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el documento");
            TxtIdUsu.grabFocus();
        } else if (TxtIdUsu.getText().length() > 11) {
            JOptionPane.showMessageDialog(null, "Error, el documento solo permite un maximo de 11 caracteres");
            TxtIdUsu.grabFocus();
        } else if (CboTipDocUsu.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe elegir el tipo de documento");
            CboTipDocUsu.grabFocus();
        } else if (TxtNomUsu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el nombre");
            TxtNomUsu.grabFocus();
        } else if (TxtNomUsu.getText().length() > 60) {
            JOptionPane.showMessageDialog(null, "Error, el nombre solo permite un maximo de 60 caracteres");
            TxtNomUsu.grabFocus();
        } else if (TxtEmaUsu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el email");
            TxtEmaUsu.grabFocus();
        } else if (TxtEmaUsu.getText().length() > 100) {
            JOptionPane.showMessageDialog(null, "Error, el email solo permite un maximo de 100 caracteres");
            TxtEmaUsu.grabFocus();
        } else if (TxtDirUsu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar la direccion");
            TxtDirUsu.grabFocus();
        } else if (TxtDirUsu.getText().length() > 60) {
            JOptionPane.showMessageDialog(null, "Error, la direccion solo permite un maximo de 60 caracteres");
            TxtDirUsu.grabFocus();
        } else if (TxtTelUsu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el telefono");
            TxtTelUsu.grabFocus();
        } else if (TxtTelUsu.getText().length() > 10) {
            JOptionPane.showMessageDialog(null, "Error, el telefono solo permite un maximo de 10 caracteres");
            TxtTelUsu.grabFocus();
        } else if (TxtTelAltUsu.getText().length() > 10) {
            JOptionPane.showMessageDialog(null, "Error, el telefono alternativo solo permite un maximo de 10 caracteres");
            TxtTelAltUsu.grabFocus();
        } else if (BtgRol.getSelection() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un rol");
            RbnRolUsuSup.grabFocus();
        } else if (TxtConUsu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar la contraseña");
            TxtConUsu.grabFocus();
        } else if (TxtConUsu.getText().length() > 60) {
            JOptionPane.showMessageDialog(null, "Error, la contraseña solo permite un maximo de 60 caracteres");
            TxtConUsu.grabFocus();
        } else {
            return true;
        }
        return false;
    }

    public void nuevo() {
        this.bloquear();
        this.desbloquear();
        TxtIdUsu.grabFocus();
    }

    public void registrar() {
        if (validar() == true) {
            this.capturar();
            objClsUsuario.registrar();
            this.bloquear();
        }
    }

    public void buscar() {
        try {
            String bus = JOptionPane.showInputDialog("Digite el documento del usuario que desea buscar");
            objClsUsuario.setIdUsu(bus);
            objClsUsuario.buscar();
            if (objClsUsuario.DatosBuscar.next() == true) {
                TxtIdUsu.setText(objClsUsuario.DatosBuscar.getString(1));
                CboTipDocUsu.setSelectedItem(objClsUsuario.DatosBuscar.getString(2));
                TxtNomUsu.setText(objClsUsuario.DatosBuscar.getString(3));
                TxtEmaUsu.setText(objClsUsuario.DatosBuscar.getString(4));
                TxtDirUsu.setText(objClsUsuario.DatosBuscar.getString(5));
                TxtTelUsu.setText(objClsUsuario.DatosBuscar.getString(6));
                TxtTelAltUsu.setText(objClsUsuario.DatosBuscar.getString(7));
                String rol = objClsUsuario.DatosBuscar.getString(8);
                if (rol.equals("Supervisor")) {
                    RbnRolUsuSup.setSelected(true);
                } else if (rol.equals("Agente")) {
                    RbnRolUsuAge.setSelected(true);
                }
                TxtConUsu.setText(objClsUsuario.DatosBuscar.getString(9));
                this.desbloquear();
                TxtIdUsu.setEnabled(false);
                BtnAct.setEnabled(true);
                BtnReg.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "El usuario no existe");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al visualizar los datos de usuario: " + e);
        }
    }

    public void actualizar() {
        try {
            if (validar() == true) {
                this.capturar();
                objClsUsuario.actualizar();
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

        BtgRol = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        TxtIdUsu = new javax.swing.JTextField();
        CboTipDocUsu = new javax.swing.JComboBox<>();
        TxtNomUsu = new javax.swing.JTextField();
        TxtEmaUsu = new javax.swing.JTextField();
        TxtDirUsu = new javax.swing.JTextField();
        TxtTelUsu = new javax.swing.JTextField();
        TxtTelAltUsu = new javax.swing.JTextField();
        RbnRolUsuSup = new javax.swing.JRadioButton();
        RbnRolUsuAge = new javax.swing.JRadioButton();
        BtnReg = new javax.swing.JButton();
        BtnNue = new javax.swing.JButton();
        BtnBus = new javax.swing.JButton();
        BtnAct = new javax.swing.JButton();
        BtnRegr = new javax.swing.JButton();
        TxtConUsu = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Usuario");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 51, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Usuario");

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

        jPanel2.setBackground(new java.awt.Color(0, 51, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/doc.png"))); // NOI18N
        jLabel2.setText("Documento");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/TipDoc.png"))); // NOI18N
        jLabel3.setText("Tipo de documento");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nom.png"))); // NOI18N
        jLabel4.setText("Nombre");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ema.png"))); // NOI18N
        jLabel5.setText("Email");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dir.png"))); // NOI18N
        jLabel6.setText("Direccion");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tel.png"))); // NOI18N
        jLabel7.setText("Telefono");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tel.png"))); // NOI18N
        jLabel8.setText("Telefono alternativo");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/rol.png"))); // NOI18N
        jLabel9.setText("Rol");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cont.png"))); // NOI18N
        jLabel10.setText("Contraseña");

        TxtIdUsu.setEnabled(false);

        CboTipDocUsu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Cedula de ciudadania", "Tarjeta de identidad", "Cedula de extranjeria", "PEP", "Permiso por proteccion temporal" }));
        CboTipDocUsu.setEnabled(false);

        TxtNomUsu.setEnabled(false);

        TxtEmaUsu.setEnabled(false);

        TxtDirUsu.setEnabled(false);

        TxtTelUsu.setEnabled(false);

        TxtTelAltUsu.setEnabled(false);

        BtgRol.add(RbnRolUsuSup);
        RbnRolUsuSup.setText("Supervisor");
        RbnRolUsuSup.setEnabled(false);

        BtgRol.add(RbnRolUsuAge);
        RbnRolUsuAge.setText("Agente");
        RbnRolUsuAge.setEnabled(false);

        BtnReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usu.png"))); // NOI18N
        BtnReg.setToolTipText("Registrar");
        BtnReg.setContentAreaFilled(false);
        BtnReg.setDefaultCapable(false);
        BtnReg.setEnabled(false);
        BtnReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnNue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnReg, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnAct)
                .addGap(153, 153, 153))
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(TxtNomUsu))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(TxtEmaUsu))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(TxtDirUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(TxtTelUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(RbnRolUsuSup)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(RbnRolUsuAge))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtConUsu))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtTelAltUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtIdUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnBus))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CboTipDocUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 132, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(BtnRegr))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(TxtIdUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(BtnBus, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(CboTipDocUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TxtNomUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TxtEmaUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TxtDirUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TxtTelUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(TxtTelAltUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(RbnRolUsuSup)
                    .addComponent(RbnRolUsuAge))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(TxtConUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(BtnReg, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(BtnNue, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(BtnAct))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(BtnRegr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBusActionPerformed
        this.buscar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBusActionPerformed

    private void BtnRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegActionPerformed
        this.registrar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRegActionPerformed

    private void BtnNueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNueActionPerformed
        this.nuevo();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnNueActionPerformed

    private void BtnActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActActionPerformed
        this.actualizar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnActActionPerformed

    private void BtnRegrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegrActionPerformed
        this.regresar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRegrActionPerformed

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
            java.util.logging.Logger.getLogger(FrmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BtgRol;
    private javax.swing.JButton BtnAct;
    private javax.swing.JButton BtnBus;
    private javax.swing.JButton BtnNue;
    private javax.swing.JButton BtnReg;
    private javax.swing.JButton BtnRegr;
    private javax.swing.JComboBox<String> CboTipDocUsu;
    private javax.swing.JRadioButton RbnRolUsuAge;
    private javax.swing.JRadioButton RbnRolUsuSup;
    private javax.swing.JPasswordField TxtConUsu;
    private javax.swing.JTextField TxtDirUsu;
    private javax.swing.JTextField TxtEmaUsu;
    private javax.swing.JTextField TxtIdUsu;
    private javax.swing.JTextField TxtNomUsu;
    private javax.swing.JTextField TxtTelAltUsu;
    private javax.swing.JTextField TxtTelUsu;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
