/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import javax.swing.JOptionPane;
import modelo.ClsAreaFiguras;

/**
 *
 * @author Juan Porras
 */
public class FrmAreaFiguras extends javax.swing.JFrame {

    /**
     * Creates new form FrmAreaFiguras
     */
    public FrmAreaFiguras() {
        initComponents();
    }
//Declaracion de variables
    ClsAreaFiguras objClsAreaFiguras = new ClsAreaFiguras();

//Metodos
    public void restaurar() {
        TxtDato1.setVisible(true);
        TxtDato2.setVisible(true);
        TxtDato3.setVisible(true);
        jLabel1.setVisible(true);
        jLabel2.setVisible(true);
        jLabel3.setVisible(true);

    }

    public void AreaFiguras() {
        if (CboFiguras.getSelectedItem() == "Cuadrado") {
            this.restaurar();
            TxtDato2.setVisible(false);
            TxtDato3.setVisible(false);
            jLabel2.setVisible(false);
            jLabel3.setVisible(false);
            jLabel1.setText("Lado");
            TxtDato1.grabFocus();
        } else if (CboFiguras.getSelectedItem() == "Triangulo") {
            this.restaurar();
            TxtDato3.setVisible(false);
            jLabel3.setVisible(false);
            jLabel1.setText("Base");
            jLabel2.setText("Altura");
            TxtDato1.grabFocus();
            TxtDato2.grabFocus();

        } else if (CboFiguras.getSelectedItem() == "Romboide") {
            this.restaurar();
            TxtDato3.setVisible(false);
            jLabel3.setVisible(false);
            jLabel1.setText("Base");
            jLabel2.setText("Altura");
            TxtDato1.grabFocus();
            TxtDato2.grabFocus();
        } else if (CboFiguras.getSelectedItem() == "Pentagono") {
            this.restaurar();
            TxtDato3.setVisible(false);
            jLabel3.setVisible(false);
            jLabel1.setText("Perimetro");
            jLabel2.setText("Apotema");
            TxtDato1.grabFocus();
            TxtDato2.grabFocus();
        } else if (CboFiguras.getSelectedItem() == "Rectangulo") {
            this.restaurar();
            TxtDato3.setVisible(false);
            jLabel3.setVisible(false);
            jLabel1.setText("Base");
            jLabel2.setText("Altura");
            TxtDato1.grabFocus();
            TxtDato2.grabFocus();
        } else if (CboFiguras.getSelectedItem() == "Rombo") {
            this.restaurar();
            TxtDato3.setVisible(false);
            jLabel3.setVisible(false);
            jLabel1.setText("Diagonal mayor");
            jLabel2.setText("Diagonal menor");
            TxtDato1.grabFocus();
            TxtDato2.grabFocus();
        } else if (CboFiguras.getSelectedItem() == "Circulo") {
            this.restaurar();
            TxtDato1.setVisible(false);
            TxtDato2.setVisible(false);
            jLabel1.setVisible(false);
            jLabel2.setVisible(false);
            jLabel3.setText("Radio");
            TxtDato3.grabFocus();

        } else if (CboFiguras.getSelectedItem() == "Trapecio") {
            this.restaurar();
            jLabel1.setText("Base mayor");
            jLabel2.setText("Altura");
            jLabel3.setText("Base menor");
            TxtDato1.grabFocus();
            TxtDato2.grabFocus();
            TxtDato3.grabFocus();
        }

    }

    public void calcular() {
        this.AreaFiguras();
        if (CboFiguras.getSelectedItem() == "Cuadrado") {
            if (TxtDato1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe digitar el Lado");
            } else {
                objClsAreaFiguras.setDato1(Float.parseFloat(TxtDato1.getText()));
                objClsAreaFiguras.cuadrado();
                TxtArea.setText("" + objClsAreaFiguras.getArea());
            }
        } else if (CboFiguras.getSelectedItem() == "Triangulo") {
            if (TxtDato1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe digitar la base");
            } else if (TxtDato2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe digitar la altura");
            } else {
                objClsAreaFiguras.setDato1(Float.parseFloat(TxtDato1.getText()));
                objClsAreaFiguras.setDato2(Float.parseFloat(TxtDato2.getText()));
                objClsAreaFiguras.triangulo();
                TxtArea.setText("" + objClsAreaFiguras.getArea());
            }
        } else if (CboFiguras.getSelectedItem() == "Romboide") {
            if (TxtDato1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe digitar la base");
            } else if (TxtDato2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe digitar la altura");
            } else {
                objClsAreaFiguras.setDato1(Float.parseFloat(TxtDato1.getText()));
                objClsAreaFiguras.setDato2(Float.parseFloat(TxtDato2.getText()));
                objClsAreaFiguras.romboide();
                TxtArea.setText("" + objClsAreaFiguras.getArea());
            }
        } else if (CboFiguras.getSelectedItem() == "Pentagono") {
            if (TxtDato1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe digitar el perimetro");
            } else if (TxtDato2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe digiar la apotema");
            } else {
                objClsAreaFiguras.setDato1(Float.parseFloat(TxtDato1.getText()));
                objClsAreaFiguras.setDato2(Float.parseFloat(TxtDato2.getText()));
                objClsAreaFiguras.pentagono();
                TxtArea.setText("" + objClsAreaFiguras.getArea());
            }
        } else if (CboFiguras.getSelectedItem() == "Rectangulo") {
            if (TxtDato1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe digitar la base");
            } else if (TxtDato2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe digiar la altura");
            } else {
                objClsAreaFiguras.setDato1(Float.parseFloat(TxtDato1.getText()));
                objClsAreaFiguras.setDato2(Float.parseFloat(TxtDato2.getText()));
                objClsAreaFiguras.rectangulo();
                TxtArea.setText("" + objClsAreaFiguras.getArea());
            }
        } else if (CboFiguras.getSelectedItem() == "Rombo") {
            if (TxtDato1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe digitar la diagonal mayor");
            } else if (TxtDato2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe digiar la diagonal menor");
            } else {
                objClsAreaFiguras.setDato1(Float.parseFloat(TxtDato1.getText()));
                objClsAreaFiguras.setDato2(Float.parseFloat(TxtDato2.getText()));
                objClsAreaFiguras.rombo();
                TxtArea.setText("" + objClsAreaFiguras.getArea());
            }
        } else if (CboFiguras.getSelectedItem() == "Circulo") {
            if (TxtDato3.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe digiar el radio");
            } else {
                objClsAreaFiguras.setDato3(Float.parseFloat(TxtDato3.getText()));
                objClsAreaFiguras.circulo();
                TxtArea.setText("" + objClsAreaFiguras.getArea());
            }
        } else if (CboFiguras.getSelectedItem() == "Trapecio") {
            if (TxtDato1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe digiar la base mayor");
            } else if (TxtDato2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe digiar la altura");
            } else if (TxtDato3.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe digiar la base menor");
            } else {
                objClsAreaFiguras.setDato1(Float.parseFloat(TxtDato1.getText()));
                objClsAreaFiguras.setDato2(Float.parseFloat(TxtDato2.getText()));
                objClsAreaFiguras.setDato3(Float.parseFloat(TxtDato3.getText()));
                objClsAreaFiguras.trapecio();
                TxtArea.setText("" + objClsAreaFiguras.getArea());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una opcion de figura para calcular su area");

        }
    }

    public void limpiar() {
        TxtDato1.setText(null);
        TxtDato2.setText(null);
        TxtDato3.setText(null);
        TxtArea.setText(null);

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
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TxtDato1 = new javax.swing.JTextField();
        TxtDato2 = new javax.swing.JTextField();
        TxtDato3 = new javax.swing.JTextField();
        BtnLimpiar = new javax.swing.JButton();
        BtnCalcular = new javax.swing.JButton();
        BtnRegresar = new javax.swing.JButton();
        CboFiguras = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        TxtArea = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Formulario area de figuras");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 255));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Area figuras");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 255));

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

        jLabel1.setText("Dato 1");

        jLabel2.setText("Dato 2");

        jLabel3.setText("Dato 3");

        BtnLimpiar.setText("Limpiar");
        BtnLimpiar.setToolTipText("Limpiar cajas de texto");
        BtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarActionPerformed(evt);
            }
        });

        BtnCalcular.setText("Calcular");
        BtnCalcular.setToolTipText("Calcular area de la figura");
        BtnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCalcularActionPerformed(evt);
            }
        });

        BtnRegresar.setText("Regresar");
        BtnRegresar.setToolTipText("Regresa al menu principal");
        BtnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegresarActionPerformed(evt);
            }
        });

        CboFiguras.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Cuadrado", "Triangulo", "Romboide", "Pentagono", "Rectangulo", "Rombo", "Circulo", "Trapecio" }));
        CboFiguras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboFigurasActionPerformed(evt);
            }
        });

        jLabel8.setText("Area");

        TxtArea.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(54, 54, 54)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(TxtDato1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtDato2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtDato3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(BtnLimpiar)
                                        .addGap(18, 18, 18)
                                        .addComponent(BtnCalcular)
                                        .addGap(18, 18, 18)
                                        .addComponent(BtnRegresar))
                                    .addComponent(TxtArea, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(65, 65, 65))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(CboFiguras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(153, 153, 153))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TxtDato1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxtDato2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TxtDato3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(TxtArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(CboFiguras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtnCalcular)
                        .addComponent(BtnRegresar)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCalcularActionPerformed
        this.calcular();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCalcularActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed
        this.limpiar();       // TODO add your handling code here:
    }//GEN-LAST:event_BtnLimpiarActionPerformed

    private void BtnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegresarActionPerformed
        this.regresar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRegresarActionPerformed

    private void CboFigurasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboFigurasActionPerformed
        this.AreaFiguras();    // TODO add your handling code here:
    }//GEN-LAST:event_CboFigurasActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAreaFiguras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAreaFiguras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAreaFiguras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAreaFiguras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAreaFiguras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCalcular;
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JButton BtnRegresar;
    private javax.swing.JComboBox<String> CboFiguras;
    private javax.swing.JTextField TxtArea;
    private javax.swing.JTextField TxtDato1;
    private javax.swing.JTextField TxtDato2;
    private javax.swing.JTextField TxtDato3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
