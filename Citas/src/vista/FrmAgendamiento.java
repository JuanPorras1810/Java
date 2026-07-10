/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.ClsAgendamiento;

/**
 *
 * @author sena cset
 */
public class FrmAgendamiento extends javax.swing.JFrame {

    /**
     * Creates new form FrmProfesional
     */
    public FrmAgendamiento() {
        initComponents();
    }
//Declaracion de variables 
    ClsAgendamiento objClsAgendamiento = new ClsAgendamiento();

//Metodo
    public void capturar() {
        objClsAgendamiento.setCodAgen(Integer.parseInt(TxtCodAgen.getText()));
        objClsAgendamiento.setDocApreAgen(TxtDocApreAgen.getText());
        objClsAgendamiento.setDocProfAgen(TxtDocProfAgen.getText());
        objClsAgendamiento.setFechaAgen(TxtFechaAgen.getText());
        objClsAgendamiento.setHoraAgen(TxtHoraAgen.getText());
        objClsAgendamiento.setMotAgen(TxtMotAgen.getText());
    }

    public void fecha() {
        Date FechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String CadenaFecha = formato.format(FechaActual);
        TxtFechaAgen.setText("" + CadenaFecha);

    }

    public void hora() {
        Date HoraActual = new Date();
        SimpleDateFormat FormatoHora = new SimpleDateFormat("HH:mm");
        String CadenaHora = FormatoHora.format(HoraActual);
        TxtHoraAgen.setText("" + CadenaHora);

    }

    public void limpiar() {
        TxtCodAgen.setText(null);
        TxtDocApreAgen.setText(null);
        TxtDocProfAgen.setText(null);
        TxtFechaAgen.setText(null);
        TxtHoraAgen.setText(null);
        TxtMotAgen.setText(null);
        TxtNomApeApre.setText(null);
        TxtNomApeProf.setText(null);
    }

    public void bloquear() {
        this.limpiar();
        TxtCodAgen.setEnabled(false);
        TxtDocApreAgen.setEnabled(false);
        TxtDocProfAgen.setEnabled(false);
        TxtFechaAgen.setEnabled(false);
        TxtHoraAgen.setEnabled(false);
        TxtMotAgen.setEnabled(false);
        BtnGua.setEnabled(false);
        BtnBusApre.setEnabled(false);
        BtnBusPro.setEnabled(false);
        BtnAct.setEnabled(false);
    }

    public void desbloquear() {
        TxtDocApreAgen.setEnabled(true);
        TxtDocProfAgen.setEnabled(true);
        TxtFechaAgen.setEnabled(true);
        TxtHoraAgen.setEnabled(true);
        TxtMotAgen.setEnabled(true);
        BtnGua.setEnabled(true);
        BtnBusApre.setEnabled(true);
        BtnBusPro.setEnabled(true);
    }

    public boolean validar() {
        if (TxtDocApreAgen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el documento del aprendiz");
            TxtDocApreAgen.grabFocus();
        } else if (TxtDocApreAgen.getText().length() > 11) {
            JOptionPane.showMessageDialog(null, "Error, el documento del aprendiz solo permite maximo 11 caracteres");
            TxtDocApreAgen.grabFocus();
        } else if (TxtDocProfAgen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el documento del profesional");
            TxtDocProfAgen.grabFocus();
        } else if (TxtDocProfAgen.getText().length() > 11) {
            JOptionPane.showMessageDialog(null, "Error, el documento del profesional solo permite maximo 11 caracteres");
            TxtDocProfAgen.grabFocus();
        } else if (TxtFechaAgen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar la fecha");
            TxtFechaAgen.grabFocus();
        } else if (TxtFechaAgen.getText().length() > 10) {
            JOptionPane.showMessageDialog(null, "Error, la fecha solo permite maximo 10 caracteres");
            TxtFechaAgen.grabFocus();
        } else if (TxtHoraAgen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar la hora");
            TxtHoraAgen.grabFocus();
        } else if (TxtHoraAgen.getText().length() > 7) {
            JOptionPane.showMessageDialog(null, "Error, la hora solo permite maximo 7 caracteres");
            TxtHoraAgen.grabFocus();
        } else if (TxtMotAgen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el motivo");
            TxtMotAgen.grabFocus();
        } else if (TxtMotAgen.getText().length() > 300) {
            JOptionPane.showMessageDialog(null, "Error, el motivo solo permite maximo 300 caracteres");
            TxtMotAgen.grabFocus();
        } else {
            return true;
        }
        return false;
    }

    public void ConsecutivoAgendamiento() {
        objClsAgendamiento.ConsecutivoAgendamiento();
        try {
            if (objClsAgendamiento.datosConsecutivo.next()) {
                TxtCodAgen.setText(objClsAgendamiento.datosConsecutivo.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se puede generar el codigo: " + e);
        }
    }

    public void BuscarAprendiz() {
        try {
            String busApre = TxtDocApreAgen.getText();
            objClsAgendamiento.setDocApreAgen(busApre);
            objClsAgendamiento.BuscarAprendiz();
            if (objClsAgendamiento.datosAprendiz.next() == true) {
                String nombre = objClsAgendamiento.datosAprendiz.getString(3);
                String apellido = objClsAgendamiento.datosAprendiz.getString(4);
                TxtNomApeApre.setText(nombre + " " + apellido);

            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el nombre y apellido del aprendiz");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al visualizar el nombre y apellido del aprendiz" + e);
        }
    }

    public void BuscarProfesional() {
        try {
            String busPro = TxtDocProfAgen.getText();
            objClsAgendamiento.setDocProfAgen(busPro);
            objClsAgendamiento.BuscarProfesional();
            if (objClsAgendamiento.datosProfesional.next() == true) {
                String nombre = objClsAgendamiento.datosProfesional.getString(2);
                String apellido = objClsAgendamiento.datosProfesional.getString(3);
                TxtNomApeProf.setText(nombre + " " + apellido);

            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el nombre y apellido del profesional");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al visualizar el nombre y apellido del profesional" + e);
        }
    }

    public void nuevo() {
        this.bloquear();
        this.desbloquear();
        this.ConsecutivoAgendamiento();
        this.fecha();
        this.hora();
    }

    public void guardar() {
        if (validar() == true) {
            this.capturar();
            objClsAgendamiento.guardar();
            this.bloquear();
        }
    }

    public void buscar() {
        try {
            String bus = JOptionPane.showInputDialog("Digite el codigo del agendamiento que desea buscar");
            int codigo = Integer.parseInt(bus);
            objClsAgendamiento.setCodAgen(codigo);
            objClsAgendamiento.Buscar();
            if (objClsAgendamiento.datosAgendamiento.next() == true) {
                TxtCodAgen.setText(objClsAgendamiento.datosAgendamiento.getString(1));
                TxtDocApreAgen.setText(objClsAgendamiento.datosAgendamiento.getString(2));
                TxtDocProfAgen.setText(objClsAgendamiento.datosAgendamiento.getString(3));
                TxtFechaAgen.setText(objClsAgendamiento.datosAgendamiento.getString(4));
                TxtHoraAgen.setText(objClsAgendamiento.datosAgendamiento.getString(5));
                TxtMotAgen.setText(objClsAgendamiento.datosAgendamiento.getString(6));
                this.desbloquear();
                BtnAct.setEnabled(true);
                BtnGua.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "El agendamiento no existe");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al visualizar los datos del agendamiento: " + e);
        }

    }

    public void actualizar() {
        try {
            if (validar() == true) {
                this.capturar();
                objClsAgendamiento.actualizar();
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
        TxtCodAgen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TxtDocProfAgen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TxtFechaAgen = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TxtHoraAgen = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        BtnNue = new javax.swing.JButton();
        BtnGua = new javax.swing.JButton();
        BtnBus = new javax.swing.JButton();
        BtnAct = new javax.swing.JButton();
        BtnReg = new javax.swing.JButton();
        TxtDocApreAgen = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtMotAgen = new javax.swing.JTextArea();
        BtnBusApre = new javax.swing.JButton();
        BtnBusPro = new javax.swing.JButton();
        TxtNomApeApre = new javax.swing.JTextField();
        TxtNomApeProf = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Agendamiento");
        setResizable(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Codigo");

        TxtCodAgen.setEnabled(false);

        jLabel2.setText("Documento aprendiz");

        jLabel3.setText("Documento profesional");

        TxtDocProfAgen.setEnabled(false);

        jLabel4.setText("Fecha");

        TxtFechaAgen.setEditable(false);
        TxtFechaAgen.setEnabled(false);

        jLabel5.setText("Hora");

        TxtHoraAgen.setEditable(false);
        TxtHoraAgen.setEnabled(false);

        jLabel6.setText("Motivo");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Agendamiento");

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
        BtnGua.setToolTipText("Guarda la informacion ");
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

        TxtDocApreAgen.setEnabled(false);

        TxtMotAgen.setColumns(20);
        TxtMotAgen.setRows(5);
        TxtMotAgen.setEnabled(false);
        jScrollPane1.setViewportView(TxtMotAgen);

        BtnBusApre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        BtnBusApre.setToolTipText("Busca el nombre y apellido");
        BtnBusApre.setBorderPainted(false);
        BtnBusApre.setContentAreaFilled(false);
        BtnBusApre.setEnabled(false);
        BtnBusApre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBusApreActionPerformed(evt);
            }
        });

        BtnBusPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        BtnBusPro.setToolTipText("Busca el nombre y apellido");
        BtnBusPro.setBorderPainted(false);
        BtnBusPro.setContentAreaFilled(false);
        BtnBusPro.setEnabled(false);
        BtnBusPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBusProActionPerformed(evt);
            }
        });

        TxtNomApeApre.setEnabled(false);

        TxtNomApeProf.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TxtHoraAgen))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TxtFechaAgen, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtCodAgen, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtDocApreAgen, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BtnBusApre, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(TxtNomApeApre, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtDocProfAgen, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BtnBusPro, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TxtNomApeProf, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(BtnNue, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnGua, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnBus, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnAct, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnReg, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TxtCodAgen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(TxtDocApreAgen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxtNomApeApre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BtnBusApre, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnBusPro)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TxtDocProfAgen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(TxtNomApeProf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TxtFechaAgen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TxtHoraAgen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnNue)
                    .addComponent(BtnGua)
                    .addComponent(BtnBus)
                    .addComponent(BtnAct)
                    .addComponent(BtnReg))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnNueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNueActionPerformed
        this.nuevo();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnNueActionPerformed

    private void BtnBusApreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBusApreActionPerformed
        this.BuscarAprendiz();       // TODO add your handling code here:
    }//GEN-LAST:event_BtnBusApreActionPerformed

    private void BtnBusProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBusProActionPerformed
        this.BuscarProfesional();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBusProActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAgendamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAgendamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAgendamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAgendamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAgendamiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAct;
    private javax.swing.JButton BtnBus;
    private javax.swing.JButton BtnBusApre;
    private javax.swing.JButton BtnBusPro;
    private javax.swing.JButton BtnGua;
    private javax.swing.JButton BtnNue;
    private javax.swing.JButton BtnReg;
    private javax.swing.JTextField TxtCodAgen;
    private javax.swing.JTextField TxtDocApreAgen;
    private javax.swing.JTextField TxtDocProfAgen;
    private javax.swing.JTextField TxtFechaAgen;
    private javax.swing.JTextField TxtHoraAgen;
    private javax.swing.JTextArea TxtMotAgen;
    private javax.swing.JTextField TxtNomApeApre;
    private javax.swing.JTextField TxtNomApeProf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
