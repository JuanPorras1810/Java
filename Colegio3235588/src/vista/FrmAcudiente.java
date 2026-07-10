/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ClsAcudiente;

/**
 *
 * @author Juan Porras
 */
public class FrmAcudiente extends javax.swing.JFrame {

    /**
     * Creates new form FrmEstudiante
     */
    public FrmAcudiente() {
        initComponents();
    }
//Declaracion de variables
    ClsAcudiente objClsAcudiente = new ClsAcudiente();

//Contenido tabla
    DefaultTableModel tabladatos;

//Metodos
    public void capturar() {
        objClsAcudiente.setDocAcu(TxtDocAcu.getText());
        objClsAcudiente.setNomAcu(TxtNomAcu.getText());
        objClsAcudiente.setApeAcu(TxtApeAcu.getText());
        objClsAcudiente.setDirAcu(TxtDirAcu.getText());
        objClsAcudiente.setTelAcu(TxtTelAcu.getText());
        objClsAcudiente.setEmaAcu(TxtEmaAcu.getText());
        objClsAcudiente.setDocEstAcuxEst(CboDoc.getSelectedItem().toString());
    }

    public void limpiar() {
        TxtDocAcu.setText(null);
        TxtNomAcu.setText(null);
        TxtApeAcu.setText(null);
        TxtDirAcu.setText(null);
        TxtTelAcu.setText(null);
        TxtEmaAcu.setText(null);
    }

    public void bloquear() {
        this.limpiar();
        TxtDocAcu.setEnabled(false);
        TxtNomAcu.setEnabled(false);
        TxtApeAcu.setEnabled(false);
        TxtDirAcu.setEnabled(false);
        TxtTelAcu.setEnabled(false);
        TxtEmaAcu.setEnabled(false);
        TxtNomApeAcu.setEnabled(false);
        BtnGua.setEnabled(false);
        BtnAct.setEnabled(false);
        BtnAgrEst.setEnabled(false);
        CboDoc.setEnabled(false);
    }

    public void desbloquear() {
        TxtDocAcu.setEnabled(true);
        TxtNomAcu.setEnabled(true);
        TxtApeAcu.setEnabled(true);
        TxtDirAcu.setEnabled(true);
        TxtTelAcu.setEnabled(true);
        TxtEmaAcu.setEnabled(true);
        BtnGua.setEnabled(true);

    }

    public boolean validar() {
        if (TxtDocAcu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el documento");
            TxtDocAcu.grabFocus();
        } else if (TxtDocAcu.getText().length() > 11) {
            JOptionPane.showMessageDialog(null, "Error, el documento solo permite un maximo de 11 caracteres");
            TxtDocAcu.grabFocus();
        } else if (TxtNomAcu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el nombre");
            TxtNomAcu.grabFocus();
        } else if (TxtNomAcu.getText().length() > 30) {
            JOptionPane.showMessageDialog(null, "Error, el nombre solo permite un maximo de 30 caracteres");
            TxtNomAcu.grabFocus();
        } else if (TxtApeAcu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el apellido");
            TxtApeAcu.grabFocus();
        } else if (TxtApeAcu.getText().length() > 30) {
            JOptionPane.showMessageDialog(null, "Error, el apellido solo permite un maximo de 30 caracteres");
            TxtApeAcu.grabFocus();
        } else if (TxtDirAcu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar la direccion");
            TxtDirAcu.grabFocus();
        } else if (TxtDirAcu.getText().length() > 60) {
            JOptionPane.showMessageDialog(null, "Error, la direccion solo permite un maximo de 60 caracteres");
            TxtDirAcu.grabFocus();
        } else if (TxtTelAcu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el numero de telefono");
            TxtTelAcu.grabFocus();
        } else if (TxtTelAcu.getText().length() > 11) {
            JOptionPane.showMessageDialog(null, "Error, el telefono solo permite un maximo de 11 caracteres");
            TxtTelAcu.grabFocus();
        } else if (TxtEmaAcu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el email");
            TxtEmaAcu.grabFocus();
        } else if (TxtEmaAcu.getText().length() > 30) {
            JOptionPane.showMessageDialog(null, "Error, el email solo permite un maximo de 30 caracteres");
            TxtEmaAcu.grabFocus();
        } else {
            return true;

        }
        return false;
    }

    public void guardar() {
        if (validar() == true) {
            this.capturar();
            objClsAcudiente.guardar();
            this.bloquear();

        }
    }

    public void LlenarComboDocEstudiante() {
        objClsAcudiente.LlenarComboDocEstudiante();
        try {
            while (objClsAcudiente.datosEstudiante.next() == true) {
                CboDoc.addItem(objClsAcudiente.datosEstudiante.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar el codigo" + e);
        }

    }

    public void CrearTabla() {
        Object modelodata[][] = new Object[0][0];
        Object modelotitulos[] = {"Documento", "Nombre", "Apellido", "Telefono"};
        tabladatos = new DefaultTableModel();
        tabladatos = new DefaultTableModel(modelodata, modelotitulos);
        this.TblEst.setModel(this.tabladatos);
    }

    public void BorrarTabla() {
        while (0 < this.TblEst.getRowCount()) {
            TblEst.setModel(new DefaultTableModel());
            this.CrearTabla();
        }
    }

    public void LlenarTabla() {
        try {
            this.BorrarTabla();
            objClsAcudiente.setDocAcu(TxtDocAcu.getText());
            objClsAcudiente.LlenarTabla();
            while (objClsAcudiente.datosTabla.next() == true) {
                String Documento = objClsAcudiente.datosTabla.getString(1);
                String Nombre = objClsAcudiente.datosTabla.getString(2);
                String Apellido = objClsAcudiente.datosTabla.getString(3);
                String Telefono = objClsAcudiente.datosTabla.getString(5);
                Object fila[] = {Documento, Nombre, Apellido, Telefono};
                tabladatos.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla" + e);
        }

    }

    public void buscar() {
        try {
            String bus = JOptionPane.showInputDialog("Digite el documento que desea buscar");
            objClsAcudiente.setDocAcu(bus);
            objClsAcudiente.buscar();
            if (objClsAcudiente.datosAcudiente.next() == true) {
                TxtDocAcu.setText(objClsAcudiente.datosAcudiente.getString(1));
                TxtNomAcu.setText(objClsAcudiente.datosAcudiente.getString(2));
                TxtApeAcu.setText(objClsAcudiente.datosAcudiente.getString(3));
                TxtDirAcu.setText(objClsAcudiente.datosAcudiente.getString(4));
                TxtTelAcu.setText(objClsAcudiente.datosAcudiente.getString(5));
                TxtEmaAcu.setText(objClsAcudiente.datosAcudiente.getString(6));
                this.desbloquear();
                this.LlenarComboDocEstudiante();
                this.LlenarTabla();
                TxtDocAcu.setEnabled(false);
                BtnGua.setEnabled(false);
                BtnAct.setEnabled(true);
                BtnAgrEst.setEnabled(true);
                CboDoc.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "El acudiente no existe");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al visualizar los datos del acudiente" + e);
        }
    }

    public void actualizar() {
        try {
            if (validar() == true) {
                this.capturar();
                objClsAcudiente.actualizar();
                this.bloquear();
                this.BorrarTabla();
                TxtNomApeAcu.setText(null);
                CboDoc.setSelectedIndex(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fallas Tecnicas" + e);
        }
    }

    public boolean validarEstudiante() {
        if (CboDoc.getSelectedItem() == ("Seleccione...")) {
            JOptionPane.showMessageDialog(null, "Debe elegir el documento del estudiante");
            CboDoc.grabFocus();
        } else {
            return true;
        }
        return false;
    }

    public void AgregarEstudiante() {
        if (validarEstudiante() == true) {
            this.capturar();
            objClsAcudiente.AgregarEstudiante();
            this.LlenarTabla();
            TxtNomApeAcu.setText(null);
            CboDoc.setSelectedIndex(0);

        }
    }

    public void NombreApellidoEstudiante() {
        if (CboDoc.getSelectedItem() == ("Seleccione...")) {
            TxtNomApeAcu.setText(null);
        } else {
            try {
                String bus = CboDoc.getSelectedItem().toString();
                objClsAcudiente.setDocEstAcuxEst(bus);
                objClsAcudiente.NombreApellidoEstudiante();
                if (objClsAcudiente.datosNomApeEst.next() == true) {
                    String nombre = objClsAcudiente.datosNomApeEst.getString(2);
                    String apellido = objClsAcudiente.datosNomApeEst.getString(3);
                    TxtNomApeAcu.setText(nombre + " " + apellido);

                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro el nombre del estudiante");
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al visualizar los datos del estudiante" + e);
            }
        }
    }

    public void nuevo() {
        this.bloquear();
        this.desbloquear();
        TxtDocAcu.grabFocus();
        this.limpiar();
        this.BorrarTabla();

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
        TxtDocAcu = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TxtNomAcu = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TxtApeAcu = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TxtDirAcu = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TxtTelAcu = new javax.swing.JTextField();
        BtnNue = new javax.swing.JButton();
        BtnGua = new javax.swing.JButton();
        BtnBus = new javax.swing.JButton();
        BtnMod = new javax.swing.JButton();
        BtnAct = new javax.swing.JButton();
        BtnImp = new javax.swing.JButton();
        BtnReg = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        TxtEmaAcu = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblEst = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        CboDoc = new javax.swing.JComboBox<>();
        BtnAgrEst = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        TxtNomApeAcu = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Acudiente");
        setResizable(false);

        jLabel1.setText("Documento");

        TxtDocAcu.setEnabled(false);
        TxtDocAcu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtDocAcuActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nombre");

        TxtNomAcu.setEnabled(false);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Apellido");

        TxtApeAcu.setEnabled(false);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Direccion");

        TxtDirAcu.setEnabled(false);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Telefono");

        TxtTelAcu.setEnabled(false);

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
        jLabel6.setText("Acudiente");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Email");

        TxtEmaAcu.setEnabled(false);

        TblEst.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Documento", "Nombre", "Apellido", "Telefono"
            }
        ));
        TblEst.setEnabled(false);
        jScrollPane1.setViewportView(TblEst);

        jLabel8.setText("Documento");

        CboDoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..." }));
        CboDoc.setEnabled(false);
        CboDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboDocActionPerformed(evt);
            }
        });

        BtnAgrEst.setText("Agregar estudiante");
        BtnAgrEst.setToolTipText("Guarda los estudiantes que tiene el acudiente en la base de datos");
        BtnAgrEst.setEnabled(false);
        BtnAgrEst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgrEstActionPerformed(evt);
            }
        });

        jLabel9.setText("Nombre y apellido");

        TxtNomApeAcu.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TxtDocAcu)
                    .addComponent(TxtNomAcu)
                    .addComponent(TxtDirAcu)
                    .addComponent(TxtEmaAcu, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtApeAcu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtTelAcu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(109, 109, 109))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BtnNue)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnGua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnBus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnMod)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnAct)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnImp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnReg))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(BtnAgrEst)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CboDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtNomApeAcu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(TxtDocAcu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel6)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxtNomAcu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(TxtApeAcu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TxtDirAcu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(TxtTelAcu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TxtEmaAcu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnNue)
                    .addComponent(BtnGua)
                    .addComponent(BtnBus)
                    .addComponent(BtnMod)
                    .addComponent(BtnAct)
                    .addComponent(BtnImp)
                    .addComponent(BtnReg))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnAgrEst)
                    .addComponent(CboDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(TxtNomApeAcu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtDocAcuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtDocAcuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtDocAcuActionPerformed

    private void BtnRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegActionPerformed
        this.regresar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRegActionPerformed

    private void BtnGuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuaActionPerformed
        this.guardar();// TODO add your handling code here:
    }//GEN-LAST:event_BtnGuaActionPerformed

    private void BtnNueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNueActionPerformed
        this.nuevo();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnNueActionPerformed

    private void BtnAgrEstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgrEstActionPerformed
        this.AgregarEstudiante();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAgrEstActionPerformed

    private void BtnBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBusActionPerformed
        this.buscar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBusActionPerformed

    private void BtnActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActActionPerformed
        this.actualizar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnActActionPerformed

    private void CboDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboDocActionPerformed
        this.NombreApellidoEstudiante();        // TODO add your handling code here:
    }//GEN-LAST:event_CboDocActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAcudiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAcudiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAcudiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAcudiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAcudiente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAct;
    private javax.swing.JButton BtnAgrEst;
    private javax.swing.JButton BtnBus;
    private javax.swing.JButton BtnGua;
    private javax.swing.JButton BtnImp;
    private javax.swing.JButton BtnMod;
    private javax.swing.JButton BtnNue;
    private javax.swing.JButton BtnReg;
    private javax.swing.JComboBox<String> CboDoc;
    private javax.swing.JTable TblEst;
    private javax.swing.JTextField TxtApeAcu;
    private javax.swing.JTextField TxtDirAcu;
    private javax.swing.JTextField TxtDocAcu;
    private javax.swing.JTextField TxtEmaAcu;
    private javax.swing.JTextField TxtNomAcu;
    private javax.swing.JTextField TxtNomApeAcu;
    private javax.swing.JTextField TxtTelAcu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
