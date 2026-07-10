/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ClsConsultas;

/**
 *
 * @author Juan Porras
 */
public class FrmConsultas extends javax.swing.JFrame {

    /**
     * Creates new form FrmConsultas
     */
    public FrmConsultas() {
        initComponents();
        this.ocultar();
    }

//Declaracion de variables 
    ClsConsultas objClsConsultas = new ClsConsultas();

//Contenido tabla
    DefaultTableModel tabladatos;

//Metodos
    public void limpiar() {
        Cbo_procedimientos_permitidos.setSelectedIndex(0);
        Cbo_animales.setSelectedIndex(0);
        Txt_fecha_inicio.setDate(null);
        Txt_fecha_fin.setDate(null);
        if (tabladatos != null) {
            tabladatos.setRowCount(0);
        }
    }

    public void ocultar() {
        Cbo_procedimientos_permitidos.setVisible(false);
        Cbo_animales.setVisible(false);
        Txt_fecha_inicio.setVisible(false);
        Txt_fecha_fin.setVisible(false);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
    }

    public void seleccion() {
        if (Rbn_procedimiento.isSelected()) {
            this.limpiar();
            this.ocultar();
            this.CrearTablaProcedimiento();
            Cbo_procedimientos_permitidos.setVisible(true);
        } else if (Rbn_animales.isSelected()) {
            this.limpiar();
            this.ocultar();
            this.CrearTablaAnimal();
            this.LlenarAnimal();
            Cbo_animales.setVisible(true);
        } else if (Rbn_usuarios.isSelected()) {
            this.limpiar();
            this.ocultar();
            this.CrearTablaUsuario();
            this.LLenarTablaUsuario();
        } else if (Rbn_rango_fechas.isSelected()) {
            this.limpiar();
            this.ocultar();
            this.CrearTablaFecha();
            Txt_fecha_inicio.setVisible(true);
            Txt_fecha_fin.setVisible(true);
            jLabel2.setVisible(true);
            jLabel3.setVisible(true);
        }
    }

    public void LlenarAnimal() {
        try {
            Cbo_animales.removeAllItems();
            Cbo_animales.addItem("Seleccione...");
            objClsConsultas.LlenarAnimal();
            while (objClsConsultas.DatosAnimal.next() == true) {
                int codigo = objClsConsultas.DatosAnimal.getInt(1);
                String nombre = objClsConsultas.DatosAnimal.getString(7);
                Cbo_animales.addItem(codigo + ". " + nombre);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar los animales:  " + e);
        }
    }

    public void CrearTablaProcedimiento() {
        Object modelodata[][] = new Object[0][0];
        Object modelotitulos[] = {"Codigo animal", "Nombre animal", "Fecha", "Nombre usuario", "Observaciones"};
        tabladatos = new DefaultTableModel();
        tabladatos = new DefaultTableModel(modelodata, modelotitulos);
        this.Tbl_consultas.setModel(this.tabladatos);
    }

    public void BorrarTablaProcedimiento() {
        while (0 < this.Tbl_consultas.getRowCount()) {
            Tbl_consultas.setModel(new DefaultTableModel());
            this.CrearTablaProcedimiento();
        }
    }

    public void LLenarTablaProcedimiento() {
        if (Cbo_procedimientos_permitidos.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe elegir el procedimiento");
            Cbo_procedimientos_permitidos.grabFocus();
        } else {
            try {
                this.BorrarTablaProcedimiento();
                objClsConsultas.setDiagnostico(Cbo_procedimientos_permitidos.getSelectedItem().toString());
                objClsConsultas.LlenarTablaProcedimiento();
                while (objClsConsultas.DatosTablaProcedimiento.next() == true) {
                    String codigo_animal = objClsConsultas.DatosTablaProcedimiento.getString(1);
                    String nombre_animal = objClsConsultas.DatosTablaProcedimiento.getString(2);
                    String fecha = objClsConsultas.DatosTablaProcedimiento.getString(3);
                    String nombre_usuario = objClsConsultas.DatosTablaProcedimiento.getString(4);
                    String observaciones = objClsConsultas.DatosTablaProcedimiento.getString(5);
                    Object fila[] = {codigo_animal, nombre_animal, fecha, nombre_usuario, observaciones};
                    tabladatos.addRow(fila);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al llenar la tabla: " + e);
            }
        }
    }

    public void CrearTablaAnimal() {
        Object modelodata[][] = new Object[0][0];
        Object modelotitulos[] = {"Procedimiento", "Observaciones", "Fecha", "Nombre usuario"};
        tabladatos = new DefaultTableModel();
        tabladatos = new DefaultTableModel(modelodata, modelotitulos);
        this.Tbl_consultas.setModel(this.tabladatos);
    }

    public void BorrarTablaAnimal() {
        while (0 < this.Tbl_consultas.getRowCount()) {
            Tbl_consultas.setModel(new DefaultTableModel());
            this.CrearTablaAnimal();
        }
    }

    public void LLenarTablaAnimal() {
        if (Cbo_animales.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe elegir el animal");
            Cbo_animales.grabFocus();
        } else {
            try {
                this.BorrarTablaAnimal();
                objClsConsultas.setCodigo(Integer.parseInt(Cbo_animales.getSelectedItem().toString().split(". ")[0]));
                objClsConsultas.LlenarTablaAnimal();
                while (objClsConsultas.DatosTablaAnimal.next() == true) {
                    String procedimiento = objClsConsultas.DatosTablaAnimal.getString(1);
                    String observaciones = objClsConsultas.DatosTablaAnimal.getString(2);
                    String fecha = objClsConsultas.DatosTablaAnimal.getString(3);
                    String nombre_usuario = objClsConsultas.DatosTablaAnimal.getString(4);
                    Object fila[] = {procedimiento, observaciones, fecha, nombre_usuario};
                    tabladatos.addRow(fila);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al llenar la tabla: " + e);
            }
        }
    }

    public void CrearTablaUsuario() {
        Object modelodata[][] = new Object[0][0];
        Object modelotitulos[] = {"Identificacion", "Nombre", "Total procedimientos"};
        tabladatos = new DefaultTableModel();
        tabladatos = new DefaultTableModel(modelodata, modelotitulos);
        this.Tbl_consultas.setModel(this.tabladatos);
    }

    public void BorrarTablaUsuario() {
        while (0 < this.Tbl_consultas.getRowCount()) {
            Tbl_consultas.setModel(new DefaultTableModel());
            this.CrearTablaUsuario();
        }
    }

    public void LLenarTablaUsuario() {
        try {
            this.BorrarTablaUsuario();
            objClsConsultas.LlenarTablaUsuario();
            while (objClsConsultas.DatosTablaUsuario.next() == true) {
                String identificacion = objClsConsultas.DatosTablaUsuario.getString(1);
                String nombre = objClsConsultas.DatosTablaUsuario.getString(2);
                String total_procedimiento = objClsConsultas.DatosTablaUsuario.getString(3);
                Object fila[] = {identificacion, nombre, total_procedimiento};
                tabladatos.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla: " + e);
        }
    }

    public void CrearTablaFecha() {
        Object modelodata[][] = new Object[0][0];
        Object modelotitulos[] = {"Codigo animal", "Nombre animal", "Procedimiento", "Fecha", "Nombre usuario", "Observacion"};
        tabladatos = new DefaultTableModel();
        tabladatos = new DefaultTableModel(modelodata, modelotitulos);
        this.Tbl_consultas.setModel(this.tabladatos);
    }

    public void BorrarTablaFecha() {
        while (0 < this.Tbl_consultas.getRowCount()) {
            Tbl_consultas.setModel(new DefaultTableModel());
            this.CrearTablaFecha();
        }
    }

    public void LLenarTablaFecha() {
        if (Txt_fecha_inicio.getDate() == null && Txt_fecha_fin.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Debe elegir o digitar la fecha de inicio o de fin");
            Txt_fecha_inicio.grabFocus();
            return;
        }
        try {
            this.BorrarTablaFecha();
            if (Txt_fecha_inicio.getDate() != null && Txt_fecha_fin.getDate() == null) {
                String FormatoFecha = new SimpleDateFormat("yyyy-MM-dd").format(Txt_fecha_inicio.getDate());
                objClsConsultas.setFecha_inicio(FormatoFecha);
                objClsConsultas.LlenarTablaFechaInicio();
                while (objClsConsultas.DatosTablaFechaInicio.next()) {
                    String codigo = objClsConsultas.DatosTablaFechaInicio.getString(1);
                    String nombre = objClsConsultas.DatosTablaFechaInicio.getString(2);
                    String procedimiento = objClsConsultas.DatosTablaFechaInicio.getString(3);
                    String fecha = objClsConsultas.DatosTablaFechaInicio.getString(4);
                    String nombre_usuario = objClsConsultas.DatosTablaFechaInicio.getString(5);
                    String observaciones = objClsConsultas.DatosTablaFechaInicio.getString(6);
                    Object fila[] = {codigo, nombre, procedimiento, fecha, nombre_usuario, observaciones};
                    tabladatos.addRow(fila);
                }
            } else if (Txt_fecha_inicio.getDate() == null && Txt_fecha_fin.getDate() != null) {
                String formatoFecha = new SimpleDateFormat("yyyy-MM-dd").format(Txt_fecha_fin.getDate());
                objClsConsultas.setFecha_fin(formatoFecha);
                objClsConsultas.LlenarTablaFechaFin();
                while (objClsConsultas.DatosTablaFechaFin.next()) {
                    String codigo = objClsConsultas.DatosTablaFechaFin.getString(1);
                    String nombre = objClsConsultas.DatosTablaFechaFin.getString(2);
                    String procedimiento = objClsConsultas.DatosTablaFechaFin.getString(3);
                    String fecha = objClsConsultas.DatosTablaFechaFin.getString(4);
                    String nombre_usuario = objClsConsultas.DatosTablaFechaFin.getString(5);
                    String observaciones = objClsConsultas.DatosTablaFechaFin.getString(6);
                    Object fila[] = {codigo, nombre, procedimiento, fecha, nombre_usuario, observaciones};
                    tabladatos.addRow(fila);
                }
            } else {
                String fechaInicio = new SimpleDateFormat("yyyy-MM-dd").format(Txt_fecha_inicio.getDate());
                String fechaFin = new SimpleDateFormat("yyyy-MM-dd").format(Txt_fecha_fin.getDate());
                objClsConsultas.setFecha_inicio(fechaInicio);
                objClsConsultas.setFecha_fin(fechaFin);
                objClsConsultas.LlenarTablaFechaInicioFin();
                while (objClsConsultas.DatosTablaFechaInicioFin.next()) {
                    String codigo = objClsConsultas.DatosTablaFechaInicioFin.getString(1);
                    String nombre = objClsConsultas.DatosTablaFechaInicioFin.getString(2);
                    String procedimiento = objClsConsultas.DatosTablaFechaInicioFin.getString(3);
                    String fecha = objClsConsultas.DatosTablaFechaInicioFin.getString(4);
                    String nombre_usuario = objClsConsultas.DatosTablaFechaInicioFin.getString(5);
                    String observaciones = objClsConsultas.DatosTablaFechaInicioFin.getString(6);
                    Object fila[] = {codigo, nombre, procedimiento, fecha, nombre_usuario, observaciones};
                    tabladatos.addRow(fila);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla: " + e);
        }
    }

    public void regresar() {
        FrmMenu objFrmMenu = new FrmMenu();
        objFrmMenu.setVisible(true);
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

        Btg_consultas = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        Rbn_procedimiento = new javax.swing.JRadioButton();
        Rbn_animales = new javax.swing.JRadioButton();
        Rbn_usuarios = new javax.swing.JRadioButton();
        Rbn_rango_fechas = new javax.swing.JRadioButton();
        Cbo_procedimientos_permitidos = new javax.swing.JComboBox<>();
        Cbo_animales = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tbl_consultas = new javax.swing.JTable();
        Btn_nuevo = new javax.swing.JButton();
        Btn_buscar = new javax.swing.JButton();
        Txt_fecha_inicio = new com.toedter.calendar.JDateChooser();
        Txt_fecha_fin = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Btn_regresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consultas");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Consultas");

        Btg_consultas.add(Rbn_procedimiento);
        Rbn_procedimiento.setText("Procedimiento");
        Rbn_procedimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rbn_procedimientoActionPerformed(evt);
            }
        });

        Btg_consultas.add(Rbn_animales);
        Rbn_animales.setText("Animales");
        Rbn_animales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rbn_animalesActionPerformed(evt);
            }
        });

        Btg_consultas.add(Rbn_usuarios);
        Rbn_usuarios.setText("Usuarios");
        Rbn_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rbn_usuariosActionPerformed(evt);
            }
        });

        Btg_consultas.add(Rbn_rango_fechas);
        Rbn_rango_fechas.setText("Rango de fechas");
        Rbn_rango_fechas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rbn_rango_fechasActionPerformed(evt);
            }
        });

        Cbo_procedimientos_permitidos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Procedimientos Permitidos", "Parto", "Vacunacion", "Control por crecimiento" }));

        Cbo_animales.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..." }));

        Tbl_consultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Tbl_consultas.setEnabled(false);
        jScrollPane1.setViewportView(Tbl_consultas);

        Btn_nuevo.setBackground(new java.awt.Color(153, 153, 153));
        Btn_nuevo.setText("Nuevo");
        Btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_nuevoActionPerformed(evt);
            }
        });

        Btn_buscar.setBackground(new java.awt.Color(153, 153, 153));
        Btn_buscar.setText("Buscar");
        Btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_buscarActionPerformed(evt);
            }
        });

        Txt_fecha_inicio.setDateFormatString("yyyy-MM-dd");

        Txt_fecha_fin.setDateFormatString("yyyy-MM-dd");

        jLabel2.setText("Fecha fin");

        jLabel3.setText("Fecha inicio");

        Btn_regresar.setBackground(new java.awt.Color(153, 153, 153));
        Btn_regresar.setText("Regresar");
        Btn_regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_regresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Btn_regresar)
                        .addGap(18, 18, 18)
                        .addComponent(Btn_nuevo)
                        .addGap(18, 18, 18)
                        .addComponent(Btn_buscar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Rbn_procedimiento)
                                .addComponent(Cbo_procedimientos_permitidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(41, 41, 41)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(Rbn_animales)
                                    .addGap(18, 18, 18)
                                    .addComponent(Rbn_usuarios))
                                .addComponent(Cbo_animales, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(99, 99, 99)
                                    .addComponent(Rbn_rango_fechas))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(60, 60, 60)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(Txt_fecha_fin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Txt_fecha_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Rbn_procedimiento)
                        .addGap(18, 18, 18)
                        .addComponent(Cbo_procedimientos_permitidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Rbn_animales)
                            .addComponent(Rbn_usuarios)
                            .addComponent(Rbn_rango_fechas))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Cbo_animales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(Txt_fecha_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Txt_fecha_fin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))))))
                .addGap(18, 34, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_nuevo)
                    .addComponent(Btn_buscar)
                    .addComponent(Btn_regresar))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_buscarActionPerformed

        if (Rbn_procedimiento.isSelected()) {
            LLenarTablaProcedimiento();
        } else if (Rbn_animales.isSelected()) {
            LLenarTablaAnimal();
        } else if (Rbn_usuarios.isSelected()) {
            LLenarTablaUsuario();
        } else if (Rbn_rango_fechas.isSelected()) {
            CrearTablaFecha();
            LLenarTablaFecha();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una opción");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_buscarActionPerformed

    private void Rbn_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rbn_usuariosActionPerformed
        this.seleccion();        // TODO add your handling code here:
    }//GEN-LAST:event_Rbn_usuariosActionPerformed

    private void Btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_nuevoActionPerformed
        this.limpiar();
        this.ocultar();        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_nuevoActionPerformed

    private void Rbn_procedimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rbn_procedimientoActionPerformed
        this.seleccion();        // TODO add your handling code here:
    }//GEN-LAST:event_Rbn_procedimientoActionPerformed

    private void Rbn_animalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rbn_animalesActionPerformed
        this.seleccion();        // TODO add your handling code here:
    }//GEN-LAST:event_Rbn_animalesActionPerformed

    private void Rbn_rango_fechasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rbn_rango_fechasActionPerformed
        this.seleccion();        // TODO add your handling code here:
    }//GEN-LAST:event_Rbn_rango_fechasActionPerformed

    private void Btn_regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_regresarActionPerformed
        this.regresar();        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_regresarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmConsultas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Btg_consultas;
    private javax.swing.JButton Btn_buscar;
    private javax.swing.JButton Btn_nuevo;
    private javax.swing.JButton Btn_regresar;
    private javax.swing.JComboBox<String> Cbo_animales;
    private javax.swing.JComboBox<String> Cbo_procedimientos_permitidos;
    private javax.swing.JRadioButton Rbn_animales;
    private javax.swing.JRadioButton Rbn_procedimiento;
    private javax.swing.JRadioButton Rbn_rango_fechas;
    private javax.swing.JRadioButton Rbn_usuarios;
    private javax.swing.JTable Tbl_consultas;
    private com.toedter.calendar.JDateChooser Txt_fecha_fin;
    private com.toedter.calendar.JDateChooser Txt_fecha_inicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
