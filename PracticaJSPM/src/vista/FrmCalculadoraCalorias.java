/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import javax.swing.JOptionPane;
import modelo.ClsCalculadoraCalorias;

/**
 *
 * @author Juan Porras
 */
public class FrmCalculadoraCalorias extends javax.swing.JFrame {

    /**
     * Creates new form FrmCalculadoraCalorias
     */
    public FrmCalculadoraCalorias() {
        initComponents();
    }
//Declaracion de variables
    ClsCalculadoraCalorias objClsCalculadoraCalorias = new ClsCalculadoraCalorias();

//Metodos
    public void capturar() {
        if (TxtPeso.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el peso");
        } else if (TxtAltura.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar la altura");
        } else if (TxtEdad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar la edad");
        } else {
            objClsCalculadoraCalorias.setPeso(Float.parseFloat(TxtPeso.getText()));
            objClsCalculadoraCalorias.setAltura(Float.parseFloat(TxtAltura.getText()));
            objClsCalculadoraCalorias.setEdad(Byte.parseByte(TxtEdad.getText()));
        }
    }

    public void calcular() {
        if (CboSexo.getSelectedItem() == "Hombre") {
            if (CboActividad.getSelectedItem() == "Persona inactiva") {
                this.capturar();
                objClsCalculadoraCalorias.HombreInactivo();
                TxtConsumoCalorico.setText("" + objClsCalculadoraCalorias.getConsumoCalorico());
            } else if (CboActividad.getSelectedItem() == "Persona con actividad fisica ligera") {
                this.capturar();
                objClsCalculadoraCalorias.HombreActividadLigera();
                TxtConsumoCalorico.setText("" + objClsCalculadoraCalorias.getConsumoCalorico());
            } else if (CboActividad.getSelectedItem() == "Persona que realiza actividad media") {
                this.capturar();
                objClsCalculadoraCalorias.HombreActividadMedia();
                TxtConsumoCalorico.setText("" + objClsCalculadoraCalorias.getConsumoCalorico());
            } else if (CboActividad.getSelectedItem() == "Persona muy activa") {
                this.capturar();
                objClsCalculadoraCalorias.HombreMuyActivo();
                TxtConsumoCalorico.setText("" + objClsCalculadoraCalorias.getConsumoCalorico());
            } else if (CboActividad.getSelectedItem() == "Persona de actividad extrema") {
                this.capturar();
                objClsCalculadoraCalorias.HombreActividadExtrema();
                TxtConsumoCalorico.setText("" + objClsCalculadoraCalorias.getConsumoCalorico());
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un indice de actividad");
            }

        } else if (CboSexo.getSelectedItem() == "Mujer") {

            if (CboActividad.getSelectedItem() == "Persona inactiva") {
                this.capturar();
                objClsCalculadoraCalorias.MujerInactiva();
                TxtConsumoCalorico.setText("" + objClsCalculadoraCalorias.getConsumoCalorico());
            } else if (CboActividad.getSelectedItem() == "Persona con actividad fisica ligera") {
                this.capturar();
                objClsCalculadoraCalorias.MujerActividadligera();
                TxtConsumoCalorico.setText("" + objClsCalculadoraCalorias.getConsumoCalorico());
            } else if (CboActividad.getSelectedItem() == "Persona que realiza actividad media") {
                this.capturar();
                objClsCalculadoraCalorias.MujerActividadMedia();
                TxtConsumoCalorico.setText("" + objClsCalculadoraCalorias.getConsumoCalorico());
            } else if (CboActividad.getSelectedItem() == "Persona muy activa") {
                this.capturar();
                objClsCalculadoraCalorias.MujerMuyActiva();
                TxtConsumoCalorico.setText("" + objClsCalculadoraCalorias.getConsumoCalorico());
            } else if (CboActividad.getSelectedItem() == "Persona de actividad extrema") {
                this.capturar();
                objClsCalculadoraCalorias.MujerActividadExtrema();
                TxtConsumoCalorico.setText("" + objClsCalculadoraCalorias.getConsumoCalorico());
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un indice de actividad");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un sexo");
        }
    }

    public void limpiar() {
        TxtPeso.setText(null);
        TxtAltura.setText(null);
        TxtEdad.setText(null);
        TxtConsumoCalorico.setText(null);
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
        CboSexo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        TxtPeso = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TxtAltura = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TxtEdad = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        CboActividad = new javax.swing.JComboBox<>();
        BtnCalcular = new javax.swing.JButton();
        BtnLimpiar = new javax.swing.JButton();
        BtnRegresar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        TxtConsumoCalorico = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculadora de calorias");
        setResizable(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sexo");

        CboSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Hombre", "Mujer" }));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Peso(Kg)");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Alturan (cm)");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Edad (años)");

        jLabel5.setText("Indice de actividad");

        CboActividad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Persona inactiva", "Persona con actividad fisica ligera", "Persona que realiza actividad media", "Persona muy activa", "Persona de actividad extrema", " ", " " }));

        BtnCalcular.setText("Calcular");
        BtnCalcular.setToolTipText("Hace el calculo del consumo calorico");
        BtnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCalcularActionPerformed(evt);
            }
        });

        BtnLimpiar.setText("Limpiar");
        BtnLimpiar.setToolTipText("Limpia las cajas de texto para digitar nuevos datos");
        BtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarActionPerformed(evt);
            }
        });

        BtnRegresar.setText("Regresar");
        BtnRegresar.setToolTipText("Regresa al menu principal");
        BtnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegresarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Consumo calorico = ");

        TxtConsumoCalorico.setEditable(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Kcal/dia");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CboSexo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TxtPeso)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TxtAltura)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TxtEdad))
                    .addComponent(jLabel5)
                    .addComponent(CboActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnCalcular)
                        .addGap(18, 18, 18)
                        .addComponent(BtnLimpiar)
                        .addGap(18, 18, 18)
                        .addComponent(BtnRegresar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtConsumoCalorico, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CboActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnCalcular)
                    .addComponent(BtnLimpiar)
                    .addComponent(BtnRegresar))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TxtConsumoCalorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCalcularActionPerformed
        this.calcular();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCalcularActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed
this.limpiar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnLimpiarActionPerformed

    private void BtnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegresarActionPerformed
this.regresar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRegresarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmCalculadoraCalorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCalculadoraCalorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCalculadoraCalorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCalculadoraCalorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCalculadoraCalorias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCalcular;
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JButton BtnRegresar;
    private javax.swing.JComboBox<String> CboActividad;
    private javax.swing.JComboBox<String> CboSexo;
    private javax.swing.JTextField TxtAltura;
    private javax.swing.JTextField TxtConsumoCalorico;
    private javax.swing.JTextField TxtEdad;
    private javax.swing.JTextField TxtPeso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
